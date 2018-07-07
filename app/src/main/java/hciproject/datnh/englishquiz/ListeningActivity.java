package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class ListeningActivity extends AppCompatActivity {

    private TextView txtQuestion;
    private TextView txtTimer;
    private TextView txtCurrent;
    private TextView txtScore;
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private Button btnConfirm;
    private int mScore = 0;
    private String finalScore;
    private int currentQues = 0;
    private long time;
    private int numQues;
    private String diff;
    private String onChosing;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        //set score
        txtScore = (TextView)findViewById(R.id.txtScore) ;
        txtScore.setText("" + mScore);
        //get num of ques and difficulty
        numQues = getIntent().getExtras().getInt("numQues");//5, 10, 20, 40
        diff = getIntent().getExtras().getString("diff");//Easy, Normal, Hard
        //set total
        txtQuestion = (TextView) findViewById(R.id.txtTotal);
        txtQuestion.setText("Total: " + numQues);
        //set time
        time = calculateTime(numQues);
        txtTimer = (TextView)findViewById(R.id.txtTimer);
        CountDownTimer timer = new CountDownTimer(time, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                //Update txtTimer
                int mins = (int)time / 60000;
                int seconds = (int)time % 60000 / 1000;
                String timeLeftText;
                timeLeftText = "" + mins;
                timeLeftText += ":";
                if (seconds < 10) timeLeftText += "0";
                timeLeftText += seconds;
                txtTimer.setText(timeLeftText);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(ListeningActivity.this, ResultActivity.class);
                finalScore = mScore + "/" + numQues;
                intent.putExtra("finalScore", finalScore);
                mediaPlayer.release();
                handler.removeCallbacks(runnable);
                startActivity(intent);
            }
        }.start();
        //set music
        handler = new Handler();
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        prepareMedia();
        mediaPlayer.start();
        playCircle();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //set textview question
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtCurrent = (TextView) findViewById(R.id.txtCurrent);
        setTextview();
        //check answer
        btnA = (Button)findViewById(R.id.btnA);
        btnB = (Button)findViewById(R.id.btnB);
        btnC = (Button)findViewById(R.id.btnC);
        btnD = (Button)findViewById(R.id.btnD);
        btnConfirm = (Button)findViewById(R.id.btnConfirm);
        btnConfirm.setEnabled(false);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color
                onChosing = "a";
                changeBgButton(onChosing);
                btnConfirm.setEnabled(true);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color
                onChosing = "b";
                changeBgButton(onChosing);
                btnConfirm.setEnabled(true);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color
                onChosing = "c";
                changeBgButton(onChosing);
                btnConfirm.setEnabled(true);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color
                onChosing = "d";
                changeBgButton(onChosing);
                btnConfirm.setEnabled(true);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dao
                //boolean = dao.checkMultipleResult();
                //mScore++;
                txtScore.setText("" + mScore);
            }
        });
    }

    private void playCircle(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (mediaPlayer.isPlaying()) {
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCircle();
                }
            };
            handler.postDelayed(runnable, 1000);
        }
    }

    private void prepareMedia() {
        //test is the music file,
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.test);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void changeBgButton(String choice) {
        if (choice.equals("a")) {
            btnA.setBackgroundResource(R.drawable.button_bg_round_chosen);
            btnB.setBackgroundResource(R.drawable.button_bg_round);
            btnC.setBackgroundResource(R.drawable.button_bg_round);
            btnD.setBackgroundResource(R.drawable.button_bg_round);
        } else if (choice.equals("b")) {
            btnA.setBackgroundResource(R.drawable.button_bg_round);
            btnB.setBackgroundResource(R.drawable.button_bg_round_chosen);
            btnC.setBackgroundResource(R.drawable.button_bg_round);
            btnD.setBackgroundResource(R.drawable.button_bg_round);
        } else if (choice.equals("c")) {
            btnA.setBackgroundResource(R.drawable.button_bg_round);
            btnB.setBackgroundResource(R.drawable.button_bg_round);
            btnC.setBackgroundResource(R.drawable.button_bg_round_chosen);
            btnD.setBackgroundResource(R.drawable.button_bg_round);
        } else if (choice.equals("d")) {
            btnA.setBackgroundResource(R.drawable.button_bg_round);
            btnB.setBackgroundResource(R.drawable.button_bg_round);
            btnC.setBackgroundResource(R.drawable.button_bg_round);
            btnD.setBackgroundResource(R.drawable.button_bg_round_chosen);
        }
    }

    private long calculateTime(int numQues) {
        //get song list and sum of time
        return 30000;
    }

    private void setTextview(){
        currentQues++;
        //dao
        //get and set ques
        txtCurrent.setText("Current: " + currentQues);
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        finalScore = mScore + "/" + numQues;
        intent.putExtra("finalScore", finalScore);
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
        startActivity(intent);
    }

    public void playMedia(View view) {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }
}
