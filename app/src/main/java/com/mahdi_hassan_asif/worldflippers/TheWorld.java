package com.mahdi_hassan_asif.worldflippers;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.renderscript.Float3;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class TheWorld extends GLSurfaceView {

    WorldRenderer renderer;
    private final float ROATION_FACTOR = 180.0f / 720;
    private final float TRANSLATION_FACTOR = 0.002f;
    private float previousX;
    private float previousY;

    public TheWorld(Context context) {
        super(context);
        initialiseRenderer(context);
    }

    public TheWorld(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialiseRenderer(context);
    }

    private void initialiseRenderer(Context context) {
        renderer = new WorldRenderer(context);
        setEGLContextClientVersion(2);
        setRenderer(renderer);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {

//            case MotionEvent.ACTION_DOWN:
////                float normalisedX = (x / getWidth()) * 2 - 1;
////                float normalisedY = -((y / getHeight()) * 2 - 1); //same calculation :)
//                float normalisedX = (2.0f * x) / getWidth() - 1;
//                float normalisedY = -((2.0f * y) / getHeight() - 1);
//
//                float[] clipCords = {normalisedX, normalisedY, -1f, 1f};
//                float[] eyeCords = toEyeCords(clipCords);
//                Float3 worldRay = toWorldCords(eyeCords);
//
//                Log.d("TOUCH", "x: " + worldRay.x + "  y: " + worldRay.y + "  z: " + worldRay.z);
//                break;

            case MotionEvent.ACTION_MOVE:

                float px = (x - previousX) * TRANSLATION_FACTOR;
                float py = (y - previousY) * TRANSLATION_FACTOR;
                renderer.setPosition(new Float3(px, -py, 0));

                float rx = (x - previousX) * 0.04f;
                float ry = (y - previousY) * 0.04f;
                // reverse direction of rotation above the mid-line
                if (y > (float) getHeight() / 2) {
                    rx = rx * -1 ;
                }
                // reverse direction of rotation to left of the mid-line
                if (x < (float) getWidth() / 2) {
                    ry = ry * -1 ;
                }
                renderer.setAngle(
                        renderer.getAngle() +
                                ((rx + ry) * ROATION_FACTOR));
                break;
            default:
                renderer.setPosition(new Float3(0, 0, 0));
                renderer.setAngle(0f);
//
        }
//
        previousX = x;
        previousY = y;
        return true;
    }

//    private Float3 toWorldCords(float[] eyeCords) {
//        float[] invertedView = new float[16];
//        Matrix.invertM(invertedView, 0, renderer.getViewMatrix(), 0);
//        float[] rayWorld = new float[4];
//        Matrix.multiplyMV(rayWorld, 0, invertedView, 0, eyeCords, 0);
//        return new Float3(rayWorld[0], rayWorld[1], rayWorld[2]);
//    }
//
//    private float[] toEyeCords(float[] clipCords) {
//        float[] invertedProjection = new float[16];
//        Matrix.invertM(invertedProjection, 0, renderer.getProjectionMatrix(), 0);
//        float[] eyeCords = new float[4];
//        Matrix.multiplyMV(eyeCords, 0, invertedProjection, 0, clipCords, 0);
//        return new float[]{eyeCords[0], eyeCords[1], -1f, 0f};
//    }
}
