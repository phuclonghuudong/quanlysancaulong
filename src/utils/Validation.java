package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 *
 * @author phucp
 */
public class Validation {

    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static Boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static boolean isNumber(String num) {
        boolean result = true;
        if (num == null) {
            return false;
        }
        try {
            long k = Long.parseLong(num);
            if (k < 0) {
                result = false;
            }
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    public static boolean isValidPhone(String str) {
        // Ví dụ: 10 chữ số, bắt đầu bằng 03, 05, 07, 08, 09
        return str != null && str.matches("^(0[3|5|7|8|9])[0-9]{8}$");
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b)); // Chuyển sang dạng hex
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
