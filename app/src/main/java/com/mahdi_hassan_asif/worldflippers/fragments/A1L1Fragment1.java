
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
import android.widget.Button;
import android.widget.Toast;

import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.databinding.Fragment1A1L1Binding;


public class A1L1Fragment1 extends Fragment {

    Fragment1A1L1Binding binding;
    private int count = 0;
    String text = "Great! Now match the texts with the images for getting familiar with the sources which causes the water pollution";


    public A1L1Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Fragment1A1L1Binding.inflate(inflater, container, false);
        getParentFragmentManager().beginTransaction().replace(R.id.layout_chatting_container, new ChattingFragment(text, CharacterSelctionFragment.character_selected)).commit();

        Button button1 = binding.button1;
        Button button2 = binding.button2;
        Button button3 = binding.button3;
        Button button4 = binding.button4;
        Button button_option_1 = binding.buttonOption1;
        Button button_option_2 = binding.buttonOption2;
        Button button_option_3 = binding.buttonOption3;
        Button button_option_4 = binding.buttonOption4;
        button1.setTag("button1");
        button2.setTag("button2");
        button3.setTag("button3");
        button4.setTag("button4");
        button_option_1.setTag("button_option_1");
        button_option_2.setTag("button_option_2");
        button_option_3.setTag("button_option_3");
        button_option_4.setTag("button_option_4");

        View.OnDragListener dragListener = new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent e) {

                switch (e.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        if (e.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
//                            ((ImageView) v).setColorFilter(Color.BLUE);
//                            ((ImageView) v).setAlpha(0.5f);
//                            v.invalidate();
                            return true;
                        }
                        return false;

                    case DragEvent.ACTION_DRAG_ENTERED:
//                        imageView_drop.setColorFilter(Color.GREEN);
//                        ((ImageView) v).setColorFilter(Color.GREEN);
//                        v.invalidate();
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
//                        ((ImageView) v).setColorFilter(Color.BLUE);
//                        imageView_drop.clearColorFilter();
//                        v.invalidate();
                        return true;

                    case DragEvent.ACTION_DROP:
                        ClipData.Item item = e.getClipData().getItemAt(0);
                        CharSequence dragData = item.getText();
//                        if (v.getTag().equals("image_drop")) {
//
                            if (dragData.equals("button_option_1")) {
                                ((Button)v).setText(button_option_1.getText().toString());
                                ((Button)v).setBackgroundColor(Color.rgb(173,227,101));
                                button_option_1.setVisibility(View.GONE);
                                count++;
                            } else if (dragData.equals("button_option_2")) {
                                ((Button)v).setText(button_option_2.getText().toString());
                                ((Button)v).setBackgroundColor(Color.rgb(173,227,101));
                                button_option_2.setVisibility(View.GONE);
                                count++;
                            } else if (dragData.equals("button_option_3")) {
                                ((Button)v).setText(button_option_3.getText().toString());
                                ((Button)v).setBackgroundColor(Color.rgb(173,227,101));
                                button_option_3.setVisibility(View.GONE);
                                count++;
                            } else if (dragData.equals("button_option_4")) {
                                ((Button)v).setText(button_option_4.getText().toString());
                                ((Button)v).setBackgroundColor(Color.rgb(173,227,101));
                                button_option_4.setVisibility(View.GONE);
                                count++;
                            }
//
                        if (count == 4){
                            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container_l1, new A1L1Fragment2()).commit();
                        }
//                        }

//                        ((ImageView) v).clearColorFilter();
//                        v.invalidate();
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
//                        ((ImageView) v).clearColorFilter();
//                        imageView_drop.clearColorFilter();
//                        imageView_drop.invalidate();
//                        v.invalidate();
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

        button_option_1.setOnTouchListener(touchListener);
        button_option_2.setOnTouchListener(touchListener);
        button_option_3.setOnTouchListener(touchListener);
        button_option_4.setOnTouchListener(touchListener);
        button_option_1.setOnDragListener(dragListener);
        button_option_2.setOnDragListener(dragListener);
        button_option_3.setOnDragListener(dragListener);
        button_option_4.setOnDragListener(dragListener);
        button1.setOnDragListener(dragListener);
        button2.setOnDragListener(dragListener);
        button3.setOnDragListener(dragListener);
        button4.setOnDragListener(dragListener);

        return binding.getRoot();
    }
}