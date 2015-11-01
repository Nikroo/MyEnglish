package by.gsu.curiosity.mybd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UnitActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnU1, btnU2, btnU3, btnU4, btnU5, btnU6, btnU7, btnU8, btnU9;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "Choose your unit", Toast.LENGTH_LONG).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        btnU1 = (Button) findViewById(R.id.U1);
        btnU2 = (Button) findViewById(R.id.U2);
        btnU3 = (Button) findViewById(R.id.U3);
        btnU4 = (Button) findViewById(R.id.U4);
        btnU5 = (Button) findViewById(R.id.U5);
        btnU6 = (Button) findViewById(R.id.U6);
        btnU7 = (Button) findViewById(R.id.U7);
        btnU8 = (Button) findViewById(R.id.U8);
        btnU9 = (Button) findViewById(R.id.U9);

        btnU1.setOnClickListener(this);
        btnU2.setOnClickListener(this);
        btnU3.setOnClickListener(this);
        btnU4.setOnClickListener(this);
        btnU5.setOnClickListener(this);
        btnU6.setOnClickListener(this);
        btnU7.setOnClickListener(this);
        btnU8.setOnClickListener(this);
        btnU9.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_unit, menu);
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
//
            case R.id.U1:
                DatabaseHelper.UNIT = "1";

                startActivity(new Intent(UnitActivity.this, TestActivity.class));

                break;
            case R.id.U2:
                DatabaseHelper.UNIT = "2";

                startActivity(new Intent(UnitActivity.this, TestActivity.class));

                break;
            case R.id.U3:
                DatabaseHelper.UNIT = "3";

                startActivity(new Intent(UnitActivity.this, TestActivity.class));

                break;
            case R.id.U4:
                DatabaseHelper.UNIT = "4";

                startActivity(new Intent(UnitActivity.this, TestActivity.class));

                break;
            case R.id.U5:
                DatabaseHelper.UNIT = "5";

                startActivity(new Intent(UnitActivity.this, TestActivity.class));

                break;
            case R.id.U6:
                DatabaseHelper.UNIT = "6";

                startActivity(new Intent(UnitActivity.this, TestActivity.class));

                break;
            case R.id.U7:
                DatabaseHelper.UNIT = "7";

                startActivity(new Intent(UnitActivity.this, TestActivity.class));

                break;
            case R.id.U8:
                DatabaseHelper.UNIT = "8";

                startActivity(new Intent(UnitActivity.this, TestActivity.class));

                break;
            case R.id.U9:
                DatabaseHelper.UNIT = "9";

                startActivity(new Intent(UnitActivity.this, TestActivity.class));

                break;



        }
    }
}
