package com.example.Customer.responseModel;

public class VendorList
{

    int CategoryId,SubCategoryId,VendorId,Pincode,PageIndex;
    String Name,Username,UKey,ProfilePic,Password,AccountStatus,Status,CategoryName,SubCategoryName,OpenTime,CloseTime,Mobile,Email,Longitude,Latitude,OwnerName,WhatsappNumber,Address,IPAddress,RegDate;
    boolean CashOnDelivery,OnlinePayment;


    public VendorList(int categoryID, int subCategoryID, int vendorId, int pincode, int pageIndex, String name, String username, String UKey, String profilePic, String password, String accountStatus, String status, String categoryName, String subCategoryName, String openTime, String closeTime, String mobile, String email, String longitude, String latitude, String ownerName, String whatsappNumber, String address, String IPAddress, String regDate, boolean cashOnDelivery, boolean onlinePayment) {
        CategoryId = categoryID;
        SubCategoryId = subCategoryID;
        VendorId = vendorId;
        Pincode = pincode;
        PageIndex = pageIndex;
        Name = name;
        Username = username;
        this.UKey = UKey;
        ProfilePic = profilePic;
        Password = password;
        AccountStatus = accountStatus;
        Status = status;
        CategoryName = categoryName;
        SubCategoryName = subCategoryName;
        OpenTime = openTime;
        CloseTime = closeTime;
        Mobile = mobile;
        Email = email;
        Longitude = longitude;
        Latitude = latitude;
        OwnerName = ownerName;
        WhatsappNumber = whatsappNumber;
        Address = address;
        this.IPAddress = IPAddress;
        RegDate = regDate;
        CashOnDelivery = cashOnDelivery;
        OnlinePayment = onlinePayment;
    }

    public int getCategoryID() {
        return CategoryId;
    }

    public void setCategoryID(int categoryID) {
        CategoryId = categoryID;
    }

    public int getSubCategoryID() {
        return SubCategoryId;
    }

    public void setSubCategoryID(int subCategoryID) {
        SubCategoryId = subCategoryID;
    }

//    public int getVendorId() {
//        return VendorId;
//    }
public int getVendorId() {
    return VendorId;
}

    public void setVendorId(int vendorId) {
        VendorId = vendorId;
    }

    public int getPincode() {
        return Pincode;
    }

    public void setPincode(int pincode) {
        Pincode = pincode;
    }

    public int getPageIndex() {
        return PageIndex;
    }

    public void setPageIndex(int pageIndex) {
        PageIndex = pageIndex;
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

    public String getSubCategoryName() {
        return SubCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        SubCategoryName = subCategoryName;
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
}
