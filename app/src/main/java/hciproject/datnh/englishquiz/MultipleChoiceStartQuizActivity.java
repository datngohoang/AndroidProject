package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MultipleChoiceStartQuizActivity extends AppCompatActivity {

    private TextView txtQuestion;
    private TextView txtTime;
    private TextView txtCurrent;
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private Button btnConfirm;
    private int mScore = 0;
    private int currentQues = 0;
    private int time;
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
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color
                onChosing = "a";
                changeBgButton(onChosing);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color
                onChosing = "b";
                changeBgButton(onChosing);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color
                onChosing = "c";
                changeBgButton(onChosing);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //change color
                onChosing = "d";
                changeBgButton(onChosing);
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

    private int calculateTime(int numQues) {
        return numQues * 60;
    }

    private void setTextview(){
        currentQues++;
        //dao
        //get and set ques
        txtCurrent.setText("Current: " + currentQues);
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(MultipleChoiceStartQuizActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void backToMultipleMenu(View view) {
        Intent intent = new Intent(MultipleChoiceStartQuizActivity.this, MultipleChoiceActivity.class);
        startActivity(intent);
    }
}
