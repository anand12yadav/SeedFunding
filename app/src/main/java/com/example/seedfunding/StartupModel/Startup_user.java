package com.example.seedfunding.StartupModel;

public class Startup_user {
    private String name,email,password;

    public Startup_user(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Startup_user() {
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
