package com.mahdi_hassan_asif.worldflippers.opengl_es_setup;

import android.opengl.GLES20;

import com.mahdi_hassan_asif.worldflippers.opengl_es_setup.loaders.Loader;

import java.nio.FloatBuffer;

public class BasicModels {

    private final int program;
    private int location_vPosition;
    private int location_vColor;
    private int location_viewMatrix;
    private int location_projectionMatrix;
    private int location_transformMatrix;
    private FloatBuffer vertexBuffer;
    private float vertex = 1.0f;
    private float[] vertices = {
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f
    };
    private final float[] color = {
            0.0f, 1.0f, 1.0f, 1.0f,
            1.0f, 0.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 0.0f, 1.0f,
            1.0f, 1.0f, 1.0f, 1.0f
    };

    public BasicModels() {
        vertexBuffer = Loader.createFloatBuffer(vertices);
        int vertexShader = Loader.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = Loader.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
        initialiseLocations();
    }

    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "uniform mat4 viewMatrix;" +
                    "uniform mat4 projectionMatrix;" +
                    "uniform mat4 transformMatrix;" +
                    "void main() {" +
                    "gl_Position = projectionMatrix * viewMatrix * transformMatrix * vPosition;" +
                    "}";
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    private void initialiseLocations() {
        location_vPosition = GLES20.glGetAttribLocation(program, "vPosition");
        location_vColor = GLES20.glGetUniformLocation(program, "vColor");
        location_viewMatrix = GLES20.glGetUniformLocation(program, "viewMatrix");
        location_projectionMatrix = GLES20.glGetUniformLocation(program, "projectionMatrix");
        location_transformMatrix = GLES20.glGetUniformLocation(program, "transformMatrix");
    }

    public void enableAndPrepare(float[] viewMatrix, float[] projectionMatrix, float[] transformMatrix) {
        GLES20.glUseProgram(program);
        GLES20.glEnableVertexAttribArray(location_vPosition);
        GLES20.glVertexAttribPointer(location_vPosition, 3, GLES20.GL_FLOAT, false, 3 * Float.BYTES, vertexBuffer);
        GLES20.glUniform4fv(location_vColor, 1, color, 0);
        GLES20.glUniformMatrix4fv(location_viewMatrix, 1, false, viewMatrix, 0);
        GLES20.glUniformMatrix4fv(location_projectionMatrix, 1, false, projectionMatrix, 0);
        GLES20.glUniformMatrix4fv(location_transformMatrix, 1, false, transformMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertices.length / 3);
        GLES20.glDisableVertexAttribArray(location_vPosition);
    }

}
