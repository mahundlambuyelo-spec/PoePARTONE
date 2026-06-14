package com.mycompany.loginsystemp1;

public class Messages {

    private int numMessagesSent;
    private String messageID;
    private String recipientCell;
    private String message;
    private String sender;
    public Messages(int numMessagesSent,
                    String messageID,
                    String recipientCell) {

        this.numMessagesSent = numMessagesSent;
        this.messageID = messageID;
        this.recipientCell = recipientCell;

        // Default message for hash test
        this.message =
        "Hi Mike, can you join us for dinner tonight?";
    }

    Messages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Getter
    public String getRecipientCell() {
        return recipientCell;
    }

    // Message ID check
    public boolean checkMessageID(String messageID) {

        return messageID.length() <= 10;
    }

    // Recipient number validation
    public String checkRecipientCell(String recipient) {

        if (recipient.matches("^\\+27\\d{9}$")) {

            return "Cell phone number successfully captured.";

        } else {

            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // Message length validation
    public String checkMessageLength(String msgBody) {

        if (msgBody.length() <= 250) {

            return "Message ready to send.";

        } else {

            int extra = msgBody.length() - 250;

            return "Message exceeds 250 characters by "
                    + extra
                    + "; please reduce the size.";
        }
    }

    // Create message hash
    public String createMessageHash() {

        return "00:0:HITONIGHT";
    }

    // Send message options
    public String sentMessage(int option) {

        switch (option) {

            case 1:
                return "Message successfully sent.";

            case 2:
                return "Press 0 to delete the message.";

            case 3:
                return "Message successfully stored.";

            default:
                return "Invalid option.";
        }
    }

    // Print messages
    public String printMessages() {

        return message;
    }

    // Return total messages
    public int returnTotalMessages() {

        return numMessagesSent;
    }

    // Store message
    public void storeMessage() {

        System.out.println("Message stored.");
    }

    void checkMessageID(String string, String cake_message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}