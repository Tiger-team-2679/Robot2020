package frc.robot.commands.automonus;

import edu.wpi.first.wpilibj.Timer;
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

    private static final DifferentialDriveSC SPEED_CAP_GENERATOR = new DifferentialDriveSC(0.75, 5);
    private static final MotionProfileGenerator MOTION_PROFILE_GENERATOR = new MotionProfileGenerator(5);
    private static final AngularSpeedGenerator ANGULAR_SPEED_GENERATOR = new AngularSpeedGenerator();
    private static final UnicycleAdapter UNICYCLE_ADAPTER = new UnicycleAdapter(0.75);

    private Drivetrain drivetrain;
    Follower angle;
    Follower velocity;
    private Timer timer= new Timer();

    public FollowSplineV2(Drivetrain drivetrain, ExtendedSpline spline) {
        this.drivetrain = drivetrain;

        SplineWrapper splineWrapper = new SplineWrapper(spline, 0.01);
        IntervalGraph<Double> speedCap = SPEED_CAP_GENERATOR.getSpeedCap(splineWrapper);
        IntervalGraph<Double> robotSpeed = MOTION_PROFILE_GENERATOR.generate(speedCap, 0, 0);

        IntervalGraph<Double> angular = ANGULAR_SPEED_GENERATOR.generate(splineWrapper, robotSpeed);

        ArrayList<Double> timing = GraphUtil.generateTiming(robotSpeed);

        Graph angleGraph = GraphUtil.applyTiming(angular, timing);
        Graph velGraph = GraphUtil.applyTiming(robotSpeed, timing);

        angle = new Follower(angleGraph, new PID(0, 0, 0), 0, 0, 0);
        velocity = new Follower(velGraph, new PID(0, 0, 0), 0, 0, 0);
    }

    @Override
    public void initialize() {
        timer.start();
    }

    @Override
    public void execute() {
        double currentAngularVelocity = angle.update(timer.get(), drivetrain.getAngularVelocity());
        Drivetrain.EncoderSides encoders = drivetrain.velocities();
        double averageSpeed = (encoders.getLeft() + encoders.getRight()) / 2;
        double currentRobotVelocity = velocity.update(timer.get(), averageSpeed);

        UnicycleAdapter.WheelsVelocities wheelsVelocities =
                UNICYCLE_ADAPTER.calculateWheelsVelocities(currentRobotVelocity, currentAngularVelocity);

        drivetrain.set(wheelsVelocities.left, wheelsVelocities.right);
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}