package com.mahdi_hassan_asif.worldflippers.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.SelectArenaActivity;
import com.mahdi_hassan_asif.worldflippers.databinding.FragmentChattingBinding;

public class ChattingFragment extends Fragment {

    FragmentChattingBinding binding;

    private String text = "We are living in a world where everything around us is getting highly polluted. " +
            "Day by day its getting harder to get fresh water, air and clean energy. Our environment is getting worse." +
            "The world is sick. So, Its your time to save the world. Are you ready to explore what happened to the earth and save it?? Please click continue to start exploring!";
    private int character_count = 0;

    public ChattingFragment() {
    }

    public ChattingFragment(String text, int character_count) {
        this.text = text;
        this.character_count = character_count;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChattingBinding.inflate(inflater, container, false);

        if (character_count == 0){
            binding.characterFemaleChat.setImageDrawable(getContext().getDrawable(R.drawable.male));
        } else if (character_count == 1) {
            binding.characterFemaleChat.setImageDrawable(getContext().getDrawable(R.drawable.female));
        } else if (character_count == 2) {
            binding.characterFemaleChat.setImageDrawable(getContext().getDrawable(R.drawable.female_sad));
        }

        binding.buttonStartChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().remove(ChattingFragment.this).commit();
                if (SelectArenaActivity.binding != null) {
                    SelectArenaActivity.binding.arenaContainerLayout.setVisibility(View.VISIBLE);
                    SelectArenaActivity.binding.bgImgSelectArena.setVisibility(View.VISIBLE);
                }
            }
        });

        final int[] index = {0};
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (index[0]< text.length()){
                    binding.textViewText.setText(text.substring(0,index[0]+1));
                    index[0]++;
                    handler.postDelayed(this,60);
//                    binding.animatedText.postDelayed(this,100);
                }
            }
        };
        handler.post(runnable);

        return binding.getRoot();
    }
}