package com.example.Customer.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Customer.R;
import com.example.Customer.responseModel.AllCategoryDataList;
import com.example.Customer.viewModel.VenderViewModel;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

public class CategoryFirstSelectAdapter extends RecyclerView.Adapter<CategoryFirstSelectAdapter.holder>{

    List<AllCategoryDataList> arr;
    Context mcontext;
    String cat_name,i,cat_id,mobile,shopname;

    VenderViewModel venderViewModel;

    public  CategoryFirstSelectAdapter(List<AllCategoryDataList> arr, Context mcontext)
    {
        this.arr = arr;
        this.mcontext = mcontext;

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

        holder.tv.setText(String.valueOf(arr.get(position).getCategoryName()));
        i= String.valueOf(arr.get(position).getId());
        cat_name=arr.get(position).getCategoryName();

        if(mcontext==null){
            return;
        }else {

            Glide.with(mcontext)
                    .load(arr.get(position).getImagePath())
                    .placeholder(R.drawable.logo)
                    .centerCrop()
                    .into(holder.img);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               // holder.cardView.setCardBackgroundColor();

            //    String IPAddress = getIPAddress(true);

              cat_id = i;
            }
        });
    }


    public static String getIPAddress(boolean useIPv4) {
        try {
            for (final Enumeration<NetworkInterface> enumerationNetworkInterface = NetworkInterface.getNetworkInterfaces(); enumerationNetworkInterface.hasMoreElements();) {
                final NetworkInterface networkInterface = enumerationNetworkInterface.nextElement();
                for (Enumeration<InetAddress> enumerationInetAddress = networkInterface.getInetAddresses(); enumerationInetAddress.hasMoreElements();) {
                    final InetAddress inetAddress = enumerationInetAddress.nextElement();
                    final String ipAddress = inetAddress.getHostAddress();
                    if(!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return ipAddress;
                    }
                }
            }
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getItemCount() {

        return arr.size();
    }

    class holder extends RecyclerView.ViewHolder
    {

        TextView tv;
        ImageView img;
        CardView cardView;

        public holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.show_firstCategory);
            img = itemView.findViewById(R.id.category_image);
            cardView = itemView.findViewById(R.id.cardViewHighlight);

        }

    }


}

