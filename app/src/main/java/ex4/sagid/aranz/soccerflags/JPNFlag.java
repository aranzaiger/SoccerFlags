package ex4.sagid.aranz.soccerflags;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by aranz on 01-Jun-16.
 */
public class JPNFlag extends Flags{



    public final String TAG = getClass().getSimpleName();


    //Constructor
    public JPNFlag(Context context) {super(context);}
    public JPNFlag(Context context, AttributeSet attrs) {super(context, attrs);}

    @Override
    protected void onDraw(Canvas canvas){
        //draw flag shape
        canvas.drawCircle(width/2, height/2, height/3, paintF);

        //draw score
        canvas.drawText(getScoreString(), (width/2) - paintS.measureText(getScoreString())/2, height/7, paintS);
        invalidate();
    }



}
