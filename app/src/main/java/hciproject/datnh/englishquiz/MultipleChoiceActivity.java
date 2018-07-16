package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import hciproject.datnh.englishquiz.common.Constant;
import hciproject.datnh.englishquiz.communicator.ApiConnector;
import hciproject.datnh.englishquiz.model.MultipleChoiceQuizModel;

public class MultipleChoiceActivity extends AppCompatActivity {

    private Spinner spinQues = null;
    private Spinner spinDiff = null;
    private int fromScreen;
    private TextView txtScreenTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_choice);

        spinQues = (Spinner)findViewById(R.id.spinnerQues);
        spinDiff = (Spinner)findViewById(R.id.spinnerDiff);
        Bundle bd = new Bundle();
        bd = getIntent().getExtras();
        fromScreen = bd.getInt("fromActivity");//0,1
        if(fromScreen == 0){
            txtScreenTitle = (TextView) findViewById(R.id.txtTitle);
            String title = "MULTIPLE CHOICE";
            txtScreenTitle.setText(title);
        } else if(fromScreen == 1){
            txtScreenTitle = (TextView) findViewById(R.id.txtTitle);
            String title = "LISTEN";
            txtScreenTitle.setText(title);
        }
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(MultipleChoiceActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void doQuiz(View view) {
        if(fromScreen == 0){
            Runnable r = createRunnable();
            Thread t = new Thread(r);
            t.start();
        } else if(fromScreen == 1){
            Intent intent = new Intent(this, ListeningActivity.class);
            int numQues = Integer.parseInt(spinQues.getSelectedItem().toString());
            String diff = spinDiff.getSelectedItem().toString();
            intent.putExtra("numQues", numQues);
            intent.putExtra("diff", diff);
            startActivity(intent);
        }

    }

    private Runnable createRunnable() {
        final Intent intent = new Intent(this, MultipleChoiceStartQuizActivity.class);
        final int numQues = Integer.parseInt(spinQues.getSelectedItem().toString());
        final String diff = spinDiff.getSelectedItem().toString();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                intent.putExtra("numQues", numQues);
                intent.putExtra("diff", diff);
                //get data
                MultipleChoiceQuizModel model = ApiConnector.callMultipleChoiceApi(numQues, 1);
                String json = (new Gson()).toJson(model);
                intent.putExtra("model", json);
                startActivity(intent);
            }
        };

        return runnable;
    }
}
