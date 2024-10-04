package com.mahdi_hassan_asif.worldflippers.opengl_es_setup;

import android.content.Context;
import android.opengl.GLES20;

import com.mahdi_hassan_asif.worldflippers.opengl_es_setup.loaders.Loader;
import com.mahdi_hassan_asif.worldflippers.opengl_es_setup.loaders.ObjLoader;

public class RawModels {

    private final int program;
    private int[] treeTexture;
    public Loader loader;

    public RawModels(Context context, int rawId, int[] textureIds) {
        this(context, rawId);
        treeTexture = new int[textureIds.length];
        for (int j = 0; j < textureIds.length; j++) {
            treeTexture[j] = Loader.loadTexture(context, textureIds[j]);
        }
    }

    public RawModels(Context context, int rawId, int textureId) {
        this(context, rawId);
        treeTexture = new int[1];
        treeTexture[0] = Loader.loadTexture(context, textureId);
    }

    private RawModels(Context context, int rawId) {
        loader = ObjLoader.loadOBJ(context, rawId);

        int vertexShader = Loader.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = Loader.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);
//        locations();
    }
    private final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "gl_Position = vPosition;" +
                    "}";
    private final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";
}
