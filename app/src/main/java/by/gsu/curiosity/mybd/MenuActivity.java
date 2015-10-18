package by.gsu.curiosity.mybd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper sqlHelper;
    Button btnBeginner;
    Button btnElementary;
    Button btnIntermedia;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initToolbar();

        btnBeginner = (Button) findViewById(R.id.btnBeginner);
        btnElementary = (Button) findViewById(R.id.btnElementary);
        btnIntermedia = (Button) findViewById(R.id.btnIntermedia);
        btnBeginner.setOnClickListener(this);
        btnElementary.setOnClickListener(this);
        btnIntermedia.setOnClickListener(this);

        // создаем базу данных 1ый раз для того что бы проинициализировать внутреннюю базу приложения значениями.
        // без этого при попытке прохождения теста без предворительного обращения к классу MainActivity приложение отваливается
        // КОСТЫЛЬ
        sqlHelper = new DatabaseHelper(getApplicationContext());
        sqlHelper.create_db();


//______________________________________________________________________________________________________
    }

    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu_menu);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//
            case R.id.btnBeginner:
                DatabaseHelper.LEVEL="1";
                startActivity(new Intent(MenuActivity.this, UnitActivity.class));

                break;
            case R.id.btnElementary:
                DatabaseHelper.LEVEL="2";
                startActivity(new Intent(MenuActivity.this, UnitActivity.class));

                break;

            case R.id.btnIntermedia:
                DatabaseHelper.LEVEL="3";
                startActivity(new Intent(MenuActivity.this, UnitActivity.class));
                break;

        }
    }
}
