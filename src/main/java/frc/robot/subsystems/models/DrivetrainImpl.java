package frc.robot.subsystems.models;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.utils.drivers.LazyTalonSRX;

public class DrivetrainImpl extends SubsystemBase implements Drivetrain {

    private TalonSRX frontLeft;
    private TalonSRX backLeft;
    private TalonSRX frontRight;
    private TalonSRX backRight;
    private PigeonIMU gyro;
    private final double ticksPerMeter = 0;

    public DrivetrainImpl() {
        frontLeft = new LazyTalonSRX(RobotMap.dtFrontLeft);
        backLeft = new LazyTalonSRX(RobotMap.dtBackLeft);
        frontRight = new LazyTalonSRX(RobotMap.dtFrontRight);
        backRight = new LazyTalonSRX(RobotMap.dtBackRight);
    }

    @Override
    public void set(double powerLeft, double powerRight) {
        frontLeft.set(ControlMode.PercentOutput, powerLeft);
        backLeft.set(ControlMode.PercentOutput, powerLeft);
        frontRight.set(ControlMode.PercentOutput, powerRight);
        backRight.set(ControlMode.PercentOutput, powerLeft);
    }

    @Override
    public EncoderSides velocities() {
        return new EncoderSides(frontLeft.getSensorCollection().getQuadratureVelocity() / ticksPerMeter,
                frontRight.getSensorCollection().getQuadratureVelocity() / ticksPerMeter);
    }

    @Override
    public EncoderSides positions() {
        return new EncoderSides(frontLeft.getSensorCollection().getQuadraturePosition() / ticksPerMeter,
                frontRight.getSensorCollection().getQuadraturePosition() / ticksPerMeter);
    }

    @Override
    public double getHeading() {
        return gyro.getFusedHeading();
    }

    @Override
    public double getAngularVelocity() {
        double[] xyz_dps = new double[3];
        gyro.getRawGyro(xyz_dps);
        return xyz_dps[0];
    }

}
