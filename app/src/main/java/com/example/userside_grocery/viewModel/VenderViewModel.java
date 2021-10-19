package com.example.Customer.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Customer.repository.MyRepository;
import com.example.Customer.responseModel.Add_Delivery_Response_Model;
import com.example.Customer.responseModel.AlreadyInsertedProductResponseModel;
import com.example.Customer.responseModel.BankResponseModel;
import com.example.Customer.responseModel.GetCategoryResponseModel;
import com.example.Customer.responseModel.GetProductNotInList;
import com.example.Customer.responseModel.GetProductResponse;
import com.example.Customer.responseModel.GetSubCategoryResponse;
import com.example.Customer.responseModel.GetVendorIDResponseModel;
import com.example.Customer.responseModel.GetVendorProfileResponse;
import com.example.Customer.responseModel.Reg_Mbile_response_Model;
import com.example.Customer.responseModel.SendAddedProduct;
import com.example.Customer.responseModel.VendorDetailsResponse;
import com.example.Customer.responseModel.Vendor_product_request_Response_Model;

public class VenderViewModel extends ViewModel {

       private  MyRepository myRepository;


      MutableLiveData<GetCategoryResponseModel> apiData;


       public VenderViewModel(){

        myRepository = new MyRepository();

       }

       public LiveData<GetCategoryResponseModel> getMovieList()
       {

         return myRepository.call_all_cat();
       }

    public LiveData<GetCategoryResponseModel> get_Category(String category_id)
    {
        return myRepository.call_Category(category_id);
    }

    public LiveData<GetSubCategoryResponse> getSubCategoryList(String category_id)
    {
        return myRepository.call_subCategory(category_id);
    }

    public LiveData<GetProductResponse> get_Product(String vendorid, String subcategory,String index)
    {
        return myRepository.call_product(vendorid,subcategory,index);
    }
//#######################################################################################
    public LiveData<VendorDetailsResponse> get_shortvendorDetails(String catid,String pageindex)
    {
        return myRepository.shortvendorDetails(catid,pageindex);
    }


    public LiveData<VendorDetailsResponse> get_shortvendorDetailsNoPara(String pageindex)
    {
        return myRepository.shortvendorDetailsNoPara(pageindex);
    }
















    public LiveData<BankResponseModel> sendBank(String vendorid,String AccountHolderName,String BankAccountNumber,String IFSC,String status,String IPAddress)
    {
        return myRepository.sendBankData(vendorid,AccountHolderName,BankAccountNumber,IFSC,status,IPAddress);
    }

    public LiveData<AlreadyInsertedProductResponseModel> getAlready_inserted(int v_id, String P_name, int page_index){
        return myRepository.already_inserted_product(v_id,P_name,page_index);
    }

    public LiveData<GetProductNotInList> get_Product_Not(String vendorid, String subcategory, String index)
    {
        return myRepository.call_product_Not_In_List(vendorid,subcategory,index);
    }


       public LiveData<GetVendorProfileResponse> get_profile(String vendorid)
       {

           return myRepository.get_Vendor_profile(vendorid);
       }


    public LiveData<Vendor_product_request_Response_Model> Vendor_product_request(int v_id, int C_id, int S_id, String P_name, String remark, float Price, String date){

        return myRepository.Vender_Product_request(v_id,C_id,S_id,P_name,remark,Price,date);


    }



    public LiveData<GetVendorIDResponseModel> get_vender(String mob_no)
    {
        return myRepository.getVender(mob_no);
    }


    public LiveData<Add_Delivery_Response_Model> add_delivery_charges(String V_id, float distance, float v_delivery_charge, String Ip_address){

        return myRepository.Add_delivery_Charges(V_id,distance,v_delivery_charge,Ip_address);

    }




     public String vendor_signup(String category_id,String Name,String Mobile,String Email, String IpAddress)
     {
         return myRepository.send_Vendor_SignUp(category_id,Name,Mobile,Email,IpAddress);
     }

    public String vendorUpdateProduct(String product_id,String price,String discountedPrice,String stock,String IpAddress)
    {
        return myRepository.send_VendorUpdateProduct(product_id,price,discountedPrice,stock,IpAddress);
    }




      public String post_profile(int vendor_id,
                               String Name,
                               String Email,
                               String OpenTime,
                               String CloseTime,
                               boolean CashOnDelivery,
                               boolean OnlinePayment,
                               String Longitude,
                               String Latitude,
                               String OwnerName,
                               String WhatsappNumber,
                               String Address,
                               int Pincode,
                               String IPAddress)
      {
       return myRepository.send_Vendor_Update(vendor_id, Name, Email, OpenTime, CloseTime, CashOnDelivery, OnlinePayment, Longitude, Latitude, OwnerName, WhatsappNumber, Address, Pincode, IPAddress);

      }

       public LiveData<Reg_Mbile_response_Model> send_mobile_otp(String mob_no){

           return myRepository.send_Mobile_otp(mob_no);

       }

       public LiveData<SendAddedProduct> send_product_Added_detail(int vendorId,int ProductId,float Price, float DiscountedPrice, int Stock, boolean Status,
                                                                   boolean StockStatus,String IpAddress){

           return myRepository.send_added_product_detail(vendorId,ProductId,Price,DiscountedPrice,Stock, Status
           ,StockStatus,IpAddress);




       }







}
