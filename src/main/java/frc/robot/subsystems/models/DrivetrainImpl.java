package frc.robot.subsystems.models;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.utils.drivers.LazyTalonSRX;
import frc.robot.utils.drivers.LazyVictorSPX;

public class DrivetrainImpl extends SubsystemBase implements Drivetrain {

    private VictorSPX frontLeft;
    private VictorSPX backLeft;
    private VictorSPX frontRight;
    private VictorSPX backRight;
    private PigeonIMU gyro;
    private final double ticksPerMeter = 4096/0.075;
    private Encoder leftEncoder;
    private Encoder rightEncoder;

    public DrivetrainImpl() {
        frontLeft = new LazyVictorSPX(RobotMap.dtFrontLeftId);
        backLeft = new LazyVictorSPX(RobotMap.dtBackLeftId);
        frontRight = new LazyVictorSPX(RobotMap.dtFrontRightId);
        backRight = new LazyVictorSPX(RobotMap.dtBackRightId);

        gyro = new PigeonIMU(new TalonSRX(3));
        leftEncoder = new Encoder(RobotMap.ASignalLeftEncoder, RobotMap.BSignalLeftEncoder, true);
        rightEncoder = new Encoder(RobotMap.ASignalRightEncoder, RobotMap.BSignalRightEncoder, false);
    }

    @Override
    public void set(double powerLeft, double powerRight) {
        frontLeft.set(ControlMode.PercentOutput, - powerLeft);
        backLeft.set(ControlMode.PercentOutput, - powerLeft);
        frontRight.set(ControlMode.PercentOutput, powerRight);
        backRight.set(ControlMode.PercentOutput, powerLeft);
    }

    @Override
    public EncoderSides velocities() {
        return new EncoderSides(leftEncoder.getRate() / ticksPerMeter,
                rightEncoder.getRate() / ticksPerMeter);
    }

    @Override
    public EncoderSides positions() {
        return new EncoderSides(leftEncoder.get() / ticksPerMeter,
                rightEncoder.getDistance() / ticksPerMeter);
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

    @Override
    public void periodic() {
        SmartDashboard.putNumber("angle", getHeading());
        SmartDashboard.putNumber("left enc", positions().getLeft());
        SmartDashboard.putNumber("right enc", positions().getRight());

        if (RobotMap.xbox.getStartButton()) {
            leftEncoder.reset();
            rightEncoder.reset();
        }
    }
}
