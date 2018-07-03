package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//test commit
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void closeApplication(View view) {
        this.finishAffinity();
    }

    public void goToMultipleChoice(View view) {
        Intent intent = new Intent(MainActivity.this, MultipleChoiceActivity.class);
        startActivity(intent);
    }

    public void goToMyWord(View view) {
        Intent intent = new Intent(MainActivity.this, MyWordActivity.class);
        startActivity(intent);
    }
}
