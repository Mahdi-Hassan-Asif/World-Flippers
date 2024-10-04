package com.mahdi_hassan_asif.worldflippers.opengl_es_setup;

import android.content.Context;
import android.opengl.GLES20;
import android.renderscript.Float3;

import com.mahdi_hassan_asif.worldflippers.R;
import com.mahdi_hassan_asif.worldflippers.opengl_es_setup.loaders.Loader;

import java.nio.FloatBuffer;

public class WaterModel {
    private final int program;
    private final FloatBuffer vertexBuffer;
    private final float[] vertices = {
            -1, -1,
            -1, 1,
            1, -1,
            1, -1,
            -1, 1,
            1, 1
    };
    int duDvTexture;
    int normalMapTexture;
    int positionHandle;
    int reflectionTL;
    int refractionTL;
    int duDvTL;
    int normalMapTL;
    int projectionMHandle;
    int viewMHandle;
    int transformationMHandle;
    int moveFactorL;
    int cameraPositionL;
    int lightColorL;
    int lightPositionL;

    public WaterModel(Context context) {
        vertexBuffer = Loader.createFloatBuffer(vertices);
        int vertexShader = Loader.loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER);
        int fragmentShader = Loader.loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER);
        program = GLES20.glCreateProgram();
        GLES20.glAttachShader(program, vertexShader);
        GLES20.glAttachShader(program, fragmentShader);
        GLES20.glLinkProgram(program);

        duDvTexture = Loader.loadTexture(context, R.drawable.water_dudv);
        normalMapTexture = Loader.loadTexture(context, R.drawable.water_normal_map);

        positionHandle = GLES20.glGetAttribLocation(program, "position");
        reflectionTL = GLES20.glGetUniformLocation(program, "reflectionTexture");
        refractionTL = GLES20.glGetUniformLocation(program, "refractionTexture");
        duDvTL = GLES20.glGetUniformLocation(program, "duDvTexture");
        normalMapTL = GLES20.glGetUniformLocation(program, "normalMapTexture");
        projectionMHandle = GLES20.glGetUniformLocation(program, "projectionMatrix");
        viewMHandle = GLES20.glGetUniformLocation(program, "viewMatrix");
        transformationMHandle = GLES20.glGetUniformLocation(program, "transformationM");
        moveFactorL = GLES20.glGetUniformLocation(program, "moveFactor");
        cameraPositionL = GLES20.glGetUniformLocation(program, "cameraPosition");
        lightColorL = GLES20.glGetUniformLocation(program, "lightColor");
        lightPositionL = GLES20.glGetUniformLocation(program, "lightPosition");
    }

    private static final String VERTEX_SHADER =
            "attribute vec2 position;" +
                    "varying vec2 textureCords;" +
                    "varying vec4 clipSpace;" +
                    "varying vec3 toCameraVector;" +
                    "varying vec3 fromLightVector;" +
                    " uniform mat4 projectionMatrix;" +
                    " uniform mat4 viewMatrix;" +
                    " uniform mat4 transformationM;" +
                    " uniform vec3 cameraPosition;" +
                    " uniform vec3 lightPosition; " +
                    " const float tiling = 6.0;" +
                    " void main(){" +
                    "  vec4 worldPosition = transformationM * vec4(position.x, position.y, 0.0,  1.0);" +
                    "  clipSpace = projectionMatrix * viewMatrix * worldPosition;" +
                    "  gl_Position = clipSpace;" +
                    "  toCameraVector = cameraPosition - worldPosition.xyz;" +
                    "  textureCords = vec2(position.x/2.0 + 0.5,position.y/2.0 + 0.5) * tiling;" +
                    "  fromLightVector = worldPosition.xyz - lightPosition;" +
//                    "  textureCords = vec2(position.x/2.0 + 0.5,position.y/2.0 + 0.5);" +
//                    "  textureCords = vec2((position.x+1.0)/2.0,1.0-(position.y+1.0)/2.0);" +
                    "     }    ";
    private static final String FRAGMENT_SHADER =
            "precision mediump float;" +
                    "uniform sampler2D reflectionTexture;" +
                    "uniform sampler2D refractionTexture;" +
                    "uniform sampler2D duDvTexture;" +
                    "uniform sampler2D normalMapTexture;" +
                    "uniform vec3 lightColor;" +
                    "uniform float moveFactor;" +
                    "varying vec2 textureCords;" +
                    "varying vec4 clipSpace;" +
                    "varying vec3 toCameraVector;" +
                    "varying vec3 fromLightVector;" +
                    "const float waveStrength = 0.02;" +
                    "const float shineDamper = 20.0;" +
                    "const float reflectivity = 0.6;" +
                    "" +
                    "void main(){" +
                    "vec2 ndc = (clipSpace.xy/clipSpace.w)/2.0 + 0.5;" +
                    "vec2 refractionTCords = vec2(ndc.x , ndc.y);" +
                    "vec2 reflectionTCords = vec2(ndc.x , -ndc.y);" +

//                    "vec2 distortion1 = (texture2D(duDvTexture, vec2(textureCords.x + moveFactor,textureCords.y)).rg * 2.0 - 1.0) * waveStrength;" +
//                    "vec2 distortion2 = (texture2D(duDvTexture, vec2(- textureCords.x + moveFactor,textureCords.y + moveFactor)).rg * 2.0 - 1.0) * waveStrength;" +
//                    "vec2 totalDistortion = distortion1 + distortion2;" +

                    "vec2 distortedTexCords = texture2D(duDvTexture, vec2(textureCords.x + moveFactor,textureCords.y)).rg * 0.1;" +
                    "distortedTexCords = textureCords + vec2(distortedTexCords.x, distortedTexCords.y + moveFactor);" +
                    "vec2 totalDistortion = (texture2D(duDvTexture, distortedTexCords).rg * 2.0 - 1.0) * waveStrength;" +

                    "refractionTCords += totalDistortion;" +
                    "refractionTCords = clamp(refractionTCords, 0.001, 0.999);" +

                    "reflectionTCords += totalDistortion;" +
                    "reflectionTCords.x = clamp(reflectionTCords.x, 0.001, 0.999);" +
                    "reflectionTCords.y = clamp(reflectionTCords.y, -0.999, -0.001);" +

                    "vec4 reflectColor = texture2D(reflectionTexture, reflectionTCords);" +
                    "vec4 refractColor = texture2D(refractionTexture, refractionTCords);" +

                    "vec3 viewVector = normalize(toCameraVector);" +
                    "float refractiveFactor = dot(viewVector, vec3(0.0, 1.0, 0.0));" +
                    "refractiveFactor = pow(refractiveFactor, 10.0);" +

                    "vec4 normalMapColor = texture2D(normalMapTexture, distortedTexCords);" +
                    "vec3 normal = vec3(normalMapColor.r * 2.0 - 1.0, normalMapColor.b, normalMapColor.g * 2.0 - 1.0);" +
                    "normal = normalize(normal);" +

                    "vec3 reflectedLight = reflect(normalize(fromLightVector), normal);" +
                    "float specular = max(dot(reflectedLight, viewVector), 0.0);" +
                    "specular = pow(specular, shineDamper);" +
                    "vec3 specularHighlights = lightColor * specular * reflectivity;" +

                    "vec4 colorMix = mix(reflectColor, refractColor, refractiveFactor);" +
                    "gl_FragColor = mix(colorMix, vec4(0.2, 0.8, 0.8, 1.0), 1.0) + vec4(specularHighlights, 0.0);" +
//                    "gl_FragColor = texture2D(reflectionTexture, reflectionTCords);" +
                    "}";


    public void enableAndPrepare(float move, int reflectionT, int refractionT, float[] viewM, float[] projectionM, Float3 cameraPosition, Float3 lightPosition) {
        GLES20.glUseProgram(program);
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 2, GLES20.GL_FLOAT, false, 2 * 4, vertexBuffer);
        GLES20.glUniformMatrix4fv(projectionMHandle, 1, false, projectionM, 0);
        GLES20.glUniformMatrix4fv(viewMHandle, 1, false, viewM, 0);

        uniformTextures(reflectionTL, refractionTL, duDvTL, normalMapTL);
        activeAndBindTextures(reflectionT,refractionT, duDvTexture,normalMapTexture);

        GLES20.glUniform1f(moveFactorL, move);
        GLES20.glUniform3f(cameraPositionL, cameraPosition.x, cameraPosition.y, cameraPosition.z);
        GLES20.glUniform3f(lightPositionL,lightPosition.x, lightPosition.y, lightPosition.z);
        GLES20.glUniform3f(lightColorL,1,1,1);
    }

    public void drawWater(float[] transformationM) {
        GLES20.glUniformMatrix4fv(transformationMHandle, 1, false, transformationM, 0);
//        GLES20.glDrawElements(GLES20.GL_TRIANGLES,indices.length,GLES20.GL_UNSIGNED_INT,indexBuffer);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertices.length / 2);
    }

    public void disableAttributes() {
        GLES20.glDisableVertexAttribArray(positionHandle);
    }

    private void activeAndBindTextures(int... textures) {
        int i=0;
        for (int texture:textures) {
            GLES20.glActiveTexture(GLES20.GL_TEXTURE0+i);
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture);
            i++;
        }
    }
    private void uniformTextures(int... locations){
        int i=0;
        for (int location:locations) {
            GLES20.glUniform1i(location,i);
            i++;
        }
    }
}
