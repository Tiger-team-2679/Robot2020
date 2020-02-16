package frc.robot.commands.automonus;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.models.ColourWheel;

public class CWCounter extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ColourWheel colourWheel;
    private boolean isRolling = false;
    private ColourWheel.Colours startColour;
    private int spins = 0;

    /**
     * Creates a new ExampleCommand.
     *
     * @param colourWheel The subsystem used by this command.
     */
    public CWCounter(ColourWheel colourWheel) {
        this.colourWheel = colourWheel;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(colourWheel);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if (RobotMap.xbox.getXButtonReleased() && !isRolling) {
            isRolling = true;
            startColour = colourWheel.getColour();
        }
        if (isRolling) {
            colourWheel.set(0.7);
            if (colourWheel.getColour() == startColour) { spins += 1; }
            if (spins >= 7) {
                spins = 0;
                colourWheel.set(0);
                isRolling = false;
            }
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
