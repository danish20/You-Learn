package com.applications.arunjotsingh.youlearn;

import android.app.Application;
import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Danish on 5/8/16.
 */
public class GlobalApp extends Application
{
    static ArrayList<Bitmap> als;
    static int[] slides=
            {
                    R.drawable.s1,
                    R.drawable.s1,
                    R.drawable.s1,
                    R.drawable.s1,
                    R.drawable.s1,
                    R.drawable.s1,
                    R.drawable.s1,
                    R.drawable.s1,
                    R.drawable.s1,
                    R.drawable.s1
            };
    static String[] titles=
            {
                    "Title 1",
                    "Title 2",
                    "Title 3",
                    "Title 4",
                    "Title 5",
                    "Title 6",
                    "Title 7",
                    "Title 8",
                    "Title 9",
                    "Title 10"
            };
   static ArrayList<Integer> viewedSlides=new ArrayList<>();
}
