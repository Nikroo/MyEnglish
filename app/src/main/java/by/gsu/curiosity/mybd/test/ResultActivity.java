package by.gsu.curiosity.mybd.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import by.gsu.curiosity.mybd.MaterialDesign;
import by.gsu.curiosity.mybd.R;


public class ResultActivity extends AppCompatActivity {

    TextView result;
    Button back;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = (TextView) findViewById(R.id.result);
        back = (Button) findViewById(R.id.back);
        back.setText(R.string.result_menu);

        String of = getResources().getString(R.string.result_text_of);
        String correct = getResources().getString(R.string.result_text_correct_words);

        result.setText(TestActivity.trueWord + of + TestActivity.cursorCounter + correct);
        TestActivity.cursorCounter = 0;
        TestActivity.trueWord = 0;


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MaterialDesign.class);
                startActivity(intent);


            }
        });
    }
}
