package by.gsu.curiosity.mybd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class UnitActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnU1;
    Button btnU2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit);

        btnU1 = (Button) findViewById(R.id.U1);
        btnU2 = (Button) findViewById(R.id.U2);

        btnU1.setOnClickListener(this);
        btnU2.setOnClickListener(this);

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


        }
    }
}
