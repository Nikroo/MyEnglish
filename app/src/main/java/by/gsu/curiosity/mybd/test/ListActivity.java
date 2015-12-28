package by.gsu.curiosity.mybd.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.ListView;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import java.sql.SQLException;

import by.gsu.curiosity.mybd.DatabaseHelper;
import by.gsu.curiosity.mybd.R;

public class ListActivity extends AppCompatActivity {

    ListView mList;
    TextView header;
    DatabaseHelper sqlHelper;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    String table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent2 = getIntent();
        table = intent2.getStringExtra("id");

        header = (TextView)findViewById(R.id.header);
        mList = (ListView)findViewById(R.id.list);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
        sqlHelper = new DatabaseHelper(getApplicationContext());

    }
    @Override
    public void onResume(){

        super.onResume();
        try {
            sqlHelper.open();
            String sqlQuery =
                    "select Word._id, Word.wordEN, Word.wordRU "
                            + "from Word "
                            + "where Word.wordLevel = ? and Word.wordUnit = ? ";

            userCursor = sqlHelper.database.rawQuery(sqlQuery, new String[] {DatabaseHelper.LEVEL, DatabaseHelper.UNIT}

            );

            String[] headers = new String[]{DatabaseHelper.EN_WORD, DatabaseHelper.RU_WORD};

            userAdapter = new SimpleCursorAdapter(
                    this,
                    android.R.layout.two_line_list_item,
                    userCursor,
                    headers,
                    new int[]{android.R.id.text1, android.R.id.text2},
                    0
            );
            header.setText("Найдено элементов: " + String.valueOf(userCursor.getCount()));
            mList.setAdapter(userAdapter);
        }
        catch (SQLException ex){}
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключения
        sqlHelper.database.close();
        userCursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
