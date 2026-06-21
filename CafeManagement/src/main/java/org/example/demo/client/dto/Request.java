package org.example.demo.client.dto;

public class Request {

    private String action;
    private String data;

    public Request() {
    }

    public Request(String action, String data) {
        this.action = action;
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return action + "|" + data;
    }
}