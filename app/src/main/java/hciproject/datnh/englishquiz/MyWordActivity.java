package hciproject.datnh.englishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_word);
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(MyWordActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
