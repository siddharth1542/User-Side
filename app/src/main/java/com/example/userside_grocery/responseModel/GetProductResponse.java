package com.example.Customer.responseModel;

import java.util.List;

public class GetProductResponse
{
    boolean Success;
    String   Message;
    int    Id;
    int  Records;
    List<VendorShowProductList> Data;

    public GetProductResponse(boolean success, String message, int id, int records, List<VendorShowProductList> data) {
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

    public List<VendorShowProductList> getData() {
        return Data;
    }

    public void setData(List<VendorShowProductList> data) {
        Data = data;
    }
}
