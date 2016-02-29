package com.geetaanjali.drishyam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Amon on 10/12/2558.
 */
public class MyAdapter extends BaseAdapter {

    private Context context;

    String[] textField;
    int[] imageField;

    public MyAdapter(Context context,String[] string,int[] image){
        this.context = context;
        textField = string;
        imageField = image;
    }

    @Override
    public int getCount() {
        return textField.length;
    }

    @Override
    public Object getItem(int position) {
        return textField[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;

        if (convertView == null) {
            LayoutInflater inflator =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflator.inflate(R.layout.custome,parent,false);  //convert to java object

        }else{
            row = convertView;
        }

        ImageView titleImageView = (ImageView)row.findViewById(R.id.imageView1);
        TextView  textView = (TextView) row.findViewById(R.id.drawerText);
        titleImageView.setImageResource(imageField[position]);
        textView.setText(textField[position]);

        return row;
    }
}