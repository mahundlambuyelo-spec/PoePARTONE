package com.mycompany.loginsystemp1;

public class User {

    private String username;
    private String password;
    private String cellPhone;

    public User(String username, String password, String cellPhone) {
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getCellPhone() {
        return cellPhone;
    }
}