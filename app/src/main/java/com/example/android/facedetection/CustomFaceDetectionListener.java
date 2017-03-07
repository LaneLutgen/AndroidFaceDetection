package com.example.android.facedetection;

import android.hardware.Camera;
import android.graphics.*;

import java.util.*;

/**
 * Created by Lane on 3/6/2017.
 */

public class CustomFaceDetectionListener implements Camera.FaceDetectionListener{

    private List<Rect> faceRects;
    private Point leftEye;
    private Point rightEye;

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        if (faces.length > 0) {
            faceRects = new ArrayList<Rect>();

            for (int i = 0; i < faces.length; i++) {
                int left = faces[i].rect.left;
                int right = faces[i].rect.right;
                int top = faces[i].rect.top;
                int bottom = faces[i].rect.bottom;
                Rect uRect = new Rect(left, top, right, bottom);
                faceRects.add(uRect);

                leftEye = faces[i].leftEye;
                rightEye = faces[i].rightEye;
            }
        }
    }

    public List<Rect> getFacesAsRectangles(){
        return faceRects;
    }

    public Point getLeftEyeCoord(){
        return leftEye;
    }

    public Point getRightEyeCoord(){
        return rightEye;
    }
}
