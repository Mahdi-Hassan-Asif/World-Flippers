package com.mahdi_hassan_asif.worldflippers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mahdi_hassan_asif.worldflippers.databinding.ActivityHomeBinding;
import com.mahdi_hassan_asif.worldflippers.fragments.SettingsFragment;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
//    String text = "hi, this is our first character by character text animation! And I'm very happy today to implement it! How are you today?";
    String text = "Hello there, We're team questers! And we're very happy to see you in 'World Flippers'! How are you today?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        fullScreenView(this);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        final int[] index = {0};
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (index[0]< text.length()){
                    binding.animatedText.setText(text.substring(0,index[0]+1));
                    index[0]++;
                    handler.postDelayed(this,80);
//                    binding.animatedText.postDelayed(this,100);
                }
            }
        };
        handler.post(runnable);
        Intent intent = new Intent(HomeActivity.this, SelectArenaActivity.class);

        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("continue", false);
                startActivity(intent);
            }
        });

        binding.buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("continue", true);
                startActivity(intent);
            }
        });

        binding.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_layout, new SettingsFragment()).commit();
            }
        });

        binding.buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

//        binding.button3Health.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this, "This will show how much greener you made your world!", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    public static void fullScreenView(AppCompatActivity context) {
//        context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        context.getSupportActionBar().hide();
        context.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}