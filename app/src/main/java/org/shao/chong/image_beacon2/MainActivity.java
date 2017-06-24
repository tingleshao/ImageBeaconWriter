package org.shao.chong.image_beacon2;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;


public class MainActivity extends AppCompatActivity {


    protected static final String TAG = null;
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int CAMERA_REQUEST = 1888;
    ImageView leftImageView;
    ImageView rightImageView;
    ImageView grayImageView;
    ImageView colorImageView;
    ImageView triImageView;


    Button leftTakeButton;
    Button rightTakeButton;
    Button writeButton;

    private BeaconManager beaconManager;
    private Region region;

    private static final int MY_REQUEST_CODE = 1800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (ContextCompat.checkSelfPermission(Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            ContextCompat.requestPermissions(new String[]{Manifest.permission.CAMERA},
//                    MY_REQUEST_CODE);
//        }
        // Buttons
        leftTakeButton = (Button)this.findViewById(R.id.leftTake);
        leftTakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   isLeft = true;
                Log.i(TAG, "left camera button clicked!");
          //       debug.setText("left camera button clicked!");
               Intent cameraIntent = new Intent(MainActivity.this, SquareCameraActivity.class);
            //    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                MainActivity.this.startActivity(cameraIntent);

            //    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
           //     startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        rightTakeButton = (Button)this.findViewById(R.id.rightTake);
        rightTakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "right camera button clicked!");
                //       debug.setText("left camera button clicked!");
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        writeButton = (Button)this.findViewById(R.id.WriteButton);


        // ImageViews
        leftImageView = (ImageView)this.findViewById(R.id.leftView);
        rightImageView = (ImageView)this.findViewById(R.id.rightView);

        grayImageView = (ImageView)this.findViewById(R.id.greyView);
        colorImageView = (ImageView)this.findViewById(R.id.colorView);
        triImageView = (ImageView)this.findViewById(R.id.triView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Now user should be able to use camera
            }
            else {
                // Your app will not have this permission. Turn off all functions
                // that require this permission or it will force close like your
                // original question
            }
        }
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
