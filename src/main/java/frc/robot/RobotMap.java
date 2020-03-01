package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.models.*;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class RobotMap {

    public static final Climber climber = new ClimberImpl();
    public static final ColourWheel colourWheel = new ColourWheelImpl();
    public static final Drivetrain drivetrain = new DrivetrainImpl();
    public static final Elevator elevator = new ElevatorImpl();
    public static final PowerCells powerCells = new PowerCellsImpl();


    public static final Joystick joystick = new Joystick(0);
    public static final XboxController xbox = new XboxController(1);

    public static final int elevatorLeftId = 1;
    public static final int elevatorRightId = 6;
    public static final int climberLeftId = 5;
    public static final int climberRightId = 3;
    public static final int colourWheelId = 4;
    public static final int dtFrontLeftId = 4;
    public static final int dtBackLeftId = 1;
    public static final int dtFrontRightId = 3;
    public static final int dtBackRightId = 2;
    public static final int pcInId = 7;
    public static final int pcOutId = 2;

    public static final int ASignalRightEncoder = 7;
    public static final int BSignalRightEncoder = 6;
    public static final int ASignalLeftEncoder = 5;
    public static final int BSignalLeftEncoder = 4;

    /**
     * RPCP - Raspberry - Pi Communication Protocol.
     * Textual or Binary? Currently textual, even though in future versions should become binary.
     * Message structure: [4 values]#
     * Example:
     *  "1, 3, 44, 53#"
     * Explanation:
     *  *values* 1, 3, 44, 53 - (1, 3) is the upper left corner of the rectangle, 44 and 53 are the width and height correspondingly.
     */
    public static class RPCP {

        public static final String IP = "10.10.2.8";
        public static final int PORT = 8089;

        private Socket socket;
        private DataInputStream in;
        private DataOutputStream out;
        private String buffer;
        private boolean stopWaiting = false;
        private boolean retransmit = true;

        /**
         * Creates socket communication with the Jetson
         * @throws IOException Throws exception when
         */
        public RPCP() {
            while(true) {
                try {
                    this.socket = new Socket(IP, PORT);
                    this.in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    this.out = new DataOutputStream(socket.getOutputStream());
                    break;
                } catch (IOException e) {}
            }
            this.buffer = "";
        }

        /**
         * Reads a message from the Jetson.
         * @return True if read successfully, false otherwise.
         */
        private void readMessage() {
            buffer = "";
            String c = " ";
            while (c.charAt(0) != '#') {
                try {
                    c = new String(new byte[] {in.readByte()}, 0, 1, StandardCharsets.UTF_8);
                    buffer += c;
                } catch (IOException e) {}
            }
            buffer = buffer.substring(1, buffer.length() - 1);
        }

        public void request() {
            try {
                out.writeUTF("Yitav says hello");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public int[] requestAndReceive() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(!stopWaiting) {
                        try {
                            Thread.sleep(5000);
                            retransmit = !retransmit;
                            if (retransmit)
                                request();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            request();
            readMessage();
            stopWaiting = true;
            return getRect();
        }

        public int[] getRect() {
            String[] data = buffer.split(" ");
            int[] result = new int[data.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = Integer.parseInt(data[i]);
            }
            return result;
        }

        public void close() {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void main(String[] args) throws IOException {
            RPCP connect = new RPCP();
            System.out.println(Arrays.toString(connect.requestAndReceive()));
            connect.close();
        }
    }
}
