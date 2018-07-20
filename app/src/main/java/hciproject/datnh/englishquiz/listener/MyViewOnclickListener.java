package hciproject.datnh.englishquiz.listener;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import hciproject.datnh.englishquiz.MyWordActivity;
import hciproject.datnh.englishquiz.SQLite.DBManager;
import hciproject.datnh.englishquiz.entity.WordQuizEntity;

public class MyViewOnclickListener implements View.OnClickListener {

    private WordQuizEntity entity;
    private Context context;

    public MyViewOnclickListener(Context context, WordQuizEntity entity) {
        this.entity = entity;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        view.setBackgroundColor(Color.BLUE);
        DBManager dbManager = new DBManager(context);
        if (!dbManager.checkWord(entity.getId())){
            dbManager.addword(entity);
            System.out.println("add " + entity.getName());
        }else{
            dbManager.deleteWord(entity);
            System.out.println("delete " + entity.getName());
        }
    }
}
