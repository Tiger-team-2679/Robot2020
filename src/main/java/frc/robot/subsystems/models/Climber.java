package frc.robot.subsystems.models;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Climber extends Subsystem {

    /**
     * Sets the power of the climbing rope motor
     * @param power Should be between -1 and 1
     */
    public void set(double power);

}
