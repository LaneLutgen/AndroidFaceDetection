package com.example.android.facedetection;

import android.hardware.Camera;
import android.graphics.*;

import java.util.*;

/**
 * Created by Lane on 3/6/2017.
 */

public class CustomFaceDetectionListener implements Camera.FaceDetectionListener{
    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        if (faces.length == 0) {
            System.out.println("No faces detected");
        } else if (faces.length > 0) {
            System.out.println("Faces Detected = " +
                    String.valueOf(faces.length));

            List<Rect> faceRects;
            faceRects = new ArrayList<Rect>();

            for (int i = 0; i < faces.length; i++) {
                int left = faces[i].rect.left;
                int right = faces[i].rect.right;
                int top = faces[i].rect.top;
                int bottom = faces[i].rect.bottom;
                Rect uRect = new Rect(left, top, right, bottom);
                faceRects.add(uRect);
            }
        }
    }
}
