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

public class FollowSplineV3 extends CommandBase {

    private static final DifferentialDriveSC SPEED_CAP_GENERATOR = new DifferentialDriveSC(0.55, 2);
    private static final MotionProfileGenerator MOTION_PROFILE_GENERATOR = new MotionProfileGenerator(1.5);
    private static final AngularSpeedGenerator ANGULAR_SPEED_GENERATOR = new AngularSpeedGenerator();
    private static final AngleGenerator ANGLE_GENERATOR = new AngleGenerator();
    private static final UnicycleAdapter UNICYCLE_ADAPTER = new UnicycleAdapter(0.55);

    private Drivetrain drivetrain;
    Follower2 velocity;
    private Timer timer= new Timer();
    private  Graph velGraph;
    private  Graph angleGraph;
    private  Graph angularGraph;

    public FollowSplineV3(Drivetrain drivetrain, ExtendedSpline spline) {
        this.drivetrain = drivetrain;

        SplineWrapper splineWrapper = new SplineWrapper(spline, 0.01);

        IntervalGraph<Double> speedCap = SPEED_CAP_GENERATOR.getSpeedCap(splineWrapper);
        IntervalGraph<Double> robotSpeed = MOTION_PROFILE_GENERATOR.generate(speedCap, 0, 0);
        IntervalGraph<Double> angle = ANGLE_GENERATOR.generate(splineWrapper, robotSpeed);
        IntervalGraph<Double> angular = ANGULAR_SPEED_GENERATOR.generate(splineWrapper, robotSpeed);

        ArrayList<Double> timing = GraphUtil.generateTiming(robotSpeed);

        angleGraph = GraphUtil.applyTiming(angle, timing);
        velGraph = GraphUtil.applyTiming(robotSpeed, timing);
        angularGraph = GraphUtil.applyTiming(angular, timing);

        velocity = new Follower2(velGraph, angleGraph, new PID(0.1, 0, 0), new PID(0.1,0,0));
    }

    @Override
    public void initialize() {
        timer.start();
        timer.reset();
    }

    @Override
    public void execute() {
        double kv = 0.35;
        double ka = 0.1;

        double currentAngularVelocity = this.velGraph.value(timer.get());
        double angle = drivetrain.getHeading();
        Drivetrain.EncoderSides encoders = drivetrain.velocities();
        double averageSpeed = (encoders.getLeft() + encoders.getRight()) / 2;
        double data[] = velocity.update(timer.get(), averageSpeed, angle);

        UnicycleAdapter.WheelsVelocities wheelsVelocities =
                UNICYCLE_ADAPTER.calculateWheelsVelocities(data[0], currentAngularVelocity);

        double left = wheelsVelocities.left*kv + data[1] - data[2];
        double right = wheelsVelocities.right*kv + data[1] + data[2];

        SmartDashboard.putNumber("vel feed", velGraph.value(timer.get()));
        SmartDashboard.putNumber("angular feed", angleGraph.value(timer.get()));
        SmartDashboard.putNumber("gyro", drivetrain.getAngularVelocity());
        SmartDashboard.putNumber("enc", averageSpeed);
        SmartDashboard.putNumber("time", timer.get());
        SmartDashboard.putNumber("fuck", velGraph.value(0.5));

        drivetrain.set(left, right);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}