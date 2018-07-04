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
        String scoreTitle = "Highest Score.............." +
                "Last Score................" +
                "Average Score..............";

        txtScoreTitle.setText(scoreTitle);

        txtScore = (TextView) findViewById(R.id.txtScore);
        String score = "60" +
                "50" +
                "50";
        txtScore.setText(score);
    }

    public void backToMenu(View view) {
    }
}
