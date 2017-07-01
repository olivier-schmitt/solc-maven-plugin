package com.jeecookbook.maven.plugins.solc.eth.model;

public class Wallet {

    private String path;
    private String password;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
