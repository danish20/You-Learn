package com.applications.arunjotsingh.youlearn;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    String[] strURL =
            {
                    "https://dl.dropboxusercontent.com/s/dgmw165b17lhl4r/Slide1.JPG",
                    "https://dl.dropboxusercontent.com/s/zw0ljnpuexq38ec/Slide2.JPG",
                    "https://dl.dropboxusercontent.com/s/fzk5yl0648mfpf4/Slide3.JPG",
                    "https://dl.dropboxusercontent.com/s/vskjkfjqkh1wra3/Slide4.JPG",
                    "https://dl.dropboxusercontent.com/s/y1pwwdc56qblbc3/Slide5.JPG",
                    "https://dl.dropboxusercontent.com/s/tnh5ousaaucu5qd/Slide6.JPG",
                    "https://dl.dropboxusercontent.com/s/ip3ja932r2lko7z/Slide7.JPG",
                    "https://dl.dropboxusercontent.com/s/eyqfusmijbnu9py/Slide8.JPG",
                    "https://dl.dropboxusercontent.com/s/gs091dhgim3yhy7/Slide9.JPG",
                    "https://dl.dropboxusercontent.com/s/xysi0er8xfaeffa/Slide10.JPG",
                    "https://dl.dropboxusercontent.com/s/6mehk5e9hy7khkd/Slide11.JPG",
                    "https://dl.dropboxusercontent.com/s/dwf0ff9a4hdw10q/Slide12.JPG",
                    "https://dl.dropboxusercontent.com/s/3kmh1qhr9fcu5fy/Slide13.JPG",
                    "https://dl.dropboxusercontent.com/s/ojhnmedm3lzyysi/Slide14.JPG",
                    "https://dl.dropboxusercontent.com/s/sxngind2ogqhyva/Slide15.JPG",
                    "https://dl.dropboxusercontent.com/s/sf5536wh6wdfbg9/Slide16.JPG",
                    "https://dl.dropboxusercontent.com/s/gonjixrbe72jpj5/Slide17.JPG",
                    "https://dl.dropboxusercontent.com/s/o9tffs0hlvrgkh7/Slide18.JPG",
                    "https://dl.dropboxusercontent.com/s/ka5aennf3gg50fx/Slide19.JPG",
                    "https://dl.dropboxusercontent.com/s/gmazgxshf5m3evw/Slide20.JPG",
                    "https://dl.dropboxusercontent.com/s/rp9gjel1v8p6xs6/Slide21.JPG",
                    "https://dl.dropboxusercontent.com/s/cm07go7798azsiu/Slide22.JPG",
                    "https://dl.dropboxusercontent.com/s/tzoak9zjmq0193k/Slide23.JPG",
                    "https://dl.dropboxusercontent.com/s/wthenj0iosmudcq/Slide24.JPG",
                    "https://dl.dropboxusercontent.com/s/kcuie7xt86gkyyk/Slide25.JPG",
                    "https://dl.dropboxusercontent.com/s/4gtiliax1q1q41g/Slide26.JPG",
                    "https://dl.dropboxusercontent.com/s/jxjfaykqj8oxyb5/Slide27.JPG"

            };
    ArrayList<Bitmap> als=new ArrayList<>();
    ProgressBar pbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pbar = (ProgressBar) findViewById(R.id.pbar);
        for (int i = 0; i < strURL.length; i++) {
            downloadImages1(i);
        }
    }

    public void downloadImages1(int i) {
        if (isNetworkConnected()) {
            new DownloadImage().execute(strURL[i]);
        } else {
            Toast.makeText(this, "Connect To Interner", Toast.LENGTH_LONG).show();
        }

    }

    private boolean isNetworkConnected() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private class DownloadImage extends AsyncTask<String, Void, Bitmap> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            System.out.println("IN PRE_EXECUTE Checking..");
        }

        @Override
        protected Bitmap doInBackground(String... URL) {


            System.out.println("IN DO_IN_BG Checking..");
            String imageURL = URL[0];
            Bitmap bitmap = null;
            try {
                InputStream input = new java.net.URL(imageURL).openStream();
                bitmap = BitmapFactory.decodeStream(input);
                als.add(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return bitmap;

        }

        @Override
        protected void onPostExecute(Bitmap result)
        {

            if(als.size()==strURL.length)
            {
                GlobalApp.als=als;
                pbar.setVisibility(View.GONE);
                startActivity(new Intent(getApplicationContext(),CardLayoutDemo.class));
            }
        }
    }
}
