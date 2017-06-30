package org.shao.chong.image_beacon2;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chongshao on 6/23/17.
 */

public class SquareCameraActivity extends Activity {

    private SurfaceView cameraPreview;
    private SurfaceView transparentView;
 //   private SurfaceHolder holderTransparent;
    private RelativeLayout overlay;

    private Camera mCamera;
    private CameraPreview mPreview;
    private Canvas canvas;
    private Paint paint;
    private SurfaceHolder holderTransparent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.square_camera);

        // Optional: Hide the status bar at the top of the window
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       mCamera = getCameraInstance();
        mCamera.setDisplayOrientation(90);


        Camera.Parameters params= mCamera.getParameters();
  //      params.setPreviewSize(400, 400);


// Find a preview size that is at least the size of our IMAGE_SIZE
        Camera.Size previewSize = params.getSupportedPreviewSizes().get(0);
        for (Camera.Size size : params.getSupportedPreviewSizes()) {
            if (size.width >= 1024 && size.height >= 1024) {
                previewSize = size;
                break;
            }
        }
        params.setPreviewSize(previewSize.width, previewSize.height);

// Try to find the closest picture size to match the preview size.
        Camera.Size pictureSize = params.getSupportedPictureSizes().get(0);
        for (Camera.Size size : params.getSupportedPictureSizes()) {
            if (size.width == previewSize.width && size.height == previewSize.height) {
                pictureSize = size;
                break;
            }
        }

        params.setPictureSize(pictureSize.width, pictureSize.height);
        transparentView = (SurfaceView)findViewById(R.id.TransparentView);

        mPreview = new CameraPreview(this, mCamera, transparentView);
        holderTransparent = mPreview.getHolderTransparent();
 //       DrawFocusRect(10,10, 500, 500, Color.BLUE);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);

     //   preview.getLayoutParams().width=params.getPreviewSize().height;
     //   preview.getLayoutParams().height=params.getPreviewSize().width;


        preview.addView(mPreview);

        // Set the content view and get references to our views
      //  setContentView(R.layout.square_camera);
      //  cameraPreview = (SurfaceView) findViewById(R.id.camera_preview);
     //   overlay = (RelativeLayout) findViewById(R.id.overlay);

//
//        transparentView = (SurfaceView)findViewById(R.id.TransparentView);
//
//        holderTransparent = transparentView.getHolder();
//        holderTransparent.setFormat(PixelFormat.TRANSPARENT);
//        holderTransparent.addCallback(callBack);
//        holderTransparent.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        final Handler handler = new Handler();
        final TextView textView = new TextView(this);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                        textView.setGravity(Gravity.CENTER);
                        textView.setTextSize(60);
                        textView.setText(currentDateTimeString);
                        setContentView(textView);
                    }
                });
            }
        }, 1000, 1000);
    }


    private void DrawFocusRect(float RectLeft, float RectTop, float RectRight, float RectBottom, int color)
    {

        canvas = holderTransparent.lockCanvas();
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        //border's properties
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        paint.setStrokeWidth(3);
        canvas.drawRect(RectLeft, RectTop, RectRight, RectBottom, paint);


        holderTransparent.unlockCanvasAndPost(canvas);
    }

    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(0); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
            Log.d("DDL", "Camera nUll");
        }
        return c; // returns null if camera is unavailable
    }
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        // Get the preview size
//        int previewWidth = cameraPreview.getMeasuredWidth(),
//                previewHeight = cameraPreview.getMeasuredHeight();
//
//        // Set the height of the overlay so that it makes the preview a square
//        RelativeLayout.LayoutParams overlayParams = (RelativeLayout.LayoutParams) overlay.getLayoutParams();
//        overlayParams.height = previewHeight - previewWidth;
//        overlay.setLayoutParams(overlayParams);
//    }

}
