package com.example.Customer.responseModel;

public class VendorDataList {


   int  VendorId;
   int  CategoryId;
   String Name;
   String Username;
   String UKey;
   String ProfilePic;
   String Password;
   String AccountStatus;
   boolean Status;
   String CategoryName;
   String  OpenTime;
   String CloseTime;
   boolean CashOnDelivery;
   boolean OnlinePayment;
   String Mobile;
   String Email;
   String Longitude;
   String Latitude;
   String OwnerName;
   String WhatsappNumber;
   String Address;
   int Pincode;
   String IPAddress;
   String RegDate;
   String AlterDate;
   String LastLoginIP;
   String LastLoginDate;

    public VendorDataList(int vendorId, int categoryId, String name, String username, String UKey, String profilePic, String password, String accountStatus, boolean status, String categoryName, String openTime, String closeTime, boolean cashOnDelivery, boolean onlinePayment, String mobile, String email, String longitude, String latitude, String ownerName, String whatsappNumber, String address, int pincode, String IPAddress, String regDate, String alterDate, String lastLoginIP, String lastLoginDate) {
        VendorId = vendorId;
        CategoryId = categoryId;
        Name = name;
        Username = username;
        this.UKey = UKey;
        ProfilePic = profilePic;
        Password = password;
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
        this.IPAddress = IPAddress;
        RegDate = regDate;
        AlterDate = alterDate;
        LastLoginIP = lastLoginIP;
        LastLoginDate = lastLoginDate;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAccountStatus() {
        return AccountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        AccountStatus = accountStatus;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
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

    public boolean isCashOnDelivery() {
        return CashOnDelivery;
    }

    public void setCashOnDelivery(boolean cashOnDelivery) {
        CashOnDelivery = cashOnDelivery;
    }

    public boolean isOnlinePayment() {
        return OnlinePayment;
    }

    public void setOnlinePayment(boolean onlinePayment) {
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

    public int getPincode() {
        return Pincode;
    }

    public void setPincode(int pincode) {
        Pincode = pincode;
    }

    public String getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(String IPAddress) {
        this.IPAddress = IPAddress;
    }

    public String getRegDate() {
        return RegDate;
    }

    public void setRegDate(String regDate) {
        RegDate = regDate;
    }

    public String getAlterDate() {
        return AlterDate;
    }

    public void setAlterDate(String alterDate) {
        AlterDate = alterDate;
    }

    public String getLastLoginIP() {
        return LastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        LastLoginIP = lastLoginIP;
    }

    public String getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }
}
