package ex4.sagid.aranz.soccerflags;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by aranz on 01-Jun-16.
 */
public class ISRFlag extends Flags {
    private Path path;
    private final String name = "ISRAEL";
    private final String TAG = getClass().getSimpleName();

    //Constructors
    public ISRFlag(Context context) {
        super(context);
        initView();
    }

    public ISRFlag(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        path = new Path();
        paintF.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas){

        //create and draw both triangles
        paintF.setStrokeWidth(15);
        path.moveTo(width/2,height/5);
        path.lineTo(2*(width/3),2*(height/3));
        path.lineTo(width/3,2*(height/3));
        path.close();
        path.moveTo(width/2,4*(height/5));
        path.lineTo(2*(width/3),height/3);
        path.lineTo(width/3,height/3);
        path.close();
        canvas.drawPath(path,paintF);

        //draw upper and lower lines
        paintF.setStrokeWidth(30);
        canvas.drawLine(left,top+30,left+width, top+30, paintF);
        canvas.drawLine(left,top+height-30,left+width, top+height-30, paintF);

        //draw score
        canvas.drawText(getScoreString(), (width/2) - paintS.measureText(getScoreString())/2, height/7, paintS);

        //draw name
        canvas.drawText(name, (width/2) - paintS.measureText(name)/2, 9*(height/10), paintS);

        invalidate();
    }

}
