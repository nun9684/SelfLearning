package com.qrpackaging.backend.qrpackaging.model;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class UserInformation {
    String username = "";
    String password = "";

    public UserInformation() {}

    public UserInformation(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Document toBsonDoc() {
        Document document = new Document();

        document.append("Username", this.username);
        document.append("Password", this.password);

        return document;
    }
}
