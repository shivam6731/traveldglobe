package com.example.shivam6731.traveldglobe;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class login extends AppCompatActivity {
LoginButton loginbtn;
    TextView textView;

    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        loginbtn=(LoginButton)findViewById(R.id.fb_login_btn);
        callbackManager=CallbackManager.Factory.create();
        AccessToken token;
        token = AccessToken.getCurrentAccessToken();

        if (token != null) {
            Intent i=new Intent(login.this,MainActivity.class);
            startActivity(i);
        }

        loginbtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Intent i=new Intent(login.this,MainActivity.class);
                startActivity(i);
                //Toast.makeText(login.this,loginResult.getAccessToken().getUserId(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(login.this,"canceled",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(login.this,"error",Toast.LENGTH_LONG).show();
            }
        });






    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
