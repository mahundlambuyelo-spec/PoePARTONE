package com.mycompany.loginsystemp1;

import java.util.Scanner;

public class LoginSystemP1 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            UserService userService = new UserService();
            User registeredUser = null;

            boolean registered = false;

            // REGISTRATION PHASE
            while (!registered) {
                System.out.println("\nREGISTRATION");

                System.out.print("Enter username (must contain _ and be <= 5 chars): ");
                String username = scanner.nextLine();

                System.out.print("Enter password (8+ chars, capital, number, special char): ");
                String password = scanner.nextLine();

                System.out.print("Enter cell phone number (+277*****983 ): ");
                String cellPhone = scanner.nextLine();

                String registrationResult = userService.registerUser(username, password, cellPhone);
                System.out.println("\n" + registrationResult);

                if (registrationResult.startsWith("Welcome")) {
                    registeredUser = new User(username, password, cellPhone);
                    registered = true;
                    System.out.println("\n Registration completed successfully!");
                } else {
                    System.out.println("\n Registration failed. Please try again.\n");
                }
            }

            // LOGIN PHASE
            System.out.println("\n" + "=".repeat(50));
            System.out.println("     LOGIN TO YOUR ACCOUNT");
            System.out.println("=".repeat(50));

            boolean loggedIn = false;
            int attempts = 0;
            int maxAttempts = 3;

            while (!loggedIn && attempts < maxAttempts) {
                System.out.println("\n--- LOGIN (Attempt " + (attempts + 1) + " of " + maxAttempts + ") ---");

                System.out.print("Enter username: ");
                String loginUsername = scanner.nextLine();

                System.out.print("Enter password: ");
                String loginPassword = scanner.nextLine();

                boolean loginSuccess = userService.loginUser(loginUsername, loginPassword, registeredUser);
                String loginMessage = userService.getLoginMessage(loginSuccess);

                System.out.println(loginMessage);

                if (loginSuccess) {
                    loggedIn = true;
                    System.out.println("\n You are now logged into Chat App!");
                    
                } else {
                    attempts++;
                    if (attempts < maxAttempts) {
                        System.out.println("Please try again.\n");
                    }
                }
            }

            if (!loggedIn) {
                System.out.println("\n✗ Too many failed login attempts. Please contact support.");
            }

        } // end try-with-resources
    } // end main

}

    

    
