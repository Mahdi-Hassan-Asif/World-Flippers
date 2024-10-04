package com.mahdi_hassan_asif.worldflippers.levels.arena_1;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Math.abs;

import com.mahdi_hassan_asif.worldflippers.HomeActivity;
import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.WorldRenderer;
import com.mahdi_hassan_asif.worldflippers.arenas.ArenaOneActivity;
import com.mahdi_hassan_asif.worldflippers.databinding.ActivityLevelThreeOneBinding;

public class LevelThreeOneActivity extends AppCompatActivity {


    ActivityLevelThreeOneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        HomeActivity.fullScreenView(this);
        binding = ActivityLevelThreeOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        int positionX = 0;

        binding.buttonCloseAiL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                startActivity(new Intent(LevelThreeOneActivity.this, ArenaOneActivity.class));
            }
        });

        binding.element1Star.post(new Runnable() {
            @Override
            public void run() {
                long time = SystemClock.uptimeMillis() % 2000L;
                float angle0 = 0.00090f * ((int) time);
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(binding.element1Star, "translationX",
//                        0f,1000f);
//                objectAnimator.setDuration(2000);
//                objectAnimator.start();

//                binding.element1Star.setTranslationX(binding.element1Star.getX()+WorldRenderer.moveWave);
//                binding.element1Star.setX(binding.element1Star.getX()+ WorldRenderer.moveWave);
                binding.element1Star.setX(binding.element1Star.getX()+angle0);
                binding.element1Star.postDelayed(this, 17);
//                binding.element1Star.postDelayed(this, (long)WorldRenderer.getFrameTimeSeconds());
            }
        });

        binding.element1Star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LevelThreeOneActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

}