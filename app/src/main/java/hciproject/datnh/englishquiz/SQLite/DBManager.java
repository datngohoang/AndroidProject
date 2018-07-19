package hciproject.datnh.englishquiz.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import hciproject.datnh.englishquiz.entity.WordQuizEntity;

public class DBManager extends SQLiteOpenHelper{
    public static final String DATABASE_NAME ="dictionary_list";
    private static final String TABLE_NAME ="dictionary";
    private static final String ID ="id";
    private static final String NAME ="name";
    private static final String MEANING ="mean";
    private static final String TYPE ="type";
    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME,null, 1);
        Log.d("DBManager", "DBManager: ");
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQuery = "CREATE TABLE "+TABLE_NAME +" (" +
                ID +" integer primary key, "+
                NAME + " TEXT, "+
                MEANING  +" TEXT, "+
                TYPE +" TEXT)";
        db.execSQL(sqlQuery);
        Toast.makeText(context, "Create successfylly", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        Toast.makeText(context, "Drop successfylly", Toast.LENGTH_SHORT).show();
    }

    //Add new a 
    public void addword(WordQuizEntity word){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, word.getName());
        values.put(MEANING, word.getMeaning());
        values.put(TYPE, word.getType());
        //Neu de null thi khi value bang null thi loi

        db.insert(TABLE_NAME,null,values);

        db.close();
    }

    public int Update(WordQuizEntity word){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME,word.getName());

        return db.update(TABLE_NAME,values,ID +"=?",new String[] { String.valueOf(word.getId())});


    }

}
