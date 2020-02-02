/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.sensors.PigeonIMU;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    /**
     * Creates a new ExampleSubsystem.
     */
    private IMotorController leftMotor;
    private IMotorController rightMotor;

    private Encoder leftEncoder;
    private Encoder rightEncoder;
    private static final double maxVoltage = 12;
    private PigeonIMU gyro;

    public static final double width = 30;

    public Drivetrain(IMotorController[] leftMotors, IMotorController[] rightMotors, PigeonIMU gyro, Encoder leftEncoder, Encoder rightEncoder)  {
        leftMotors[0].configVoltageCompSaturation(maxVoltage, 0);
        leftMotors[1].configVoltageCompSaturation(maxVoltage, 0);
        rightMotors[0].configVoltageCompSaturation(maxVoltage, 0);
        rightMotors[1].configVoltageCompSaturation(maxVoltage, 0);

        rightMotors[1].follow(rightMotors[0]);
        this.rightMotor = rightMotors[0];

        leftMotors[1].follow(leftMotors[0]);
        this.leftMotor = leftMotors[0];

        this.leftEncoder = leftEncoder;
        this.rightEncoder = rightEncoder;

        this.gyro = gyro;
        gyro.setFusedHeading(0);
    }

    public void setVoltage(double left, double right) {
        leftMotor.set(ControlMode.PercentOutput, left/maxVoltage);
        rightMotor.set(ControlMode.PercentOutput, right/maxVoltage);
    }

    public double getLeftVel() {
        return leftEncoder.getRate();
    }

    public double getRightVel() {
        return rightEncoder.getRate();
    }

    public double getHeading() {
        return gyro.getFusedHeading();
    }

    public double getAngularVelocity() {
        double[] xyz_dps = new double[3];
        gyro.getRawGyro(xyz_dps);
        return xyz_dps[0];
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
