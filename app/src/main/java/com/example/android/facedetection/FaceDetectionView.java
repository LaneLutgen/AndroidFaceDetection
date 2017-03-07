package com.example.android.facedetection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.List;


/**
 * Created by Lane on 3/6/2017.
 */

public class FaceDetectionView extends View {

    private Paint paint = new Paint();
    private List<Rect> rectangles;
    private CustomFaceDetectionListener listener;

    public FaceDetectionView(Context context, CustomFaceDetectionListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    public void onDraw(Canvas canvas){
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        rectangles = listener.getFacesAsRectangles();
        if(rectangles != null){
            for(Rect rect: rectangles){
                RectF rectF = new RectF(rect);
                Matrix matrix = new Matrix();
                matrix.setScale(-1, 1);
                matrix.postRotate(90);
                matrix.postScale(this.getWidth() / 2000f, this.getHeight() / 2000f);
                matrix.postTranslate(this.getWidth() / 2f, this.getHeight() / 2f);
                matrix.mapRect(rectF);

                canvas.drawRect(rectF, paint);
            }
        }

        this.invalidate();
    }
}
