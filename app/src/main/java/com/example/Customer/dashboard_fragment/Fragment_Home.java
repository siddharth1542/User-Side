package com.example.Customer.dashboard_fragment;

import static android.content.Context.MODE_PRIVATE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Customer.CheckInternet;
import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.SubCategory_Tab;
import com.example.Customer.adapter.SlideViewAdapter;
import com.example.Customer.responseModel.GetVendorIDResponseModel;
import com.example.Customer.viewModel.VenderViewModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import net.gotev.uploadservice.MultipartUploadRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class Fragment_Home extends Fragment {

    private VenderViewModel myViewModel;
    RecyclerView recyclerView;

//    @BindView(R.id.viewpager)
//    ViewPager2 viewPager2;

    @BindView(R.id.mapping_product)
    LinearLayout mapping;
    // String id="10";

//    @BindView(R.id.logout)
//    LinearLayout logout;

//   @BindView(R.id.img_Profile)
//    ImageView img_Profile;

    @BindView(R.id.img_Profile)
    CircleImageView img_Profile;

    @BindView(R.id.btnUpload)
    Button img_Upload;

    @BindView(R.id.shopName)
    TextView txt_shopName;

    @BindView(R.id.category)
    TextView txt_category;

    @BindView(R.id.txt_open_time)
    TextView openTime;

    @BindView(R.id.txt_close_time)
    TextView closeTime;
    String vendor_id,mobile_no;


    @BindView(R.id.imageSlider)
    SliderView sliderView;

    @BindView(R.id.mProgressbar)
    ProgressBar mProgressBar;
    //private ArrayList<AllCategoryDataList> arr;

    SliderViewAdapter sliderViewAdapter;


    Dialog dialog;
    String imgPath="";

    ImageView dialog_imageView;
    ImageButton dialog_select,dialog_save;

    private static final int STORAGE_PERMISSION_CODE = 4655;
    private int PICK_IMAGE_REQUEST = 1;
    private Uri filepath;
    private Bitmap bitmap;
    public static final String UPLOAD_URL = "http://shopadmin.usddoller.org/adminpanel/WebService.asmx/VendorUpdateShopImg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);

        requestStoragePermission();

        mProgressBar.setVisibility(View.VISIBLE);
        callItSelf();

        mProgressBar.setVisibility(View.GONE);


        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.img_slider);
        images.add(R.drawable.add_image);
        images.add(R.drawable.ic_home);
        images.add(R.drawable.ic_profile);
        images.add(R.drawable.ic_category);

        sliderViewAdapter = new SlideViewAdapter(images);
        sliderView.setSliderAdapter(sliderViewAdapter);
        sliderView.setAutoCycle(true);
         sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
         sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);



        //+++++++++++++++++++++++++++++++++++++++++++++++

        img_Profile.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view)
            {

               // ShowFileChooser();
              openDialogue();
            }
        });


        mapping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), SubCategory_Tab.class);
                startActivity(intent);
            }
        });

        ((Dashboard) getActivity()).getSupportActionBar().setTitle("Dashboard");
        return view;

    }

    private void callItSelf()
    {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
        txt_shopName.setText(sharedPreferences.getString("SHOPNAME",null));
        txt_category.setText(sharedPreferences.getString("CATEGORYNAME",null));
        openTime.setText(sharedPreferences.getString("OPENTIME",null));
        closeTime.setText(sharedPreferences.getString("CLOSETIME",null));
        vendor_id = sharedPreferences.getString("VENDOR_ID",null);
        mobile_no = sharedPreferences.getString("MOBILE",null);


        myViewModel = new ViewModelProvider(this).get(VenderViewModel.class);

        myViewModel.get_vender(mobile_no).observe(getViewLifecycleOwner(), new Observer<GetVendorIDResponseModel>() {
            @Override
            public void onChanged(GetVendorIDResponseModel getVendorIDResponseModel)
            {

                if (getVendorIDResponseModel.getData().size() != 0)
                {
                    if(getVendorIDResponseModel==null){
                        return;
                    }else {

                        Glide.with(getActivity())
                                .load(getVendorIDResponseModel.getData().get(0).getProfilePic())
                                .placeholder(R.drawable.loding)
                                .centerCrop()
                                .into(img_Profile);
                        imgPath = getVendorIDResponseModel.getData().get(0).getProfilePic();
                        txt_shopName.setText(getVendorIDResponseModel.getData().get(0).getName());
                        txt_category.setText(getVendorIDResponseModel.getData().get(0).getCategoryName());
                        if (imgPath.isEmpty())
                        {
                            Intent intent = new Intent(getActivity().getApplicationContext(), CheckInternet.class);
                            startActivity(intent);
                        }
                    }


                } else {
                    Log.d("TAG6", "there is problem");
                }
            }
        });

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void openDialogue()
    {

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.activity_pic_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(true);

        dialog_select = dialog.findViewById(R.id.img_button);
        dialog_save = dialog.findViewById(R.id.save_img_button);
        dialog_imageView = dialog.findViewById(R.id.dialog_pic);

        Glide.with(getActivity())
                .load(imgPath)
                .placeholder(R.drawable.loding)
                .centerCrop()
                .into(dialog_imageView);

        dialog_select.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ShowFileChooser();
            }
        });

        dialog_save.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view)
            {
                img_Profile.setImageBitmap(bitmap);
                uploadImage();




                dialog.dismiss();

            }
        });

        dialog.show();

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
                    dialog_imageView.setImageBitmap(bitmap);
          //      img_Profile.setImageBitmap(bitmap);

           //     img_Upload.setVisibility(View.VISIBLE);
                   dialog_save.setVisibility(View.VISIBLE);
                //  tv.setText(filepath.toString());
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
        //  String name = editTextName.getText().toString().trim();
//      //  String email = editTextEmail.getText().toString().trim();
        String path = getPath(filepath);
        try {

            String uploadId = UUID.randomUUID().toString();
            new MultipartUploadRequest(getActivity(), uploadId, UPLOAD_URL)
                    .addParameter("vid", vendor_id)
                    .addFileToUpload(path, "op")
                    .setMaxRetries(2)
                    .startUpload();

            Toast.makeText(getActivity(), "Successfully Updated.", Toast.LENGTH_SHORT).show();

























        } catch (Exception ex) {
            Toast.makeText(getActivity(), ""+ex.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

}