package pl.touristguide.common;

import org.mindrot.jbcrypt.BCrypt;

public class HashUtils {
    public static String generateHash(String password, int logRounds) throws Exception {
        System.out.println(password + " " + logRounds);
        return BCrypt.hashpw(password, BCrypt.gensalt(logRounds));
    }

    public static boolean verifyPassword(String password, String hashPassword) throws Exception {
        return BCrypt.checkpw(password, hashPassword);
    }

    /*private boolean verifyAndUpdateHash(String password, String hashPassword, Function<String, Boolean> updateFunc) {
        if (verifyPassword(password, hashPassword)) {
            int rounds = getRounds(hashPassword);
            // It might be smart to only allow increasing the rounds.
            // If someone makes a mistake the ability to undo it would be nice though.
            if (rounds != logRounds) {
                String newHash = generateHash(password, rounds);
                return updateFunc.apply(newHash);
            }
            return true;
        }
        return false;
    }*/

    public static int getRounds(String salt) throws Exception {
        char minor = (char) 0;
        int off = 0;

        if (salt.charAt(0) != '$' || salt.charAt(1) != '2')
            throw new IllegalArgumentException("Invalid salt version");
        if (salt.charAt(2) == '$')
            off = 3;
        else {
            minor = salt.charAt(2);
            if (minor != 'a' || salt.charAt(3) != '$')
                throw new IllegalArgumentException("Invalid salt revision");
            off = 4;
        }

        // Extract number of rounds
        if (salt.charAt(off + 2) > '$')
            throw new IllegalArgumentException("Missing salt rounds");
        return Integer.parseInt(salt.substring(off, off + 2));
    }
}
