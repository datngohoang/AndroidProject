package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MultipleChoiceStartQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice_start_quiz);
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
