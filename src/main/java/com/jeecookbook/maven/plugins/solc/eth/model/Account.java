package com.jeecookbook.maven.plugins.solc.eth.model;

public class Account {

    private String publicKey;
    private String password;

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
