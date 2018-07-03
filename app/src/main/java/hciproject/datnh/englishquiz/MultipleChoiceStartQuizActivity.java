package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MultipleChoiceStartQuizActivity extends AppCompatActivity {

    private TextView txtQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_start_quiz);

        //set textview
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        String s = "Mary is 30 years old. Which one of the following is correct? \n\n" +
                "A. Mary is 19 years old.\n\n" +
                "B. Mary is 30 years old.\n\n" +
                "C. Mary is 15 years old.\n\n" +
                "D. Mary is 19 years old.\n\n";
        txtQuestion.setText(s);
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
