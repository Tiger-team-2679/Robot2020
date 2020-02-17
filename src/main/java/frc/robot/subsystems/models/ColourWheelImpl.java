package frc.robot.subsystems.models;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import frc.robot.utils.drivers.LazyTalonSRX;

public class ColourWheelImpl extends SubsystemBase implements ColourWheel {

    private LazyTalonSRX motor;
    private ColorSensorV3 colourSensor;

    private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
    private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
    private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
    private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

    private final ColorMatch colourMatcher = new ColorMatch();


    public ColourWheelImpl() {
        motor = new LazyTalonSRX(RobotMap.colourWheel);
        colourSensor = new ColorSensorV3(I2C.Port.kOnboard);
        colourMatcher.addColorMatch(kBlueTarget);
        colourMatcher.addColorMatch(kGreenTarget);
        colourMatcher.addColorMatch(kRedTarget);
        colourMatcher.addColorMatch(kYellowTarget);
    }

    @Override
    public void set(double power) {
        motor.set(ControlMode.PercentOutput, power);
    }

    @Override
    public Colours getColour() {
        Colours result;
        Color detected = colourSensor.getColor();
        ColorMatchResult match = colourMatcher.matchClosestColor(detected);
        if (match.color == kBlueTarget) { result = Colours.BLUE; }
        else if (match.color == kGreenTarget) { result = Colours.GREEN; }
        else if (match.color == kRedTarget) { result = Colours.RED; }
        else if (match.color == kYellowTarget) { result = Colours.YELLOW; }
        else { result = null; }
        return result;
    }

}
