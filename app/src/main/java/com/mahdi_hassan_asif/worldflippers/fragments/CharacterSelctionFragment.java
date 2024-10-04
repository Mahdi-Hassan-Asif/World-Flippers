package com.mahdi_hassan_asif.worldflippers.fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.databinding.FragmentCharacterSelctionBinding;

public class CharacterSelctionFragment extends Fragment {


    FragmentCharacterSelctionBinding binding;
    public static int character_selected = 1;
    String text = "We are living in a world where everything around us is getting highly polluted. " +
            "Day by day its getting harder to get fresh water, air and clean energy. Our environment is getting worse." +
            "The world is sick. So, Its your time to save the world. Are you ready to explore what happened to the earth and save it?? Please click continue to start exploring!";


    public CharacterSelctionFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterSelctionBinding.inflate(inflater, container, false);

        binding.characterMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.characterMale.setBackgroundColor(Color.argb(255, 255, 255, 255));
                binding.characterFemale.setBackgroundColor(Color.argb(0, 124, 203, 235));
                character_selected = 0;
            }
        });
        binding.characterFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.characterFemale.setBackgroundColor(Color.argb(255, 255, 255, 255));
                binding.characterMale.setBackgroundColor(Color.argb(0, 124, 203, 235));
                character_selected = 1;
            }
        });

        binding.buttonContinueFr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction().remove(CharacterSelctionFragment.this).commit();
                getParentFragmentManager().beginTransaction().replace(R.id.layout_fragment_container, new ChattingFragment(text, character_selected)).commit();
            }
        });

        return binding.getRoot();
    }
}