package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class RobotMap {

    public static Joystick joystick = new Joystick(0);
    public static XboxController xbox = new XboxController(1);

    public static int elevatorLeft = 0;
    public static int elevatorRight = 1;
    public static int climber = 2;
    public static int colourWheel = 3;
    public static int dtFrontLeft = 4;
    public static int dtBackLeft = 5;
    public static int dtFrontRight = 6;
    public static int dtBackRight = 7;

}
