package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import hciproject.datnh.englishquiz.SQLite.DBManager;
import hciproject.datnh.englishquiz.communicator.ApiConnector;
import hciproject.datnh.englishquiz.entity.WordQuizEntity;
import hciproject.datnh.englishquiz.listadapter.CustomAdapter;
import hciproject.datnh.englishquiz.model.WordQuizModel;

public class MyWordActivity extends AppCompatActivity {
    private ListView lvDictionary;
    private SearchView editWord;
    private EditText editMeaning;
    private CustomAdapter customAdapter;
    private ArrayList<WordQuizEntity> arrWordAPI = new ArrayList<>();
    private List<WordQuizEntity> arrWordSQLite = new ArrayList<>();
    private ArrayList<WordQuizEntity> arrSearchWord = new ArrayList<>();
    private ArrayList<WordQuizEntity> arrSearchMeaning = new ArrayList<>();
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_word);
        lvDictionary = (ListView) findViewById(R.id.lvDictionary);
        //list API
        ArrayList<WordQuizEntity> arrWordAPI = new ArrayList<>();

        //list SQLite
        dbManager = new DBManager(this);
        Runnable r = createWordRunnable();
        Thread t = new Thread(r);
        t.start();
        System.out.println(arrWordAPI.size());
//        WordQuizEntity WordQuizEntity1 = new WordQuizEntity(1,"Be","Là", "(v)");
//        WordQuizEntity WordQuizEntity2 = new WordQuizEntity(2,"Have","Có", "(v)");
//        WordQuizEntity WordQuizEntity3 = new WordQuizEntity(3,"Cat","Mèo", "(n)");
//        WordQuizEntity WordQuizEntity4 = new WordQuizEntity(4,"Dog","Chó", "(n)");
//        WordQuizEntity WordQuizEntity5 = new WordQuizEntity(5,"Beautiful","Xinh đẹp", "(a)");
//        WordQuizEntity WordQuizEntity6 = new WordQuizEntity(6,"Hot","Nóng", "(a)");
//        WordQuizEntity WordQuizEntity7 = new WordQuizEntity(7,"Hat","Cái nón", "(n)");
////        addWordToFavorite(WordQuizEntity1);
////        addWordToFavorite(WordQuizEntity2);
////        addWordToFavorite(WordQuizEntity3);
////        dbManager.deleteWord(WordQuizEntity1);
////        dbManager.deleteWord(WordQuizEntity2);
////        dbManager.deleteWord(WordQuizEntity3);
        arrWordSQLite = dbManager.getAllWord();
//        arrWordAPI.add(WordQuizEntity1);
//        arrWordAPI.add(WordQuizEntity2);
//        arrWordAPI.add(WordQuizEntity3);
//        arrWordAPI.add(WordQuizEntity4);
//        arrWordAPI.add(WordQuizEntity5);
//        arrWordAPI.add(WordQuizEntity6);
//        arrWordAPI.add(WordQuizEntity7);

        for (WordQuizEntity word:dbManager.getAllWord()) {
            word.getName();
            System.out.println("------------");
        }
        for (WordQuizEntity word:arrWordAPI) {
            word.getName();
            System.out.println("------------");
        }
        lvDictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                boolean check = false;
                if(!check){

                }
            }
        });



        Collections.sort(arrWordAPI, new Comparator<WordQuizEntity>() {
            @Override
            public int compare(WordQuizEntity w1, WordQuizEntity w2) {
                return w1.getName().compareTo(w2.getName());
            }
        });
        customAdapter = new CustomAdapter(this,R.layout.list_word,arrWordAPI);
        lvDictionary.setAdapter(customAdapter);

        //search by word
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

    private Runnable createWordRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //get data
                WordQuizModel model = ApiConnector.callAllWordApi();
                arrWordAPI.addAll(model.getQuestions());
            }
        };
        return runnable;
    }

    public void addWordToFavorite(WordQuizEntity word){
        dbManager.addword(word);
    }


    public void backToMenu(View view) {
        Intent intent = new Intent(MyWordActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
