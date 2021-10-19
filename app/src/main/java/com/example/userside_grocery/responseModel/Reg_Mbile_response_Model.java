package com.example.Customer.responseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Reg_Mbile_response_Model {


    @SerializedName("Success")
    @Expose
    boolean Success;

    @SerializedName("Message")
    @Expose
    String Message;

    @SerializedName("ActionType")
    @Expose
     int ActionType;

    @SerializedName("OTP")
    @Expose
     int OTP;

    public Reg_Mbile_response_Model(boolean success, String message, int actionType, int OTP) {
        Success = success;
        Message = message;
        ActionType = actionType;
        this.OTP = OTP;
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

    public int getActionType() {
        return ActionType;
    }

    public void setActionType(int actionType) {
        ActionType = actionType;
    }

    public int getOTP() {
        return OTP;
    }

    public void setOTP(int OTP) {
        this.OTP = OTP;
    }
}
