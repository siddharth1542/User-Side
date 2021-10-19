package com.example.Customer.adapter;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Customer.R;
import com.example.Customer.responseModel.VendorShowProductList;
import com.example.Customer.viewModel.VenderViewModel;

import java.util.List;

public class ShowCustomerProductAdapter extends RecyclerView.Adapter <ShowCustomerProductAdapter.holder>{

    List<VendorShowProductList> arr;
    Context mContext;
    private VenderViewModel venderViewModel;
    private int vender_id;
    private Dialog dialog;
    private String ProductId,Price,DiscountedPrice,Stock,IPAddress;

    public ShowCustomerProductAdapter(List<VendorShowProductList> arr, Context mContext, VenderViewModel venderViewModel, int vender_Id) {
        this.arr = arr;
        this.mContext = mContext;
       this.venderViewModel = venderViewModel;
       this.vender_id = vender_Id;

    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_customer_product_each_list, parent, false);
            return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {

        String txt = arr.get(position).getProductName();

        if(txt.length()>29){
            txt = txt.substring(0,29)+"...";
        }
        if(mContext==null){
            return;
        }else {

            Glide.with(mContext)
                    .load(arr.get(position).getImagePath())
                    .placeholder(R.drawable.loding)
                    .into(holder.img);

        }
        int i = position;

        holder.productName.setText(txt);
        holder.unitValue.setText(String.valueOf(arr.get(position).getUnitValue()));
        holder.Unit.setText(arr.get(position).getUnit());
        holder.Price.setText(String.valueOf(arr.get(position).getPrice()));
        holder.btn_add.setOnClickListener(new View.OnClickListener()
        {
            int count=0;
            @Override
            public void onClick(View view)
            {


//                holder.img_add.setOnClickListener(new View.OnClickListener()
//                {
//                    @Override
//                    public void onClick(View v)
//                    {
//                        if(count == 99)
//                        {
//
//                        }
//                        else{
//                            count++;
//                            holder.txt_Pro.setText(String.valueOf(count));
//                        }
//
//                    }
//                });
//
//                holder.img_sub.setOnClickListener(new View.OnClickListener()
//                {
//                    @Override
//                    public void onClick(View v)
//                    {
//                        if (count>1)
//                        {
//                            count--;
//                            holder.txt_Pro.setText(String.valueOf(count));
//                        }
//                        else{
//                            holder.addProduct.setVisibility(View.GONE);
//                            holder.btn_add.setVisibility(View.VISIBLE);
//                        }
//
//                    }
//                });
            }
        });

    }


    @Override
    public int getItemCount()
    {
        return arr.size();
    }

    class holder extends RecyclerView.ViewHolder{

        TextView productName, unitValue, Unit, Price;
        Button btn_add;
        ImageView img,img_add,img_sub;
        TextView txt_Pro;
        LinearLayout addProduct;

        public holder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.eachProductName);
            unitValue = itemView.findViewById(R.id.eachUnitValue);
            Unit = itemView.findViewById(R.id.eachUnit);
            Price = itemView.findViewById(R.id.eachPrice);
            img = itemView.findViewById(R.id.image);
            btn_add = itemView.findViewById(R.id.btn_CustomerAdd);
       //     img_add = itemView.findViewById(R.id.img_add);
       //     img_sub = itemView.findViewById(R.id.img_sub);
        //    txt_Pro = itemView.findViewById(R.id.txtPro);
         //   addProduct = itemView.findViewById(R.id.Linear_addProduct);

        }
    }


}

