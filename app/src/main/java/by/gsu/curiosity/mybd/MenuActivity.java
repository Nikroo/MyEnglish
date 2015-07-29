package by.gsu.curiosity.mybd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    DatabaseHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findViewById(R.id.btnBeginner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuActivity.this, TestActivity.class));
            }
        });
//______________________________________________________________________________________________________
        // создаем базу данных 1ый раз для того что бы проинициализировать внутреннюю базу приложения значениями.
        // без этого при попытке прохождения теста без предворительного обращения к классу MainActivity приложение отваливается
        // КОСТЫЛЬ
        sqlHelper = new DatabaseHelper(getApplicationContext());
        sqlHelper.create_db();
//______________________________________________________________________________________________________
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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
