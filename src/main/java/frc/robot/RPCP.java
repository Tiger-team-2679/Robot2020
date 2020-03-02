package frc.robot;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

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

    public static final String IP = "127.0.0.1";
    public static final int PORT = 8089;

    private Socket socket;
    private DataInputStream in;
    private PrintWriter out;
    private String buffer;

    /**
     * Creates socket communication with the Raspberry-Pi
     */
    public RPCP() {
        while(true) {
            try {
                this.socket = new Socket(IP, PORT);
                this.in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);
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
        System.out.println("Connected...");
    }

    public void request(char requestType) {
        out.print(requestType);
        out.flush();
    }

    public int[] requestAndReceive(char requestType) {
        System.out.println("Requesting...");
        request(requestType);
        System.out.println("Wait for message...");
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

    public static void main(String[] args) {
        RPCP rp = new RPCP();
        Scanner sc = new Scanner(System.in);
        while (true) {
            sc.nextLine();
            int[] rect = rp.requestAndReceive(RPCP.REFLECTOR_DATA_REQUEST);
            System.out.println(Arrays.toString(rect));
        }
    }
}
