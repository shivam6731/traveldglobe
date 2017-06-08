package com.example.shivam6731.traveldglobe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Context context;
    FloatingActionButton catsearch,catsearch1,catsearch2;
ProgressDialog progressDialog;
    ArrayList prgmName;
     StringBuffer buffer;
    String abc;
int j;
    public static int[] prgmImages = {R.drawable.xf, R.drawable.de, R.drawable.xf, R.drawable.de, R.drawable.xf, R.drawable.acc, R.drawable.acc, R.drawable.acc, R.drawable.acc};
    public static String[] prgmNameList;
    public static String[] images;
    public static String[] desc;
    public static String[] title;
    public static String[] name={"a"};
    public static String[] lat;
    private static String[] lng;
boolean isopen =false,is1=false;
    Button b1;
    TextView tv11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
        progressDialog.show();
        catsearch=(FloatingActionButton)findViewById(R.id.catsearch);
        catsearch1=(FloatingActionButton)findViewById(R.id.catsearch1);
        catsearch2=(FloatingActionButton)findViewById(R.id.catsearch2);

        catsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isopen==false) {
                    catsearch1.setVisibility(View.VISIBLE);
                    catsearch2.setVisibility(View.VISIBLE);
                    isopen=true;
                }
                else
                {

                    catsearch1.setVisibility(View.INVISIBLE);
                    catsearch2.setVisibility(View.INVISIBLE);
                    isopen=false;
                }
            }
        });
        new JSONTask().execute();
        context = this;

        lv = (ListView) findViewById(R.id.listView);



    }




    public class JSONTask extends AsyncTask<URL, String, String> {

        @Override
        protected String doInBackground(URL... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("http://35.166.3.171:8080/api/v1/location/state/jammu-kashmir");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                if(connection==null)
                {

                }
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                 buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);

                }

                String x=buffer.toString();
                JSONObject jsonObj = new JSONObject(x);

             JSONObject jsonData = jsonObj.getJSONObject("result");

                JSONArray arrJson = jsonData.getJSONArray("destinations");

                j=arrJson.length();
                prgmNameList=new String[j];
                images=new String[j];
                desc=new String[j];
                title=new String[j];
                name=new String[j];
                lat=new String[j];
                lng=new String[j];
                for(int i = 0; i < arrJson.length(); i++){
                    JSONObject jsonObject=arrJson.getJSONObject(i);
                  //  JSONArray as=jsonObject.getJSONArray("activities");
                  //  JSONObject ob=as.getJSONObject(0);
                    prgmNameList[i] = jsonObject.getString("name");
                    images[i]=jsonObject.getString("thumb_url");
                    desc[i]=jsonObject.getString("desc");
                    title[i]=jsonObject.getString("title");
                    name[i]=jsonObject.getString("slug");
                    lat[i]=jsonObject.getString("latitude");
                    lng[i]=jsonObject.getString("longitude");

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
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, j+"", Toast.LENGTH_SHORT).show();
             lv.setAdapter(new CustomAdapter(MainActivity.this, prgmNameList, images,desc,title,name,lat,lng));
            abc();


        }
    }

public void abc()
{
    ActionBar actionBar = getSupportActionBar();
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowCustomEnabled(true);


    LayoutInflater inflator = (LayoutInflater) this
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View v = inflator.inflate(R.layout.search, null);

    actionBar.setCustomView(v);

    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.select_dialog_item, prgmNameList);
    final AutoCompleteTextView textView = (AutoCompleteTextView) v
            .findViewById(R.id.editText1);
    textView.setThreshold(0);
    Toast.makeText(MainActivity.this, j+"", Toast.LENGTH_SHORT).show();
    textView.setAdapter(adapter);
    textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            for(int i=0;i<j;++i)
            {
                if(prgmNameList[i].equals(textView.getText().toString()))
                { Toast.makeText(MainActivity.this, i+"", Toast.LENGTH_SHORT).show();
                    lv.smoothScrollToPosition(i);
                    /*Intent i11=new Intent(MainActivity.this,M2.class);
                    i11.putExtra("abc",prgmNameList[i]);
                    i11.putExtra("abb",prgmImages[i]);
                    i11.putExtra("aaa",desc[i]);
                    i11.putExtra("bbb",title[i]);
                    i11.putExtra("ccc",name[i]);
                    i11.putExtra("ddd",lat[i]);
                    i11.putExtra("eee",lng[i]);
                    startActivity(i11);*/
                }
            }


        }
    });

}
}

