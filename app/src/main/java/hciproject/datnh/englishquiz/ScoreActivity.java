package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    private TextView txtScoreTitle;
    private TextView txtScore;
    private String[] scoreName = {"txtQuizScore", "txtListenScore", "txtVocaScore"};
    private ImageView quizImgView;
    private ImageView listeningImgView;
    private ImageView vocabularyImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        txtScoreTitle = (TextView) findViewById(R.id.txtScoreTitle);
        String scoreTitle = "Highest Score\n" +
                "Last Score\n" +
                "Total Score\n";

        txtScoreTitle.setText(scoreTitle);
        changePictureAndScore();
        quizImgView = (ImageView) findViewById(R.id.txtQuizScore);
        listeningImgView = (ImageView) findViewById(R.id.txtListenScore);
        vocabularyImgView = (ImageView) findViewById(R.id.txtVocaScore);
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void changePictureAndScore(){
        txtScore = (TextView) findViewById(R.id.txtScore);
        String score = "60\n" +
                "50\n" +
                "50\n";
        txtScore.setText(score);
    }

    public void clickQuizScore(View view) {
        quizImgView.setBackgroundColor(Color.BLUE);
        listeningImgView.setBackgroundColor(Color.TRANSPARENT);
        vocabularyImgView.setBackgroundColor(Color.TRANSPARENT);
        txtScore = (TextView) findViewById(R.id.txtScore);
        String score = "100\n" +
                "50\n" +
                "1250\n";
        txtScore.setText(score);
    }

    public void clickListening(View view) {
        quizImgView.setBackgroundColor(Color.TRANSPARENT);
        listeningImgView.setBackgroundColor(Color.BLUE);
        vocabularyImgView.setBackgroundColor(Color.TRANSPARENT);
        txtScore = (TextView) findViewById(R.id.txtScore);
        String score = "60\n" +
                "50\n" +
                "1000\n";
        txtScore.setText(score);
    }

    public void clickVocabulary(View view) {
        quizImgView.setBackgroundColor(Color.TRANSPARENT);
        listeningImgView.setBackgroundColor(Color.TRANSPARENT);
        vocabularyImgView.setBackgroundColor(Color.BLUE);
        txtScore = (TextView) findViewById(R.id.txtScore);
        String score = "30\n" +
                "10\n" +
                "750\n";
        txtScore.setText(score);
    }
}
