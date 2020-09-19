package com.qrpackaging.backend.qrpackaging.Utils;

import java.security.SecureRandom;
import java.util.Random;

public class SerialNumberGenrator {
    final private String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    final private Random rng = new SecureRandom();

    public SerialNumberGenrator() {}

    private char randomChar() {
        return ALPHABET.charAt(rng.nextInt(ALPHABET.length()));
    }

    public String getSerialNumber() {
        StringBuilder sb = new StringBuilder();
        int spacer = 0;
        int length = 16;
        int spacing = 4;
        char spacerChar = '-';
        while(length > 0){
            if(spacer == spacing){
                sb.append(spacerChar);
                spacer = 0;
            }
            length--;
            spacer++;
            sb.append(randomChar());
        }
        return sb.toString();
    }
}
