package com.example.shivam6731.traveldglobe;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomAdapter1 extends BaseAdapter{
    String [] result;
    Context context;
    String [] imageId;


    public CustomAdapter1(Context context) {
        this.context = context;
    }


    private static LayoutInflater inflater=null;
    public CustomAdapter1(hotel1 mainActivity, String[] hname, String[] image) {

        result=hname;
        context=mainActivity;
        imageId=image;


        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {

        return result.length;
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView tv6,tv11,tv12;
        ImageView img6;
        Button b1;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;
        final boolean[] is1 = {false};
        rowView = inflater.inflate(R.layout.row1, null);
         holder.tv6=(TextView)rowView.findViewById(R.id.t1);
        holder.img6=(ImageView)rowView.findViewById(R.id.image1);
        holder.tv6.setText(result[position]);
        Picasso.with(context).load(imageId[position]).into(holder.img6);



        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub




            }
        });
        return rowView;
    }



}