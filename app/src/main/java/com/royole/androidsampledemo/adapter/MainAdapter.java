package com.royole.androidsampledemo.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.royole.androidsampledemo.R;
import com.royole.androidsampledemo.model.MainItem;
import com.royole.views.activity.ViewMainActivity;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {
    private MainItem[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;
        public MyViewHolder(View v) {
            super(v);
            //mTextView = v;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickImageView(v,getAdapterPosition());
                }
            });
            mImageView = v.findViewById(R.id.mainimageView);
            mTextView = v.findViewById(R.id.mainitemname);
        }

        private void clickImageView(View v,int position){
            Intent intent = null;
            switch (position){
                case 0:// view
                    intent = new Intent(v.getContext(),ViewMainActivity.class);
                    v.getContext().startActivity(intent);
                    break;
            }
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainAdapter(MainItem[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View mainItemView  =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_recycle_item, parent, false);
        //...
        MyViewHolder vh = new MyViewHolder(mainItemView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mDataset[position]);
        holder.mImageView.setImageResource(mDataset[position].imageResId);
        holder.mTextView.setText(mDataset[position].imageName);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}
