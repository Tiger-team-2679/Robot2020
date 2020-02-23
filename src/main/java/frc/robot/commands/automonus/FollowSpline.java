package frc.robot.commands.automonus;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.models.Drivetrain;
import team2679.core.controller.Follower;
import team2679.core.controller.PID;
import team2679.core.graph.*;
import team2679.core.spline.ExtendedSpline;
import team2679.core.spline.SplineWrapper;

import java.util.ArrayList;

public class FollowSpline extends CommandBase {

    private static final DifferentialDriveSC SPEED_CAP_GENERATOR = new DifferentialDriveSC(0.55,2);
    private static final MotionProfileGenerator MOTION_PROFILE_GENERATOR = new MotionProfileGenerator(3);
    private static final RelativeSpeedGenerator RELATIVE_SPEED_GENERATOR = new RelativeSpeedGenerator(0.55);
    private static final WheelsSpeedGenerator WHEELS_SPEED_GENERATOR = new WheelsSpeedGenerator();

    private Drivetrain drivetrain;
    Follower leftWheel;
    Follower rightWheel;
    private Timer timer= new Timer();

    public FollowSpline(Drivetrain drivetrain, ExtendedSpline spline) {
        this.drivetrain = drivetrain;
        spline.getCurvature(0.5);
        SplineWrapper splineWrapper = new SplineWrapper(spline, 0.001);
        IntervalGraph<Double> speedCap = SPEED_CAP_GENERATOR.getSpeedCap(splineWrapper);
        IntervalGraph<Double> robotSpeed = MOTION_PROFILE_GENERATOR.generate(speedCap, 0, 0);

        WheelGraph relative = RELATIVE_SPEED_GENERATOR.getRelativeSpeed(splineWrapper);
        WheelGraph absolute = WHEELS_SPEED_GENERATOR.generate(robotSpeed, relative);

        ArrayList<Double> timing = GraphUtil.generateTiming(robotSpeed);

        Graph leftWheelGraph = GraphUtil.applyTiming(absolute.getLeft(), timing);
        Graph rightWheelGraph = GraphUtil.applyTiming(absolute.getRight(), timing);

        leftWheel = new Follower(leftWheelGraph, new PID(0.1, 0, 0), 0.30, .1, 0);
        rightWheel = new Follower(rightWheelGraph, new PID(0.1, 0, 0), 0.30, .1, 0);
    }

    @Override
    public void initialize() {
        timer.start();
        timer.reset();
    }

    @Override
    public void execute() {
        Drivetrain.EncoderSides velocities = drivetrain.velocities();
        double left = leftWheel.update(timer.get(), velocities.getLeft());
        double right = rightWheel.update(timer.get(), velocities.getRight());
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