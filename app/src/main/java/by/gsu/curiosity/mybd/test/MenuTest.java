package by.gsu.curiosity.mybd.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import by.gsu.curiosity.mybd.DatabaseHelper;
import by.gsu.curiosity.mybd.R;

public class MenuTest extends AppCompatActivity implements View.OnClickListener {


    DatabaseHelper sqlHelper;
    Button btnBeginner;
    Button btnElementary;
    Button btnIntermediate;

    private static final int MENU = R.layout.activity_test_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.AppDefault);

        super.onCreate(savedInstanceState);
        setContentView(MENU);


        btnBeginner = (Button) findViewById(R.id.btnBeginner);
        btnElementary = (Button) findViewById(R.id.btnElementary);
        btnIntermediate = (Button) findViewById(R.id.btnIntermediate);
        btnBeginner.setText(R.string.button_level_beginner);
        btnElementary.setText(R.string.button_level_elementary);
        btnIntermediate.setText(R.string.button_level_intermediate);
        btnBeginner.setOnClickListener(this);
        btnElementary.setOnClickListener(this);
        btnIntermediate.setOnClickListener(this);

        // создаем базу данных 1ый раз для того что бы проинициализировать внутреннюю базу приложения значениями.
        // без этого при попытке прохождения теста без предворительного обращения к классу ListActivity приложение отваливается
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
                startActivity(new Intent(MenuTest.this, UnitActivity.class));

                break;
            case R.id.btnElementary:
                DatabaseHelper.LEVEL="2";
                startActivity(new Intent(MenuTest.this, UnitActivity.class));
                break;

            case R.id.btnIntermediate:
                DatabaseHelper.LEVEL="3";
                startActivity(new Intent(MenuTest.this, UnitActivity.class));
                break;

        }
    }
}