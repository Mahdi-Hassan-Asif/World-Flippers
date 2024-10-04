package com.mahdi_hassan_asif.worldflippers.levels.arena_1;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.DragStartHelper;

import com.bumptech.glide.Glide;
import com.mahdi_hassan_asif.worldflippers.HomeActivity;
import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.databinding.ActivityLevelOneOneBinding;
import com.mahdi_hassan_asif.worldflippers.fragments.A1L1Fragment1;
import com.mahdi_hassan_asif.worldflippers.fragments.CharacterSelctionFragment;
import com.mahdi_hassan_asif.worldflippers.fragments.ChattingFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class LevelOneOneActivity extends AppCompatActivity {

    ActivityLevelOneOneBinding binding;
    private static final String API_KEY = "a924qo5Wv3uEEs7Z84GpHwS8asTzevrZbjt7lDbH";
    private static final String EPIC_API_URL = "https://api.nasa.gov/EPIC/api/natural/images?api_key=" + API_KEY;
    private static final String EARTH_API_URL = "https://api.nasa.gov/planetary/earth/imagery?lon=-95.33&lat=29.78&date=2018-01-01&dim=0.15&api_key=" + API_KEY;
    float startX = 0;
    float startY = 0;
    String text = "So, let's start! First we will learn how to identify the water pollution. You will be seen 4 different images. Choose one image which actually points the water pollution.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeActivity.fullScreenView(this);
        binding = ActivityLevelOneOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        new FetchEpicImagesTask().execute();

        Glide.with(this).load(R.drawable.water_pollution).centerCrop().transform(new BlurTransformation(15, 1)).into(binding.bgA1L1);

        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainerL1.getId(), new ChattingFragment(text, CharacterSelctionFragment.character_selected)).commit();

        binding.buttonBackAiL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.nextButtonL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.img1A1L1.setVisibility(View.GONE);
                binding.img2A1L1.setVisibility(View.GONE);
                binding.img3A1L1.setVisibility(View.GONE);
                binding.img4A1L1.setVisibility(View.GONE);
                binding.imgDropA1L1.setVisibility(View.GONE);
                binding.nextButtonL1.setVisibility(View.GONE);
                binding.textViewDropA1L1.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainerL1.getId(), new A1L1Fragment1()).commit();
            }
        });

//        binding.imgDropA1L1.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        startX = event.getX();
//                        startY = event.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        float movedX, movedY;
//                        movedX = event.getX() - startX;
//                        movedY = event.getY() - startY;
//
//                        v.setX(v.getX() + movedX);
//                        v.setY(v.getY() + movedY);
//                        break;
//                    case MotionEvent.ACTION_UP:
//
//                        break;
//
//                }
//
//                return true;
//            }
//        });

        ImageView imageView_1 = binding.img1A1L1;
        ImageView imageView_2 = binding.img2A1L1;
        ImageView imageView_3 = binding.img3A1L1;
        ImageView imageView_4 = binding.img4A1L1;
        ImageView imageView_drop = binding.imgDropA1L1;
        imageView_1.setTag("image_1");
        imageView_2.setTag("image_2");
        imageView_3.setTag("image_3");
        imageView_4.setTag("image_4");
        imageView_drop.setTag("image_drop");

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
                        imageView_drop.clearColorFilter();
//                        v.invalidate();
                        return true;

                    case DragEvent.ACTION_DROP:
                        ClipData.Item item = e.getClipData().getItemAt(0);
                        CharSequence dragData = item.getText();
                        if (v.getTag().equals("image_drop")) {
//                            imageView_drop.setImageDrawable(imageView_1.getDrawable());
//                            imageView_1.setImageDrawable(null);
//                            ((ImageView) v).setImageURI(e.getClipData().getItemAt(0).getUri());
//                            Bitmap bitmap = null;
//                            try {
//                                bitmap = MediaStore.Images.Media.getBitmap(LevelOneOneActivity.this.getContentResolver(), item.getUri());
//                            } catch (IOException ex) {
//                                throw new RuntimeException(ex);
//                            }
//                            ((ImageView) v).setImageBitmap(bitmap);

                            if (dragData.equals("image_1")) {
                                imageView_drop.setImageDrawable(imageView_1.getDrawable());
//                                imageView_1.setColorFilter(Color.LTGRAY);
                                imageView_1.setImageDrawable(null);
                                imageView_1.setBackgroundColor(Color.LTGRAY);
                            } else if (dragData.equals("image_2")) {
                                imageView_drop.setImageDrawable(imageView_2.getDrawable());
//                                imageView_2.setColorFilter(Color.LTGRAY);
                                imageView_2.setImageDrawable(null);
                                imageView_2.setBackgroundColor(Color.LTGRAY);
                            } else if (dragData.equals("image_3")) {
                                imageView_drop.setImageDrawable(imageView_3.getDrawable());
//                                imageView_3.setColorFilter(Color.LTGRAY);
                                imageView_3.setImageDrawable(null);
                                imageView_3.setBackgroundColor(Color.LTGRAY);
                            } else if (dragData.equals("image_4")) {
                                imageView_drop.setImageDrawable(imageView_4.getDrawable());
                                imageView_4.setImageDrawable(null);
                                imageView_4.setBackgroundColor(Color.LTGRAY);
                            }
                        }

//                        ((ImageView) v).clearColorFilter();
//                        v.invalidate();
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
//                        ((ImageView) v).clearColorFilter();
                        imageView_drop.clearColorFilter();
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


        imageView_1.setOnDragListener(dragListener);
        imageView_2.setOnDragListener(dragListener);
        imageView_3.setOnDragListener(dragListener);
        imageView_4.setOnDragListener(dragListener);
        imageView_drop.setOnDragListener(dragListener);
        imageView_1.setOnTouchListener(touchListener);
        imageView_2.setOnTouchListener(touchListener);
        imageView_3.setOnTouchListener(touchListener);
        imageView_4.setOnTouchListener(touchListener);

    }


    private class FetchEpicImagesTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(EPIC_API_URL);
//                URL url = new URL(EARTH_API_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // Close connections
                in.close();
                conn.disconnect();

                return content.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    JSONArray jsonArray = new JSONArray(result);
                    if (jsonArray.length() > 0) {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String image = jsonObject.getString("image");
                        String date = jsonObject.getString("date");

                        // Construct the image URL
//                        String imageUrl = "https://epic.gsfc.nasa.gov/archive/natural/" + date.replace("-", "/") + "/png/" + image + ".png";
                        int index = date.indexOf(" ");
//                        String imageUrl = "https://epic.gsfc.nasa.gov/archive/natural/" + "2024/09/25" + "/png/" + image + ".png";
                        String imageUrl = "https://epic.gsfc.nasa.gov/archive/natural/" + date.substring(0, index).replace('-', '/') + "/png/" + image + ".png";

                        // Load the image using Glide
                        Glide.with(LevelOneOneActivity.this)
                                .load(imageUrl)
                                .into(binding.imageView3);

                        binding.textView3.setText("Image: " + image + ", Date: " + date);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}