package frc.robot.commands.teleoperated;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.models.Elevator;

public class TeleopElevator extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final Elevator elevator;

    /**
     * Creates a new ExampleCommand.
     *
     * @param elevator The subsystem used by this command.
     */
    public TeleopElevator(Elevator elevator) {
        this.elevator = elevator;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(elevator);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        double rightStickY = RobotMap.xbox.getY(GenericHID.Hand.kRight);
        if (rightStickY < 0) {
            elevator.set( -(rightStickY * rightStickY) );
        }
        else {
            elevator.set(rightStickY * rightStickY);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
