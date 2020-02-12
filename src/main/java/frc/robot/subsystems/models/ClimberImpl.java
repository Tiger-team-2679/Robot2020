package frc.robot.subsystems.models;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.RobotMap;
import frc.robot.utils.drivers.LazyTalonSRX;

public class ClimberImpl implements Climber {

    private TalonSRX motor;

    public ClimberImpl() {
        motor = new LazyTalonSRX(RobotMap.climber);
    }

    @Override
    public void set(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

}
