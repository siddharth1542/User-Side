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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.Customer.Dashboard;
import com.example.Customer.R;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.time.LocalDateTime;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Fragment_KYC extends Fragment
{


    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST1 = 1;
    private int PICK_IMAGE_REQUEST2 = 2;
    private int PICK_IMAGE_REQUEST3 = 3;
    private Uri filepath, PANFilePath,AadhaarFrontFilepath,AadhaarBackFilepath;
    private Bitmap bitmap;
    public static final String UPLOAD_URL = "http://shopadmin.usddoller.org/adminpanel/WebService.asmx/VendorKycUpdateImg2T";

    @BindView(R.id.edt_PANName)
    EditText edt_PANName;

    @BindView(R.id.PANNumber)
    EditText edt_PANNumber;

    @BindView(R.id.edt_AadhaarName)
    EditText edt_AadhaarName;

    @BindView(R.id.edt_AadhaarNumber)
    EditText edt_AadhaarNumber;

    @BindView(R.id.btn_KYCSubmit)
    Button KYC_submit;

    @BindView(R.id.PAN_Browse)
    Button PAN_Browse;

    @BindView(R.id.Aadhaar_FrontBrowse)
    Button Aadhaar_FrontBrowse;

    @BindView(R.id.Aadhaar_BackBrowse)
    Button Aadhaar_BackBrowse;

    @BindView(R.id.txtPAN)
    TextView txtPAN;

    @BindView(R.id.txtFrontAadhaar)
    TextView txtFrontAadhaar;

    @BindView(R.id.txtBackAadhaar)
    TextView txtBackAadhaar;


    String vendor_id,IPAddress,PAN_NUM,PAN_NAME,AadhaarName,AadhaarNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_k_y_c, container, false);
        ButterKnife.bind(this,view);
        requestStoragePermission();


        SharedPreferences sharedPreferences = getContext().getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
        IPAddress = sharedPreferences.getString("IPADDRESS",null);
        vendor_id = sharedPreferences.getString("VENDOR_ID",null);

        PAN_Browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ShowFileChooserPAN();
            }
        });

        Aadhaar_FrontBrowse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ShowFileChooserFrontAadhaar();
            }
        });

        Aadhaar_BackBrowse.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ShowFileChooserAadhaarBack();
            }
        });



        KYC_submit.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v)
            {
                if (edt_PANName.getText().toString().isEmpty())
                {
                    edt_PANName.setError("Please Enter PAN Name");
                    edt_PANName.requestFocus();
                    return;
                }
                PAN_NAME = edt_PANName.getText().toString();

                if (edt_PANNumber.getText().toString().isEmpty())
                {
                    edt_PANNumber.setError("Please Enter PAN Number");
                    edt_PANNumber.requestFocus();
                    return;
                }
                PAN_NUM = edt_PANNumber.getText().toString();

                if (edt_AadhaarName.getText().toString().isEmpty())
                {
                    edt_AadhaarName.setError("Please Enter Aadhaar Name");
                    edt_AadhaarName.requestFocus();
                    return;
                }
                AadhaarName = edt_AadhaarName.getText().toString();

                if (edt_AadhaarNumber.getText().toString().isEmpty())
                {
                    edt_AadhaarNumber.setError("Please Enter Aadhaar Number");
                    edt_AadhaarNumber.requestFocus();
                    return;
                }
                AadhaarNumber = edt_AadhaarNumber.getText().toString();


                if (txtPAN.getText().toString().isEmpty())
                {
                    txtPAN.setText("Please Upload PAN");
                    txtPAN.setTextColor(Integer.parseInt("#FF0000"));
                    txtPAN.requestFocus();
                    return;
                }
                if (txtFrontAadhaar.getText().toString().isEmpty())
                {
                    txtFrontAadhaar.setText("Upload Aadhaar Front Image");
                    txtFrontAadhaar.setTextColor(Integer.parseInt("#FF0000"));
                    txtFrontAadhaar.requestFocus();
                    return;
                }
                if (txtBackAadhaar.getText().toString().isEmpty())
                {
                    txtBackAadhaar.setText("Upload Aadhaar Back Image");
                    txtBackAadhaar.setTextColor(Integer.parseInt("#FF0000"));
                    txtBackAadhaar.requestFocus();
                    return;
                }

                  uploadImagePAN();
                  uploadImageAadhaarFront();
                  uploadImageAadhaarBack();

            }
        });



        ((Dashboard) getActivity()).getSupportActionBar().setTitle("KYC");
        return view;
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





    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST1 && data != null && data.getData() != null)
        {

            PANFilePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), PANFilePath);
                //     img_Profile.setImageBitmap(bitmap);
                //    img_Upload.setVisibility(View.VISIBLE);
                txtPAN.setText("Image Selected");
                // Toast.makeText(getApplicationContext(),getPath(filepath),Toast.LENGTH_LONG).show();
            } catch (Exception ex) {

            }
        }
        if (requestCode == PICK_IMAGE_REQUEST2 && data != null && data.getData() != null)
        {

            AadhaarFrontFilepath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), AadhaarFrontFilepath);
                //     img_Profile.setImageBitmap(bitmap);
                //    img_Upload.setVisibility(View.VISIBLE);

                    txtFrontAadhaar.setText("Image Selected");

                // Toast.makeText(getApplicationContext(),getPath(filepath),Toast.LENGTH_LONG).show();
            } catch (Exception ex) {

            }
        }

        if (requestCode == PICK_IMAGE_REQUEST3 && data != null && data.getData() != null)
        {

            AadhaarBackFilepath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), AadhaarBackFilepath);
                //     img_Profile.setImageBitmap(bitmap);
                //    img_Upload.setVisibility(View.VISIBLE);
                txtBackAadhaar.setText("Image Selected");
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





    //============================================PAN========================================

    private void ShowFileChooserPAN() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST1);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadImagePAN()
    {
        String PANpath = getPath(PANFilePath);
        try {
            //String uploadId = UUID.randomUUID().toString();
            String uploadId = String.valueOf(LocalDateTime.now());
            new MultipartUploadRequest(getActivity(), uploadId,
                    UPLOAD_URL)
                    .addParameter("vid", vendor_id)
                    .addParameter("IPAddress",IPAddress)
                    .addParameter("doctype", "2")
                    .addFileToUpload(PANpath,"op")
                    .addParameter("docvalue", PAN_NUM)
                    .addParameter("docholdername", PAN_NAME)
                    .setMaxRetries(6)
                    .startUpload();

            Toast.makeText(getActivity(), "Successfully Uploaded!! ", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(getActivity(), ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    //===========================================================Aadhaar Front====================================
    private void ShowFileChooserFrontAadhaar() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST2);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadImageAadhaarFront() {

        String AadhaarFrontPath = getPath(AadhaarFrontFilepath);
        try {
            //String uploadId = UUID.randomUUID().toString();
            String uploadId = String.valueOf(LocalDateTime.now());
            new MultipartUploadRequest(getActivity(), uploadId,
                    UPLOAD_URL)
                    .addParameter("vid", vendor_id)
                    .addParameter("IPAddress",IPAddress)
                    .addParameter("doctype", "3")
                    .addFileToUpload(AadhaarFrontPath,"op")
                    .addParameter("docvalue", AadhaarNumber)
                    .addParameter("docholdername", AadhaarName)
//                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(6)
                    .startUpload();

            Toast.makeText(getActivity(), "Successfully Uploaded!! ", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(getActivity(), ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    //===========================================================Aadhaar Back====================================

    private void ShowFileChooserAadhaarBack() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST3);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void uploadImageAadhaarBack() {

        String AadhaarBackPath = getPath(AadhaarBackFilepath);
        try {
            //String uploadId = UUID.randomUUID().toString();
            String uploadId = String.valueOf(LocalDateTime.now());
            new MultipartUploadRequest(getActivity(), uploadId,
                    UPLOAD_URL)
                    .addParameter("vid", vendor_id)
                    .addParameter("IPAddress",IPAddress)
                    .addParameter("doctype", "4")
                    .addFileToUpload(AadhaarBackPath,"op")
                    .addParameter("docvalue", AadhaarNumber)
                    .addParameter("docholdername", AadhaarName)
//                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(6)
                    .startUpload();

            Toast.makeText(getActivity(), "Successfully Uploaded!! ", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(getActivity(), ""+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}