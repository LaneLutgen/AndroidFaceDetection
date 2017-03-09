package com.example.android.facedetection;

import android.graphics.Rect;
import android.hardware.Camera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lane on 3/6/2017.
 */

public class CustomFaceDetectionListener implements Camera.FaceDetectionListener{

    private List<Rect> faceRects;
    private List<Rect> leftEyes;
    private List<Rect> rightEyes;



    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        if (faces.length > 0) {
            faceRects = new ArrayList<Rect>();
            leftEyes = new ArrayList<Rect>();
            rightEyes = new ArrayList<Rect>();

            for (int i = 0; i < faces.length; i++) {
                int left = faces[i].rect.left;
                int right = faces[i].rect.right;
                int top = faces[i].rect.top;
                int bottom = faces[i].rect.bottom;
                Rect faceRect = new Rect(left, top, right, bottom);
                faceRects.add(faceRect);

                Rect eyearea_right = new Rect(faceRect.left + (faceRect.width()/2), faceRect.top + (faceRect.width()/4), faceRect.right- (faceRect.width()/4), faceRect.bottom - faceRect.width());
                Rect eyearea_left = new Rect(faceRect.left + (faceRect.width()/2), faceRect.top + faceRect.width(), faceRect.right- (faceRect.width()/4), faceRect.bottom - + (faceRect.width()/4));
                leftEyes.add(eyearea_left);
                rightEyes.add(eyearea_right);

            }
        }
    }

    public List<Rect> getFacesAsRectangles(){
        return faceRects;
    }

    public List<Rect> getLeftEyeCoords(){
        return leftEyes;
    }

    public List<Rect> getRightEyeCoords(){
        return rightEyes;
    }
}
