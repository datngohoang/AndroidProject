package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VocabularyActivity extends AppCompatActivity {
    private String[] words;
    private String[] blankWords;
    private String word = "V O C A B U L A R Y";
    private String blankWord = "V O C _ _ _ L _ R Y";
    private String fillWord ="";
    private int countFail = 5;
    private boolean checkRight = false;
    private Button btnWord;
    private int score = 0;
    private TextView showWord;
    private TextView showFail;
    private TextView showScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        showWord = (TextView) findViewById(R.id.txtShowWord);
        showWord.setText(blankWord);
        for (char i = 65; i < 91; i++) {
            String name = (String) "btn" + i;
            System.out.println(name);
            int id = getResources().getIdentifier(name, "id", this.getPackageName());
            btnWord = (Button) findViewById(id);

            btnWord.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(btnWord.getText());
                    fillInWord((Button)v);
                    invisibleButton((Button)v);
                    fillWord = "";
                }
            });
        }


    }

    //TODO: không hiển thị Button sau khi nhấn
    public void invisibleButton(Button button) {
        button.setVisibility(View.INVISIBLE);
    }

    //Dien chu vao TEXTVIEW
    public void fillInWord(Button btnWord) {
        //tach chuoi
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
        //TODO: Gắn chuỗi String thành String
        for (int i = 0; i < blankWords.length; i++) {
            if (i == blankWords.length - 1) {
                fillWord += blankWords[i];
            }else {
                fillWord += blankWords[i] + " ";
            }
        }
        blankWord = fillWord;
        if (checkRight) {
            if (blankWord.equals(word)) {
                //TODO: cộng score, chuyển sang câu tiếp theo
                score = score + 10;
                TextView txtScore = (TextView) findViewById(R.id.txtScore);
                txtScore.setText(score);
            }
        } else {
            if (countFail == 0) {
                //TODO: Fail còn 0, Chuyển sang trang result
                Intent intent = new Intent(this, ResultActivity.class);
                startActivity(intent);
            } else {
                //TODO Fail < 5, sai -1
                countFail = countFail - 1;
                showFail = (TextView) findViewById(R.id.txtCountFail);
                showFail.setText(countFail + "");
            }
        }

        //TODO: Set Text
        showWord = (TextView) findViewById(R.id.txtShowWord);
        showWord.setText(fillWord);



    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
