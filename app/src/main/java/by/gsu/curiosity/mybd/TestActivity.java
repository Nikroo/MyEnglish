package by.gsu.curiosity.mybd;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.SQLException;

public class TestActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    TextView textViewMainWord;
    Button btnCheckWord;
    Button btnEditDictionary;
    DatabaseHelper sqlHelper;
    int i = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textViewMainWord = (TextView) findViewById(R.id.textViewMainWord);
        btnCheckWord = (Button) findViewById(R.id.btnCheckWord);
        btnEditDictionary = (Button) findViewById(R.id.btnEditDictionary);
//_________________________________________________________________________________________________
////
         sqlHelper = new DatabaseHelper(getApplicationContext());
//_________________________________________________________________________________________________
        View.OnClickListener oclBtn = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//_________________________________________________________________________________________________
                SQLiteDatabase db =
                        sqlHelper.getWritableDatabase();
////_________________________________________________________________________________________________


                switch (v.getId()){
                case R.id.btnEditDictionary:
                    startActivity(new Intent(TestActivity.this, MainActivity.class));
                    break;
                case R.id.btnCheckWord:

//_________________________________________________________________________________________________


                    Cursor c = db.query("beginner", null, null, null, null, null, null);
                    // ставим позицию курсора на первую строку выборки
                    // если в выборке нет строк, вернется false
                    if (c.moveToFirst()) {

                        // определяем номера столбцов по имени в выборке
                        int idColIndex = c.getColumnIndex("_id");
                        int nameColIndex = c.getColumnIndex("en_word");
                        int emailColIndex = c.getColumnIndex("ru_word");

                        textViewMainWord.setText(c.getString(i));

                        do {
                            // получаем значения по номерам столбцов и пишем все в лог
                            Log.d(LOG_TAG,
                                    "ID = " + c.getInt(idColIndex) +
                                            ", en_word = " + c.getString(nameColIndex) +
                                            ", ru_word = " + c.getString(emailColIndex));
                            // переход на следующую строку
                            // а если следующей нет (текущая - последняя), то false - выходим из цикла
                        } while (c.moveToNext());
                    } else
                        Log.d(LOG_TAG, "0 rows");

                    c.close();
                    i++;
//_________________________________________________________________________________________________
                    break;
            }
            }
        };
        btnEditDictionary.setOnClickListener(oclBtn);
        btnCheckWord.setOnClickListener(oclBtn);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
