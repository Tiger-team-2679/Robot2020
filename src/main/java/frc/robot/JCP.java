package frc.robot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.LinkedList;

/**
 * JCP - Jetson Communication Protocol.
 * Textual or Binary? Currently textual, even though in future versions should become binary.
 * Message structure: [len] [type] [values...].
 * Example:
 *  "4 0 1 2 33 42"
 * Explanation:
 *  *len* 4 - The message has four values
 *  *type* 0 - The values represent the bounding box of a ball
 *  *values* 1, 2, 33, 42 - (1, 2) is the upper left corner of the rectangle, 33 and 42 are the width and height correspondingly.
 * Types:
 *  0 balls bounding boxes
 *  1 reflectors bounding boxes
 *  2 distance and orientation from a corner
 * Additional Information:
 *  The values are always given in groups of four. For example, a description of distance and orientation from a corner
 *  can be represented as follows: 12 30 0 0. Where 12 and 30 are the distance and orientation correspondingly, and the
 *  zeros are used to fill the blank space for the rest of the group.
 */
public class JCP implements Runnable {

    public static final String IP = "10.26.79.25";
    public static final int PORT = 2679;

    private Socket socket;
    private BufferedReader reader;
    private LinkedList<int[]> balls;
    private LinkedList<int[]> reflectors;
    private int[] corner;
    private Thread thread;

    /**
     * Creates socket communication with the Jetson
     * @throws IOException
     */
    public JCP() throws IOException{
        this.socket = new Socket(IP, PORT);
        InputStream input = socket.getInputStream();
        this.reader = new BufferedReader(new InputStreamReader(input));
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Reads a message from the Jetson.
     * @return True if read successfully, false otherwise.
     */
    public boolean readMessage() {
        LinkedList<int[]> points;
        int alreadyRead = 0;
        int len = -1;
        int type = -1;
        String s;
        while (alreadyRead != len) {
            try {
                s = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            String[] data = s.split(" ");
            if (data.length != 0) {
                int start = 0;
                if (len == -1)
                    len = Integer.parseInt(data[0]);
                if (type == -1 && data.length > 1) {
                    type = Integer.parseInt(data[1]);
                    start++;
                }
                for (int i = start; i < data.length; i++) {
                    
                    alreadyRead++;
                }
            }
        }
        return true;
    }

    /**
     * Get the bounding boxes of the detected balls.
     * @return List of rectangles, represented as follows: [x, y, width, height]
     */
    public LinkedList<int[]> getBalls() {
        return balls;
    }

    /**
     * Get the bounding boxes of the detected reflectors.
     * @return List of rectangles, represented as follows: [x, y, width, height]
     */
    public LinkedList<int[]> getReflectors() {
        return reflectors;
    }

    /**
     * Get the a corner that's been detected, along with a point on one of the lines.
     * @return A corner coordinate and another ponit coordinate [xc, yc, xp, yp]
     */
    public int[] getCorner() {
        return corner;
    }

    @Override
    public void run() {
        while (true) {

        }
    }
}
