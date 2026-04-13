package com.mycompany.loginsystemp1;

class User {
    private String username;
    private String password;
    private String cellPhone;

    User(String username, String password, String cellPhone) {
        this.username  = username;
        this.password  = password;
        this.cellPhone = cellPhone;
    }

    String getUsername()  { return username; }
    String getPassword()  { return password; }
    String getCellPhone() { return cellPhone; }
}