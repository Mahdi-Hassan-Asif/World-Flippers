package com.mahdi_hassan_asif.worldflippers;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.animation.DecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.request.RequestOptions;
import com.mahdi_hassan_asif.worldflippers.databinding.ActivitySplachScreenBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class SplashScreen extends AppCompatActivity {

    ActivitySplachScreenBinding binding;
    int progress = 0;
    ObjectAnimator objectAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        HomeActivity.fullScreenView(this);
        binding = ActivitySplachScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        Glide.with(this).load(R.drawable.background_1).centerCrop().apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 1))).into(binding.bgImgSplashScreen);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (progress <= 100) {
                    binding.progressBar.setProgress(progress);
                    SystemClock.sleep(52);
                    progress += 2;
                }


//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        objectAnimator = ObjectAnimator.ofInt(binding.progressBar, "progress", 0, 100);
//                        objectAnimator.setDuration(3500);
//                        objectAnimator.setInterpolator(new DecelerateInterpolator());
//                        objectAnimator.start();
                        startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                        finish();
                    }
                });
            }
        });
    }
}