package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import hciproject.datnh.englishquiz.entity.WordQuizEntity;
import hciproject.datnh.englishquiz.listadapter.CustomAdapter;

public class MyWordActivity extends AppCompatActivity {
    private ListView lvDictionary;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_word);
        lvDictionary = (ListView) findViewById(R.id.lvDictionary);
        ArrayList<WordQuizEntity> arrWord = new ArrayList<>();
        
        WordQuizEntity WordQuizEntity1 = new WordQuizEntity("Trương Đình Chiến","0988 933 xxx", "(v)");
        WordQuizEntity WordQuizEntity2 = new WordQuizEntity("Võ Văn Tá","01667 585 545", "(v)");
        WordQuizEntity WordQuizEntity3 = new WordQuizEntity("Lê Tấn Dũng","0918 033 033", "(v)");
        WordQuizEntity WordQuizEntity4 = new WordQuizEntity("Trương Quang Lâm","0978 102 102", "(v)");
        WordQuizEntity WordQuizEntity5 = new WordQuizEntity("Võ Duy Tính","01667 333 000", "(v)");
        WordQuizEntity WordQuizEntity6 = new WordQuizEntity("Trần Văn Toàn","08 999 321", "(v)");
        WordQuizEntity WordQuizEntity7 = new WordQuizEntity("Lại Thế Quang","01222 331 331", "(v)");

        arrWord.add(WordQuizEntity1);
        arrWord.add(WordQuizEntity2);
        arrWord.add(WordQuizEntity3);
        arrWord.add(WordQuizEntity4);
        arrWord.add(WordQuizEntity5);
        arrWord.add(WordQuizEntity6);
        arrWord.add(WordQuizEntity7);

        CustomAdapter customAdaper = new CustomAdapter(this,R.layout.list_word,arrWord);
        lvDictionary.setAdapter(customAdaper);
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(MyWordActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
