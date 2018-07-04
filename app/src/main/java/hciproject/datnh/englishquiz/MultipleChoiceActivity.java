package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;

public class MultipleChoiceActivity extends AppCompatActivity {

    private Spinner spinQues = null;
    private Spinner spinDiff = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        spinQues = (Spinner)findViewById(R.id.spinnerQues);
        spinDiff = (Spinner)findViewById(R.id.spinnerDiff);
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(MultipleChoiceActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void doQuiz(View view) {
        Intent intent = new Intent(MultipleChoiceActivity.this, MultipleChoiceStartQuizActivity.class);
        int numQues = Integer.parseInt(spinQues.getSelectedItem().toString());
        String diff = spinDiff.getSelectedItem().toString();
        intent.putExtra("numQues", numQues);
        intent.putExtra("diff", diff);
        startActivity(intent);
    }
}
