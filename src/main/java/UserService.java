import java.util.Scanner;

class UserService {
    private String username;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
    private String password;
    private String cellPhoneNumber;
    
    class User {
    private String username;
    private String password;
    private String cellPhone;

    public User(String username, String password, String cellPhone) {
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getCellPhone() { return cellPhone; }
}
    
        
    // USERNAME: must contain '_' and be <= 5 characters
    private boolean isValidUsername(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // PASSWORD: 8+ chars, at least one capital, one number, one special character
    private boolean isValidPassword(String password) {
        if (password.length() <=8) return false;

        boolean hasCapital = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasCapital = true;
            else if (Character.isDigit(c))  hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }

        return hasCapital && hasDigit && hasSpecial;
    }

    // CELL PHONE: must start with +27 or 27, followed by 9 digits
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

    String registerUser(String username, String password, String cellPhone) {
        if (!isValidUsername(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if (!isValidPassword(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        if (!isValidCellPhone(cellPhone)) {
            return "Cell phone number is incorrectly formatted, please ensure the number is in the format +27xxxxxxxxx or 27xxxxxxxxx.";
        }

        return "Welcome " + username + ", it is great to see you.";
    }

}
