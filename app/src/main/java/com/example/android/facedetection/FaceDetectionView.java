package com.example.android.facedetection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import java.util.List;


/**
 * Created by Lane on 3/6/2017.
 */

public class FaceDetectionView extends View {

    private Paint facePaint = new Paint();
    private Paint eyePaint = new Paint();
    private List<Rect> rectangles;
    private Point leftEyePoint;
    private Point rightEyePoint;
    private CustomFaceDetectionListener listener;

    public FaceDetectionView(Context context, CustomFaceDetectionListener listener) {
        super(context);
        this.listener = listener;

        facePaint.setColor(Color.RED);
        facePaint.setStyle(Paint.Style.STROKE);
        facePaint.setStrokeWidth(3.0f);

        eyePaint.setColor(Color.GREEN);
        eyePaint.setStyle(Paint.Style.STROKE);
        eyePaint.setStrokeWidth(3.0f);
    }

    @Override
    public void onDraw(Canvas canvas){
        rectangles = listener.getFacesAsRectangles();
        leftEyePoint = listener.getLeftEyeCoord();
        rightEyePoint = listener.getRightEyeCoord();
        if(rectangles != null){
            for(Rect rect: rectangles){
                RectF rectF = new RectF(rect);
                Matrix matrix = new Matrix();
                matrix.setScale(-1, 1);
                matrix.postRotate(90);
                matrix.postScale(this.getWidth() / 2000f, this.getHeight() / 2000f);
                matrix.postTranslate(this.getWidth() / 2f, this.getHeight() / 2f);
                matrix.mapRect(rectF);

                canvas.drawRect(rectF, facePaint);
                canvas.drawRect(leftEyePoint.x-50,leftEyePoint.y-50,leftEyePoint.x+50,leftEyePoint.y+50,eyePaint);
            }
        }

        this.invalidate();
    }
}
