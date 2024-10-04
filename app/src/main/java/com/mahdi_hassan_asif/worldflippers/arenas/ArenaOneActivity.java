package com.mahdi_hassan_asif.worldflippers.arenas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mahdi_hassan_asif.worldflippers.HomeActivity;
import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.databinding.ActivityArenaOneBinding;
import com.mahdi_hassan_asif.worldflippers.fragments.ChattingFragment;
import com.mahdi_hassan_asif.worldflippers.levels.arena_1.LevelOneOneActivity;
import com.mahdi_hassan_asif.worldflippers.levels.arena_1.LevelThreeOneActivity;
import com.mahdi_hassan_asif.worldflippers.levels.arena_1.LevelTwoOneActivity;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ArenaOneActivity extends AppCompatActivity {

    ActivityArenaOneBinding binding;
    String text = "Around 49 percent of the country's drinking well water - which almost everyone drinks - has unsafe limits of carcinogenic arsenic. A new study has found that the climate crisis is worsening, pointing to a looming public health crisis in Bangladesh. - Euronews\n" +
            "\n" +
            "At this rate, it will only take a few years for clean water to vanish from our site. \n" +
            "\n" +
            "It's high time we started taking action!\n" +
            "\n" +
            "Will you help us on this journey?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        HomeActivity.fullScreenView(this);
        binding = ActivityArenaOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

//        Glide.with(this).load(R.drawable.background_2).centerCrop().transform(new BlurTransformation(25, 1)).into(binding.imageView4);

        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainerArena1.getId(), new ChattingFragment(text, 2)).commit();

        binding.level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ArenaOneActivity.this, LevelOneOneActivity.class));
            }
        });

        binding.level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ArenaOneActivity.this, LevelTwoOneActivity.class));
            }
        });

        binding.buttonBackA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}