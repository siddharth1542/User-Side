package com.example.Customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Customer.R;
import com.example.Customer.dashboard_fragment.Fragment_VendorOnCategory;
import com.example.Customer.responseModel.AllCategoryDataList;

import java.util.List;

public class Adapter_home extends RecyclerView.Adapter<Adapter_home.holder_home> {

    List<AllCategoryDataList> arr;
    int limit =0;
    Context mContext;

    public Adapter_home(List<AllCategoryDataList> arr,Context mContext)
    {

        this.arr =  arr;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public holder_home onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_each_list_home,parent,false);
        return new holder_home(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder_home holder, int position)
    {
     holder.tv_home.setText(arr.get(position).getCategoryName());
     String cat_id = String.valueOf(arr.get(position).getId());

           Glide.with(mContext)
                    .load(arr.get(position).getImagePath())
                    .placeholder(R.drawable.loding)
                    .into(holder.imageView);



     int i = position;

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Fragment_VendorOnCategory(cat_id)).commit();

            }
        });
    }

    @Override
    public int getItemCount() {

      return arr.size();
    }


    class holder_home extends RecyclerView.ViewHolder{

        TextView tv_home;
        ImageView imageView;

        public holder_home(@NonNull View itemView) {
            super(itemView);
            tv_home = itemView.findViewById(R.id.show_each_home);
            imageView = itemView.findViewById(R.id.categoryPic);
        }
    }
}
