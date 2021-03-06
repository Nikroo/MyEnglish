package by.gsu.curiosity.mybd.lists;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Curiosity on 29.12.2015.
 */
public class ContactDbHelperInfo extends SQLiteOpenHelper
        implements BaseColumns

{
    //        public static final String DB_CONTACTS = "contacts.db";
    private static String DB_PATH = "/data/data/by.gsu.curiosity.mybd/databases/";
    private static String DB_NAME = "mainDBv3.sqlite3";

    public static final String TABLE_PEOPLE = "people";
    public static final String TABLE_IRREGULAR_VERBS = "VERBS";
    public static final String TABLE_IRREGULAR_PLURAL = "PLURAL";
    public static final String TABLE_IRREGULAR_ADJECTIVES = "ADJECTIVES";



    private Context context;
    public SQLiteDatabase database2;

    private Context myContext;

    public ContactDbHelperInfo(Context con) {
        super(con, DB_NAME, null, 1);
        this.context = con;
    }

//  В теле метода onCreate необходимо реализовать логику создания таблиц

    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    public void create_db(){
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
        }
        catch(IOException ex){

        }
    }
    public void open() throws SQLException {
        String path = DB_PATH + DB_NAME;
        database2 = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {
        if (database2 != null) {
            database2.close();
        }
        super.close();
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PEOPLE);
        onCreate(db);
    }

    public Cursor getTable(String tbl_name) {
        String sqlQuery = "select * from " + tbl_name + ";";
        return database2.rawQuery(sqlQuery, null);
    }

    private void copyMedia(String destDir) {
        try {
            File folder = new File(destDir);
            // Проверяем, существует ли каталог ContactPhotos,
            // если нет создаем
            if (!folder.exists())
                folder.mkdir();
            AssetManager mgr = context.getAssets();
            final String assetSubDir = "photos";
            String[] files = mgr.list(assetSubDir);

            for (String fileName : files) {
                InputStream is = mgr.open(assetSubDir + "/" + fileName);
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();

                File destFile = new File(destDir, fileName);
                FileOutputStream output = new FileOutputStream(destFile);
                output.write(buffer);
                output.close();
            }
        }
        catch (Exception e) {
            Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
        }
    }


}