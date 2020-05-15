package com.example.testmvvm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

import java.io.File;

public class User {
    public String name;
    public String psw;
    public Uri url;

    @BindingAdapter({"image"})
    public static void getImage(ImageView imageView,Uri url){

        Glide.with(imageView.getContext()).load(url).into(imageView);

    }

}
