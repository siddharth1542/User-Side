package com.example.Customer.responseModel;

public class VendorShowProductList
{
    String ProductName,Unit,ImagePath;
    Float UnitValue,Price;
    int ProductId;

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public Float getUnitValue() {
        return UnitValue;
    }

    public void setUnitValue(Float unitValue) {
        UnitValue = unitValue;
    }

    public Float getPrice() {
        return Price;
    }

    public void setPrice(Float price) {
        Price = price;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public VendorShowProductList(String productName, String unit, String imagePath, Float unitValue, Float price, int productId) {
        ProductName = productName;
        Unit = unit;
        ImagePath = imagePath;
        UnitValue = unitValue;
        Price = price;
        ProductId = productId;
    }
}
