package com.example.Customer.responseModel;

public class SendAddedProduct {


    boolean Success;
    String Message;
    int Id;
    int Data;

    public SendAddedProduct(boolean success, String message, int id, int data) {
        Success = success;
        Message = message;
        Id = id;
        Data = data;
    }


    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getData() {
        return Data;
    }

    public void setData(int data) {
        Data = data;
    }
}
