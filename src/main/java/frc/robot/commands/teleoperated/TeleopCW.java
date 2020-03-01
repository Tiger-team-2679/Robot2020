package frc.robot.commands.teleoperated;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.models.ColourWheel;

public class TeleopCW extends CommandBase {
    ColourWheel colourWheel;
    public TeleopCW(){
        this.colourWheel = RobotMap.colourWheel;
    }

    @Override
    public void execute() {
        if (RobotMap.xbox.getXButton()) {
            colourWheel.set(0.8);
        }
        else {
            colourWheel.set(0);
        }
    }
}
