package hciproject.datnh.englishquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

import hciproject.datnh.englishquiz.entity.WordQuizEntity;
import hciproject.datnh.englishquiz.listadapter.CustomAdapter;

public class MyWordActivity extends AppCompatActivity {
    private ListView lvDictionary;
    private SearchView editWord;
    private EditText editMeaning;
    private CustomAdapter customAdapter;
    private ArrayList<WordQuizEntity> arrSearchWord = new ArrayList<>();
    private ArrayList<WordQuizEntity> arrSearchMeaning = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_word);
        lvDictionary = (ListView) findViewById(R.id.lvDictionary);
        final ArrayList<WordQuizEntity> arrWord = new ArrayList<>();


        WordQuizEntity WordQuizEntity1 = new WordQuizEntity("Be","Là", "(v)");
        WordQuizEntity WordQuizEntity2 = new WordQuizEntity("Have","Có", "(v)");
        WordQuizEntity WordQuizEntity3 = new WordQuizEntity("Cat","Mèo", "(n)");
        WordQuizEntity WordQuizEntity4 = new WordQuizEntity("Dog","Chó", "(n)");
        WordQuizEntity WordQuizEntity5 = new WordQuizEntity("Beautiful","Xinh đẹp", "(a)");
        WordQuizEntity WordQuizEntity6 = new WordQuizEntity("Hot","Nóng", "(a)");
        WordQuizEntity WordQuizEntity7 = new WordQuizEntity("Hat","Cái nón", "(n)");

        arrWord.add(WordQuizEntity1);
        arrWord.add(WordQuizEntity2);
        arrWord.add(WordQuizEntity3);
        arrWord.add(WordQuizEntity4);
        arrWord.add(WordQuizEntity5);
        arrWord.add(WordQuizEntity6);
        arrWord.add(WordQuizEntity7);
        Collections.sort(arrWord, new Comparator<WordQuizEntity>() {
            @Override
            public int compare(WordQuizEntity w1, WordQuizEntity w2) {
                return w1.getName().compareTo(w2.getName());
            }
        });
        customAdapter = new CustomAdapter(this,R.layout.list_word,arrWord);
        lvDictionary.setAdapter(customAdapter);

        editWord = (SearchView) findViewById(R.id.editWord);
        editWord.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                customAdapter.getFilter().filter(s);
                return false;

            }
        });


    }


    public void backToMenu(View view) {
        Intent intent = new Intent(MyWordActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
