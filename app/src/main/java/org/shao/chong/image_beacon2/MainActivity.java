package org.shao.chong.image_beacon2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.estimote.sdk.Beaconmanager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementChecker;


public class MainActivity extends AppCompatActivity {


    Button leftTakeButton;
    Button rightTakeButton;
    Button writeButton;

    private BeaconManager beaconManager;
    private Region region;
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
