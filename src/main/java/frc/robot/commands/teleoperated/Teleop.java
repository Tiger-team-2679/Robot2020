package frc.robot.commands.teleoperated;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

public class Teleop extends ParallelCommandGroup {

    public Teleop(){
        super(new TeleopPowerCells(), new TeleopClimber(), new TeleopDrivetrain(), new TeleopElevator(), new TeleopCW());
    }
}
