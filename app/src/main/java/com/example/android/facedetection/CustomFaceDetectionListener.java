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
                Rect uRect = new Rect(left, top, right, bottom);
                faceRects.add(uRect);

                Rect eyearea_right = new Rect(uRect.left + (uRect.width()/2), uRect.top + (uRect.width()/4), uRect.right- (uRect.width()/4), uRect.bottom - uRect.width());
                Rect eyearea_left = new Rect(uRect.left + (uRect.width()/2), uRect.top + uRect.width(), uRect.right- (uRect.width()/4), uRect.bottom - + (uRect.width()/4));
                leftEyes.add(eyearea_left);
                rightEyes.add(eyearea_right);

            }
        }
    }

    public List<Rect> getFacesAsRectangles(){
        return faceRects;
    }

    public List<Rect> getLeftEyeCoord(){
        return leftEyes;
    }

    public List<Rect> getRightEyeCoord(){
        return rightEyes;
    }
}
