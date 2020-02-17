package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class RobotMap {

    public static final Joystick joystick = new Joystick(0);
    public static final XboxController xbox = new XboxController(1);

    public static final int elevatorLeft = 0;
    public static final int elevatorRight = 1;
    public static final int climberLeft = 2;
    public static final int climberRight = 10;
    public static final int colourWheel = 3;
    public static final int dtFrontLeft = 4;
    public static final int dtBackLeft = 5;
    public static final int dtFrontRight = 6;
    public static final int dtBackRight = 7;
    public static final int pcIn = 8;
    public static final int pcOut = 9;

}
