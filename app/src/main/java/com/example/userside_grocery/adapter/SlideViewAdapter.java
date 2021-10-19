package com.example.Customer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.Customer.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;


public class SlideViewAdapter extends SliderViewAdapter<SlideViewAdapter.MyViewHolder1>
{

    List<Integer> ImageList;

   public SlideViewAdapter(List<Integer> imageList)
   {
        ImageList = imageList;
    }



    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, null);
        return new MyViewHolder1(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder1 viewHolder, int position)
    {

        viewHolder.imageView.setImageResource(ImageList.get(position));



    }

    @Override
    public int getCount() {
        return ImageList.size();
    }

    class MyViewHolder1 extends ViewHolder
    {

        ImageView imageView;
        public MyViewHolder1(View itemView)
        {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
