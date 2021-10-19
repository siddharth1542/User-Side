package com.example.Customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.Customer.adapter.CategoryFirstSelectAdapter;
import com.example.Customer.responseModel.GetCategoryResponseModel;
import com.example.Customer.viewModel.VenderViewModel;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFirstSelection extends AppCompatActivity {

    Bundle b;

    String mobile_no, ShopName, cat_id, email = "", IPAddress ="";

    @BindView(R.id.edt_shopName)
    EditText shopName;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arr;
    private HashMap<String, Integer> hm;

    @BindView(R.id.spiner_cat)
    Spinner spinner_cat;

    private VenderViewModel myViewModel;

    GetCategoryResponseModel getCategoryResponseModel;


    CategoryFirstSelectAdapter GridMiniAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_first_selection);

        ButterKnife.bind(this);


        IPAddress = getIPAddress(true);

        SharedPreferences sharedPreference = getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
        mobile_no= sharedPreference.getString("MOBILE", null);




        myViewModel = new ViewModelProvider(this).get(VenderViewModel.class);


        setSpinner();


        //  get_data();
//==========================================================================================================

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                if (shopName.getText().toString().isEmpty())
                {
                    shopName.setError("Please Enter Shop Name");
                    shopName.requestFocus();
                    return;
                }
                ShopName = shopName.getText().toString();

                cat_id = String.valueOf(hm.get(spinner_cat.getSelectedItem().toString()));
                myViewModel.vendor_signup(cat_id,ShopName,mobile_no,email,IPAddress);



                Intent intent = new Intent(view.getContext(), Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                view.getContext().startActivity(intent);
            }
        });



//==========================================================================================================



    }

    public static String getIPAddress(boolean useIPv4) {
        try {
            for (final Enumeration<NetworkInterface> enumerationNetworkInterface = NetworkInterface.getNetworkInterfaces(); enumerationNetworkInterface.hasMoreElements();) {
                final NetworkInterface networkInterface = enumerationNetworkInterface.nextElement();
                for (Enumeration<InetAddress> enumerationInetAddress = networkInterface.getInetAddresses(); enumerationInetAddress.hasMoreElements();) {
                    final InetAddress inetAddress = enumerationInetAddress.nextElement();
                    final String ipAddress = inetAddress.getHostAddress();
                    if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return ipAddress;
                    }
                }
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setSpinner() {
        hm = new HashMap<>();
        arr = new ArrayList<>();

        myViewModel.getMovieList().observe(this, new Observer<GetCategoryResponseModel>()
        {
            @Override
            public void onChanged(GetCategoryResponseModel getCategoryResponseModel)
            {
                for (int i = 0; i < getCategoryResponseModel.getData().size(); i++) {

                    arr.add(getCategoryResponseModel.getData().get(i).getCategoryName());
                    hm.put(getCategoryResponseModel.getData().get(i).getCategoryName(), getCategoryResponseModel.getData().get(i).getId());
                    adapter = new ArrayAdapter<String>(CategoryFirstSelection.this, android.R.layout.simple_spinner_item, arr);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner_cat.setAdapter(adapter);
                }
            }
        });


//    private void get_data()
//    {
////        myViewModel.getMovieList().observe(this, new Observer<GetCategoryResponseModel>() {
////            @Override
////            public void onChanged(GetCategoryResponseModel getCategoryResponseModel)
////            {
////                GridMiniAdapter = new CategoryFirstSelectAdapter(getCategoryResponseModel.getData(),CategoryFirstSelection.this);
////                recyclerView.setAdapter(GridMiniAdapter);
////
////               recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
////                   @Override
////                   public void onClick(View view, int position)
////                   {
////
////
////                       myViewModel.vendor_signup(cat_id,ShopName,mobile_no,email,IPAddress);
////
////
////                   }
////
////                   @Override
////                   public void onLongClick(View view, int position)
////                   {
////
////                   }
////               }));
////
////
////
////            }
////        });
//
//    }
    }
}