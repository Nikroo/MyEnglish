package by.gsu.curiosity.mybd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.sql.SQLException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/by.gsu.curiosity.mybd/databases/";
    private static String DB_NAME = "mainDBv3.sqlite3";
    private static final int SCHEMA = 1; // версия базы данных
    public static String TABLE = "Word";
    public static String TABLEIRR = "irregularV";
    public static String LEVEL;
    public static String UNIT;


    public static final String COLUMN_ID = "_id";
    public static String EN_WORD = "wordEN";
    public static String RU_WORD = "wordRU";

    public static String WORDONE = "wordONE";
    public static String WORDTWO = "wordTWO";
    public static String WORDTHREE = "wordThree";
    public static String IRRNAME = "people";


    public DatabaseHelper db;
    public SQLiteDatabase database;
    private Context myContext;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        intent = getIntent();
//        table = intent.getStringExtra("level");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getTable(String tbl_name) {
        String sqlQuery = "select * from " + tbl_name + ";";
        return database.rawQuery(sqlQuery, null);
    }

    public void create_db() {
        InputStream myInput = null;
        OutputStream myOutput = null;
        try {
            File file = new File(DB_PATH + DB_NAME);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH + DB_NAME;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        } catch (IOException ex) {

        }
    }

    public void open() throws SQLException {
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }


}
