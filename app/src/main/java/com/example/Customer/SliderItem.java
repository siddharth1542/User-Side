package com.example.Customer;

public class SliderItem {

    private int img;
    private String txt;

    SliderItem(int image,String txt){
        this.img = image;
        this.txt = txt;
    }


    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
