package com.example.Customer.responseModel;

public class VendorShowProductNotInList {

    String ProductName,ProductUnit,ProductLogo;
    int ProductId,ProductPrice;
    Float ProductUnitValue;

    public VendorShowProductNotInList(String productName, String productUnit, String productLogo, int productId, int productPrice, Float productUnitValue) {
        ProductName = productName;
        ProductUnit = productUnit;
        ProductLogo = productLogo;
        ProductId = productId;
        ProductPrice = productPrice;
        ProductUnitValue = productUnitValue;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductUnit() {
        return ProductUnit;
    }

    public void setProductUnit(String productUnit) {
        ProductUnit = productUnit;
    }

    public String getProductLogo() {
        return ProductLogo;
    }

    public void setProductLogo(String productLogo) {
        ProductLogo = productLogo;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int productPrice) {
        ProductPrice = productPrice;
    }

    public Float getProductUnitValue() {
        return ProductUnitValue;
    }

    public void setProductUnitValue(Float productUnitValue) {
        ProductUnitValue = productUnitValue;
    }
}
