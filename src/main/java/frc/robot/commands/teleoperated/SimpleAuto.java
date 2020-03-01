package frc.robot.commands.teleoperated;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.models.Drivetrain;

public class SimpleAuto extends CommandBase {
    Timer timer;
    Drivetrain drivetrain;
    @Override
    public void initialize() {
        timer = new Timer();
        timer.start();
        this.drivetrain = RobotMap.drivetrain;
    }

    @Override
    public void execute() {
        if (timer.get() < 2.5) {
            drivetrain.set(0.5, 0.5);
        }

    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
