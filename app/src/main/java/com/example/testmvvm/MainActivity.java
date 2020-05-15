package com.example.testmvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.testmvvm.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int number = -1;
    String TAG = "aaaa";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.userTV.setOnClickListener(v -> {
            User user = new User();
            user.name = "haha";
            user.psw = "123";
            binding.setUser(user);
        });


        binding.pswTV.setOnClickListener(v -> {
            User user = new User();
            user.name = "heihei";
            user.psw = "456";
            binding.setUser(user);
        });

        MyPop popupWindow = new MyPop(this);

        popupWindow.setMyPopClickListener(new MyPopClickListener() {
            @Override
            public void Click1() {

                CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                try {
                    number = cameraManager.getCameraIdList().length;
                } catch (CameraAccessException e) {
                    Toast.makeText(MainActivity.this, "获取摄像头数据失败" + e, Toast.LENGTH_SHORT).show();
                    number = -1;
                    e.printStackTrace();
                }

                if (number == -1 || number == 0) {
                    Toast.makeText(MainActivity.this, "未检测到摄像头", Toast.LENGTH_SHORT).show();
                } else {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        try {
                            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 666);
                        } catch (ActivityNotFoundException e) {
                            Log.e(TAG, "Click1: " + e);
                            Toast.makeText(MainActivity.this, "未找到相机应用", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 555);
                    }
                }
            }

            @Override
            public void Click2() {
                Log.i(TAG, "Click2: ");
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intentToPickPic = new Intent(Intent.ACTION_PICK);
                    intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                    startActivityForResult(intentToPickPic, 777);
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 888);
                }
            }

            @Override
            public void Click3() {
                popupWindow.dismiss();
            }

        });

        binding.choosePic.setOnClickListener(v -> {
            popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 777 && resultCode == RESULT_OK) {
            List<Uri> uris = new ArrayList<>();
            for (int i = 0; i < 30; i++) {
                uris.add(data.getData());
            }
            binding.rv.setAdapter(new MyAdapter(uris));
            binding.rv.setLayoutManager(new GridLayoutManager(this, 3));

        }
    }
}
