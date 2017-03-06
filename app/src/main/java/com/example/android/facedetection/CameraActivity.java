package com.example.android.facedetection;

import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Camera;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.content.Context;
import android.media.FaceDetector;


public class CameraActivity extends AppCompatActivity {

    private Camera mCamera;
    private CameraPreview mPreview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);

        // Create an instance of Camera
        mCamera = getCamera();
        mCamera.setDisplayOrientation(90);
        mCamera.setFaceDetectionListener(new CustomFaceDetectionListener());
        mCamera.startFaceDetection();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }

    private boolean checkCameraHardware() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public Camera getCamera(){
        Camera c = null;
        Camera.CameraInfo info = new Camera.CameraInfo();

        int cameraCount = Camera.getNumberOfCameras();

        for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
            Camera.getCameraInfo(camIdx, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                c = Camera.open(camIdx);
            }
        }
        return c;
    }
}
