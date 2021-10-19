package com.example.Customer.adapter;


import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Customer.R;
import com.example.Customer.responseModel.VendorShowProductList;
import com.example.Customer.viewModel.VenderViewModel;

import java.util.List;

public class ShowMyProductAdapter extends RecyclerView.Adapter <ShowMyProductAdapter.holder>{

    List<VendorShowProductList> arr;
    Context mContext;
    private VenderViewModel venderViewModel;
    private int vender_id;
    private Dialog dialog;
    private String ProductId,Price,DiscountedPrice,Stock,IPAddress;

    public ShowMyProductAdapter(List<VendorShowProductList> arr, Context mContext,VenderViewModel venderViewModel,int vender_Id) {
        this.arr = arr;
        this.mContext = mContext;
       this.venderViewModel = venderViewModel;
       this.vender_id = vender_Id;

    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_product_update_each_list, parent, false);
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

       // holder.tv.setText(arr.get(position).getSubCategoryName());
        holder.productName.setText(txt);
        holder.unitValue.setText(String.valueOf(arr.get(position).getUnitValue()));
        holder.Unit.setText(arr.get(position).getUnit());
        holder.Price.setText(String.valueOf(arr.get(position).getPrice()));
        holder.btn_update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.update_product_popup);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(true);
                dialog.getWindow().getAttributes().windowAnimations = R.style.Animation_MaterialComponents_BottomSheetDialog;

                dialog.show();

                EditText pop_product_price = dialog.findViewById(R.id.pop_addPrice);
                EditText pop_product_discount_price = dialog.findViewById(R.id.pop_add_discount);
                EditText pop_product_add_stock = dialog.findViewById(R.id.pop_add_stock);
                Button pop_btnUpdate = dialog.findViewById(R.id.popProduct_btnUpdate);
                ImageView img_clear = dialog.findViewById(R.id.img_clear);

                img_clear.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        dialog.dismiss();
                    }
                });

                pop_btnUpdate.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                         ProductId =String.valueOf(arr.get(i).getProductId());
                         Price = pop_product_price.getText().toString();
                         DiscountedPrice = pop_product_discount_price.getText().toString();
                         Stock = pop_product_add_stock.getText().toString();

                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
                        IPAddress = sharedPreferences.getString("IPADDRESS",null);




                       venderViewModel.vendorUpdateProduct(ProductId,Price,DiscountedPrice,Stock,IPAddress);
                       dialog.setCancelable(true);
                       dialog.setCanceledOnTouchOutside(true);
                       dialog.dismiss();

                    }
                });


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
        Button btn_update;
        ImageView img;

        public holder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.eachProductName);
            unitValue = itemView.findViewById(R.id.eachUnitValue);
            Unit = itemView.findViewById(R.id.eachUnit);
            Price = itemView.findViewById(R.id.eachPrice);
            img = itemView.findViewById(R.id.image);
            btn_update = itemView.findViewById(R.id.btn_update);

        }
    }


}

