package com.mycompany.loginsystemp1;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class LoginSystemP1 {

    private static String[] storedMessageIDs = new String[100];
    private static String[] storedRecipients = new String[100];
    private static String[] storedMessages = new String[100];
    private static String[] storedHashes = new String[100];
    private static String[] storedSenders = new String[100];

    private static int storedCount = 0;

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            UserService userService = new UserService();
            User registeredUser = null;

            boolean registered = false;

            // =========================
            // REGISTRATION
            // =========================
            while (!registered) {

                System.out.print("Username: ");
                String username = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                System.out.print("Cell: ");
                String cell = scanner.nextLine();

                String result = userService.registerUser(username, password, cell);
                System.out.println(result);

                if (result.startsWith("Welcome")) {
                    registeredUser = new User(username, password, cell);
                    registered = true;
                }
            }

            // =========================
            // LOGIN
            // =========================
            boolean loggedIn = false;
            int attempts = 0;

            while (!loggedIn && attempts < 3) {

                System.out.print("Login username: ");
                String u = scanner.nextLine();

                System.out.print("Login password: ");
                String p = scanner.nextLine();

                loggedIn = userService.loginUser(u, p, registeredUser);

                System.out.println(userService.getLoginMessage(loggedIn));

                attempts++;
            }

            if (!loggedIn) {
                System.out.println("Too many attempts.");
                return;
            }

            // =========================
            // MAIN MENU
            // =========================
            boolean running = true;

            while (running) {
                System.out.println("\n==== MAIN MENU ====");
                System.out.println("\n1 Send Messages");
                System.out.println("2 Stored Messages");
                System.out.println("3 Quit");
                System.out.println("4 Stored Menu");

                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {

                    case 1:

                        System.out.print("How many messages: ");
                        int n = scanner.nextInt();
                        scanner.nextLine();

                        MessageManager[] arr = new MessageManager[n];

                        for (int i = 0; i < n; i++) {

                            System.out.print("Recipient: ");
                            String r = scanner.nextLine();

                            System.out.print("Message: ");
                            String m = scanner.nextLine();

                            arr[i] = new MessageManager(i + 1, r, m);

                            System.out.println("1 Send 2 Disregard 3 Store");
                            int c = scanner.nextInt();
                            scanner.nextLine();

                            String res = arr[i].sentMessage(c);
                            System.out.println(res);

                            if (c == 3) {

                                storedMessageIDs[storedCount] = String.valueOf(i + 1);
                                storedRecipients[storedCount] = r;
                                storedMessages[storedCount] = m;
                                storedHashes[storedCount] = arr[i].getMessageHash();
                                storedSenders[storedCount] = registeredUser.getUsername();

                                storedCount++;
                            }

                            System.out.println(arr[i].printMessages());
                        }

                        break;

                    case 2:
                        displayFullReport();
                        break;

                    case 3:
                        running = false;
                        break;

                    case 4:
                        storedMessagesMenu(scanner);
                        break;
                }
            }

            System.out.println("Program ended.");
        }
    }
    // ==========================================
    // STORED MESSAGES MENU FUNCTIONS
    // ==========================================
    private static void storedMessagesMenu(Scanner scanner) {

        System.out.println("1 Sender/Recipient");
        System.out.println("2 Longest");
        System.out.println("3 Search ID");
        System.out.println("4 Search Recipient");
        System.out.println("5 Delete Hash");
        System.out.println("6 Full Report");

        int c = scanner.nextInt();
        scanner.nextLine();

        switch (c) {

            case 1:
                for (int i = 0; i < storedCount; i++) {
                    System.out.println(storedSenders[i] + " -> " + storedRecipients[i]);
                }
                break;

            case 2:
                String longest = "";
                for (int i = 0; i < storedCount; i++) {
                    if (storedMessages[i].length() > longest.length()) {
                        longest = storedMessages[i];
                    }
                }
                System.out.println(longest);
                break;

            case 3:
                System.out.print("ID: ");
                String id = scanner.nextLine();

                for (int i = 0; i < storedCount; i++) {
                    if (storedMessageIDs[i].equals(id)) {
                        System.out.println(storedMessages[i]);
                    }
                }
                break;

            case 4:
                System.out.print("Recipient: ");
                String rec = scanner.nextLine();

                for (int i = 0; i < storedCount; i++) {
                    if (storedRecipients[i].equals(rec)) {
                        System.out.println(storedMessages[i]);
                    }
                }
                break;

            case 5:
                System.out.print("Hash: ");
                String h = scanner.nextLine();

                for (int i = 0; i < storedCount; i++) {
                    if (storedHashes[i].equals(h)) {

                        for (int j = i; j < storedCount - 1; j++) {
                            storedMessageIDs[j] = storedMessageIDs[j + 1];
                            storedRecipients[j] = storedRecipients[j + 1];
                            storedMessages[j] = storedMessages[j + 1];
                            storedHashes[j] = storedHashes[j + 1];
                            storedSenders[j] = storedSenders[j + 1];
                        }

                        storedCount--;
                        System.out.println("Deleted");
                        break;
                    }
                }
                break;

            case 6:
                displayFullReport();
                break;
        }
    }
    // ==========================================
    // DISPLAY REPORT
    // ==========================================
    private static void displayFullReport() {

        for (int i = 0; i < storedCount; i++) {
            System.out.println("\nID: " + storedMessageIDs[i]);
            System.out.println("Sender: " + storedSenders[i]);
            System.out.println("Recipient: " + storedRecipients[i]);
            System.out.println("Message: " + storedMessages[i]);
            System.out.println("Hash: " + storedHashes[i]);
        }
    }
    // ==========================================
    // FILE SAVING FUNCTION
    // ==========================================
    private static void saveMessagesToFile() {

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter("stored_messages.txt", true))) {

            writer.println("\n===== STORED MESSAGES REPORT =====");

            for (int i = 0; i < storedCount; i++) {

                writer.println("Message ID: " + storedMessageIDs[i]);
                writer.println("Sender: " + storedSenders[i]);
                writer.println("Recipient: " + storedRecipients[i]);
                writer.println("Message: " + storedMessages[i]);
                writer.println("Hash: " + storedHashes[i]);
                writer.println("-----------------------------------");
            }

            writer.println("===================================\n");

            System.out.println("Messages saved to file successfully.");

        } catch (IOException e) {

            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
