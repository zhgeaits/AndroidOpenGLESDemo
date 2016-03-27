/*
 * Copyright (C) 2016 Zhang Ge <zhgeaits@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zhgeaits.androidopenglesdemo1;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by zhgeaits on 2016/3/26 0026.
 */
public class MyOpenGLESView extends GLSurfaceView {

    private MyOpenGLESRenderer mRenderer;

    public MyOpenGLESView(Context context) {
        super(context);
        this.setEGLContextClientVersion(2);
        mRenderer = new MyOpenGLESRenderer();
        this.setRenderer(mRenderer);
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }


    private class MyOpenGLESRenderer implements GLSurfaceView.Renderer {
        private Triangle tle;

        public void onDrawFrame(GL10 gl) {
            //清除深度缓冲与颜色缓冲
            GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
            //绘制三角形
            tle.drawSelf();
        }

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //设置视窗大小及位置
            GLES20.glViewport(0, 0, width, height);

            //调用此方法计算产生透视投影矩阵
            Matrix.frustumM(Triangle.mProjMatrix, 0, -1, 1, -1, 1, 1, 10);

            //调用此方法产生摄像机9参数位置矩阵
            Matrix.setLookAtM(Triangle.mVMatrix, 0, 0, 0, 3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            tle = new Triangle(MyOpenGLESView.this);

            //设置屏幕背景色RGBA
            GLES20.glClearColor(0, 0, 0, 1.0f);
            //打开深度检测
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
        }
    }
}
