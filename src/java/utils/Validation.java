/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author nguye
 */
public class Validation {

    public static byte[] getSHA256(String password) throws NoSuchAlgorithmException  {
       MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }
    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }

    public static boolean checkEmptyAndLenght(String checkString, int maxLenght) {
        boolean result = false;
        if ((!checkString.trim().isEmpty()) && checkString.length() <= maxLenght) {
            result = true;
        }
        return result;
    }
}
