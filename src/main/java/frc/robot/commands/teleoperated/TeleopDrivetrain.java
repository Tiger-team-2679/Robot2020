package frc.robot.commands.teleoperated;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.models.Climber;
import frc.robot.subsystems.models.Drivetrain;

public class TeleopDrivetrain extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Drivetrain drivetrain;

    /**
     * Creates a new ExampleCommand.
     *
     * @param drivetrain The subsystem used by this command.
     */
    public TeleopDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drivetrain);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double stickY = RobotMap.joystick.getY();
        double stickX = RobotMap.joystick.getX();

        double leftSpeed = 0;
        double rightSpeed = 0;

        leftSpeed += (stickX);
        rightSpeed -= (stickX);

        leftSpeed += (stickY);
        rightSpeed += (stickY);

        if (leftSpeed < 0) {
            leftSpeed *= -leftSpeed;
        }
        else {
            leftSpeed *= leftSpeed;
        }
        if (rightSpeed < 0) {
            rightSpeed *= -rightSpeed;
        }
        else {
            rightSpeed *= rightSpeed;
        }

        drivetrain.set(leftSpeed, rightSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drivetrain.set(0,0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
