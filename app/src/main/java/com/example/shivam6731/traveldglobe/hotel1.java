package com.example.shivam6731.traveldglobe;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

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

public class hotel1 extends AppCompatActivity {
    ListView list1;
    int j;
    String [] hname;
    String [] image;
    StringBuffer buffer;
    String name;
    //BufferedReader reader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel1);
        list1=(ListView)findViewById(R.id.list1);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("a");
       // Toast.makeText(hotel1.this,name,Toast.LENGTH_LONG).show();
       new hotel1.JSONTask().execute();

    }


    public class JSONTask extends AsyncTask<URL, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(hotel1.this,name,Toast.LENGTH_LONG).show();

        }

        @Override
        protected String doInBackground(URL... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("http://35.166.3.171:8080/api/v1/activity/stay?location_slug="+name);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                if (connection == null) {

                }
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }

                String x = buffer.toString();
                JSONObject jsonObj = new JSONObject(x);

                JSONObject jsonData = jsonObj.getJSONObject("result");

                JSONArray arrJson = jsonData.getJSONArray("stays");

                j = arrJson.length();
                hname=new String[j];
                image=new String[j];
                for (int i = 0; i < arrJson.length(); i++) {
                    JSONObject jsonObject = arrJson.getJSONObject(i);
                    hname[i]=jsonObject.getString("name");
                    image[i]=jsonObject.getString("thumb_url");
                    if(image[i].isEmpty())
                    {
                        image[i]="http://wallpaper-gallery.net/images/image/image-13.jpg";
                    }

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Toast.makeText(hotel1.this,j+"",Toast.LENGTH_LONG).show();
            list1.setAdapter(new CustomAdapter1(hotel1.this, hname,image));

        }
    }
}
