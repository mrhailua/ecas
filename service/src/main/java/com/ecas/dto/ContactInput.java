package com.ecas.dto;

/**
 * Created by LENOVO on 5/28/2016.
 */
public class ContactInput {
    private String name;

    private String phoneNumber;
    private String email;
    private String title;
    private String content;

    public ContactInput(String content, String email, String name, String phoneNumber, String title) {
        this.content = content;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getTitle() {
        return title;
    }
}
