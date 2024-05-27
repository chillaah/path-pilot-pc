package com.example.pathpilotfx.model;

import com.example.pathpilotfx.database.UserDAO;
import static com.example.pathpilotfx.MainApplication.db;
import static com.kosprov.jargon2.api.Jargon2.*;

/**
 * This class provides methods for authenticating users and hashing passwords.
 */
public class PasswordHash {

    /**
     * Authenticates a user by comparing the provided password with the stored hashed password.
     *
     * @param email            The email of the user.
     * @param providedPassword The password provided by the user.
     * @return true if the provided password matches the stored hashed password, false otherwise.
     */
    public static boolean authenticateUser(String email, String providedPassword) {

        // Retrieve the hashed password from the database
        String storedHashedPassword = db.getStoredHashedPassword(email);

        // User not found in the database
        if (storedHashedPassword == null)
        {
            return false;
        }

        byte[] passwordBytes = providedPassword.getBytes();

        // Just get a hold on the verifier. No special configuration needed
        Verifier verifier = jargon2Verifier();

        // Set the encoded hash, the password and verify
        boolean matches = verifier
                .hash(storedHashedPassword)
                .password(passwordBytes)
                .verifyEncoded();

        System.out.printf("Matches: %s%n", matches);

        // Return the result of the verification
        return matches;
    }

    /**
     * Hashes the given password using the Argon2 algorithm.
     *
     * @param password The password to be hashed.
     * @return The hashed password.
     */
    public static String hashPassword(String password) {

        // Return null if the password is empty
        if (password.isBlank() || password.isEmpty())
        {
            return null;
        }

        // Convert the password to a byte array
        byte[] passwordBytes = password.getBytes();

        // Configure the hasher
        Hasher hasher = jargon2Hasher()
                .type(Type.ARGON2d) // Data-dependent hashing
                .memoryCost(65536)  // 64MB memory cost
                .timeCost(3)        // 3 passes through memory
                .parallelism(4)     // use 4 lanes and 4 threads
                .saltLength(16)     // 16 random bytes salt
                .hashLength(16);    // 16 bytes output hash

        // Set the password and calculate the encoded hash
        String encodedHash = hasher
                .password(passwordBytes)
                .encodedHash();

        System.out.printf("Hash: %s%n", encodedHash);

        // Return the encoded hash
        return encodedHash;
    }
}
