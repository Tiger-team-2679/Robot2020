package frc.robot.subsystems.models;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.RobotMap;
import frc.robot.utils.drivers.LazyTalonSRX;

public class ElevatorImpl implements Elevator {

    private TalonSRX left;
    private TalonSRX right;
    private final double ticksPerMeter = 0;

    public ElevatorImpl() {
        left = new LazyTalonSRX(RobotMap.elevatorLeft);
        right = new LazyTalonSRX(RobotMap.elevatorRight);
    }

    @Override
    public void set(double power) {
        left.set(ControlMode.PercentOutput, power);
        right.set(ControlMode.PercentOutput, power);
    }

    @Override
    public double getVelocity() {
        return left.getSensorCollection().getQuadratureVelocity() / ticksPerMeter;
    }

    @Override
    public double getPos() {
        return left.getSensorCollection().getQuadraturePosition() / ticksPerMeter;
    }

}
