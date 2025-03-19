package com.carango.bom.dto;

public class SuccessResponse {

    private String message;
    private String location;

    public SuccessResponse(String message, String location) {
        this.message = message;
        this.location = location;
    }
    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}

    public String getLocation() {return location;}

    public void setLocation(String location) {this.location = location;}
}