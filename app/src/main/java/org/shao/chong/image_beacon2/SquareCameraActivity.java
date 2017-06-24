package org.shao.chong.image_beacon2;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by chongshao on 6/23/17.
 */

public class SquareCameraActivity extends Activity {

    private SurfaceView cameraPreview;
    private RelativeLayout overlay;

    private Camera mCamera;
    private CameraPreview mPreview;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.square_camera);

        // Optional: Hide the status bar at the top of the window
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       mCamera = getCameraInstance();
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
        // Set the content view and get references to our views
      //  setContentView(R.layout.square_camera);
      //  cameraPreview = (SurfaceView) findViewById(R.id.camera_preview);
     //   overlay = (RelativeLayout) findViewById(R.id.overlay);
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
