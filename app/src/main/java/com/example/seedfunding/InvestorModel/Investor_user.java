package com.example.seedfunding.InvestorModel;

public class Investor_user {
    private String name,email,password;

    public Investor_user(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Investor_user() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
