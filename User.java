import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

class User {
    private static HashMap<String, String> userDatabase = new HashMap<>();

    // Method to register a user
    public static boolean register(String username, String password) {
        if (!userDatabase.containsKey(username)) {
            userDatabase.put(username, hashPassword(password));
            System.out.println("User registered successfully!");
            return true;
        }
        System.out.println("Username already exists!");
        return false;
    }

    // Method to log in a user
    public static boolean login(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(hashPassword(password));
    }

    // Password hashing using SHA-256
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error", e);
        }
    }
}
