package by.gsu.curiosity.mybd.test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import by.gsu.curiosity.mybd.DatabaseHelper;
import by.gsu.curiosity.mybd.R;

public class TestActivity extends AppCompatActivity
        implements View.OnClickListener {

    TextView textViewRuWord;
    static TextView trueFolse;
    Button btnCheckWord;
    Button btnEditDictionary;
    DatabaseHelper sqlHelper;
    EditText editWord;
    static Cursor c;
    static int cursorCounter = 0;
    static int trueWord = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textViewRuWord = (TextView) findViewById(R.id.ru_Word);
//        textViewEnWord = (TextView) findViewById(R.id.en_Word);
        trueFolse = (TextView) findViewById(R.id.true_folse);
        editWord = (EditText) findViewById(R.id.inputWord);

        btnCheckWord = (Button) findViewById(R.id.btnCheckWord);
        btnCheckWord.setText(R.string.button_check);
        btnEditDictionary = (Button) findViewById(R.id.btnEditDictionary);
        btnEditDictionary.setText(R.string.button_edit_dictionary);
        btnEditDictionary.setOnClickListener(this);
        btnCheckWord.setOnClickListener(this);


//_________________________________________________________________________________________________

        sqlHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db =
                sqlHelper.getWritableDatabase();
        String sqlQuery =
                "select Word.wordEN, Word.wordRU "
                        + "from Word "
                        + "where Word.wordLevel = ? and Word.wordUnit = ? ";

        c = db.rawQuery(sqlQuery, new String[] {DatabaseHelper.LEVEL, DatabaseHelper.UNIT});
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
////_________________________________________________________________________________________________
//
        if (cursorCounter <= 0) {
            if (c.moveToPosition(cursorCounter)) {
//                textViewEnWord.setText(c.getString(c.getColumnIndex("wordEN")));
                textViewRuWord.setText(c.getString(c.getColumnIndex("wordRU")));
            }
        }

//_________________________________________________________________________________________________
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditDictionary:
//_________________________________________________________________________________________________

                Intent intentDictionary = new Intent(this, ListActivity.class);
                intentDictionary.putExtra("id", DatabaseHelper.TABLE);
                startActivity(intentDictionary);
//_________________________________________________________________________________________________

                break;
            case R.id.btnCheckWord:
//_________________________________________________________________________________________________
                if (!TextUtils.isEmpty(editWord.getText().toString())) {

                    if (editWord.getText().toString().equalsIgnoreCase(c.getString(c.getColumnIndex(DatabaseHelper.EN_WORD)))) {
                        trueFolse.setText("true");
                        trueWord++;

                    } else {
                        trueFolse.setText("FALSE! true is: \n"+c.getString(c.getColumnIndex(DatabaseHelper.EN_WORD)));
                    }

                } else {
                    trueFolse.setText("write text");
                    break;
                }
                editWord.setText("");
                cursorCounter++;
                if (c.moveToPosition(cursorCounter)) {
                    textViewRuWord.setText(c.getString(c.getColumnIndex(DatabaseHelper.RU_WORD)));

                } else {


                    startActivity(new Intent(TestActivity.this, ResultActivity.class));
                    c.close();



                }
        }


    }

  }
