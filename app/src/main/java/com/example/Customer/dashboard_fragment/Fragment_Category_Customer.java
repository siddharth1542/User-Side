package com.example.Customer.dashboard_fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Customer.Dashboard;
import com.example.Customer.R;
import com.example.Customer.adapter.Adapter_home;
import com.example.Customer.responseModel.GetCategoryResponseModel;
import com.example.Customer.viewModel.VenderViewModel;

import butterknife.BindView;


public class Fragment_Category_Customer extends Fragment {

    private VenderViewModel venderViewModel;
    Adapter_home adapter_home;

    @BindView(R.id.category_Recycler)
    RecyclerView rcv_category;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment__category__customer, container, false);
           ((Dashboard) getActivity()).getSupportActionBar().setTitle("Category");

       get_category();

        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 3);
        gridLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_category.setLayoutManager(gridLayoutManager1);



        return view;
    }


    private void get_category()
    {
        venderViewModel.getMovieList().observe(getViewLifecycleOwner(), new Observer<GetCategoryResponseModel>()
        {
            @Override
            public void onChanged(GetCategoryResponseModel getCategoryResponseModel)
            {

                if (getCategoryResponseModel != null)
                {
                    adapter_home = new Adapter_home(getCategoryResponseModel.getData(),getContext());
                    adapter_home.notifyDataSetChanged();
                    rcv_category.setAdapter(adapter_home);

                }

            }
        });
    }







}