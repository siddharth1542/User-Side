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

public class CategoryGridViewAdapter extends RecyclerView.Adapter<CategoryGridViewAdapter.holder>{

    List<AllCategoryDataList> arr;
    Context mcontext;

    public CategoryGridViewAdapter(List<AllCategoryDataList> arr,Context mcontext) {
        this.arr = arr;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_catagory_each_list,parent,false);
      return new holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {
        holder.tv.setText(String.valueOf(arr.get(position).getCategoryName()));
       holder.tv2.setText(String.valueOf(arr.get(position).getId()));


        if(mcontext==null){
            return;
        }else {

            Glide.with(mcontext)
                    .load(arr.get(position).getImagePath())
                    .placeholder(R.drawable.logo)
                    .into(holder.img);

        }
        int i = position;
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

//                if(arr.size()!=0) {
//                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                            new Fragment_Home(arr.get(i).getId() + "")).addToBackStack(null).commit();
//                }else{
//                    Toast.makeText(mcontext, "there is not any added sub category", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    @Override
    public int getItemCount() {

        return arr.size();
    }

    class holder extends RecyclerView.ViewHolder
    {

        TextView tv,tv2;
        ImageView img;

        public holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.show_each);
            tv2 = itemView.findViewById(R.id.show_each1);
            img = itemView.findViewById(R.id.category_image);
        }

    }


}

