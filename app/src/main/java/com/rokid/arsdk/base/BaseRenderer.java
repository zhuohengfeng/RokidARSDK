package com.rokid.arsdk.base;

import android.opengl.GLSurfaceView;

import com.rokid.arsdk.utils.Logger;
import com.rokid.arsdk.vuforia.SampleAppRenderer;
import com.rokid.arsdk.vuforia.SampleApplicationSession;
import com.rokid.arsdk.vuforia.utils.Texture;

import java.util.Vector;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class BaseRenderer implements GLSurfaceView.Renderer {

    protected SampleAppRenderer mSampleAppRenderer;
    protected SampleApplicationSession vuforiaAppSession;
    protected Vector<Texture> mTextures;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        Logger.d("GLRenderer.onSurfaceCreated");

        // Call Vuforia function to (re)initialize rendering after first use
        // or after OpenGL ES context was lost (e.g. after onPause/onResume):
        vuforiaAppSession.onSurfaceCreated();

        mSampleAppRenderer.onSurfaceCreated();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        Logger.d("GLRenderer.onSurfaceChanged width="+width+", height="+height);

        // Call Vuforia function to handle render surface size changes:
        vuforiaAppSession.onSurfaceChanged(width, height);

        // RenderingPrimitives to be updated when some rendering change is done
        onConfigurationChanged();
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // Call our function to render content from SampleAppRenderer class
        mSampleAppRenderer.render();
    }

    public void onConfigurationChanged()
    {
        mSampleAppRenderer.onConfigurationChanged();
    }
}
