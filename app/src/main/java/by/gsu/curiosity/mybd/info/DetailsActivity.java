//package by.gsu.curiosity.mybd.lists;
//
//import android.app.Activity;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import by.gsu.curiosity.mybd.R;
//
///**
// * Created by Curiosity on 17.11.2015.
// */
//
////Окно детализированного представления выбранного контакта
//
//public class DetailsActivity extends AppCompatActivity {
//    private ImageView photo;
//    private TextView name;
//    private TextView phone;
//    private TextView about;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.details);
//
//        photo = (ImageView) findViewById(R.id.detailsPhoto);
//        name = (TextView) findViewById(R.id.detailsName);
//        phone = (TextView) findViewById(R.id.detailsPhone);
//        about = (TextView) findViewById(R.id.detailsAbout);
//
//        ContactItem item = ContactItem.selectedItem;
//// Метод setImageDrawable представляет в коде наш загружаемый файл с изображением
//// getPhotoPath переменная хранит полный путь до файла с изображением
//        photo.setImageDrawable(Drawable.createFromPath(item.getPhotoPath()));
//        name.setText(item.getName());
//        phone.setText(item.getPhone());
//        about.setText(item.getAbout());
//    }
//}
