package com.applications.arunjotsingh.youlearn;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ViewPagerDemo extends AppCompatActivity {

    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    private boolean mVisible;
    Toolbar toolbar;
    Button screen;
    Button slideshow;
    Button home;
    View lineView;
    Timer timer;
    int page = 0;
    RelativeLayout outer;
    LinearLayout button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_demo);
        mCustomPagerAdapter = new CustomPagerAdapter(this);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        button=(LinearLayout)findViewById(R.id.button);
        outer=(RelativeLayout)findViewById(R.id.outer);
    }


    class CustomPagerAdapter extends PagerAdapter
    {

        Context mContext;
        LayoutInflater mLayoutInflater;
        protected void unbindDrawables(View view)
        {
            if (view.getBackground() != null)
            {
                view.getBackground().setCallback(null);
            }
            if (view instanceof ViewGroup)
            {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++)
                {
                    unbindDrawables(((ViewGroup) view).getChildAt(i));
                }
                ((ViewGroup) view).removeAllViews();
            }
        }
        public CustomPagerAdapter(Context context)
        {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return 27;
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {

            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageBitmap(GlobalApp.als.get(position));
            container.addView(itemView);
            return itemView;


        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
            unbindDrawables((View) object);
            object = null;
        }

    }
    public void toggle(View v) {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }
    public void play(View v)
    {
        pageSwitcher(10);
    }

    public void hide() {
        // Hide UI first
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar) findViewById(R.id.toolbarView);
        toolbar.setVisibility(View.GONE);

        screen = (Button) findViewById(R.id.Screen);
        screen.setBackgroundResource(R.drawable.exitfullscreen);

        slideshow = (Button) findViewById(R.id.playSlides);
        slideshow.setVisibility(View.GONE);

        home = (Button) findViewById(R.id.home);
        home.setVisibility(View.GONE);

        lineView = (View) findViewById(R.id.view);
        lineView.setVisibility(View.GONE);


        mVisible = false;


    }

    public void show() {
        // Show the system bar
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = (Toolbar) findViewById(R.id.toolbarView);
        toolbar.setVisibility(View.VISIBLE);

        screen = (Button) findViewById(R.id.Screen);
        screen.setBackgroundResource(R.drawable.fullscreen);

        slideshow = (Button) findViewById(R.id.playSlides);
        slideshow.setVisibility(View.VISIBLE);

        home = (Button) findViewById(R.id.home);
        home.setVisibility(View.VISIBLE);

        lineView = (View) findViewById(R.id.view);
        lineView.setVisibility(View.VISIBLE);


        mVisible = true;


    }
    public void pageSwitcher(int seconds)
    {
        timer = new Timer(); // At this line a new Thread will be created
        timer.scheduleAtFixedRate(new RemindTask(), 0, seconds * 1000); // delay
        // in
        // milliseconds
    }

    // this is an inner class...
    class RemindTask extends TimerTask {

        @Override
        public void run() {

            // As the TimerTask run on a seprate thread from UI thread we have
            // to call runOnUiThread to do work on UI thread.
            runOnUiThread(new Runnable() {
                public void run()
                {
                    page++;
                    if(page==27)
                    {
                        outer.setVisibility(View.GONE);
                        button.setVisibility(View.VISIBLE);
                    }
                    else
                    mViewPager.setCurrentItem(page++);
                }

            });

        }
    }
    public void quizstart(View v)
    {
        startActivity(new Intent(getApplicationContext(),QuestionsActivity.class));
    }
}
