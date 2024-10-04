package com.mahdi_hassan_asif.worldflippers.opengl_es_setup.loaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.renderscript.Float3;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Loader {

    private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;
    private FloatBuffer normalBuffer;
    private IntBuffer indicesBuffer;
    private int indicesCount;
    private Float3 furthestPoint;

    public Loader(float[] vertex, float[] texture, int[] indices) {
        vertexBuffer = createFloatBuffer(vertex);
        textureBuffer = createFloatBuffer(texture);
        indicesBuffer = createIntBuffer(indices);
    }

    public Loader(float[] vertex, float[] texture, float[] normals, int[] indices) {
        this(vertex, texture, indices);
        normalBuffer = createFloatBuffer(normals);
    }

    public Loader(float[] vertex, float[] texture, float[] normals, int[] indices, int indicesLength) {
        this(vertex, texture, normals, indices);
        indicesCount = indicesLength;
    }

    public Loader(float[] vertex, float[] texture, float[] normals, int[] indices, int indicesLength, Float3 furthestPoint) {
        this(vertex, texture, normals, indices, indicesLength);
        this.furthestPoint = furthestPoint;
    }

    public static int loadShader(int type, String shaderCode) {
        int shader = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

    public static int loadTexture(final Context context, final int resourceId) {
        final int[] textureHandle = new int[1];
        GLES20.glGenTextures(1, textureHandle, 0);
        if (textureHandle[0] != 0) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inScaled = false;    // No pre-scaling
            final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);

            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
            GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

            GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
            bitmap.recycle();
        }
        return textureHandle[0];
    }

    public static FloatBuffer createFloatBuffer(float[] vertices) {
        FloatBuffer buffer = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(vertices).position(0);
        return buffer;
    }

    public IntBuffer createIntBuffer(int[] vertices) {
        IntBuffer buffer = ByteBuffer.allocateDirect(vertices.length * 4)
                .order(ByteOrder.nativeOrder()).asIntBuffer();
        buffer.put(vertices).position(0);
        return buffer;
    }

    public static void enableAttributes(int... attributes) {
        for (int i : attributes) {
            GLES20.glEnableVertexAttribArray(i);
        }
    }

    public static void disableAttributes(int... attributes) {
        for (int i : attributes) {
            GLES20.glDisableVertexAttribArray(i);
        }
    }

    public FloatBuffer getVertexBuffer() {
        return vertexBuffer;
    }

    public FloatBuffer getTextureBuffer() {
        return textureBuffer;
    }

    public FloatBuffer getNormalBuffer() {
        return normalBuffer;
    }

    public IntBuffer getIndicesBuffer() {
        return indicesBuffer;
    }

    public int getIndicesCount() {
        return indicesCount;
    }

    public Float3 getFurthestPoint() {
        return furthestPoint;
    }

    public void setFurthestPoint(Float3 furthestPoint) {
        this.furthestPoint = furthestPoint;
    }
}
