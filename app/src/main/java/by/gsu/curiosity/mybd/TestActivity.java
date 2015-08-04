package by.gsu.curiosity.mybd;

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

public class TestActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    TextView textViewEnWord;
    TextView textViewRuWord;
    TextView trueFolse;
    Button btnCheckWord;
    Button btnEditDictionary;
    DatabaseHelper sqlHelper;
    EditText editWord;
    Cursor c;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textViewRuWord = (TextView) findViewById(R.id.ru_Word);
        textViewEnWord = (TextView) findViewById(R.id.en_Word);
        trueFolse = (TextView) findViewById(R.id.true_folse);
        editWord = (EditText) findViewById(R.id.inputWord);
        btnCheckWord = (Button) findViewById(R.id.btnCheckWord);
        btnEditDictionary = (Button) findViewById(R.id.btnEditDictionary);
//_________________________________________________________________________________________________

        sqlHelper = new DatabaseHelper(getApplicationContext());
        SQLiteDatabase db =
                sqlHelper.getWritableDatabase();
        c = db.query("beginner", null, null, null, null, null, null);
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false
//_________________________________________________________________________________________________

        if (i <= 0) {
            if (c.moveToPosition(i)) {
                btnCheckWord.setText("Go");
                textViewEnWord.setText(c.getString(c.getColumnIndex("en_word")));
                textViewRuWord.setText(c.getString(c.getColumnIndex("ru_word")));
            }
        }
//_________________________________________________________________________________________________
        View.OnClickListener oclBtn = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                switch (v.getId()) {
                    case R.id.btnEditDictionary:
                        startActivity(new Intent(TestActivity.this, MainActivity.class));
                        break;
                    case R.id.btnCheckWord:
//_________________________________________________________________________________________________
                        if (!TextUtils.isEmpty(editWord.getText().toString())) {
                            if (editWord.getText().toString().equals(c.getString(c.getColumnIndex("en_word")))) {
                                trueFolse.setText("true");
                            } else {
                                trueFolse.setText("folse");
                            }

                        } else {
                            trueFolse.setText("write text");
                            break;
                        }
                        editWord.setText("");
                        i++;
                        if (c.moveToPosition(i)) {
                            textViewEnWord.setText(c.getString(c.getColumnIndex("en_word")));
                            textViewRuWord.setText(c.getString(c.getColumnIndex("ru_word")));
                        } else {
                            c.close();
                            trueFolse.setText("Нужно что то сделать когда закончаться слова");
                        }
//_________________________________________________________________________________________________
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
