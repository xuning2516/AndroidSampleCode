package com.royole.views.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.royole.views.R;
import com.royole.views.activity.ViewsActivity;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {
    private String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public  ImageView mImageView;
        public TextView mTextView;
        public MyViewHolder(View v) {
            super(v);
            mImageView = v.findViewById(R.id.view_image_view);
            mTextView = v.findViewById(R.id.view_text_view);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    switch (mTextView.getText().toString()){
                        case "RecyclerView":
                            Toast.makeText(v.getContext(), "recyclerview", Toast.LENGTH_SHORT).show();
                            break;
                        case "ListView":
                            break;
                    }

                    Intent intent = new Intent(v.getContext(),ViewsActivity.class);
                    intent.putExtra("viewName",mTextView.getText().toString());
                    v.getContext().startActivity(intent);
                }
            });

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ViewAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View linearView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_adapter, parent, false);

        //...
        MyViewHolder vh = new MyViewHolder(linearView);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
