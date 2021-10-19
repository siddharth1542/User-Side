package com.example.Customer.dashboard_fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.adapter.Already_inserted_adapter;
import com.example.Customer.responseModel.AlreadyInsertedProductResponseModel;
import com.example.Customer.responseModel.GetSubCategoryResponse;
import com.example.Customer.responseModel.Vendor_product_request_Response_Model;
import com.example.Customer.viewModel.VenderViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Fragment_ProductRequest extends Fragment {


    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.insert_product_P_name)
    EditText product_insert_P_Name;

    @BindView(R.id.insert_product_P_price)
    EditText product_insert_P_price;

    @BindView(R.id.insert_product_P_remark)
    EditText product_insert_P_remark;

    @BindView(R.id.insert_product_save_btn)
    Button product_insert_btn;



    private VenderViewModel venderViewModel;
   private ArrayAdapter<String> adapter;
    private  ArrayList<String> arr;
    private HashMap<String,Integer> hm;
    Already_inserted_adapter already_inserted_adapter;



    @BindView(R.id.insert_product_rcv)
    RecyclerView already_inserted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__product_request, container, false);
        ButterKnife.bind(this,view);
        ((Dashboard) getActivity()).getSupportActionBar().setTitle("Product Request");

            //  spinner.getOnItemSelectedListener();
        String categoryid;
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("VENDOR_DATA", Context.MODE_PRIVATE);
        categoryid = sharedPreferences.getString("CATEGORY_ID",null);

        venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);
        get_data(categoryid);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        already_inserted.setLayoutManager(gridLayoutManager);

        getAllInsertedData();



        product_insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String P_name = product_insert_P_Name.getText().toString();
                String  remark = product_insert_P_remark.getText().toString();
                int sub_cat_id = hm.get(spinner.getSelectedItem().toString());
                float price = Float.parseFloat(product_insert_P_price.getText().toString());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  'T' HH:mm:ss z");
                String currentDateandTime = sdf.format(new Date());



                venderViewModel.Vendor_product_request(Integer.parseInt(sharedPreferences.getString("VENDOR_ID",null)),
                        Integer.parseInt(categoryid),sub_cat_id,P_name,remark,price,currentDateandTime).observe(getViewLifecycleOwner(), new Observer<Vendor_product_request_Response_Model>() {
                    @Override
                    public void onChanged(Vendor_product_request_Response_Model vendor_product_request_response_model) {
                        Toast.makeText(getActivity(), ""+vendor_product_request_response_model.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });





        return view;
    }

    private void getAllInsertedData() {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("VENDOR_DATA",Context.MODE_PRIVATE);
        int v_id = Integer.parseInt(sharedPreferences.getString("VENDOR_ID",null));
        String p_name = "";


        venderViewModel.getAlready_inserted(v_id,p_name,0).observe(getViewLifecycleOwner(), new Observer<AlreadyInsertedProductResponseModel>() {
            @Override
            public void onChanged(AlreadyInsertedProductResponseModel alreadyInsertedProductResponseModel) {

                if (alreadyInsertedProductResponseModel!= null)
                {
                    if (already_inserted.getVisibility() == View.INVISIBLE) {
                        already_inserted.setVisibility(View.VISIBLE);
                        already_inserted_adapter = new Already_inserted_adapter(alreadyInsertedProductResponseModel.getData(),getContext());
                        already_inserted_adapter.notifyDataSetChanged();
                        already_inserted.setAdapter(already_inserted_adapter);
                    } else {
                        already_inserted_adapter = new Already_inserted_adapter(alreadyInsertedProductResponseModel.getData(),getContext());
                        already_inserted_adapter.notifyDataSetChanged();
                        already_inserted.setAdapter(already_inserted_adapter);



                    }
                } else {
                    Toast.makeText(getActivity(), "List Empty!!", Toast.LENGTH_SHORT).show();
                   already_inserted.setVisibility(View.INVISIBLE);
                }

            }
        });




    }


    private void get_data(String id)
    {

        venderViewModel.getSubCategoryList(id).observe(getViewLifecycleOwner(), new Observer<GetSubCategoryResponse>() {
            @Override
            public void onChanged(GetSubCategoryResponse getSubCategoryResponse) {

                hm = new HashMap<>();
                arr = new ArrayList<>();

                for(int i =0;i<getSubCategoryResponse.getData().size();i++){

                    arr.add(getSubCategoryResponse.getData().get(i).getSubCategoryName());
                    hm.put(getSubCategoryResponse.getData().get(i).getSubCategoryName(),getSubCategoryResponse.getData().get(i).getId());
                    adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arr);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                }
            }
        });

    }
}