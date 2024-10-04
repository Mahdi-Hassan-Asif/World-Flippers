package com.mahdi_hassan_asif.worldflippers.levels.arena_1;

import static java.lang.Math.abs;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mahdi_hassan_asif.worldflippers.HomeActivity;
import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.SelectArenaActivity;
import com.mahdi_hassan_asif.worldflippers.arenas.ArenaOneActivity;
import com.mahdi_hassan_asif.worldflippers.databinding.ActivityLevelTwoOneBinding;
import com.mahdi_hassan_asif.worldflippers.fragments.CharacterSelctionFragment;
import com.mahdi_hassan_asif.worldflippers.fragments.ChattingFragment;
import com.mahdi_hassan_asif.worldflippers.fragments.RewardFragment;
import com.mahdi_hassan_asif.worldflippers.levels.puzzle.PuzzlePiece;
import com.mahdi_hassan_asif.worldflippers.levels.puzzle.TouchListener;

import java.util.ArrayList;

public class LevelTwoOneActivity extends AppCompatActivity {

    ActivityLevelTwoOneBinding binding;
    private int submit = 0;
    String text = "Now here is a water pond with full of wastes and trashes in it. Let's clean it! Drag the trashes and drop it to the dustbin...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.fullScreenView(this);
        binding = ActivityLevelTwoOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainerL2.getId(), new ChattingFragment(text, CharacterSelctionFragment.character_selected)).commit();

        ImageView imageView_waste_1 = binding.imageViewWaste1;
        ImageView imageView_waste_2 = binding.imageViewWaste2;
        ImageView imageView_waste_3 = binding.imageViewWaste3;
        ImageView imageView_waste_4 = binding.imageViewWaste4;
        ImageView imageView_waste_5 = binding.imageViewWaste5;
        ImageView imageView_trash = binding.imageViewTrash;
        imageView_waste_1.setTag("image_waste_1");
        imageView_waste_2.setTag("image_waste_2");
        imageView_waste_3.setTag("image_waste_3");
        imageView_waste_4.setTag("image_waste_4");
        imageView_waste_5.setTag("image_waste_5");
        imageView_trash.setTag("image_trash");

        binding.buttonSubmitA1L2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (submit >= 5) {
                    binding.materialCardView.setVisibility(View.GONE);
                    binding.imageViewTrash.setVisibility(View.GONE);
                    binding.buttonSubmitA1L2.setVisibility(View.GONE);
                    getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainerL2.getId(), new RewardFragment(LevelThreeOneActivity.class)).commit();
                } else {
                    Toast.makeText(LevelTwoOneActivity.this, "Please Clean all the trashes!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.buttonBackAiL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                startActivity(new Intent(LevelTwoOneActivity.this, ArenaOneActivity.class));
            }
        });

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
                        imageView_trash.setAlpha(0.5f);
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
                        imageView_trash.setAlpha(1.0f);
                        return true;

                    case DragEvent.ACTION_DROP:
                        ClipData.Item item = e.getClipData().getItemAt(0);
                        CharSequence dragData = item.getText();

                        if (dragData.equals("image_waste_1")) {
                            imageView_waste_1.setVisibility(View.GONE);
                            submit++;
                        } else if (dragData.equals("image_waste_2")) {
                            imageView_waste_2.setVisibility(View.GONE);
                            submit++;
                        } else if (dragData.equals("image_waste_3")) {
                            imageView_waste_3.setVisibility(View.GONE);
                            submit++;
                        } else if (dragData.equals("image_waste_4")) {
                            imageView_waste_4.setVisibility(View.GONE);
                            submit++;
                        } else if (dragData.equals("image_waste_5")) {
                            imageView_waste_5.setVisibility(View.GONE);
                            submit++;
                        }


                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        imageView_trash.setAlpha(1.0f);
                        return true;

                    default:
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

                    case MotionEvent.ACTION_MOVE:
//                        v.setAlpha(0.0f);
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
                    case MotionEvent.ACTION_UP:
//                        v.setAlpha(1.0f);
                        break;
                }

                return true;
            }
        };

        imageView_waste_1.setOnTouchListener(touchListener);
        imageView_waste_2.setOnTouchListener(touchListener);
        imageView_waste_3.setOnTouchListener(touchListener);
        imageView_waste_4.setOnTouchListener(touchListener);
        imageView_waste_5.setOnTouchListener(touchListener);
        imageView_trash.setOnDragListener(dragListener);
        imageView_waste_1.setOnDragListener(dragListener);
        imageView_waste_2.setOnDragListener(dragListener);
        imageView_waste_3.setOnDragListener(dragListener);
        imageView_waste_4.setOnDragListener(dragListener);
        imageView_waste_5.setOnDragListener(dragListener);

    }

}