package com.example.Customer.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.Customer.request.ApiService;
import com.example.Customer.request.CallApi;
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
import com.example.Customer.responseModel.VendorInfoUpdateResponse;
import com.example.Customer.responseModel.VendorProductUpdateResponse;
import com.example.Customer.responseModel.VendorSignUpResponse;
import com.example.Customer.responseModel.Vendor_product_request_Response_Model;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepository {

    private CallApi apiService;
   private String a="";
     private String otp = "";


    public MyRepository(){

        apiService = ApiService.getRetrofit().create(CallApi.class);

    }


       public LiveData<GetCategoryResponseModel> call_all_cat()
       {
//
            MutableLiveData<GetCategoryResponseModel> data = new MutableLiveData<>();

           apiService.getAllCategory().enqueue(new Callback<GetCategoryResponseModel>() {
               @Override
               public void onResponse(Call<GetCategoryResponseModel> call,@NonNull Response<GetCategoryResponseModel> response) {
                   data.setValue(response.body());
               }

               @Override
               public void onFailure(Call<GetCategoryResponseModel> call, Throwable t) {
                   data.setValue(null);
               }
           });

           return data;
//
   }

    public LiveData<GetCategoryResponseModel> call_Category(String categoryid)
    {
        int temp1=Integer.parseInt(categoryid);
        MutableLiveData<GetCategoryResponseModel> data1 = new MutableLiveData<>();
        apiService.get_Category(temp1).enqueue(new Callback<GetCategoryResponseModel>()
        {

            @Override
            public void onResponse(Call<GetCategoryResponseModel> call, Response<GetCategoryResponseModel> response)
            {
                data1.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GetCategoryResponseModel> call, Throwable t)
            {
                data1.setValue(null);
            }
        });
        return data1;
    }

    public LiveData<AlreadyInsertedProductResponseModel> already_inserted_product(int v_id, String P_name, int page_index){
        MutableLiveData<AlreadyInsertedProductResponseModel> Data9 = new MutableLiveData<>();

        apiService.get_already_inserted_data(v_id,P_name,page_index).enqueue(new Callback<AlreadyInsertedProductResponseModel>() {
            @Override
            public void onResponse(Call<AlreadyInsertedProductResponseModel> call, Response<AlreadyInsertedProductResponseModel> response) {

                if(response.body().isSuccess()){

                    Data9.setValue(response.body());


                }else{


                    Log.e("TAGG","problem :"+response.body().getMessage());


                }



            }

            @Override
            public void onFailure(Call<AlreadyInsertedProductResponseModel> call, Throwable t) {
                Log.e("TAGG",t.getMessage());
            }
        });

        return Data9;

    }





    public LiveData<GetSubCategoryResponse> call_subCategory(String categoryid)
   {
       int temp1=Integer.parseInt(categoryid);
       MutableLiveData<GetSubCategoryResponse> data1 = new MutableLiveData<>();
        apiService.get_subCategory(temp1).enqueue(new Callback<GetSubCategoryResponse>()
        {


            @Override
            public void onResponse(Call<GetSubCategoryResponse> call, Response<GetSubCategoryResponse> response)
            {
                data1.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GetSubCategoryResponse> call, Throwable t)
            {
                data1.setValue(null);
            }
        });
       return data1;
   }

  public LiveData<GetProductResponse> call_product(String vendorid, String subcategory,String index)
  {
      int tempVendor=Integer.parseInt(vendorid);
      int tempSubcategory=Integer.parseInt(subcategory);
      int tempIndex=Integer.parseInt(index);
      MutableLiveData<GetProductResponse> data = new MutableLiveData<>();
      apiService.get_Product(tempVendor,tempSubcategory,tempIndex).enqueue(new Callback<GetProductResponse>() {
          @Override
          public void onResponse(Call<GetProductResponse> call, Response<GetProductResponse> response)
          {
              if(response.body().isSuccess())
              {
                  data.setValue(response.body());
              }else{
                  data.setValue(null);
                  Log.d("TAG4","ERROR :"+response.body().getMessage());
              }
          }
          @Override
          public void onFailure(Call<GetProductResponse> call, Throwable t) {

              Log.d("TAG8",""+t.getMessage());
          }
      });
      return data;
  }

    public LiveData<GetProductNotInList> call_product_Not_In_List(String vendorid, String subcategory, String index)
    {
        int tempVendor=Integer.parseInt(vendorid);
        int tempSubcategory=Integer.parseInt(subcategory);
        int tempIndex=Integer.parseInt(index);
        MutableLiveData<GetProductNotInList> data4 = new MutableLiveData<>();
        apiService.get_ProductNot(tempVendor,tempSubcategory,tempIndex).enqueue(new Callback<GetProductNotInList>()
        {
            @Override
            public void onResponse(Call<GetProductNotInList> call, Response<GetProductNotInList> response)
            {
                if(response.body().isSuccess())
                {
                    data4.setValue(response.body());
                }else{
                    data4.setValue(null);
                    Log.d("TAG4","ERROR :"+response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<GetProductNotInList> call, Throwable t) {

                Log.d("TAG4","ERROR :"+t.getMessage());
            }
        });

        return data4;
    }

      public LiveData<GetVendorProfileResponse> get_Vendor_profile(String vendorid)
      {
          int temp=Integer.parseInt(vendorid);
          MutableLiveData<GetVendorProfileResponse> data = new MutableLiveData<>();
          apiService.get_vendor_data(temp)
                  .enqueue(new Callback<GetVendorProfileResponse>() {
              @Override
              public void onResponse(Call<GetVendorProfileResponse> call, Response<GetVendorProfileResponse> response)
              {
                  data.setValue(response.body());
              }

              @Override
              public void onFailure(Call<GetVendorProfileResponse> call, Throwable t)
              {

              }
          });
          return data;
      }


      public String send_VendorUpdateProduct(String product_id,String price,String discountedPrice,String stock,String IpAddress)
      {
          int ProductID=Integer.parseInt(product_id),Stock=Integer.parseInt(stock);
          float Price=Float.parseFloat(price),DiscountedPrice=Float.parseFloat(discountedPrice);
          String IPAddress=IpAddress;
          apiService.vendorProductUpdate(ApiVendorProductUpdate(ProductID,Price,DiscountedPrice,Stock,IPAddress))
                  .enqueue(new Callback<VendorProductUpdateResponse>() {
                      @Override
                      public void onResponse(Call<VendorProductUpdateResponse> call, Response<VendorProductUpdateResponse> response)
                      {

                      }
                      @Override
                      public void onFailure(Call<VendorProductUpdateResponse> call, Throwable t)
                      {

                      }
                  });
          return a;
      }






    public LiveData<Add_Delivery_Response_Model> Add_delivery_Charges(String V_id,float distance,float v_delivery_charge,String Ip_address){

        MutableLiveData<Add_Delivery_Response_Model> Data9 = new MutableLiveData<>();
        int temp = Integer.parseInt(V_id);
        apiService.Add_delivery_Charges(Api_json_delivey(temp,distance,v_delivery_charge,Ip_address)).enqueue(new Callback<Add_Delivery_Response_Model>() {
            @Override
            public void onResponse(Call<Add_Delivery_Response_Model> call, Response<Add_Delivery_Response_Model> response) {

                if(response.body().isSuccess())
                {


                    Data9.setValue(response.body());
                    //  openDialogue();

                }
                else {

                    Log.d("TAG",""+response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<Add_Delivery_Response_Model> call, Throwable t) {
                Log.d("TAG",""+t.getMessage());
            }
        });



        return Data9;
    }




    public LiveData<Vendor_product_request_Response_Model> Vender_Product_request(int v_id, int C_id, int S_id, String P_name, String remark, float Price, String date)
    {
        MutableLiveData<Vendor_product_request_Response_Model> Data5 = new MutableLiveData<>();

        apiService.vendorProductRequest(Api_Vendor_product_request(v_id,C_id,S_id,P_name,remark,Price,date)).enqueue(new Callback<Vendor_product_request_Response_Model>() {
            @Override
            public void onResponse(Call<Vendor_product_request_Response_Model> call, Response<Vendor_product_request_Response_Model> response) {
                if(response.body().isSuccess())
                {


                    Data5.setValue(response.body());
                    //  openDialogue();

                }
                else {

                    Log.d("TAG",""+response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<Vendor_product_request_Response_Model> call, Throwable t) {
                Log.d("TAG",""+t.getMessage());
            }
        });

        return Data5;

    }











        public String send_Vendor_SignUp(String category_id,String Name,String Mobile,String Email, String IpAddress)
        {
            int temp = Integer.parseInt(category_id);
           apiService.signUpVendor(Api_Vendor_Signup(temp,Name,Mobile,Email,IpAddress))
                   .enqueue(new Callback<VendorSignUpResponse>() {
                       @Override
                       public void onResponse(Call<VendorSignUpResponse> call, Response<VendorSignUpResponse> response)
                       {

                           if(response.body().getSuccess())
                           {

                               String  temp = response.body().getMessage();
                               a = temp;
                               Log.d("TAG_DATA_INSERTED","Inserted");
                               //  openDialogue();

                           }
                           else {

                               Log.d("TAG",""+response.body().getMessage());
                           }


                       }

                       @Override
                       public void onFailure(Call<VendorSignUpResponse> call, Throwable t) {

                       }
                   });
           return a;

        }

       public String send_Vendor_Update(int Vendor_id,
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
           apiService.updateData(ApiJsonMap(Vendor_id,Name,Email,OpenTime,CloseTime,CashOnDelivery,OnlinePayment,Longitude,Latitude,OwnerName,WhatsappNumber,Address,Pincode,IPAddress))
                   .enqueue(new Callback<VendorInfoUpdateResponse>() {
               @Override
               public void onResponse(Call<VendorInfoUpdateResponse> call, Response<VendorInfoUpdateResponse> response)
               {

                   if(response.body().getSuccess())
                   {

                       String  temp = response.body().getMessage();
                       a = temp;
                       Log.d("TAG","Inserted");
                    //  openDialogue();



                   }
                   else {

                       Log.d("TAG",""+response.body().getMessage());
                   }



               }
               @Override
               public void onFailure(Call<VendorInfoUpdateResponse> call, Throwable t)
               {
                   Log.d("TAG","Error "+t.getMessage());
               }
           });
           return a;

       }



       public LiveData<GetVendorIDResponseModel> getVender(String mob_no)
       {
        long temp =Long.parseLong(mob_no);
        MutableLiveData<GetVendorIDResponseModel> data3 = new MutableLiveData<>();
        apiService.get_vendor(temp).enqueue(new Callback<GetVendorIDResponseModel>() {
            @Override
            public void onResponse(Call<GetVendorIDResponseModel> call, Response<GetVendorIDResponseModel> response) {

                if(response.body().isSuccess())
                {

                    Log.d("TAG2","message"+response.body().getMessage());

                    data3.setValue(response.body());

               }


            }

            @Override
            public void onFailure(Call<GetVendorIDResponseModel> call, Throwable t) {
              Log.d("TAG2","ERROR :"+t.getMessage());
            }
        });
             return data3;

       }

       public LiveData<BankResponseModel> sendBankData(String vendorid,String AccountHolderName,String BankAccountNumber,String IFSC,String status,String IPAddress)
       {
           int temp = Integer.parseInt(vendorid);
           MutableLiveData<BankResponseModel> data = new MutableLiveData<>();
           apiService.SendBankDetails(Api_sendBankData(temp,AccountHolderName,BankAccountNumber,IFSC,status,IPAddress))
                   .enqueue(new Callback<BankResponseModel>() {
                       @Override
                       public void onResponse(Call<BankResponseModel> call, Response<BankResponseModel> response)
                       {



                       }

                       @Override
                       public void onFailure(Call<BankResponseModel> call, Throwable t) {

                       }
                   });
           return  data;
       }
//##########################################################################################
  public LiveData<VendorDetailsResponse>  shortvendorDetails(String catid,String pageindex)
  {
      int page_temp = Integer.parseInt(pageindex);
      int page_catid = Integer.parseInt(catid);

      MutableLiveData<VendorDetailsResponse> data = new MutableLiveData<>();
      apiService.AllVendorDetails(Api_vendorDetails(page_catid,page_temp)).enqueue(new Callback<VendorDetailsResponse>() {
          @Override
          public void onResponse(Call<VendorDetailsResponse> call, Response<VendorDetailsResponse> response)
          {
              if(response.body().isSuccess()){

                  data.setValue(response.body());


              }else{

                  Log.i("TAG","MESGG :"+response.body().getMessage());
                  data.setValue(null);

              }
          }

          @Override
          public void onFailure(Call<VendorDetailsResponse> call, Throwable t)
          {

          }
      });
      return data;
  }

    public LiveData<VendorDetailsResponse>  shortvendorDetailsNoPara(String pageindex)
    {
        int page_temp = Integer.parseInt(pageindex);

        MutableLiveData<VendorDetailsResponse> data = new MutableLiveData<>();
        apiService.AllVendorDetailsNOPara(Api_vendorDetailsNoPara(page_temp)).enqueue(new Callback<VendorDetailsResponse>() {
            @Override
            public void onResponse(Call<VendorDetailsResponse> call, Response<VendorDetailsResponse> response)
            {
                if(response.body().isSuccess()){

                    data.setValue(response.body());


                }else{

                    Log.i("TAG","MESGG :"+response.body().getMessage());
                    data.setValue(null);

                }
            }

            @Override
            public void onFailure(Call<VendorDetailsResponse> call, Throwable t)
            {

            }
        });
        return data;
    }
    //##########################################################################################
       public LiveData<Reg_Mbile_response_Model> send_Mobile_otp(String mob_no){

           MutableLiveData<Reg_Mbile_response_Model> data2 = new MutableLiveData<>();

        apiService.SendOtp_mobile(Api_mobile_Map(mob_no)).enqueue(new Callback<Reg_Mbile_response_Model>() {
            @Override
            public void onResponse(Call<Reg_Mbile_response_Model> call, Response<Reg_Mbile_response_Model> response) {

                if(response.body().isSuccess()){

                   data2.setValue(response.body());


                }else{

                    Log.i("TAG","MESGG :"+response.body().getMessage());
                    data2.setValue(null);

                }

            }

            @Override
            public void onFailure(Call<Reg_Mbile_response_Model> call, Throwable t) {

                Log.d("TAG",""+t.getMessage());
            }
        });


        return data2;

       }


    public LiveData<SendAddedProduct> send_added_product_detail(int vendorId, int ProductId, float Price, float DiscountedPrice, int Stock, boolean Status,
                                                                boolean StockStatus, String IpAddress){

        MutableLiveData<SendAddedProduct> Data8 = new MutableLiveData<>();

        apiService.send_added_data_detail(Api_product_added_map(vendorId,ProductId,Price,DiscountedPrice,Stock,Status,StockStatus,IpAddress)).enqueue(new Callback<SendAddedProduct>() {
            @Override
            public void onResponse(Call<SendAddedProduct> call, Response<SendAddedProduct> response)
            {
                Data8.setValue(response.body());
            }
            @Override
            public void onFailure(Call<SendAddedProduct> call, Throwable t) {

                Log.d("TAG7","ERROR :"+t.getMessage());

            }
        });

        return Data8;
    }




    private JsonObject ApiJsonMap(int Vendor_id,
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


        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("VendorId", Vendor_id);
            jsonObj_.put("Name", Name);
            jsonObj_.put("Email", Email);
            jsonObj_.put("OpenTime", OpenTime);
            jsonObj_.put("CloseTime", CloseTime);
            jsonObj_.put("CashOnDelivery", CashOnDelivery);
            jsonObj_.put("OnlinePayment", OnlinePayment);
            jsonObj_.put("Longitude", Longitude);
            jsonObj_.put("Latitude", Latitude);
            jsonObj_.put("OwnerName", OwnerName);
            jsonObj_.put("WhatsappNumber", WhatsappNumber);
            jsonObj_.put("Address", Address);
            jsonObj_.put("Pincode", Pincode);
            jsonObj_.put("IPAddress", IPAddress);
            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return gsonObject;



    }




    private JsonObject Api_sendBankData(int vendorid,String AccountHolderName,String BankAccountNumber,String IFSC,String status,String IPAddress)
    {


        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("VendorId",vendorid);
            jsonObj_.put("AccountHolderName",AccountHolderName);
            jsonObj_.put("BankAccountNumber",BankAccountNumber);
            jsonObj_.put("BankIFCSCode",IFSC);
            jsonObj_.put("Status",status);
            jsonObj_.put("IPAddress",IPAddress);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return gsonObject;
    }




    private JsonObject Api_mobile_Map(String mob_no)
    {


        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("Mobile",mob_no);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return gsonObject;
  }









    private JsonObject Api_Vendor_Signup(int category_id,String Name,String Mobile,String Email, String IpAddress)
    {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("CategoryId",category_id);
            jsonObj_.put("Name",Name);
            jsonObj_.put("Mobile",Mobile);
            jsonObj_.put("Email",Email);
            jsonObj_.put("IPAddress",IpAddress);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return gsonObject;
    }

    private JsonObject ApiVendorProductUpdate(int ProductId,float Price,float DiscountedPrice,int Stock,String IPAddress)
    {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("ProductId",ProductId);
            jsonObj_.put("Price",Price);
            jsonObj_.put("DiscountedPrice",DiscountedPrice);
            jsonObj_.put("Stock",Stock);
            jsonObj_.put("IPAddress",IPAddress);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return gsonObject;
    }



    private JsonObject Api_product_added_map(int vendorId,int ProductId,float Price, float DiscountedPrice, int Stock,boolean Status,
                                             boolean StockStatus, String Ipaddress)
    {


        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("VendorId",vendorId);
            jsonObj_.put("ProductId",ProductId);
            jsonObj_.put("Price",Price);
            jsonObj_.put("DiscountedPrice",DiscountedPrice);
            jsonObj_.put("Stock",Stock);
            jsonObj_.put("Status",Status);
            jsonObj_.put("StockStatus",StockStatus);
            jsonObj_.put("IpAddress",Ipaddress);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return gsonObject;
    }

    private JsonObject Api_Vendor_product_request(int v_id,int C_id,int S_id,String P_name,String remark,float Price,String date)
    {


        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("VendorId",v_id);
            jsonObj_.put("CategoryId",C_id);
            jsonObj_.put("SubCategoryId",S_id);
            jsonObj_.put("ProductName",P_name);
            jsonObj_.put("Remarks",remark);
            jsonObj_.put("Price",Price);
            jsonObj_.put("RequestStatus",0);
            jsonObj_.put("date",date);
            jsonObj_.put("Records",0);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return gsonObject;
    }





    private JsonObject Api_json_delivey(int v_id,float dis,float d_chrg,String ipAddress)
    {


        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("VendorId",v_id);
            jsonObj_.put("Distance",dis);
            jsonObj_.put("VendorDeliveryCharges",d_chrg);
            jsonObj_.put("IPAddress",ipAddress);


            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return gsonObject;
    }

//##############################################################################################
private JsonObject Api_vendorDetails(int catid,int pageindex)
{

    JsonObject gsonObject = new JsonObject();
    try {
        JSONObject jsonObj_ = new JSONObject();
        jsonObj_.put("CategoryId",catid);
        jsonObj_.put("PageIndex",pageindex);

        JsonParser jsonParser = new JsonParser();
        gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

    } catch (JSONException e)
    {
        e.printStackTrace();
    }

    return gsonObject;
}









    private JsonObject Api_vendorDetailsNoPara(int pageindex)
    {

        JsonObject gsonObject = new JsonObject();
        try {
            JSONObject jsonObj_ = new JSONObject();
            jsonObj_.put("PageIndex",pageindex);

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(jsonObj_.toString());

        } catch (JSONException e)
        {
            e.printStackTrace();
        }

        return gsonObject;
    }

}
