package com.mahdi_hassan_asif.worldflippers.arenas;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mahdi_hassan_asif.worldflippers.HomeActivity;
import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.databinding.ActivityArenaThreeBinding;

public class ArenaThreeActivity extends AppCompatActivity {

    ActivityArenaThreeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        HomeActivity.fullScreenView(this);
        binding = ActivityArenaThreeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}