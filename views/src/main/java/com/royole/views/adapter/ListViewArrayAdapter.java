package com.royole.views.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.royole.views.R;

public class ListViewArrayAdapter<T> extends ArrayAdapter {
    private int resourceid;

    public ListViewArrayAdapter(Context context,int resId,String[] fruits){
        super(context,resId,fruits);
        resourceid = resId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        FruitViewHolder holder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceid,parent,false);
            holder = new FruitViewHolder();
            holder.fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
            holder.fruitName = (TextView)view.findViewById(R.id.fruit_name);

//            holder.fruitName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(v.getContext(), "click "+ ((TextView)v).getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//            });

            Rect delegateArea = new Rect();
            view.getHitRect(delegateArea);

            TouchDelegate touchDelegate = new TouchDelegate(delegateArea, holder.fruitName);
            // Sets the TouchDelegate on the parent view, such that touches
            // within the touch delegate bounds are routed to the child.
            if (View.class.isInstance(holder.fruitName.getParent())) {
                ((View) holder.fruitName.getParent()).setTouchDelegate(touchDelegate);
            }

            view.setTag(holder);


        }else {
            view = convertView;
            holder = (FruitViewHolder)convertView.getTag();
        }

        holder.fruitName.setText((String)getItem(position));
        return view;
    }

    static class FruitViewHolder {

        public ImageView fruitImage;
        public TextView fruitName;

    }
}
