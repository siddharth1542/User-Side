package com.example.Customer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Customer.R;
import com.example.Customer.dashboard_fragment.Fragment_Product_Customer;
import com.example.Customer.responseModel.VendorList;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Vendor_Adapter extends RecyclerView.Adapter<Vendor_Adapter.holder>
{

    List<VendorList> arr;
      Context mContext;

    public Vendor_Adapter(List<VendorList> arr, Context mContext) {
        this.arr = arr;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_vendorlist,parent,false);
        return new Vendor_Adapter.holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {
        String vid = String.valueOf(arr.get(position).getVendorId());
        String cat_id = String.valueOf(arr.get(position).getCategoryID());
            holder.textView.setText(arr.get(position).getName());

        if(mContext==null){
            return;
        }else {

            Glide.with(mContext)
                    .load(arr.get(position).getProfilePic())
                    .placeholder(R.drawable.loding)
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


          AppCompatActivity activity = (AppCompatActivity) v.getContext();
              activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Fragment_Product_Customer(vid,cat_id)).commit();


            }
        });




    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class  holder extends RecyclerView.ViewHolder
   {

       CircleImageView imageView;
       TextView textView;
       public holder(@NonNull View itemView)
       {
           super(itemView);
           imageView = itemView.findViewById(R.id.vendorShopPic);
           textView = itemView.findViewById(R.id.vendorShopName);


       }

   }
}
