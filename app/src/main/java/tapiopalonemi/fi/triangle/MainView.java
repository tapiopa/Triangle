package tapiopalonemi.fi.triangle;
import android.graphics.* ;
import android.view.* ;
import android.content.Context ;
import android.util.AttributeSet ;
import android.view.MotionEvent ;
import android.view.GestureDetector ;
import android.util.Log;


public class MainView extends View implements GestureDetector.OnGestureListener {

    GestureDetector gesture_detector ;

    int center_x = 100 ;
    int center_y = 100 ;

    int width = 0;
    int height = 0;

    double angleChange = 0.0;

    public MainView(Context context) {

        super(context);
        gesture_detector = new GestureDetector( context,
                new GestureDetector.SimpleOnGestureListener());
    }

    public MainView(Context context, AttributeSet attributes) {
        super(context, attributes);
        gesture_detector = new GestureDetector( context, this ) ;
    }

    public void onSizeChanged(int currentWidth, int currentHeight,
                                int oldWidth, int oldHeight) {
        center_x = currentWidth/2;
        center_y = currentHeight/2;
        width = currentWidth;
        height = currentHeight;
    }
    public boolean onDown( MotionEvent motion_event )
    {
        return true ;
    }

    public boolean onFling( MotionEvent first_down_motion,
                            MotionEvent last_move_motion,
                            float velocity_x, float velocity_y )
    {
        return true ;
    }

    public void onLongPress( MotionEvent first_down_motion )
    {
        invalidate();

    }

    public boolean onScroll( MotionEvent first_down_motion,
                             MotionEvent last_move_motion,
                             float distance_x, float distance_y )
    {
        angleChange += distance_y;
        Log.i("onScroll", "onScroll, y: " + distance_y + ", angleChange: " + angleChange);
        invalidate() ;

        return true ;
    }

    public void onShowPress( MotionEvent down_motion )
    {
//        Log.i("onShowPress", "onShowPress");
        invalidate();
    }
//
    public boolean onSingleTapUp( MotionEvent up_motion )
    {

        return true ;
    }


    public boolean onTouchEvent ( MotionEvent motion_event )
    {
        if (gesture_detector != null) {
            gesture_detector.onTouchEvent( motion_event ) ;

        }
        return true ;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        angleChange = angleChange % 360;

        double angleZ = Math.toRadians(90 - angleChange);
        double angle1 = Math.toRadians(210 - angleChange);
        double angle2 = Math.toRadians(330 - angleChange);

        Point base1 = new Point(center_x, center_y - 200);
        Point base2 = new Point(center_x + 200,center_y + 200);
        Point zenith = new Point(center_x - 200, center_y + 200);

        double r = width / 4;
        double centerPoint = width / 2;

        Paint fill = new Paint();
        Paint outline = new Paint();
        fill.setStyle(Paint.Style.FILL);
        outline.setStyle(Paint.Style.STROKE);
        fill.setColor(Color.BLUE);
        outline.setColor(Color.RED);

        Path tri = new Path();

        tri.moveTo((float)(centerPoint + Math.cos(angle1) * r),
                (float)(centerPoint - Math.sin(angle1)*r));
        tri.lineTo((float)(centerPoint + Math.cos(angle2) * r),
                (float)(centerPoint - Math.sin(angle2)*r));
        tri.lineTo((float)(centerPoint + Math.cos(angleZ) * r),
                (float)(centerPoint - Math.sin(angleZ)*r));
        tri.close();
        canvas.drawPath(tri, outline);
        canvas.drawPath(tri, fill);
    }

}
