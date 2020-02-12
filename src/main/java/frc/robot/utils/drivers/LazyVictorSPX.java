package frc.robot.utils.drivers;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class LazyVictorSPX extends VictorSPX {

    private double lastSet = Double.NaN;
    private ControlMode lastControlMode = null;

    public LazyVictorSPX(int deviceNumber) {
        super(deviceNumber);
    }

    @Override
    public void set(ControlMode mode, double value) {
        if (value != lastSet || mode != lastControlMode) {
            lastSet = value;
            lastControlMode = mode;
            super.set(mode, value);
        }
    }

}
