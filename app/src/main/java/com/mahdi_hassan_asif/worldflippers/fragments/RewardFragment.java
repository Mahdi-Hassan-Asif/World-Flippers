package com.mahdi_hassan_asif.worldflippers.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.databinding.FragmentRewardBinding;
import com.mahdi_hassan_asif.worldflippers.levels.arena_1.LevelOneOneActivity;
import com.mahdi_hassan_asif.worldflippers.levels.arena_1.LevelThreeOneActivity;
import com.mahdi_hassan_asif.worldflippers.levels.arena_1.LevelTwoOneActivity;

public class RewardFragment extends Fragment {

    FragmentRewardBinding binding;
    Class class_next;

    public RewardFragment() {
    }

    public RewardFragment(Class class_next) {
        this.class_next = class_next;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRewardBinding.inflate(inflater, container, false);
        binding.buttonNextReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), class_next));
                getActivity().finish();
            }
        });
        binding.buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), LevelOneOneActivity.class));
                Intent intent = getActivity().getIntent();
                getActivity().finish();
                startActivity(intent);
            }
        });
        return binding.getRoot();
    }
}