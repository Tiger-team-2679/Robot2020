package frc.robot.subsystems.models;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.utils.drivers.LazyTalonSRX;

public class PowerCellsImpl extends SubsystemBase implements PowerCells {
    private LazyTalonSRX outMotor;
    private LazyTalonSRX inMotor;

    public PowerCellsImpl() {
        outMotor = new LazyTalonSRX(RobotMap.pcOutId);
        inMotor = new LazyTalonSRX(RobotMap.pcInId);
    }

    @Override
    public void setCollector(double value) {
        inMotor.set(ControlMode.PercentOutput, value);
    }

    @Override
    public void setOut(double value) {
        outMotor.set(ControlMode.PercentOutput, value);
    }
}
