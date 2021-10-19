package com.example.Customer.responseModel;

public class AlreadyInsertedList {




    int VendorId;
    int ProductId;
     String SubCategoryName;
     String Remarks;
     String ProductName;
     float Unit;
     float UnitValue;
     float Price;
     int vrpStatus;//": 1-pending,0-done
     String date;//": "2021-10-06T16:07:05.44",


    public AlreadyInsertedList(int vendorId, int productId, String subCategoryName, String remarks, String productName, float unit, float unitValue, float price, int vrpStatus, String date) {
        VendorId = vendorId;
        ProductId = productId;
        SubCategoryName = subCategoryName;
        Remarks = remarks;
        ProductName = productName;
        Unit = unit;
        UnitValue = unitValue;
        Price = price;
        this.vrpStatus = vrpStatus;
        this.date = date;
    }


    public int getVendorId() {
        return VendorId;
    }

    public void setVendorId(int vendorId) {
        VendorId = vendorId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getSubCategoryName() {
        return SubCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        SubCategoryName = subCategoryName;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public float getUnit() {
        return Unit;
    }

    public void setUnit(float unit) {
        Unit = unit;
    }

    public float getUnitValue() {
        return UnitValue;
    }

    public void setUnitValue(float unitValue) {
        UnitValue = unitValue;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getVrpStatus() {
        return vrpStatus;
    }

    public void setVrpStatus(int vrpStatus) {
        this.vrpStatus = vrpStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
