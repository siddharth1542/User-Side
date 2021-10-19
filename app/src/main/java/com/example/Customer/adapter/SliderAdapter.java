package com.example.Customer.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Customer.R;
import com.example.Customer.SliderItem;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;

public class SliderAdapter {


    class SliderViewHolder extends RecyclerView.ViewHolder{


        private RoundedImageView imgV;

        private TextView txt;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);

            imgV = itemView.findViewById(R.id.imageSlide);
            txt = itemView.findViewById(R.id.show_each_pro_home);

        }

        void setItem(SliderItem sliderItem){
            imgV.setImageResource(sliderItem.getImg());
            txt.setText(sliderItem.getTxt());
        }


    }


}
