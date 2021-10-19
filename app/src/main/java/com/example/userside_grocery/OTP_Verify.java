package com.example.Customer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.Customer.responseModel.GetVendorIDResponseModel;
import com.example.Customer.responseModel.Reg_Mbile_response_Model;
import com.example.Customer.viewModel.VenderViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OTP_Verify extends AppCompatActivity {

    @BindView(R.id.btn_verify22)
    Button btn_verify;

    @BindView(R.id.edt_otp)
    EditText verify_otp_edt;

    @BindView(R.id.resend_otp)
    TextView resend_otp;

    @BindView(R.id.resend_visible)
    TextView resend_otp_v;

    VenderViewModel venderViewModel;



    Bundle b;

    String Otp;

    String Mobile_no,ShopName;
    private String vendorId ="",category_id="";
    private  String v_id;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ButterKnife.bind(this);


         b = getIntent().getExtras();
        Otp = b.getString("OTP");
        Mobile_no  = b.getString("MOBILE");



       venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);
        venderViewModel.get_vender(Mobile_no).observe(OTP_Verify.this, new Observer<GetVendorIDResponseModel>() {
            @Override
            public void onChanged(GetVendorIDResponseModel getVendorIDResponseModel) {

                if (getVendorIDResponseModel.getData().size() != 0) {
                    vendorId = getVendorIDResponseModel.getData().get(0).getVendorId() + "";
//jk
                }else{
                    Log.d("TAG6","there is problem");
                }
            }
        });


        Toast.makeText(this, "otp :"+Otp, Toast.LENGTH_SHORT).show();

        new CountDownTimer(60000, 1000) {
            int time=60;

            public void onTick(long millisUntilFinished) {
                resend_otp.setText(checkDigit(time));
                time--;
            }

            public void onFinish() {
                Otp = Integer.MAX_VALUE+"";
                resend_otp_v.setVisibility(View.INVISIBLE);
                resend_otp.setText("Resend_OTP");

                resend_otp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new_Otp();
                        resend_otp_v.setVisibility(View.VISIBLE);

                        new_Otp();

                    }
                });
            }

        }.start();

       btn_verify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {

               SharedPreferences sharedPreferences = getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
               SharedPreferences.Editor edtr = sharedPreferences.edit();
               edtr.putString("MOBILE",Mobile_no);
               edtr.commit();
               edtr.apply();

                 v_id= vendorId;

              String  otp = verify_otp_edt.getText().toString();
               if(otp.equals(Otp)){

                   if (v_id.length()==0)
                   {
                       Intent intent = new Intent(OTP_Verify.this,CategoryFirstSelection.class);
                       intent.putExtra("Mobile",Mobile_no);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);

                   }else
                       {
                           Intent intent = new Intent(OTP_Verify.this,Dashboard.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(intent);
                   }

               }else{

                 DoNotOpen();

               }

           }


       });
    }

//    private void saveVendorId(String vendorId) {
//
//        SharedPreferences sharedPreferences = getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
//        SharedPreferences.Editor edtr = sharedPreferences.edit();
//        edtr.putString("VENDOR_ID",vendorId);
//        edtr.commit();
//        edtr.apply();
//
//
//    }

    private void new_Otp() {


        String mob_in = b.getString("MOBILE");

        venderViewModel.send_mobile_otp(mob_in).observe(OTP_Verify.this, new Observer<Reg_Mbile_response_Model>() {
            @Override
            public void onChanged(Reg_Mbile_response_Model reg_mbile_response_model) {

                String s = String.valueOf(reg_mbile_response_model.getOTP());
                Otp = s;

                toastOtp(Otp);


            }
        });


        new CountDownTimer(90000, 1000) {
            int time=90;


            public void onTick(long millisUntilFinished) {
                resend_otp.setText(checkDigit(time));
                time--;
            }

            public void onFinish() {
                Otp = Integer.MAX_VALUE+"";
                resend_otp_v.setVisibility(View.INVISIBLE);
                resend_otp.setText("Resend_OTP");

                resend_otp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        new_Otp();
                        resend_otp_v.setVisibility(View.VISIBLE);

                    }
                });
            }

        }.start();






    }

    private void toastOtp(String otp) {

        Toast.makeText(this,"New OTP :"+otp,Toast.LENGTH_LONG).show();

    }

    private void DoNotOpen() {

        Toast.makeText(this, "Please Enter Valide OTP!", Toast.LENGTH_SHORT).show();



    }

    public String checkDigit(int number) {
        return number < 1 ? "0"+number : String.valueOf(number);
    }
}