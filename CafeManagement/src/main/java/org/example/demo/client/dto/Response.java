package org.example.demo.client.dto;

public class Response {

    private boolean success;
    private String message;
    private String data;

    public Response() {
    }

    public Response(boolean success,
                    String message,
                    String data) {

        this.success = success;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}