package by.gsu.curiosity.mybd.info;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import by.gsu.curiosity.mybd.R;

public class DetailsActivity extends AppCompatActivity {

    private ImageView photo;
    private TextView name;
    private TextView phone;
    private TextView about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        photo = (ImageView)findViewById(R.id.detailsPhoto);
        name = (TextView)findViewById(R.id.detailsName);
        phone = (TextView)findViewById(R.id.detailsPhone);
        about = (TextView)findViewById(R.id.detailsAbout);

        ContactItem item = ContactItem.selectedItem;
        photo.setImageResource(item.getPhotoID());
        name.setText(item.getName());
        phone.setText(item.getPhone());
        about.setText(item.getAbout());
    }

}
