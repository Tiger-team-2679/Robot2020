package frc.robot.commands.automonus;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.RobotMap;
import frc.robot.subsystems.models.ColourWheel;

public class CWColour extends CommandBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    private final ColourWheel colourWheel;
    private boolean isRolling = false;
    private ColourWheel.Colours target;

    /**
     * Creates a new ExampleCommand.
     *
     * @param colourWheel The subsystem used by this command.
     */
    public CWColour(ColourWheel colourWheel) {
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
        switch (DriverStation.getInstance().getGameSpecificMessage()) {
            case "B":
                target = ColourWheel.Colours.BLUE;
                break;
            case "R":
                target = ColourWheel.Colours.RED;
                break;
            case "G":
                target = ColourWheel.Colours.GREEN;
                break;
            case "Y":
                target = ColourWheel.Colours.YELLOW;
                break;
        }
        if (RobotMap.xbox.getYButtonReleased() && !isRolling) {
            isRolling = true;
        }
        if (isRolling) {
            if (colourWheel.getColour() == target) {
                isRolling = false;
                colourWheel.set(0);
            }
            colourWheel.set(0.5);
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