package com.example.Customer.dashboard_fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.adapter.ShowMyProductAdapter;
import com.example.Customer.responseModel.GetProductResponse;
import com.example.Customer.responseModel.GetSubCategoryResponse;
import com.example.Customer.responseModel.SubCategoryList;
import com.example.Customer.viewModel.VenderViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment_MyProduct extends Fragment {

   private VenderViewModel venderViewModel;
   RecyclerView rcv_show;

    TabLayout tab_layout;
    String vendor_Id, index="0";


    private  String cat_id;
    private ArrayList<SubCategoryList> arr;
    private HashMap<String,String> hm_data;
    private  ShowMyProductAdapter show_product_adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


//
//    public Fragment_MyProduct(String cat_id,String Vender_Id){
//        this.cat_id = cat_id;
//        this.vender_Id = Vender_Id;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myproduct, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
        cat_id = sharedPreferences.getString("CATEGORY_ID",null);



        rcv_show = view.findViewById(R.id.rcv_show_Myproduct_frag);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_show.setLayoutManager(gridLayoutManager);

        tab_layout = view.findViewById(R.id.tab_layout_Myproduct);


        ((Dashboard) getActivity()).getSupportActionBar().setTitle("My Products");


        venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);
        get_data(cat_id);

       swipeRefreshLayout = view.findViewById(R.id.refreshLayout);

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
//        {
//            @Override
//            public void onRefresh()
//            {
//                Fragment_MyProduct fragment_home= new Fragment_MyProduct();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment_home);
//                fragmentTransaction.commit();
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                }, 1000);
//            }
//        });



        return view;
    }

    private void get_data(String id)
    {
        venderViewModel.getSubCategoryList(id).observe(getActivity(), new Observer<GetSubCategoryResponse>() {
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

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
        vendor_Id = sharedPreferences.getString("VENDOR_ID",null);

        venderViewModel.get_Product(vendor_Id,sub_cat_id,index).observe(getViewLifecycleOwner(), new Observer<GetProductResponse>() {
            @Override
            public void onChanged(GetProductResponse getProductResponse) {
                if (getProductResponse != null)
                {
                    if (rcv_show.getVisibility() == View.INVISIBLE) {
                        rcv_show.setVisibility(View.VISIBLE);
                        show_product_adapter = new ShowMyProductAdapter(getProductResponse.getData(),getContext(),venderViewModel,Integer.parseInt(vendor_Id));
                        show_product_adapter.notifyDataSetChanged();
                        rcv_show.setAdapter(show_product_adapter);
                    } else {
                        show_product_adapter = new ShowMyProductAdapter(getProductResponse.getData(),getContext(), venderViewModel, Integer.parseInt(vendor_Id));
                        show_product_adapter.notifyDataSetChanged();
                        rcv_show.setAdapter(show_product_adapter);

                    }
                } else {
                    Toast.makeText(getActivity(), "List Empty!!", Toast.LENGTH_SHORT).show();
                    rcv_show.setVisibility(View.INVISIBLE);
                }
            }
        });


    }




}