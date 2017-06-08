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

public class CustomAdapter extends BaseAdapter{
    String [] result;
    Context context;
    String [] imageId;
    String [] description;
    String [] tle;
    String [] na;
    String [] latitude;
    String [] longitude;


    public CustomAdapter(Context context) {
        this.context = context;
    }


    private static LayoutInflater inflater=null;
    public CustomAdapter(MainActivity mainActivity, String[] prgmNameList, String[] prgmImages,String[] desc,String[] title,String[] name,String[] lat,String[] lng) {

        result=prgmNameList;
        context=mainActivity;
        imageId=prgmImages;
        description=desc;
        tle=title;
        na=name;
        latitude=lat;
        longitude=lng;

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
        TextView tv,tv11,tv12;
        ImageView img;
        Button b1;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final Holder holder=new Holder();
        View rowView;
        final boolean[] is1 = {false};
        rowView = inflater.inflate(R.layout.row, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView1);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView1);
        holder.tv12=(TextView)rowView.findViewById(R.id.tv12);
        holder.tv.setText(result[position]);
       holder.b1=(Button)rowView.findViewById(R.id.b1);
        holder.tv11=(TextView)rowView.findViewById(R.id.tv11);
        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is1[0] ==false)
                {holder.tv12.setVisibility(View.VISIBLE);
                    holder.tv11.setVisibility(View.VISIBLE);
                    holder.tv11.setText(description[position]);
                    is1[0] =true;
                }
                else
                {
                    holder.tv11.setVisibility(View.GONE);
                    holder.tv12.setVisibility(View.GONE);
                    is1[0] =false;

                }
            }
        });
        Picasso.with(context).load(imageId[position]).into(holder.img);
      //  new DownLoadImageTask(holder.img).execute(imageId[position]);
       // holder.img.setImageResource(imageId[position]);
        rowView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


               // Toast.makeText(context, "You Clicked "+imageId[position], Toast.LENGTH_LONG).show();
               // String im=Integer.toString(imageId[position]);
                    Intent i1=new Intent(context,M2.class);
                i1.putExtra("abc",result[position]);
                i1.putExtra("abb",imageId[position]);
                i1.putExtra("aaa",description[position]);
                i1.putExtra("bbb",tle[position]);
                i1.putExtra("ccc",na[position]);
                i1.putExtra("ddd",latitude[position]);
                i1.putExtra("eee",longitude[position]);
                context.startActivity(i1);


            }
        });
        return rowView;
    }



}