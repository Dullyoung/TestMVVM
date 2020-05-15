package com.example.testmvvm;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.PopupWindow;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.example.testmvvm.databinding.MypopBinding;

public class MyPop extends PopupWindow {
    int number = -1;

    public MyPop(Context context) {
        super(context);

        MypopBinding mypopBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.mypop, null, false);
        setContentView(mypopBinding.getRoot());
        setOutsideTouchable(false);
        setAnimationStyle(R.style.pop);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(0x66000000));
        mypopBinding.getRoot().setOnTouchListener((v, event) -> {

            if (event.getAction() == MotionEvent.ACTION_UP && v.getY() < mypopBinding.menu.getTop()) {
                dismiss();
            }
            return true;
        });
        mypopBinding.camera.setOnClickListener(v -> {
            myPopClickListener.Click1();
        });
        mypopBinding.pics.setOnClickListener(v -> {
            myPopClickListener.Click2();
        });
        mypopBinding.cancel.setOnClickListener(v -> {
            myPopClickListener.Click3();
        });
    }

    MyPopClickListener myPopClickListener;

    public void setMyPopClickListener(MyPopClickListener myPopClickListener) {
        this.myPopClickListener = myPopClickListener;
    }
}
