package com.example.Customer.responseModel;

import java.util.List;

public class GetVendorProfileResponse {

         boolean Success;
         String    Message;
         int    Id;
         List<GetVendorById> Data;

    public GetVendorProfileResponse(boolean success, String message, int id, List<GetVendorById> data) {
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

    public List<GetVendorById> getData() {
        return Data;
    }

    public void setData(List<GetVendorById> data) {
        Data = data;
    }
}
