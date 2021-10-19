package com.example.Customer.adapter;



import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.Customer.R;
import com.example.Customer.responseModel.SendAddedProduct;
import com.example.Customer.responseModel.VendorShowProductNotInList;
import com.example.Customer.viewModel.VenderViewModel;

import java.util.List;

public class ShowAddProductAdapter extends RecyclerView.Adapter <ShowAddProductAdapter.holder>{

    List<VendorShowProductNotInList> arr;
    Context c;
    private VenderViewModel vendorViewModel;
    private Dialog dialog;
    private int venderId;


    public ShowAddProductAdapter(List<VendorShowProductNotInList> arr, Context c, VenderViewModel venderViewModel, int vendor_id) {
       this.venderId = vendor_id;
        this.vendorViewModel  = venderViewModel;
        this.arr = arr;
        this.c = c;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_product_each_list, parent, false);
            return new holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {


        String txt = arr.get(position).getProductName();
        if(txt.length()>25){
            txt = txt.substring(0,25)+"...";
        }

       // holder.tv.setText(arr.get(position).getSubCategoryName());
        holder.productName.setText(txt);
        holder.unitValue.setText(String.valueOf(arr.get(position).getProductUnitValue()));
        holder.Unit.setText(arr.get(position).getProductUnit());
        holder.Price.setText(String.valueOf(arr.get(position).getProductPrice()));

        if(c==null){
            return;
        }else {

            Glide.with(c)
                    .load(arr.get(position).getProductLogo())
                    .placeholder(R.drawable.logo)
//                    .circleCrop()
//                    .apply(new RequestOptions().override(50,50))
                    .centerCrop()
//                    .fitCenter()
                    .into(holder.img);
        }

       int i = position;

       holder.btn_add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {


                EditText pop_product_price ;
                EditText pop_product_discount_price ;
                EditText pop_product_add_stock;
                SwitchCompat pop_product_stock_status;
                SwitchCompat pop_product_status;
                Button btn_save;
                ImageView img_clear;





                 dialog = new Dialog(view.getContext());
                dialog.setContentView(R.layout.add_product_popup);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCanceledOnTouchOutside(true);



                  pop_product_price = dialog.findViewById(R.id.pop_addPrice);
                  pop_product_discount_price = dialog.findViewById(R.id.pop_add_discount);
                  pop_product_add_stock = dialog.findViewById(R.id.pop_add_stock);
                  pop_product_stock_status = dialog.findViewById(R.id.pop_add_stock_status);
                  pop_product_status = dialog.findViewById(R.id.pop_add_status);
                  btn_save = dialog.findViewById(R.id.popProduct_btnSave);
                  img_clear = dialog.findViewById(R.id.img_clear);

                img_clear.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        dialog.dismiss();
                    }
                });

                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        int ProductId =arr.get(i).getProductId();
                        float Price = Float.parseFloat(pop_product_price.getText().toString());
                        float DiscountedPrice = Float.parseFloat(pop_product_discount_price.getText().toString());
                        int Stock = Integer.parseInt(pop_product_add_stock.getText().toString());

                        boolean StockStatus = false;
                        boolean Status = false;

                        if(pop_product_stock_status.isChecked()){
                            StockStatus = true;
                        }
                        if(pop_product_status.isChecked()){
                            Status = true;
                        }
                        String IPAddress;
                        SharedPreferences sharedPreferences = c.getSharedPreferences("VENDOR_DATA",MODE_PRIVATE);
                        IPAddress = sharedPreferences.getString("IPADDRESS",null);
                        send_product_Entry(ProductId,Price,DiscountedPrice,Stock,StockStatus,Status,IPAddress);

                        dialog.dismiss();

                    }
                });


                dialog.show();
                final Handler handler  = new Handler();
                final Runnable runnable = new Runnable()
                {
                    @Override
                    public void run() {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                    }
                };
            }
        });

    }

    private void send_product_Entry(int productId, float price, float discountedPrice, int stock, boolean stockStatus, boolean status, String ipAddress) {

        vendorViewModel.send_product_Added_detail(venderId,productId,price,discountedPrice,stock,status,stockStatus,ipAddress).observeForever(new Observer<SendAddedProduct>() {
            @Override
            public void onChanged(SendAddedProduct sendAddedProduct)
            {
                Toast.makeText(c.getApplicationContext(), ""+sendAddedProduct.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
      //  setFinishOnTouchOutside(false);
        dialog.dismiss();

    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    class holder extends RecyclerView.ViewHolder{

        TextView productName, unitValue, Unit, Price;
        ImageView img;
        Button btn_add;

        public holder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.eachProductName);
            unitValue = itemView.findViewById(R.id.eachUnitValue);
            Unit = itemView.findViewById(R.id.eachUnit);
            Price = itemView.findViewById(R.id.eachPrice);
             btn_add = itemView.findViewById(R.id.btn_add);
             img = itemView.findViewById(R.id.productLogo_img);

        }
    }


}

