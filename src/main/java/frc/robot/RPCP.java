package frc.robot;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * RPCP - Raspberry - Pi Communication Protocol.
 * Textual or Binary? Currently textual, even though in future versions should become binary.
 * Message structure: [4 values]#
 * Example:
 *  "1, 3, 44, 53#"
 * Explanation:
 *  *values* 1, 3, 44, 53 - (1, 3) is the upper left corner of the rectangle, 44 and 53 are the width and height correspondingly.
 */
public class RPCP {

    public static final char REFLECTOR_DATA_REQUEST = 'R';

    public static final String IP = "10.10.2.8";
    public static final int PORT = 8089;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String buffer;

    /**
     * Creates socket communication with the Raspberry-Pi
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
     * Reads a message from the Raspberry-Pi.
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

    public void request(char requestType) {
        try {
            out.writeChar(requestType);
            out.flush();
        } catch (IOException e) {
            System.out.println("Connection Problem... Sending request again");
            request(requestType);
        }
    }

    public int[] requestAndReceive(char requestType) {
        request(requestType);
        readMessage();
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
            System.out.println("Connection Problem when trying to close socket...");
            close();
        }
    }
}
