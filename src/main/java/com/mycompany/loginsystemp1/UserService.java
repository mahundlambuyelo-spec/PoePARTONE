package com.mycompany.loginsystemp1;

public class UserService {

    // USERNAME validation
    private boolean isValidUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // PASSWORD validation
    private boolean isValidPassword(String password) {

        if (password.length() < 8) return false;

        boolean hasCapital = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasCapital && hasDigit && hasSpecial;
    }

    // CELL validation
    private boolean isValidCellPhone(String cellPhone) {

        String digits;

        if (cellPhone.startsWith("+27")) {
            digits = cellPhone.substring(3);
        } else if (cellPhone.startsWith("27")) {
            digits = cellPhone.substring(2);
        } else {
            return false;
        }

        return digits.length() == 9 && digits.matches("\\d+");
    }

    // REGISTER
    public String registerUser(String username, String password, String cellPhone) {

        if (!isValidUsername(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }

        if (!isValidPassword(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }

        if (!isValidCellPhone(cellPhone)) {
            return "Cell phone number incorrectly formatted, please ensure the number is in the format +27xxxxxxxxx or 27xxxxxxxxx.";
        }

        return "Welcome " + username + ", it is great to see you.";
    }

    // LOGIN
    public boolean loginUser(String loginUsername, String loginPassword, User registeredUser) {

        if (registeredUser == null) return false;

        return registeredUser.getUsername().equals(loginUsername)
                && registeredUser.getPassword().equals(loginPassword);
    }

    // LOGIN MESSAGE
    public String getLoginMessage(boolean success) {

        if (success) {
            return "Welcome back! You have successfully logged in.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}