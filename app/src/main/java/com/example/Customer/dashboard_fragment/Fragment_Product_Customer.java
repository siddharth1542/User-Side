package com.example.Customer.dashboard_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.adapter.ShowCustomerProductAdapter;
import com.example.Customer.responseModel.GetProductResponse;
import com.example.Customer.responseModel.GetSubCategoryResponse;
import com.example.Customer.responseModel.GetVendorProfileResponse;
import com.example.Customer.responseModel.SubCategoryList;
import com.example.Customer.viewModel.VenderViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;


public class Fragment_Product_Customer extends Fragment
{

   String vendorid;
   private  String cat_id;

    public Fragment_Product_Customer(String vendoRid,String CAT_id)
    {
          this.vendorid = vendoRid;
          this.cat_id = CAT_id;
    }


    @BindView(R.id.txtCustomerShopName)
    TextView C_ShopName;

    @BindView(R.id.txtCustomerCategory)
    TextView C_category;

    @BindView(R.id.img_Profile)
    CircleImageView img_Profile;

    private VenderViewModel venderViewModel;
    RecyclerView rcv_show;

    TabLayout tab_layout;
    String  index="0";



    private ArrayList<SubCategoryList> arr;
    private HashMap<String,String> hm_data;
    private ShowCustomerProductAdapter show_customer_product_adapter;
    private SwipeRefreshLayout swipeRefreshLayout;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__product__customer, container, false);
        ButterKnife.bind(this, view);

        venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);
        get_vendor_profile(vendorid);




        rcv_show = view.findViewById(R.id.rcv_CustomerProduct);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_show.setLayoutManager(gridLayoutManager);

        tab_layout = view.findViewById(R.id.tab_CustomerProduct);

        get_data(cat_id);


        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                Fragment_MyProduct fragment_home= new Fragment_MyProduct();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment_home);
                fragmentTransaction.commit();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });


        ((Dashboard) getActivity()).getSupportActionBar().setTitle("Product");

        return view;
    }






    private void get_data(String id)
    {
        venderViewModel.getSubCategoryList(id).observe(getViewLifecycleOwner(), new Observer<GetSubCategoryResponse>() {
            @Override
            public void onChanged(GetSubCategoryResponse getSubCategoryResponse)
            {
                if(getSubCategoryResponse != null)
                {
                    arr = new ArrayList<>();
                    hm_data = new HashMap<>();

                    arr.addAll(getSubCategoryResponse.getData());

                    for(int i=0;i<arr.size();i++){
                        tab_layout.addTab(tab_layout.newTab().setText(arr.get(i).getSubCategoryName()));
                        hm_data.put(arr.get(i).getSubCategoryName(),arr.get(i).getId()+"");
                    }


                    get_product_List(arr.get(0).getId()+"");

                    tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            String Sub_cat_id  =  hm_data.get(tab.getText());
                            get_product_List(Sub_cat_id);

                        }
                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                }
                else{
                    Toast.makeText(getActivity(), "Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void get_product_List(String sub_cat_id)
    {


        venderViewModel.get_Product(vendorid,sub_cat_id,index).observe(getViewLifecycleOwner(), new Observer<GetProductResponse>() {
            @Override
            public void onChanged(GetProductResponse getProductResponse) {
                if (getProductResponse != null)
                {
                    if (rcv_show.getVisibility() == View.INVISIBLE) {
                        rcv_show.setVisibility(View.VISIBLE);
                        show_customer_product_adapter = new ShowCustomerProductAdapter(getProductResponse.getData(),getContext(),venderViewModel,Integer.parseInt(vendorid));
                        show_customer_product_adapter.notifyDataSetChanged();
                        rcv_show.setAdapter(show_customer_product_adapter);
                    } else {
                        show_customer_product_adapter = new ShowCustomerProductAdapter(getProductResponse.getData(),getContext(), venderViewModel, Integer.parseInt(vendorid));
                        show_customer_product_adapter.notifyDataSetChanged();
                        rcv_show.setAdapter(show_customer_product_adapter);

                    }
                } else {
                    Toast.makeText(getActivity(), "List Empty!!", Toast.LENGTH_SHORT).show();
                    rcv_show.setVisibility(View.INVISIBLE);
                }
            }
        });


    }


    private void get_vendor_profile(String temp_vendor)
    {
        venderViewModel.get_profile(temp_vendor).observe(getViewLifecycleOwner(), new Observer<GetVendorProfileResponse>() {
            @Override
            public void onChanged(GetVendorProfileResponse getVendorProfileResponse) {
                if (getVendorProfileResponse != null)
                {
//                    edt_shopName.setText(getVendorProfileResponse.getData().get(0).getName());
//                    edt_ownerName.setText(getVendorProfileResponse.getData().get(0).getOwnerName());
//                    edt_whatsappNumber.setText(getVendorProfileResponse.getData().get(0).getWhatsappNumber());
//                    edt_email.setText(getVendorProfileResponse.getData().get(0).getEmail());
//                    check_online =getVendorProfileResponse.getData().get(0).getOnlinePayment();
//                    check_cash1 = getVendorProfileResponse.getData().get(0).getCashOnDelivery();

                     C_ShopName.setText(getVendorProfileResponse.getData().get(0).getName());
                     C_category.setText(getVendorProfileResponse.getData().get(0).getCategoryName());
                      cat_id = String.valueOf(getVendorProfileResponse.getData().get(0).getCategoryId());
                    Glide.with(getActivity())
                            .load(getVendorProfileResponse.getData().get(0).getProfilePic())
                            .placeholder(R.drawable.loding)
                            .centerCrop()
                            .into(img_Profile);

                }
                else
                {
                    Toast.makeText(getActivity(), "Check Internet Connectivity.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}