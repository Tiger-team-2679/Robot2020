package frc.robot.subsystems.models;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface PowerCells extends Subsystem {
    public void setCollector(double value);
    public void setOut(double value);
}
