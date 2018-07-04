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
    private TextView txtTotal;
    private TextView txtCurrent;
    private Button btnA;
    private Button btnB;
    private Button btnC;
    private Button btnD;
    private int mScore = 0;
    private int currentQues = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_start_quiz);
        //get num of ques and difficulty
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        int numQues = b.getInt("numQues");//5, 10, 20, 40
        String diff = b.getString("diff");//Easy, Normal, Hard
        //set total
        txtQuestion = (TextView) findViewById(R.id.txtTotal);
        txtTotal.setText(numQues);
        //set textview question
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtCurrent = (TextView) findViewById(R.id.txtCurrent); 
        setTextview();
        //check answer
        btnA = (Button)findViewById(R.id.btnA);
        btnB = (Button)findViewById(R.id.btnB);
        btnC = (Button)findViewById(R.id.btnC);
        btnD = (Button)findViewById(R.id.btnD);
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dao
                //boolean = dao.checkMultipleResult();
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void setTextview(){
        currentQues++;
        //dao
        //get and set ques
        txtCurrent.setText("" + currentQues);
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
