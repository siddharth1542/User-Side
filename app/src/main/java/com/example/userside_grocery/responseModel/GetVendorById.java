package com.example.Customer.responseModel;

public class GetVendorById
{


    String Name,
    Usename,
    UKey,
    ProfilePic,
    AccountStatus,
    Status,
    CategoryName,
    OpenTime,
    CloseTime,
    CashOnDelivery,
    OnlinePayment,
    Mobile,
    Email,
    Longitude,
    Latitude,
    OwnerName,
    WhatsappNumber,
    Address,
    Pincode;
    int VendorId,
    CategoryId;


    public GetVendorById(String name, String usename, String UKey, String profilePic, String accountStatus, String status, String categoryName, String openTime, String closeTime, String cashOnDelivery, String onlinePayment, String mobile, String email, String longitude, String latitude, String ownerName, String whatsappNumber, String address, String pincode, int vendorId, int categoryId) {
        Name = name;
        Usename = usename;
        this.UKey = UKey;
        ProfilePic = profilePic;
        AccountStatus = accountStatus;
        Status = status;
        CategoryName = categoryName;
        OpenTime = openTime;
        CloseTime = closeTime;
        CashOnDelivery = cashOnDelivery;
        OnlinePayment = onlinePayment;
        Mobile = mobile;
        Email = email;
        Longitude = longitude;
        Latitude = latitude;
        OwnerName = ownerName;
        WhatsappNumber = whatsappNumber;
        Address = address;
        Pincode = pincode;
        VendorId = vendorId;
        CategoryId = categoryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsename() {
        return Usename;
    }

    public void setUsename(String usename) {
        Usename = usename;
    }

    public String getUKey() {
        return UKey;
    }

    public void setUKey(String UKey) {
        this.UKey = UKey;
    }

    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getAccountStatus() {
        return AccountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        AccountStatus = accountStatus;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getOpenTime() {
        return OpenTime;
    }

    public void setOpenTime(String openTime) {
        OpenTime = openTime;
    }

    public String getCloseTime() {
        return CloseTime;
    }

    public void setCloseTime(String closeTime) {
        CloseTime = closeTime;
    }

    public String getCashOnDelivery() {
        return CashOnDelivery;
    }

    public void setCashOnDelivery(String cashOnDelivery) {
        CashOnDelivery = cashOnDelivery;
    }

    public String getOnlinePayment() {
        return OnlinePayment;
    }

    public void setOnlinePayment(String onlinePayment) {
        OnlinePayment = onlinePayment;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getWhatsappNumber() {
        return WhatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        WhatsappNumber = whatsappNumber;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public int getVendorId() {
        return VendorId;
    }

    public void setVendorId(int vendorId) {
        VendorId = vendorId;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
    }
}
