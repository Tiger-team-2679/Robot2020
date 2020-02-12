package frc.robot.subsystems.models;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface ColourWheel extends Subsystem {

    /**
     * Sets the power of the colour wheel motor
     * @param power Should be between -1 and 1
     */
    public void set(double power);

    /**
     * @return The RGB values of the detected colour
     */
    public Colours getColour();

    public enum Colours {
        BLUE, RED, GREEN, YELLOW
    }

}
