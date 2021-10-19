package com.example.Customer.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Customer.R;
import com.example.Customer.responseModel.AlreadyInsertedList;

import java.util.List;

public class Already_inserted_adapter extends RecyclerView.Adapter<Already_inserted_adapter.AlreadyViewHolder> {



    List<AlreadyInsertedList> arr;
    Context context;

    public Already_inserted_adapter(List<AlreadyInsertedList> list,Context ctx) {
        this.arr = list;
        this.context = ctx;
    }

    @NonNull
    @Override
    public AlreadyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.show_requested_product_each_list,parent,false);


        return new AlreadyViewHolder(v);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull AlreadyViewHolder holder, int position) {
        String txt = arr.get(position).getProductName();

        if(txt.length()>29){
            txt = txt.substring(0,29)+"...";
        }

        holder.productName.setText(txt);
        holder.Price.setText(arr.get(position).getPrice()+"");
        holder.Unit.setText(arr.get(position).getUnit()+"");
        holder.unitValue.setText(arr.get(position).getUnitValue()+"");

        if(arr.get(position).getVrpStatus()==1){

            if(holder.inserted_add.getVisibility()==View.VISIBLE){

                holder.inserted_add.setVisibility(View.GONE);
            }
            holder.pending.setVisibility(View.VISIBLE);


//            if(holder.inserted_add.getVisibility()==View.VISIBLE){
//                holder.inserted_add.setVisibility(View.INVISIBLE);
//            }

        }else if(arr.get(position).getVrpStatus()==2){
            if(holder.pending.getVisibility()==View.VISIBLE){

                holder.pending.setVisibility(View.GONE);
            }
            holder.inserted_add.setVisibility(View.VISIBLE);

        }


    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    class AlreadyViewHolder extends RecyclerView.ViewHolder {


        TextView productName, unitValue, Unit, Price,pending;
        Button inserted_add;


        public AlreadyViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.inserted_product_name);
            unitValue = itemView.findViewById(R.id.inserted_UnitValue);
            Unit  = itemView.findViewById(R.id.inserted_unit);
            Price = itemView.findViewById(R.id.inserted_eachPrice);
            inserted_add = itemView.findViewById(R.id.insert_btn_add);

            pending = itemView.findViewById(R.id.insert_pending);
        }
    }


}
