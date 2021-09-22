package com.example.testmvvm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.databinding.DataBindingUtil;

import com.example.testmvvm.databinding.MypopBinding;

public class MyPop extends PopupWindow {
    int number = -1;

    @SuppressLint("ClickableViewAccessibility")
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
            myPopClickListener.takePic();
        });
        mypopBinding.pics.setOnClickListener(v -> {
            myPopClickListener.choosePic();
        });
        mypopBinding.cancel.setOnClickListener(v -> {
            myPopClickListener.cancel();
        });
    }

    MyPopClickListener myPopClickListener;

    public void setMyPopClickListener(MyPopClickListener myPopClickListener) {
        this.myPopClickListener = myPopClickListener;
    }
}
