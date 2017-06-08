package com.example.shivam6731.traveldglobe;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class M2 extends AppCompatActivity {
    ImageView imagev;
    TextView textv, textView2, textv1,text1;
    ImageView hotel,exp;
    String name,lat,lng;
    String[] hname;
    String[] image;    StringBuffer buffer;
    int j;


boolean is=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("abc");
        String me = bundle.getString("abb");
        String desc = bundle.getString("aaa");
        String title = bundle.getString("bbb");
         name = bundle.getString("ccc");
        lat=bundle.getString("ddd");
        lng=bundle.getString("eee");
        imagev = (ImageView) findViewById(R.id.imagev);
        textv = (TextView) findViewById(R.id.textv);
        exp=(ImageView)findViewById(R.id.exp);
        exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2=new Intent(M2.this,map.class);
                i2.putExtra("aq",lat);
                i2.putExtra("bq",lng);
                startActivity(i2);
            }
        });
        textView2 = (TextView) findViewById(R.id.textView2);
        textv1 = (TextView) findViewById(R.id.textv1);
        hotel = (ImageView) findViewById(R.id.hotel);
        text1=(TextView)findViewById(R.id.text1);

        textv.setText(message);
        textView2.setText(desc);
        textv1.setText(title);
        Picasso.with(M2.this).load(me).into(imagev);
        Toast.makeText(M2.this,name,Toast.LENGTH_LONG).show();

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(M2.this,hotel1.class);
                i1.putExtra("a",name);

                startActivity(i1);
            }
        });

    }


}
