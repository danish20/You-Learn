package com.applications.arunjotsingh.youlearn;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CardLayoutDemo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_layout_demo);
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
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

            holder.iv.setImageResource(GlobalApp.slides[position]);
            holder.tv.setText(GlobalApp.titles[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view,"Opening Slide "+(position+1),Snackbar.LENGTH_SHORT).show();
                    GlobalApp.viewedSlides.add(position);
                    Intent intent=new Intent(getApplicationContext(), ViewPagerDemo.class);

                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
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

    public void viewedSlides(View v)
    {
        startActivity(new Intent(getApplicationContext(),ViewedSlides.class));
    }
}
