package hciproject.datnh.englishquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MultipleChoiceStartQuizActivity extends AppCompatActivity {
    public static final int SCORE_FROM_QUIZ = 0;
    public static final int SCORE_FROM_LISTEN = 1;
    private TextView txtQuestion;
    private TextView txtTimer;
    private TextView txtCurrent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_start_quiz);
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
                Intent intent = new Intent(MultipleChoiceStartQuizActivity.this, ResultActivity.class);
                finalScore = mScore + "/" + numQues;
                intent.putExtra("scoreFromIntent", SCORE_FROM_QUIZ);
                intent.putExtra("finalScore", finalScore);
                startActivity(intent);
            }
        }.start();
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
            }
        });
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
        return numQues * 60 * 1000;
    }

    private void setTextview(){
        currentQues++;
        //dao
        //get and set ques
        txtCurrent.setText("Current: " + currentQues);
    }

    public void backToMenu(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to finish the test?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MultipleChoiceStartQuizActivity.this, ResultActivity.class);
                        finalScore = mScore + "/" + numQues;
                        intent.putExtra("scoreFromIntent", SCORE_FROM_QUIZ);
                        intent.putExtra("finalScore", finalScore);
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }
}
