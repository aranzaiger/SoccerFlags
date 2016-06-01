package ex4.sagid.aranz.soccerflags;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by aranz on 01-Jun-16.
 */
public class JPNFlag extends Flags{
    private final String TAG = getClass().getSimpleName();
    private final String name = "JAPAN";


    //Constructor
    public JPNFlag(Context context) {super(context);}
    public JPNFlag(Context context, AttributeSet attrs) {super(context, attrs);}

    @Override
    protected void onDraw(Canvas canvas){
        //draw flag shape
        canvas.drawCircle(width/2, height/2, height/3, paintF);

        //draw score
        canvas.drawText(getScoreString(), (width/2) - paintS.measureText(getScoreString())/2, height/7, paintS);

        //draw name
        canvas.drawText(name, (width/2) - paintS.measureText(name)/2, 13*(height/14), paintS);
        invalidate();
    }



}
