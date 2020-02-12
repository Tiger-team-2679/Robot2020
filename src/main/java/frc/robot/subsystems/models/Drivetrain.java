package frc.robot.subsystems.models;

import edu.wpi.first.wpilibj2.command.Subsystem;

public interface Drivetrain extends Subsystem {

    public void set(double powerLeft, double powerRight);

    public EncoderSides velocities();

    public EncoderSides positions();

    public double getHeading();

    public double getAngularVelocity();

    public static class EncoderSides {

        private double left;
        private double right;

        public EncoderSides(double left, double right) {
            this.left = left;
            this.right = right;
        }

        public double getLeft() {
            return left;
        }

        public double getRight() {
            return right;
        }

    }

}
