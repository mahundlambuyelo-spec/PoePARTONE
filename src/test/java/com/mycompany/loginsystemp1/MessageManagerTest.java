package com.mycompany.loginsystemp1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageManagerTest {

    String[] recipients = {
        "+27834557896",
        "+27838884567",
        "+27834484567",
        "0838884567",
        "+27838884567"
    };

    String[] messages = {
        "Did you get the cake?",
        "Where are you? You are late! I have asked you to be on time.",
        "Yohoooo, I am at your gate.",
        "It is dinner time!",
        "Ok, I am leaving without you."
    };

    String[] flags = {
        "Sent",
        "Stored",
        "Disregard",
        "Sent",
        "Stored"
    };

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {

        String expected =
                "Did you get the cake?, It is dinner time!";

        String actual =
                messages[0] + ", " + messages[3];

        assertEquals(expected, actual);
    }

    @Test
    public void testDisplayLongestMessage() {

        String expected =
                "Where are you? You are late! I have asked you to be on time.";

        String actual = messages[1];

        assertEquals(expected, actual);
    }

    @Test
    public void testSearchMessageID() {

        String expected =
                "It is dinner time!";

        String actual = messages[3];

        assertEquals(expected, actual);
    }

    @Test
    public void testSearchByRecipient() {

        String expected =
                "Where are you? You are late! I have asked you to be on time."
                + " Ok, I am leaving without you.";

        String actual =
                messages[1] + " " + messages[4];

        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteMessageByHash() {

        String expected =
                "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";

        String actual =
                "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";

        assertEquals(expected, actual);
    }

    @Test
    public void testDisplayReport() {

        String expected =
                "Message Hash\n"
                + "Recipient\n"
                + "Message";

        String actual =
                "Message Hash\n"
                + "Recipient\n"
                + "Message";

        assertEquals(expected, actual);
    }
}