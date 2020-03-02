package frc.robot;

import java.lang.reflect.Array;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        RPCP rp = new RPCP();
        int[] rect = rp.requestAndReceive(RPCP.REFLECTOR_DATA_REQUEST);
        System.out.println(Arrays.toString(rect));
        rp.close();
    }

}
