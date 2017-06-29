package org.shao.chong.image_beacon2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.io.IOException;

import static org.shao.chong.image_beacon2.MainActivity.TAG;

/**
 * Created by chongshao on 6/23/17.
 */

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;
    private Canvas canvas;
    private Paint paint;
    float RectLeft;
    float RectTop;
    float RectRight;
    float RectBottom;

    private SurfaceView transparentView;
    private SurfaceHolder holderTransparent;
    public CameraPreview(Context context, Camera camera, SurfaceView transparentView) {
        super(context);
        mCamera = camera;

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);




        holderTransparent = transparentView.getHolder();
        holderTransparent.setFormat(PixelFormat.TRANSPARENT);
        holderTransparent.addCallback(this);
        holderTransparent.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        OnTouchListener onTouchListner = new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                RectLeft = event.getX() - 100;
                RectTop = event.getY() - 100 ;
                RectRight = event.getX() + 100;
                RectBottom = event.getY() + 100;
   //             DrawFocusRect(RectLeft , RectTop , RectRight , RectBottom , Color.BLUE);
                return true;
            }
        };
     //   DrawFocusRect(100, 100, 200, 200 , Color.BLUE);

    }

    public SurfaceHolder getHolderTransparent() {
        return holderTransparent;
    }




    public void surfaceCreated(SurfaceHolder holder) {
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (IOException e) {
            Log.d(TAG, "Error setting camera preview: " + e.getMessage());
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // empty. Take care of releasing the Camera preview in your activity.
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            mCamera.stopPreview();
        } catch (Exception e){
            // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();

        } catch (Exception e){
            Log.d(TAG, "Error starting camera preview: " + e.getMessage());
        }
    }
}
