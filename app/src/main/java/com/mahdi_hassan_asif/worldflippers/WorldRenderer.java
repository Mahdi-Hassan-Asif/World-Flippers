package com.mahdi_hassan_asif.worldflippers;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;
import android.renderscript.Float3;

import com.mahdi_hassan_asif.worldflippers.opengl_es_setup.BasicModels;
import com.mahdi_hassan_asif.worldflippers.opengl_es_setup.WaterModel;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class WorldRenderer implements GLSurfaceView.Renderer {


    Context context;
    BasicModels square;
    private float[] viewMatrix = new float[16];
    private float[] projectionMatrix = new float[16];
    float[] transformationMatrix = new float[16];
    private float angle = 0;
    private Float3 position = new Float3(0, 0, 0);
    WaterModel water;
    private static final float WAVE_SPEED = 0.3f;
    public static float moveWave = 0;
    private static long lastFrameTime;
    private static float delta = 0;

    public WorldRenderer(Context context) {
        this.context = context;
    }


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
//        GLES20.glClearColor(0.4f, 0.9f, 1.0f, 0.0f);
        GLES20.glClearColor((30.0f / 255.0f), 70.0f / 255.0f, 100.0f / 255.0f, 0.5f);
        square = new BasicModels();
        water = new WaterModel(context);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        GLES20.glViewport(0, 0, i, i1);
        float ratio = (float) i / i1;
        Matrix.setLookAtM(viewMatrix, 0, 0, 0, -2f, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
//        Matrix.perspectiveM(projectionMatrix, 0, 45, (float) i / (float) i1, 0.1f, 100.0f);
        Matrix.frustumM(projectionMatrix, 0, ratio, -ratio, -1, 1, 2f, 100.0f);
        Matrix.setIdentityM(transformationMatrix, 0);
//        Matrix.rotateM(transformationMatrix, 0, -85, 1f, 0f, 0f);
        Matrix.scaleM(transformationMatrix, 0, 3f, 2f, 2f);
        Matrix.rotateM(transformationMatrix, 0,180, 0f, 0f, 1f);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
//        long time = SystemClock.uptimeMillis() % 4000L;
//        float angle0 = 0.090f * ((int) time);
//        Matrix.rotateM(transformationMatrix, 0, angle, 0f, 0f, -1f);
        Matrix.translateM(transformationMatrix, 0, position.x, position.y, position.z);
//        square.enableAndPrepare(viewMatrix, projectionMatrix, transformationMatrix);


        moveWave += WAVE_SPEED * getFrameTimeSeconds();
        moveWave %= 1;
        water.enableAndPrepare(moveWave, R.drawable.grass, R.drawable.grass, viewMatrix, projectionMatrix, new Float3(10, 1, 0), new Float3(-10, 17, 10));
        water.drawWater(transformationMatrix);
        water.disableAttributes();

        long currentTime = getCurrentTime();
        delta = (currentTime - lastFrameTime) / 1000f;
        lastFrameTime = currentTime;

    }

    public float[] getViewMatrix() {
        return viewMatrix;
    }

    public void setViewMatrix(float[] viewMatrix) {
        this.viewMatrix = viewMatrix;
    }

    public float[] getProjectionMatrix() {
        return projectionMatrix;
    }

    public void setProjectionMatrix(float[] projectionMatrix) {
        this.projectionMatrix = projectionMatrix;
    }

    public static float getFrameTimeSeconds() {
        return delta;
    }

    private long getCurrentTime() {
        return System.nanoTime() / 1000000;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public Float3 getPosition() {
        return position;
    }

    public void setPosition(Float3 position) {
        this.position = position;
    }
}
