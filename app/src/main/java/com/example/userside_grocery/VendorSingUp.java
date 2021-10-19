package com.example.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.Customer.viewModel.VenderViewModel;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import butterknife.BindView;
import butterknife.ButterKnife;


public class VendorSingUp extends AppCompatActivity
{

    private VenderViewModel myViewModel;
    //RecyclerView recyclerView;
    String IPAddress="";

  //  CategoryGridViewMiniAdapter GridMiniAdapter;


    @BindView(R.id.Register)
    Button Register;

    @BindView(R.id.shopName)
    EditText  edt_shopName;

    @BindView(R.id.edt_email)
    EditText  edt_email;

    String shopName="",email="",mobile="",ipAddress="";
    int category_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_sing_up);

        ButterKnife.bind(this);




        IPAddress = getIPAddress(true);
        myViewModel = new ViewModelProvider(this).get(VenderViewModel.class);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
              shopName = edt_shopName.getText().toString();
              email = edt_email.getText().toString();


          //      myViewModel.vendor_signup(category_id,shopName,email,mobile,ipAddress);
              //  register(category_id,shopName,email,mobile,ipAddress);

                Intent intent = new Intent(VendorSingUp.this,Dashboard.class);
                startActivity(intent);

            }
        });
























//        recyclerView = findViewById(R.id.category_select);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(gridLayoutManager);

     //   get_data();

    }

    private void register(int category_id, String shopName, String email, String mobile, String ipAddress)
    {

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






























//    private void get_data()
//    {
//     myViewModel.getMovieList().observe(this, new Observer<GetCategoryResponseModel>() {
//         @Override
//         public void onChanged(GetCategoryResponseModel getCategoryResponseModel)
//         {
//             GridMiniAdapter = new CategoryGridViewMiniAdapter(getCategoryResponseModel.getData(),VendorSingUp.this);
//            recyclerView.setAdapter(GridMiniAdapter);
//            int id =  GridMiniAdapter.getCategoryId();
//
//         }
//     });
//
//    }



}