package frc.robot.subsystems.models;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.utils.drivers.LazyTalonSRX;

public class ClimberImpl extends SubsystemBase implements Climber {

    private TalonSRX leftMotor;
    private TalonSRX rightMotor;

    public ClimberImpl() {
        leftMotor = new LazyTalonSRX(RobotMap.climberLeftId);
        rightMotor = new LazyTalonSRX(RobotMap.climberRightId);
    }

    @Override
    public void set(double power) {
        rightMotor.set(ControlMode.PercentOutput, power);
        leftMotor.set(ControlMode.PercentOutput, power);
    }

}
