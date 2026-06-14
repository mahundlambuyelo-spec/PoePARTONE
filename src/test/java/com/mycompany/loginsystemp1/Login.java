package com.mycompany.loginsystemp1;

public class Login {

    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;

    // =========================
    // USERNAME CHECK
    // =========================
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // =========================
    // PASSWORD CHECK
    // =========================
    public boolean checkPasswordComplexity(String password) {

        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        boolean longEnough = password.length() >= 8;

        return hasUpper && hasDigit && hasSpecial && longEnough;
    }

    // =========================
    // CELL NUMBER CHECK
    // =========================
    public boolean checkCellPhoneNumber(String cellNumber) {
        return cellNumber.matches("^\\+27\\d{9}$");
    }

    // =========================
    // REGISTER PASSWORD
    // =========================
    public String registerPassword(String password) {

        if (checkPasswordComplexity(password)) {
            this.password = password;
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }

    // =========================
    // REGISTER CELL NUMBER
    // =========================
    public String registerCellPhoneNumber(String cellNumber) {

        if (checkCellPhoneNumber(cellNumber)) {
            this.cellNumber = cellNumber;
            return "Cell number successfully captured.";
        } else {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
    }

    // =========================
    // REGISTER USER
    // =========================
    public String registerUser(String username, String password,
                               String cellNumber,
                               String firstName,
                               String lastName) {

        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;

        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }

        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }

        this.password = password;
        this.cellNumber = cellNumber;

        return "Welcome " + firstName + " " + lastName + ", it is great to see you.";
    }

    // =========================
    // LOGIN USER
    // =========================
    public boolean loginUser(String username, String password) {
        return this.username != null
                && this.username.equals(username)
                && this.password != null
                && this.password.equals(password);
    }
}