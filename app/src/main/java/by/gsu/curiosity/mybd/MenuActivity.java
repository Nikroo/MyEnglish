package by.gsu.curiosity.mybd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import by.gsu.curiosity.mybd.fragment.ExampleFragment;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper sqlHelper;
    Button btnBeginner;
    Button btnElementary;
    Button btnIntermedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_menu);

        btnBeginner = (Button) findViewById(R.id.btnBeginner);
        btnElementary = (Button) findViewById(R.id.btnElementary);
        btnIntermedia = (Button) findViewById(R.id.btnIntermediate);
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

            case R.id.btnIntermediate:
                DatabaseHelper.LEVEL="3";
                startActivity(new Intent(MenuActivity.this, UnitActivity.class));
                break;

        }
    }
}