package frc.robot.subsystems.models;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Elevator extends Subsystem {

    /**
     * Sets the power of the elevator motors
     * @param power Should be between -1 and 1
     */
    public void set(double power);

    /**
     * @return The current velocity of the elevator
     */
    public double getVelocity();

    /**
     * @return The position of the elevator, in metres, ground is 0
     */
    public double getPos();

}
