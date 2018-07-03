package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MultipleChoiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(MultipleChoiceActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void doQuiz(View view) {
        Intent intent = new Intent(MultipleChoiceActivity.this, MultipleChoiceStartQuizActivity.class);
        startActivity(intent);
    }
}
