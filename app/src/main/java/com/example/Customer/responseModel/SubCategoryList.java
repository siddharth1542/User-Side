package com.example.Customer.responseModel;

public class SubCategoryList
{
    int Id;
    String SubCategoryName;

    public SubCategoryList(int id, String subCategoryName) {
        Id = id;
        SubCategoryName = subCategoryName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getSubCategoryName() {
        return SubCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        SubCategoryName = subCategoryName;
    }
}
