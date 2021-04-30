package com.example.userservice.model;

public class SignupMail {

    private final String to;
    private final String content;

    public SignupMail(String to, String content) {
        this.to = to;
        this.content = content;
    }


    public String getTo() {
        return this.to;
    }

    public String getSubject() {
        return "WELCOME TO 	PROJECT MANAGEMENT";
    }

    public String getContent() {
        return this.content;
    }
}
