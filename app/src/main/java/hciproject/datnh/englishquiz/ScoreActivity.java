package hciproject.datnh.englishquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    private  TextView txtScoreTitle;
    private  TextView txtScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        txtScoreTitle = (TextView) findViewById(R.id.txtScoreTitle);
        String scoreTitle = "Highest Score.........\n" +
                "Last Score..........\n" +
                "Average Score.........\n";

        txtScoreTitle.setText(scoreTitle);

        txtScore = (TextView) findViewById(R.id.txtScore);
        String score = "60\n" +
                "50\n" +
                "50\n";
        txtScore.setText(score);
    }

    public void backToMenu(View view) {
    }
}
