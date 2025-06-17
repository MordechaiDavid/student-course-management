package dev.mordechai.studentcoursemanager.db;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

        String[] passwords = {
                "admin123",
                "password456",
                "secret789",
                "qwerty111",
                "letmein222",
                "welcome333",
                "football444"
        };

        for (String password : passwords) {
            String hash = encoder.encode(password);
            System.out.println(password + " -> " + hash);
        }
    }
}

