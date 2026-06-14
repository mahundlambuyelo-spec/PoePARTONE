package com.mycompany.loginsystemp1;

public class MessageManager {

    private String messageID;
    private String recipientCell;
    private String message;
    private String messageHash;

    private static int numMessagesSent = 0;

    public MessageManager(int id, String recipientCell, String message) {

        this.messageID = String.valueOf(id);
        this.recipientCell = recipientCell;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    public String getMessageID() {
        return messageID;
    }

    public String getRecipientCell() {
        return recipientCell;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String checkRecipientCell(String recipient) {

        if (recipient.matches("^\\+27\\d{9}$")) {
            return "Cell phone number successfully captured.";
        }

        return "Cell phone number is incorrectly formatted or does not contain an international code.";
    }

    public String checkMessageLength(String msgBody) {

        if (msgBody.length() <= 250) {
            return "Message ready to send.";
        }

        return "Message exceeds 250 characters.";
    }

    public String createMessageHash() {

        String[] words = message.trim().split("\\s+");

        String firstWord = words.length > 0 ? words[0].toUpperCase() : "";
        String lastWord = words.length > 0 ? words[words.length - 1].toUpperCase() : "";

        return messageID + ":" + recipientCell.substring(recipientCell.length() - 3)
                + ":" + firstWord + lastWord;
    }

    public String sentMessage(int option) {

        switch (option) {

            case 1:
                numMessagesSent++;
                return "Message successfully sent.";

            case 2:
                return "Message disregarded.";

            case 3:
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    public void storeMessage() {
        System.out.println("Message stored.");
    }

    public String printMessages() {
        return "Message ID: " + messageID +
                "\nRecipient: " + recipientCell +
                "\nHash: " + messageHash +
                "\nMessage: " + message;
    }

    public int returnTotalMessages() {
        return numMessagesSent;
    }
}