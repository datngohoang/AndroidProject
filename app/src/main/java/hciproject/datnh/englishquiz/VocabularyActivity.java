package hciproject.datnh.englishquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import hciproject.datnh.englishquiz.communicator.ApiConnector;
import hciproject.datnh.englishquiz.entity.WordQuizEntity;
import hciproject.datnh.englishquiz.model.MultipleChoiceQuizModel;
import hciproject.datnh.englishquiz.model.WordQuizModel;
import hciproject.datnh.englishquiz.storage.ScoreStorage;

public class VocabularyActivity extends AppCompatActivity {
    public static final int SCORE_FROM_VOCABULARY = 2;
    private String[] words;
    private String[] blankWords;
    private String word = "V O C A B U L A R Y";
    private String blankWord = "V O C _ _ _ L _ R Y";
    private String fillWord = "";
    private int countFail = 5;
    private boolean checkRight = false;
    private Button btnWord;
    private int score = 0;
    private TextView showWord;
    private TextView showFail;
    private TextView showScore;
    private long time;
    private TextView txtTimer;
    private String[] listQuestion;
    private CountDownTimer timer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        showWord = (TextView) findViewById(R.id.txtShowWord);
        time = 60 * 1000;
        txtTimer = (TextView)findViewById(R.id.txtTimer);
        for (char i = 65; i < 91; i++) {
            String name = (String) "btn" + i;
            int id = getResources().getIdentifier(name, "id", this.getPackageName());
            btnWord = (Button) findViewById(id);

            btnWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fillInWord((Button) v);
                    fillWord = "";
                }
            });
        }
        //Call api
        Runnable r = createWordRunnable();
        Thread t = new Thread(r);
        t.start();
    }

    private Runnable createWordRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //get data
                WordQuizEntity entity = ApiConnector.callWordApi();
                setUp(entity);
                setUpTimer();
            }
        };
        return runnable;
    }

    public void setUpTimer() {
        if (timer == null) {
            //setup timer
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    timer = new CountDownTimer(time, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            time = millisUntilFinished;
                            //Update txtTimer
                            int mins = (int)time / 60000;
                            int seconds = (int)time % 60000 / 1000;
                            String timeLeftText;
                            timeLeftText = "" + mins;
                            timeLeftText += ":";
                            if (seconds < 10) timeLeftText += "0";
                            timeLeftText += seconds;
                            txtTimer.setText(timeLeftText);
                        }

                        @Override
                        public void onFinish() {
                            Intent intent = new Intent(VocabularyActivity.this, ResultActivity.class);
                            //Set score
                            ScoreStorage scoreStorage = new ScoreStorage(VocabularyActivity.this);
                            scoreStorage.setValue(ScoreStorage.NAMES[2], score);
                            intent.putExtra("scoreFromIntent", SCORE_FROM_VOCABULARY);
                            intent.putExtra("finalScore", score + "");
                            startActivity(intent);
                        }
                    }.start();
                }
            });
        } else {
            timer.start();
        }
    }

    public void setUp(WordQuizEntity entity) {
        final WordQuizEntity entity1 = entity;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //Set up word
                word = entity1.getWord();
                blankWord = entity1.getBlankWord();
                showWord.setText(blankWord);
            }
        });
    }

    public void invisibleButton(Button button) {
        button.setVisibility(View.INVISIBLE);
    }

    //Dien chu vao TEXTVIEW
    public void fillInWord(Button btnWord) {
        //tach chuoi
        fillWord = "";
        words = word.split("\\s");
        blankWords = blankWord.split("\\s");
        String letter = (String) btnWord.getText();
        for (int i = 0; i < words.length; i++) {
            if ((words[i].equals(letter)) && blankWords[i].equals("_")) {
                //đổi biến cờ thành true, đổi "_" thành letter
                checkRight = true;
                blankWords[i] = letter;
            }
        }
        // Gắn chuỗi String thành String
        for (int i = 0; i < blankWords.length; i++) {
            if (i == blankWords.length - 1) {
                fillWord += blankWords[i];
            } else {
                fillWord += blankWords[i] + " ";
            }
        }
        blankWord = fillWord;
        if (checkRight) {
            if (blankWord.equals(word)) {
                //cộng score, chuyển sang câu tiếp theo
                score = score + 10;
                TextView txtScore = (TextView) findViewById(R.id.txtScore);
                txtScore.setText(score + "");
                goToNextQuestion();
            } else {
                invisibleButton(btnWord);
            }
        } else {
            if (countFail == 1) {
                //Fail còn 1, Chuyển sang trang result
                Intent intent = new Intent(this, ResultActivity.class);
                //Set score
                ScoreStorage scoreStorage = new ScoreStorage(this);
                scoreStorage.setValue(ScoreStorage.NAMES[2], score);
                intent.putExtra("scoreFromIntent", SCORE_FROM_VOCABULARY);
                intent.putExtra("finalScore", score + "");
                timer.cancel();
                startActivity(intent);
            } else {
                //Fail < 5, sai -1
                countFail = countFail - 1;
                showFail = (TextView) findViewById(R.id.txtCountFail);
                showFail.setText(countFail + "");
            }
            invisibleButton(btnWord);
        }
        // Set Text
        showWord = (TextView) findViewById(R.id.txtShowWord);
        showWord.setText(fillWord);
        checkRight = false;
    }

    public void goToNextQuestion() {
        timer.cancel();
        showAgainButton();
        //Call api
        Runnable r = createWordRunnable();
        Thread t = new Thread(r);
        t.start();
    }

    public void showAgainButton() {
        for (char i = 65; i < 91; i++) {
            String name = (String) "btn" + i;
            System.out.println(name);
            int id = getResources().getIdentifier(name, "id", this.getPackageName());
            btnWord = (Button) findViewById(id);
            btnWord.setVisibility(View.VISIBLE);
        }
    }

    public void backToMenu(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to finish the game?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(VocabularyActivity.this, ResultActivity.class);
                        //Set score
                        ScoreStorage scoreStorage = new ScoreStorage(VocabularyActivity.this);
                        scoreStorage.setValue(ScoreStorage.NAMES[2], score);
                        intent.putExtra("scoreFromIntent", SCORE_FROM_VOCABULARY);
                        intent.putExtra("finalScore", score + "");
                        startActivity(intent);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
        .show();
    }
}
