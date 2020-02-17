package frc.robot.commands.teleoperated;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.models.Elevator;
import frc.robot.subsystems.models.PowerCells;

public class TeleopPowerCells extends CommandBase {
    private final PowerCells powerCells;

    public TeleopPowerCells(PowerCells powerCells) {
        this.powerCells = powerCells;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(powerCells);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        boolean AButton = RobotMap.xbox.getAButton();
        boolean BButton = RobotMap.xbox.getBButton();
        powerCells.setCollector(AButton ? 0.8 : 0.0);
        powerCells.setOut(BButton ? 0.8 : 0.0);
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
