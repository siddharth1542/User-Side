package com.example.Customer.dashboard_fragment;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.responseModel.BankResponseModel;
import com.example.Customer.viewModel.VenderViewModel;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.time.LocalDateTime;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_Bank extends Fragment
{

    private VenderViewModel venderViewModel;

    @BindView(R.id.edt_AccountName)
    EditText edt_accountName;

    @BindView(R.id.edt_AccountNumber)
    EditText edt_accountNumber;

    @BindView(R.id.edt_IFSC)
    EditText edt_IFSC;

    @BindView(R.id.Ac_Browse)
    Button btnBrowse;

    @BindView(R.id.Ac_Upload)
    Button btnUpload;

    @BindView(R.id.btnSubmit)
    Button btnSubmit;

    @BindView(R.id.chk_cheque)
    CheckBox chkCheque;

    @BindView(R.id.chk_pass)
    CheckBox chkPass;

    @BindView(R.id.check_error)
    TextView check_error;

    @BindView(R.id.txtPAss)
    TextView txtPAss;

    String docValue="";
    String vendor_id,IPAddress,AccountNumber,AccountName,IFSC,status;

    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri filepath;
    private Bitmap bitmap;
    public static final String UPLOAD_URL = "http://shopadmin.usddoller.org/adminpanel/WebService.asmx/VendorKycUpdateImg2T";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__bank, container, false);
        ButterKnife.bind(this,view);

        requestStoragePermission();

        chkPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                chkCheque.setChecked(false);
            }
        });
        chkCheque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                 chkPass.setChecked(false);
            }
        });
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
        IPAddress = sharedPreferences.getString("IPADDRESS",null);
        vendor_id = sharedPreferences.getString("VENDOR_ID",null);

        venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);



        btnBrowse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ShowFileChooser();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view)
            {
                if (chkPass.isChecked())
                 {
                    docValue = "Passbook";
                 }
                if (chkCheque.isChecked())
                {
                    docValue = "Cheque";
                }

                if (docValue.isEmpty())
                {
                    check_error.setError("Please Select Anyone");
                    check_error.requestFocus();
                    return;
                }
                uploadImage();

                if (edt_accountName.getText().toString().isEmpty())
                {
                    edt_accountName.setError("Please Enter Account Name");
                    edt_accountName.requestFocus();
                    return;
                }
                AccountName = edt_accountName.getText().toString();

                if (edt_accountNumber.getText().toString().isEmpty())
                {
                    edt_accountNumber.setError("Please Enter Account Number");
                    edt_accountNumber.requestFocus();
                    return;
                }
                AccountNumber = edt_accountNumber.getText().toString();

                if (edt_IFSC.getText().toString().isEmpty())
                {
                    edt_IFSC.setError("Please Enter IFSC Code");
                    edt_IFSC.requestFocus();
                    return;
                }
                IFSC = edt_IFSC.getText().toString();
                status="1";


                sendBankData(vendor_id,AccountName,AccountNumber,IFSC,status,IPAddress);

            }
        });




        ((Dashboard) getActivity()).getSupportActionBar().setTitle("Bank Details");
        return view;
    }

    private void sendBankData(String vendor_id, String accountName, String accountNumber, String ifsc, String status, String ipAddress)
    {
       venderViewModel.sendBank(vendor_id,accountName,accountNumber,ifsc,status,ipAddress).observe(getActivity(), new Observer<BankResponseModel>()
       {
           @Override
           public void onChanged(BankResponseModel bankResponseModel)
           {

               Toast.makeText(getActivity(), ""+bankResponseModel.getMessage(), Toast.LENGTH_SHORT).show();


           }
       });
    }


    private void requestStoragePermission() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getActivity(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(getActivity(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void ShowFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && data != null && data.getData() != null) {

            filepath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filepath);
           //     img_Profile.setImageBitmap(bitmap);
            //    img_Upload.setVisibility(View.VISIBLE);
                  txtPAss.setText("Image Selected");
                // Toast.makeText(getApplicationContext(),getPath(filepath),Toast.LENGTH_LONG).show();
            } catch (Exception ex) {

            }
        }
    }

    private String getPath(Uri uri) {

        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, MediaStore.Images.Media._ID + "=?", new String[]{document_id}, null
        );
        cursor.moveToFirst();
        @SuppressLint("Range")
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadImage() {

        String path = getPath(filepath);
        try {
            //String uploadId = UUID.randomUUID().toString();
            String uploadId = String.valueOf(LocalDateTime.now());
            new MultipartUploadRequest(getActivity(), uploadId,
                    UPLOAD_URL)
                    .addParameter("vid", vendor_id)
                    .addParameter("IPAddress",IPAddress)
                    .addParameter("doctype", "1")
                    .addFileToUpload(path,"op")
                    .addParameter("docvalue", docValue)
                    .addParameter("docholdername", "www")
                    .setMaxRetries(6)
                    .startUpload();

         //   Toast.makeText(getActivity(), "Successfully Uploaded!! ", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(getActivity(), ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}