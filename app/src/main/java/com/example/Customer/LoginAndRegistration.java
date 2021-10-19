package com.example.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.Customer.responseModel.Reg_Mbile_response_Model;
import com.example.Customer.viewModel.VenderViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginAndRegistration extends AppCompatActivity {


    @BindView(R.id.edt_mob)
    EditText mob_no;


    @BindView(R.id.send_Otp21)
    Button send_otp;

    VenderViewModel venderViewModel;

    String otp = "";
    String mobile_no,ShopName="",vendorId="";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       ButterKnife.bind(this);

        venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);




        send_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {



                if(mob_no.getText().toString().length()==10)
                {
                    mobile_no  = mob_no.getText().toString();


                    Intent intent = new Intent(LoginAndRegistration.this, OTP_Verify.class);
                    venderViewModel.send_mobile_otp(mobile_no).observe(LoginAndRegistration.this, new Observer<Reg_Mbile_response_Model>() {
                        @Override
                        public void onChanged(Reg_Mbile_response_Model reg_mbile_response_model) {

                            String s = String.valueOf(reg_mbile_response_model.getOTP());
                            otp = s;
                            intent.putExtra("MOBILE", mobile_no);
                            intent.putExtra("OTP", s);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    });
                }else{
                    Toast.makeText(getApplicationContext(),"Please Enter Valide Mobile Number",Toast.LENGTH_SHORT).show();
                }

            }
        });





    }


}