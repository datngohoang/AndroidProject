package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MultipleChoiceActivity extends AppCompatActivity {

    private Spinner spinQues = null;
    private Spinner spinDiff = null;
    private int fromScreen;
    private TextView txtScreenTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        spinQues = (Spinner)findViewById(R.id.spinnerQues);
        spinDiff = (Spinner)findViewById(R.id.spinnerDiff);
        Bundle bd = new Bundle();
        bd = getIntent().getExtras();
        fromScreen = bd.getInt("fromActivity");//0,1
        if(fromScreen == 0){
            txtScreenTitle = (TextView) findViewById(R.id.txtTitle);
            String title = "MULTIPLE CHOICE";
            txtScreenTitle.setText(title);
        } else if(fromScreen == 1){
            txtScreenTitle = (TextView) findViewById(R.id.txtTitle);
            String title = "LISTEN";
            txtScreenTitle.setText(title);
        }
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(MultipleChoiceActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void doQuiz(View view) {
        if(fromScreen == 0){
            Intent intent = new Intent(MultipleChoiceActivity.this, MultipleChoiceStartQuizActivity.class);
            int numQues = Integer.parseInt(spinQues.getSelectedItem().toString());
            String diff = spinDiff.getSelectedItem().toString();
            intent.putExtra("numQues", numQues);
            intent.putExtra("diff", diff);
            startActivity(intent);
        } else if(fromScreen == 1){
            Intent intent = new Intent(MultipleChoiceActivity.this, ListeningActivity.class);
            int numQues = Integer.parseInt(spinQues.getSelectedItem().toString());
            String diff = spinDiff.getSelectedItem().toString();
            intent.putExtra("numQues", numQues);
            intent.putExtra("diff", diff);
            startActivity(intent);
        }

    }
}
