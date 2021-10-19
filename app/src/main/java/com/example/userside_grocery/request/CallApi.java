package com.example.Customer.request;

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

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CallApi {


    @GET(" api/Category/GetCategoryAll")
    Call<GetCategoryResponseModel> getAllCategory();


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @POST("api/Vendors/VendorInfoUpdate")
    Call<VendorInfoUpdateResponse> updateData(@Body JsonObject jsonBody);


    @POST("api/Vendors/VendorSignUp")
    Call<VendorSignUpResponse> signUpVendor(@Body JsonObject jsonBody);



    @POST("api/VendorProduct/VendorProductUpdate")
    Call<VendorProductUpdateResponse> vendorProductUpdate(@Body JsonObject jsonBody);

    @POST("api/VendorProduct/VendorProductInsert")
    Call<SendAddedProduct> send_added_data_detail(@Body JsonObject jsonBody);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })

    @POST("api/Vendors/VendorSignUpOTP")
    Call<Reg_Mbile_response_Model> SendOtp_mobile(@Body JsonObject jsonBody);




    @GET("api/Vendors/GetVendorByVendorId")
    Call<GetVendorProfileResponse> get_vendor_data(
            @Query("VendorId") int vendor_id);


    @GET("api/Vendors/GetVendorByMobile")
    Call<GetVendorIDResponseModel> get_vendor(
            @Query("Mobile") long Mobile);



    @GET("api/SubCategory/GetSubCategoryQS")
    Call<GetSubCategoryResponse> get_subCategory(
            @Query("ScatVal") int category_id);


    @GET("api/Category/GetCategoryQS")
    Call<GetCategoryResponseModel> get_Category(
            @Query("catVal") int category_id);


    @GET("api/VendorProduct/VendorProductGet")
    Call<GetProductResponse> get_Product(
            @Query("VendorId") int vendor_id,
            @Query("SubCategoryId") int sub_categoryid,
            @Query("PageIndex") int index
    );

    @GET("api/VendorProduct/ProductsNotInVendor")
    Call<GetProductNotInList> get_ProductNot(
            @Query("VendorId") int vendor_id,
            @Query("SubCategoryId") int sub_categoryid,
            @Query("PageIndex") int index
    );


    @POST("api/VendorReqProduct/VendorReqProductInsert")
    Call<Vendor_product_request_Response_Model> vendorProductRequest(@Body JsonObject jsonBody);


    @POST("api/Vendors/VendorUpdateDeliveryCharges")
    Call<Add_Delivery_Response_Model> Add_delivery_Charges(@Body JsonObject jsonBody);


    @GET("api/VendorReqProduct/VendorReqProductGet")
    Call<AlreadyInsertedProductResponseModel> get_already_inserted_data(
            @Query("VendorId") int vendor_id,
            @Query("ProductName") String product_name,
            @Query("PageIndex") int page_index );



     @POST("api/Vendors/VendorUpdateBankAndKYCDetails")
     Call<BankResponseModel> SendBankDetails(@Body JsonObject jsonBody);

     //################################################################################################

    @POST("api/Customers/CategoryWiseVendors")
    Call<VendorDetailsResponse> AllVendorDetails(@Body JsonObject jsonBody);

    @POST("api/Customers/CategoryWiseVendors")
    Call<VendorDetailsResponse> AllVendorDetailsNOPara(@Body JsonObject jsonBody);


}


//                for (int i = 0; i < arr.size(); i++)
//                {
//                    //latitudee 25
//                    //longitude 82
//
//                    double vendor_latitude = Double.parseDouble(vendorDetailsResponse.getData().get(i).getLatitude());
//                    double vendor_longitude = Double.parseDouble(vendorDetailsResponse.getData().get(i).getLongitude());
//
//                    double earthRadius = 3958.75;
//                    double dLat = Math.toRadians(vendor_latitude-customer_latitude);
//                    double dLng = Math.toRadians(vendor_longitude-customer_longitude);
//                    double a = Math.sin(dLat/2) *
//                               Math.sin(dLat/2) +
//                               Math.cos(Math.toRadians(customer_latitude)) *
//                               Math.cos(Math.toRadians(vendor_latitude)) *
//                               Math.sin(dLng/2) *
//                               Math.sin(dLng/2);
//                    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//                    double dist = earthRadius * c;
//
//
//                    if (dist == 4)
//                    {
//                        SelectVendor.add(vendorDetailsResponse.getData().get(i));
//                    }
//                }
