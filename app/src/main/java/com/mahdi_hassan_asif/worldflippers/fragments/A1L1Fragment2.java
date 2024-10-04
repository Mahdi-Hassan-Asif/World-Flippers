package com.mahdi_hassan_asif.worldflippers.fragments;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.databinding.Fragment2A1L1Binding;
import com.mahdi_hassan_asif.worldflippers.levels.arena_1.LevelTwoOneActivity;

public class A1L1Fragment2 extends Fragment {

    Fragment2A1L1Binding binding;
    private int count = 0;
    String text = "Good Job! Now drop the images which best fit with the texts. This section shows the different components cause water to be highly polluted!\nHInts: sulfur : a bit orangic.\nNItrogen : a bit greenish water. And so on.";

    public A1L1Fragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment2A1L1Binding.inflate(inflater, container, false);
        getParentFragmentManager().beginTransaction().replace(R.id.layout_chatting_container, new ChattingFragment(text, CharacterSelctionFragment.character_selected)).commit();

        ImageView imageView1 = binding.imageView1L1F2;
        ImageView imageView2 = binding.imageView2L1F2;
        ImageView imageView3 = binding.imageView3L1F2;
        ImageView imageView_option_1 = binding.imageViewOption1L1F2;
        ImageView imageView_option_2 = binding.imageViewOption2L1F2;
        ImageView imageView_option_3 = binding.imageViewOption3L1F2;
        imageView1.setTag("imageView1");
        imageView2.setTag("imageView2");
        imageView3.setTag("imageView3");
        imageView_option_1.setTag("imageView_option_1");
        imageView_option_2.setTag("imageView_option_2");
        imageView_option_3.setTag("imageView_option_3");

        View.OnDragListener dragListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent e) {

                switch (e.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
//
                            return true;
                        }
                        return false;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
                        return true;

                    case DragEvent.ACTION_DROP:
                        ClipData.Item item = e.getClipData().getItemAt(0);
                        CharSequence dragData = item.getText();

                        if (dragData.equals("imageView_option_1")) {
                            ((ImageView) v).setImageDrawable(imageView_option_1.getDrawable());
                            imageView_option_1.setVisibility(View.GONE);
                            count++;
                        } else if (dragData.equals("imageView_option_2")) {
                            ((ImageView) v).setImageDrawable(imageView_option_2.getDrawable());
                            imageView_option_2.setVisibility(View.GONE);
                            count++;
                        } else if (dragData.equals("imageView_option_3")) {
                            ((ImageView) v).setImageDrawable(imageView_option_3.getDrawable());
                            imageView_option_3.setVisibility(View.GONE);
                            count++;
                        }

                        if (count == 3) {
                            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_l1, new RewardFragment(LevelTwoOneActivity.class)).commit();
                        }
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;

                    default:
                        Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.");
                        break;
                }

                return false;
            }
        };

        View.OnTouchListener touchListener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
                        ClipData dragData = new ClipData(
                                (CharSequence) v.getTag(),
                                new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                                item);

                        View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
                        v.startDragAndDrop(dragData,
                                myShadow,
                                this,
                                0
                        );
                        break;
                }

                return true;
            }
        };

        imageView_option_1.setOnTouchListener(touchListener);
        imageView_option_2.setOnTouchListener(touchListener);
        imageView_option_3.setOnTouchListener(touchListener);
        imageView1.setOnDragListener(dragListener);
        imageView2.setOnDragListener(dragListener);
        imageView3.setOnDragListener(dragListener);
        imageView_option_1.setOnDragListener(dragListener);
        imageView_option_2.setOnDragListener(dragListener);
        imageView_option_3.setOnDragListener(dragListener);

        return binding.getRoot();
    }
}