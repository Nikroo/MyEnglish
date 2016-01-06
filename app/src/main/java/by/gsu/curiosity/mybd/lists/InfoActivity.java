package by.gsu.curiosity.mybd.lists;

import java.sql.SQLException;
import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

import by.gsu.curiosity.mybd.R;

public class InfoActivity extends AppCompatActivity implements OnGroupClickListener,OnChildClickListener {
        private ArrayList<ContactGroup> list = new ArrayList<ContactGroup>();
        private CustomAdapter adapter;
        private TextView textSelect;
        ContactDbHelperInfo sqlHelper;
        public Context context;

    Cursor cursor; // в классе DB должен быть метод

    String[] column1;
    String[] column2;
    String[] column3;

    ContactGroup group;

//Без этих конструкторов не возможно получить доступ к рессурсам
    public InfoActivity() {
            }

    public InfoActivity(Context context) {
        this.context = context;
    }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.list);

            ExpandableListView listView =
                    (ExpandableListView)findViewById(R.id.expListView);
            listView.setSelected(true);
            textSelect = (TextView)findViewById(R.id.textSelect);

            sqlHelper = new ContactDbHelperInfo(getApplicationContext());
            try {
                sqlHelper.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            addColums(ContactDbHelperInfo.TABLE_IRREGULAR_PLURAL);
            initContacts(ContactDbHelperInfo.TABLE_IRREGULAR_PLURAL);
            addColums(ContactDbHelperInfo.TABLE_IRREGULAR_ADJECTIVES);
            initContacts(ContactDbHelperInfo.TABLE_IRREGULAR_ADJECTIVES);
            addColums(ContactDbHelperInfo.TABLE_IRREGULAR_VERBS);
            initContacts(ContactDbHelperInfo.TABLE_IRREGULAR_VERBS);

            sqlHelper.close();
//Два способа получения ресурсов, первый устаревший
            int color2 =  getResources().getColor(R.color.colorPrimaryDark);
            int color1 = getResources().getColor(R.color.colorPrimary);

            adapter = new CustomAdapter(this, list, color1, color2);
            listView.setAdapter(adapter);
            listView.setOnGroupClickListener(this);
            listView.setOnChildClickListener(this);

//__________________Создадим объект для открытия БД________________________

        }

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
        int childPosition, long id) {
            ContactItem item = adapter.getChild(groupPosition, childPosition);

            String text = String.format("Choose list\n%s",
                    item.getName());
            textSelect.setText(text);

            ContactItem.selectedItem = item;
            Intent intent = new Intent();
            intent.setClass(this, DetailsActivity.class);
            startActivity(intent);
            return false;
        }

        @Override
        public boolean onGroupClick(ExpandableListView parent, View v,
        int groupPosition, long id) {
            // TODO Auto-generated method stub
            return false;
        }

    private void initContacts(String tableName) {
//________________________________________________________________________________________
// Добавление элементов не из Базы данных, а на прямую
//        ContactGroup group = new ContactGroup("My friends");
//        group.addContact(new ContactItem(
//                "Jacob Anderson", "412412411", R.drawable.a1, about));
//        group.addContact(new ContactItem(
//                "Emily Duncan", "161863187", R.drawable.a2, about));
//        group.addContact(new ContactItem(
//                "Michael Fuller", "896443658", R.drawable.a3, about));
//        group.addContact(new ContactItem(
//                "Emma Greenman", "964990543", R.drawable.a4, about));
//        group.addContact(new ContactItem(
//                "Joshua Harrison", "759285086", R.drawable.a5, about));
//        list.add(group);
//___________________________________________________________________________________________

//____________Делаем выборку из БД для ExbandableListAdapter_______________________________
        group = new ContactGroup(tableName);

//        if (column1 != null) // если курсор не пустой
//        {
            for (int i = 0; i < column1.length; i++) {
                group.addContact(new ContactItem(
                        column1[i], column2[i], R.drawable.a15, about, column3[i]));
            }

//        }
        list.add(group);
    }

    public void addColums(String tableName){
        // создаем коллекцию элементов для дочерней группы

        cursor = sqlHelper.getTable(tableName);
        column1 = new String[cursor.getCount()]; // объявляем массив
        column2 = new String[cursor.getCount()]; // объявляем массив
        column3 = new String[cursor.getCount()]; // объявляем массив
         // объявляем массив

        if (cursor.moveToFirst()) // если курсор не пустой
        {
            for (int i = 0; i < cursor.getCount(); i++)
            {
                column1[i] = cursor.getString(1); // заполняем массив из поля БД
                column2[i] = cursor.getString(2); // заполняем массив из поля БД
                column3[i] = cursor.getString(3);

                cursor.moveToNext();
            }

        }

        cursor.close();

    }

    private final String about = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";

}
