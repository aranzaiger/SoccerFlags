package ex4.sagid.aranz.soccerflags;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by aranz on 01-Jun-16.
 */
public class Flags extends View {

    public final String TAG = getClass().getSimpleName();
    public final int TEXT_SIZE = 60;
    public int left, top, width, height, score;
    public Paint paintF, paintS;
    private String finalScore;
    TypedArray  flagView;
    private GestureDetector gd;

    //Constructors
    public Flags(Context context) {
        super(context);
        gd = new GestureDetector(context, new MyGestureListener());
        initView(null);
    }

    public Flags(Context context, AttributeSet attrs) {
        super(context, attrs);
        gd = new GestureDetector(context, new MyGestureListener());
        initView(attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        left = getPaddingLeft();
        top = getPaddingTop();
        width = w - (getPaddingLeft() + getPaddingRight());
        height = h - (getPaddingBottom() + getPaddingTop());
    }

    private void initView(AttributeSet attrs){
        //get all colors and draw shapes
        flagView = getContext().obtainStyledAttributes(attrs, R.styleable.Flags);
        int BGColor = flagView.getColor(R.styleable.Flags_BGColor, Color.WHITE);
        int shapeColor = flagView.getColor(R.styleable.Flags_ShapeColor, Color.BLACK);
        int textColor = flagView.getColor(R.styleable.Flags_textColor, Color.BLACK);
        flagView.recycle();

        paintF = new Paint();
        paintS = new Paint();


        //initialize score painter
        paintS.setTextSize(TEXT_SIZE);
        paintS.setColor(textColor);

        //initialize flag painter
        paintF.setColor(shapeColor);

        setBackgroundColor(BGColor);
        score = 0;
        finalScore = "";
    }


    //returns current score if game is not over.
    //if game is over - will return win/lose/tie
    public String getScoreString() {
        if (finalScore.isEmpty())
            return Integer.toString(score);
        else return finalScore;
    }

    //set label in case of game over
    public void setFinalScore(String msg){ finalScore=msg; }

    //returns Flag score
    public int getScore(){return score;}

    /*
    Gesture Listener and Event handler
    Handles double tap and long press on Flags
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {return gd.onTouchEvent(event);}

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {return true;}
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            score++;
            return true;
        }
        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
            score = 0;
        }
    }
}
