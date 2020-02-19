package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.models.*;

public class RobotMap {

    public static final Climber climber = new ClimberImpl();
    public static final ColourWheel colourWheel = new ColourWheelImpl();
    public static final Drivetrain drivetrain = new DrivetrainImpl();
    public static final Elevator elevator = new ElevatorImpl();
    public static final PowerCells powerCells = new PowerCellsImpl();


    public static final Joystick joystick = new Joystick(0);
    public static final XboxController xbox = new XboxController(1);

    public static final int elevatorLeftId = 1;
    public static final int elevatorRightId = 6;
    public static final int climberLeftId = 5;
    public static final int climberRightId = 3;
    public static final int colourWheelId = 4;
    public static final int dtFrontLeftId = 4;
    public static final int dtBackLeftId = 1;
    public static final int dtFrontRightId = 3;
    public static final int dtBackRightId = 2;
    public static final int pcInId = 7;
    public static final int pcOutId = 2;

    public static final int dtEncoderRight1 = 9;
    public static final int dtEncoderRight2 = 8;
    public static final int dtEncoderLeft1 = 7;
    public static final int dtEncoderLeft2 = 6;

}
