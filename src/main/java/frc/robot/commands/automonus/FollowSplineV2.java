package frc.robot.commands.automonus;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.models.Drivetrain;
import team2679.core.controller.Follower;
import team2679.core.controller.PID;
import team2679.core.controller.UnicycleAdapter;
import team2679.core.graph.*;
import team2679.core.spline.ExtendedSpline;
import team2679.core.spline.SplineWrapper;

import java.util.ArrayList;

public class FollowSplineV2 extends CommandBase {

    private static final DifferentialDriveSC SPEED_CAP_GENERATOR = new DifferentialDriveSC(0.55, 2.5);
    private static final MotionProfileGenerator MOTION_PROFILE_GENERATOR = new MotionProfileGenerator(2.5);
    private static final AngularSpeedGenerator ANGULAR_SPEED_GENERATOR = new AngularSpeedGenerator();
    private static final UnicycleAdapter UNICYCLE_ADAPTER = new UnicycleAdapter(0.55);

    private Drivetrain drivetrain;
    Follower angle;
    Follower velocity;
    private Timer timer= new Timer();
    private  Graph velGraph;
    private  Graph angleGraph;
    boolean finished = false;

    public FollowSplineV2(Drivetrain drivetrain, ExtendedSpline spline) {
        this.drivetrain = drivetrain;

        SplineWrapper splineWrapper = new SplineWrapper(spline, 0.01);

        IntervalGraph<Double> speedCap = SPEED_CAP_GENERATOR.getSpeedCap(splineWrapper);
        IntervalGraph<Double> robotSpeed = MOTION_PROFILE_GENERATOR.generate(speedCap, 0, 0);

        IntervalGraph<Double> angular = ANGULAR_SPEED_GENERATOR.generate(splineWrapper, robotSpeed);

        ArrayList<Double> timing = GraphUtil.generateTiming(robotSpeed);

        angleGraph = GraphUtil.applyTiming(angular, timing);
        velGraph = GraphUtil.applyTiming(robotSpeed, timing);

        angle = new Follower(angleGraph, new PID(0.0, 0, 0), 0.40, 0., 0);
        velocity = new Follower(velGraph, new PID(0.2, 0, 0), 0.30, 0.1, 0);
    }

    @Override
    public void initialize() {
        timer.start();
        timer.reset();
    }

    @Override
    public void execute() {
        try {
            double currentAngularVelocity = angle.update(timer.get(), drivetrain.getAngularVelocity() / 180 * Math.PI);
            Drivetrain.EncoderSides encoders = drivetrain.velocities();
            double averageSpeed = (encoders.getLeft() + encoders.getRight()) / 2;
            double currentRobotVelocity = velocity.update(timer.get(), averageSpeed);

            UnicycleAdapter.WheelsVelocities wheelsVelocities =
                    UNICYCLE_ADAPTER.calculateWheelsVelocities(currentRobotVelocity, currentAngularVelocity);

            SmartDashboard.putNumber("vel feed", velGraph.value(timer.get()));
            SmartDashboard.putNumber("angular feed", angleGraph.value(timer.get()));
            SmartDashboard.putNumber("gyro", drivetrain.getAngularVelocity() / 180 * Math.PI);
            SmartDashboard.putNumber("enc", averageSpeed);
            SmartDashboard.putNumber("time", timer.get());
            SmartDashboard.putNumber("fuck", velGraph.value(0.5));

            drivetrain.set(wheelsVelocities.left, wheelsVelocities.right);
        }
        catch (Exception e) {
            finished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}