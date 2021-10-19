package com.example.Customer.responseModel;

public class AllCategoryDataList {


        int  Id;
        String    CategoryName;
        String ImagePath;

    public AllCategoryDataList(int id, String categoryName, String imagePath) {
        Id = id;
        CategoryName = categoryName;
        ImagePath = imagePath;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }
}
