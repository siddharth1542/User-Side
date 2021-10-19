package com.example.Customer.responseModel;

import java.util.List;

public class GetProductNotInList {


    boolean Success;
    String   Message;
    int    Id;
    int  Records;
    List<VendorShowProductNotInList> Data;


    public GetProductNotInList(boolean success, String message, int id, int records, List<VendorShowProductNotInList> data) {
        Success = success;
        Message = message;
        Id = id;
        Records = records;
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

    public int getRecords() {
        return Records;
    }

    public void setRecords(int records) {
        Records = records;
    }

    public List<VendorShowProductNotInList> getData() {
        return Data;
    }

    public void setData(List<VendorShowProductNotInList> data) {
        Data = data;
    }
}
