package com.applications.arunjotsingh.youlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Danish on 5/8/16.
 */
public class ViewedSlides extends AppCompatActivity
{
    ArrayList<Integer> pics=new ArrayList<>();
    ArrayList<String> titles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_layout_demo);
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        for(int i=0;i<GlobalApp.viewedSlides.size();i++)
        {
            pics.add(GlobalApp.slides[GlobalApp.viewedSlides.get(i)]);
            titles.add(GlobalApp.titles[GlobalApp.viewedSlides.get(i)]);
        }
        list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
        list.setAdapter(new MyAdapter(this));
        list.setHasFixedSize(true);
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder>
    {


        private Activity host;
        private final LayoutInflater inflater;

        public MyAdapter(Activity activity) {
            host = activity;
            inflater = LayoutInflater.from(host);
        }
        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(inflater.inflate(R.layout.category_single, parent, false));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {

            holder.iv.setImageResource(pics.get(position));
            holder.tv.setText(titles.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Opening Slide " + (position + 1), Snackbar.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), ViewPagerDemo.class);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return pics.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder
    {
        ImageView iv;
        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            iv=(ImageView)itemView.findViewById(R.id.imgCat);
            tv=(TextView)itemView.findViewById(R.id.titleCat);

        }
    }
}
