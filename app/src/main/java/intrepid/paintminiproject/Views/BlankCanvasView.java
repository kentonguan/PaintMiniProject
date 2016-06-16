package intrepid.paintminiproject.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import intrepid.paintminiproject.Containers.PathContainer;

public class BlankCanvasView extends View {

    Paint paint;
    List<PathContainer> pathContainers;

    public BlankCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        pathContainers = new ArrayList<>();
        pathContainers.add(createNewPathContainer());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float xCoord = event.getX();
        float yCoord = event.getY();
        Path currentPath = pathContainers.get(pathContainers.size() - 1).getPath();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath.moveTo(xCoord, yCoord);
                break;
            case MotionEvent.ACTION_MOVE:
                currentPath.lineTo(xCoord, yCoord);
                break;
            case MotionEvent.ACTION_UP:
                currentPath.lineTo(xCoord, yCoord);
                pathContainers.add(createNewPathContainer());
                break;
            default:
                return false;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        for (PathContainer pathContainer : pathContainers) {
            paint.setColor(pathContainer.getPaintColor());
            paint.setStrokeWidth(pathContainer.getStrokeWidth());
            canvas.drawPath(pathContainer.getPath(), paint);
        }
    }

    public void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.BLACK);
    }

    public void clear() {
        pathContainers.clear();
        pathContainers.add(createNewPathContainer());
        postInvalidate();
    }

    public void setStrokeWidth(int width) {
        paint.setStrokeWidth(width);
        pathContainers.add(createNewPathContainer());
    }

    public void setStrokeColor(int color) {
        paint.setColor(color);
        pathContainers.add(createNewPathContainer());
    }

    public PathContainer createNewPathContainer() {
        return new PathContainer(new Path(), paint.getColor(), paint.getStrokeWidth());
    }

    public void setToEraseMode() {
        paint.setColor(Color.WHITE);
        pathContainers.add(createNewPathContainer());
    }
}
