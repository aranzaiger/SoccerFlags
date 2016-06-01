package ex4.sagid.aranz.soccerflags;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    public final String TAG = getClass().getSimpleName();

    private final int JPN=-1, ISR=1, TIE=0;
    private final int GAME_TIME = 90, GAME_EXTRA = 15, MILLISECONDS_MULTI = 1000;
    private final Timer T = new Timer();
    private JPNFlag JPNView;
    private ISRFlag ISRView;
    private TextView timer, extraTimeView;
    private int time;
    private boolean inExtraTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JPNView = (JPNFlag) findViewById(R.id.JPNFlagView);
        ISRView = (ISRFlag) findViewById(R.id.ISRFlagView);
        timer = (TextView) findViewById(R.id.timer);
        extraTimeView = (TextView)findViewById(R.id.extraTimeView);

        inExtraTime = false;
        time=0;
        timer.setText(""+time);


        T.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        timer.setText(""+time);

                        //first round is over
                        if (!inExtraTime && time == GAME_TIME){
                            //tie
                            if (getWinner() == TIE){
                                inExtraTime = true;
                                extraTimeView.setText(getString(R.string.Extra));
                                time=0;
                            }
                            else
                                endGame();

                        }
                        //second round is over
                        else if (inExtraTime && time == GAME_EXTRA)
                            endGame();
                    }
                });
            }
        }, MILLISECONDS_MULTI, MILLISECONDS_MULTI);
    }


    //this method stops timer and sets winner & loser
    private void endGame(){
        T.cancel();
        timer.setText(getString(R.string.GameOver));
        setEndMessage(getWinner());
    }
    //return an INT to represent the winning team (or a tie)
    private int getWinner(){
        if(JPNView.getScore() == ISRView.getScore())
            return TIE;
        else if (JPNView.getScore() > ISRView.getScore())
            return JPN;
        else
            return ISR;
    }

    //sets win/lose/tie message according to score
    private void setEndMessage(int winner){
        if (winner == JPN){
            JPNView.setFinalScore(getString(R.string.Win));
            ISRView.setFinalScore(getString(R.string.Lose));
        }
        else if(winner == ISR){
            JPNView.setFinalScore(getString(R.string.Lose));
            ISRView.setFinalScore(getString(R.string.Win));
        }
        else{
            JPNView.setFinalScore(getString(R.string.Tie));
            ISRView.setFinalScore(getString(R.string.Tie));
        }
    }
}
