package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    private TextView txtScoreTitle;
    private TextView txtScore;
    private String[] scoreName = {"txtQuizScore", "txtListenScore", "txtVocaScore"};
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        for (int i = 0; i < scoreName.length; i++) {
            String name = scoreName[i];
            System.out.println(name);
            int id = getResources().getIdentifier(name, "id", this.getPackageName());
            imageView = (ImageView) findViewById(id);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            txtScoreTitle = (TextView) findViewById(R.id.txtScoreTitle);
            String scoreTitle = "Highest Score\n" +
                    "Last Score\n" +
                    "Average Score\n";

            txtScoreTitle.setText(scoreTitle);


        }
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void changePictureAndScore(ImageView imageView){

        txtScore = (TextView) findViewById(R.id.txtScore);
        String score = "60\n" +
                "50\n" +
                "50\n";
        txtScore.setText(score);
    }
}
