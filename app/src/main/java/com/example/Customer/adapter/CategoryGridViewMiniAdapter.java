package com.example.Customer.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Customer.R;
import com.example.Customer.responseModel.AllCategoryDataList;

import java.util.List;

public class CategoryGridViewMiniAdapter extends RecyclerView.Adapter<CategoryGridViewMiniAdapter.holder>{

    List<AllCategoryDataList> arr;
    Context mcontext;
    int id;

    public CategoryGridViewMiniAdapter(List<AllCategoryDataList> arr,Context context) {
        this.arr = arr;
        mcontext = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_categoryrecycler_eachlist,parent,false);
      return new holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {
        holder.tv.setText(String.valueOf(arr.get(position).getCategoryName()));

        if(mcontext==null){
            return;
        }else {

            Glide.with(mcontext)
                    .load(arr.get(position).getImagePath())
                    .placeholder(R.drawable.loding)
                    .into(holder.img);

        }



        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               // id =arr.get(position).getId();
            }
        });
    }

    public int getCategoryId()
    {
        return id;
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class holder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView tv;

        public holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.show_each_category);
            img = itemView.findViewById(R.id.img_show_each);
        }

    }


}

