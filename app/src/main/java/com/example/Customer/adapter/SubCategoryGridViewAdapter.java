package com.example.Customer.adapter;



import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Customer.R;
import com.example.Customer.responseModel.SubCategoryList;

import java.util.List;

public class SubCategoryGridViewAdapter extends RecyclerView.Adapter <SubCategoryGridViewAdapter.holder>{

    List<SubCategoryList> arr;


    public SubCategoryGridViewAdapter(List<SubCategoryList> arr) {
        this.arr = arr;
        Log.i("SUB","happpp"+arr.get(0).getSubCategoryName());
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_catagory_firsteach_list,parent,false);
      return new holder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {

        holder.tv.setText(arr.get(position).getSubCategoryName());

//        holder.itemView.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Fragment_Product()).addToBackStack(null).commit();
//
//            }
//        });
    }

    @Override
    public int getItemCount() {

        return arr.size();

    }

    class holder extends RecyclerView.ViewHolder{

        TextView tv;
        ImageView img;

        public holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.show_firstCategory);
            img = itemView.findViewById(R.id.category_image);

        }
    }


}

