package com.example.Customer;

import static android.view.ViewGroup.OnClickListener;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.Customer.adapter.ShowAddProductAdapter;
import com.example.Customer.responseModel.GetProductNotInList;
import com.example.Customer.responseModel.GetSubCategoryResponse;
import com.example.Customer.responseModel.SubCategoryList;
import com.example.Customer.viewModel.VenderViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.ButterKnife;

public class SubCategory_Tab extends AppCompatActivity {


  private   TabLayout tab_layout;
   private RecyclerView rcv_show;
   private ImageView back_img;
    private ArrayList<SubCategoryList> arr;
    private HashMap<String,String> hm_data;
    VenderViewModel venderViewModel;
    int blabla=0;
    private ShowAddProductAdapter show_product_adapter;
private SwipeRefreshLayout swipeRefreshLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_tab);
        ButterKnife.bind(this);

        tab_layout  = findViewById(R.id.tab_layout);
        rcv_show = findViewById(R.id.rcv_show_product_frag);
        back_img = findViewById(R.id.go_back_img);
        rcv_show.setLayoutManager(new LinearLayoutManager(this));


        back_img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view)
            {
              onBackPressed();
            }
        });

        String categoryid;
        SharedPreferences sharedPreferences = getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
        categoryid = sharedPreferences.getString("CATEGORY_ID",null);

       venderViewModel = new ViewModelProvider(this).get(VenderViewModel.class);
        get_data(categoryid);

        swipeRefreshLayout = findViewById(R.id.refreshLayout_add_product);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                finish();
                startActivity(getIntent());

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });


    }



    private void get_data(String id)
    {
        venderViewModel.getSubCategoryList(id).observe(SubCategory_Tab.this, new Observer<GetSubCategoryResponse>() {
            @Override
            public void onChanged(GetSubCategoryResponse getSubCategoryResponse) {

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
                    Toast.makeText(SubCategory_Tab.this, "Check Your Internet Connectivity", Toast.LENGTH_SHORT).show();
                }




            }
        });

    }

    private void get_product_List(String sub_cat_id) {


        SharedPreferences sharedPreferences = getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
       String vendor_Id =  sharedPreferences.getString("VENDOR_ID",null);



        venderViewModel.get_Product_Not(vendor_Id,sub_cat_id,"0").observe(SubCategory_Tab.this, new Observer<GetProductNotInList>() {
            @Override
            public void onChanged(GetProductNotInList getProductNotInList) {



                    if (getProductNotInList != null) {
                        if (rcv_show.getVisibility() == View.INVISIBLE) {
                            rcv_show.setVisibility(View.VISIBLE);
                            show_product_adapter = new ShowAddProductAdapter(getProductNotInList.getData(), getApplicationContext(), venderViewModel, Integer.parseInt(vendor_Id));
                            show_product_adapter.notifyDataSetChanged();
                            rcv_show.setAdapter(show_product_adapter);
                        } else {
                            show_product_adapter = new ShowAddProductAdapter(getProductNotInList.getData(), getApplicationContext(), venderViewModel, Integer.parseInt(vendor_Id));
                            show_product_adapter.notifyDataSetChanged();
                            rcv_show.setAdapter(show_product_adapter);
                        }

                    } else {
                        Toast.makeText(SubCategory_Tab.this, "List Empty!!", Toast.LENGTH_SHORT).show();
                        rcv_show.setVisibility(View.INVISIBLE);
                    }

            }

    });


}

}