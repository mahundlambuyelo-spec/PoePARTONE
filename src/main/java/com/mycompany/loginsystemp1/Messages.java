package com.mycompany.loginsystemp1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Messages {

    private String messageID;
    private String recipientCell;
    private String message;
    private String messageHash;
    private String sender;

    private static int numMessagesSent = 0;

    // ArrayLists required by the assignment
    public static ArrayList<Messages> sentMessages = new ArrayList<>();
    public static ArrayList<Messages> storedMessages = new ArrayList<>();
    public static ArrayList<Messages> disregardedMessages = new ArrayList<>();

    public Messages(int id, String recipientCell, String message) {

        this.messageID = String.valueOf(id);
        this.recipientCell = recipientCell;
        this.message = message;
        this.sender = "Current User";

        numMessagesSent++;

        this.messageHash = createMessageHash();
    }

    // ======================
    // GETTERS
    // ======================

    public String getMessageID() {
        return messageID;
    }

    public String getRecipient() {
        return recipientCell;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getSender() {
        return sender;
    }

    // ======================
    // VALIDATION METHODS
    // ======================

    public boolean checkMessageID(String messageID) {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell(String recipient) {

        if (recipient != null
                && recipient.startsWith("+27")
                && recipient.length() == 12) {

            return "Cell phone number successfully captured.";
        }

        return "Cellphone number is incorrectly formatted or does not contain an international code.";
    }

    public String checkMessageLength(String msgBody) {

        if (msgBody.length() <= 250) {
            return "Message ready to send.";
        }

        int extra = msgBody.length() - 250;

        return "Message exceeds 250 characters by "
                + extra
                + ", please reduce size.";
    }

    // ======================
    // HASH CREATION
    // ======================

    public String createMessageHash() {

        String[] words = message.split(" ");

        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        return messageID.substring(0,
                Math.min(2, messageID.length()))
                + ":"
                + numMessagesSent
                + ":"
                + firstWord.toUpperCase()
                + lastWord.toUpperCase();
    }

    // ======================
    // MESSAGE OPTIONS
    // ======================

    public String sentMessage(int option) {

        switch (option) {

            case 1:
                sentMessages.add(this);
                return "Message successfully sent.";

            case 2:
                disregardedMessages.add(this);
                return "Message disregarded.";

            case 3:
                storedMessages.add(this);
                storeMessage();
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    // ======================
    // DISPLAY METHODS
    // ======================

    public String printMessages() {

        return "Message ID: " + messageID
                + "\nMessage Hash: " + messageHash
                + "\nSender: " + sender
                + "\nRecipient: " + recipientCell
                + "\nMessage: " + message;
    }

    public int returnTotalMessages() {
        return sentMessages.size();
    }

    // ======================
    // STORE TO JSON FILE
    // ======================

    public void storeMessage() {

        try {

            FileWriter writer =
                    new FileWriter("messages.json", true);

            writer.write("{\n");
            writer.write("\"MessageID\":\"" + messageID + "\",\n");
            writer.write("\"MessageHash\":\"" + messageHash + "\",\n");
            writer.write("\"Sender\":\"" + sender + "\",\n");
            writer.write("\"Recipient\":\"" + recipientCell + "\",\n");
            writer.write("\"Message\":\"" + message + "\"\n");
            writer.write("}\n");

            writer.close();

        } catch (IOException e) {

            System.out.println("Error writing to file.");
        }
    }
}