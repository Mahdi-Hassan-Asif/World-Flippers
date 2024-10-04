package com.mahdi_hassan_asif.worldflippers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mahdi_hassan_asif.worldflippers.arenas.ArenaFourActivity;
import com.mahdi_hassan_asif.worldflippers.arenas.ArenaOneActivity;
import com.mahdi_hassan_asif.worldflippers.arenas.ArenaThreeActivity;
import com.mahdi_hassan_asif.worldflippers.arenas.ArenaTwoActivity;
import com.mahdi_hassan_asif.worldflippers.databinding.ActivitySelectArenaBinding;
import com.mahdi_hassan_asif.worldflippers.fragments.CharacterSelctionFragment;
import com.mahdi_hassan_asif.worldflippers.fragments.SettingsFragment;

import jp.wasabeef.glide.transformations.BlurTransformation;


public class SelectArenaActivity extends AppCompatActivity {


    public static ActivitySelectArenaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        HomeActivity.fullScreenView(this);
        binding = ActivitySelectArenaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        Glide.with(this).load(R.drawable.background_2).centerCrop().transform(new BlurTransformation(5,1)).into(binding.bgImgSelectArena);

        if (!getIntent().getBooleanExtra("continue", false)){
            getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, new CharacterSelctionFragment()).commit();
        } else {
            binding.arenaContainerLayout.setVisibility(View.VISIBLE);
        }


        binding.imageViewArena1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectArenaActivity.this, ArenaOneActivity.class));
            }
        });

        binding.buttonWp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectArenaActivity.this, ArenaOneActivity.class));
            }
        });

        binding.imageViewArena2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectArenaActivity.this, "Please Complete the Previous Arena to Unlock!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(SelectArenaActivity.this, ArenaTwoActivity.class));
            }
        });
        binding.imageViewArena3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectArenaActivity.this, "Please Complete the Previous Arena to Unlock!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(SelectArenaActivity.this, ArenaThreeActivity.class));
            }
        });
        binding.imageViewArena4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectArenaActivity.this, "Please Complete the Previous Arena to Unlock!", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(SelectArenaActivity.this, ArenaFourActivity.class));
            }
        });


        binding.imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, new SettingsFragment()).commit();
            }
        });
        binding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectArenaActivity.this, ArenaOneActivity.class));
            }
        });

        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.imageViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectArenaActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });
        binding.imageViewPrivious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectArenaActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}