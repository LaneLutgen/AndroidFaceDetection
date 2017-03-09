package com.example.android.facedetection;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;


public class CameraActivity extends AppCompatActivity {

    private Camera camera;
    private CameraPreview preview;
    private CustomFaceDetectionListener faceDetectionListener;
    private FaceDetectionView faceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_camera);

        faceDetectionListener = new CustomFaceDetectionListener();

        camera = getCamera();
        camera.setDisplayOrientation(90);
        camera.setFaceDetectionListener(faceDetectionListener);
        camera.startFaceDetection();

        preview = new CameraPreview(this, camera);
        faceView = new FaceDetectionView(this, faceDetectionListener);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(this.preview);
        preview.addView(faceView);
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
