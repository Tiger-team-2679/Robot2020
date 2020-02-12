package frc.robot.utils.drivers;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class LazyTalonSRX extends TalonSRX {

    private double lastSet = Double.NaN;
    private ControlMode lastControlMode = null;

    public LazyTalonSRX(int deviceNumber) {
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
