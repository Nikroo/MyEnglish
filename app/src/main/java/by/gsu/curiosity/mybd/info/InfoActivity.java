package by.gsu.curiosity.mybd.info;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.TextView;

import by.gsu.curiosity.mybd.R;
import by.gsu.curiosity.mybd.info.ContactDbHelperInfo;

public class InfoActivity extends AppCompatActivity implements OnGroupClickListener,OnChildClickListener {
        private ArrayList<ContactGroup> list;
        private CustomAdapter adapter;
        private TextView textSelect;
        ContactDbHelperInfo sqlHelper;

    String[] column1;
    String[] column2;
    int[] column3;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.info);

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

            addColums("plural");

            initContacts();
            adapter = new CustomAdapter(this, list);
            listView.setAdapter(adapter);
            listView.setOnGroupClickListener(this);
            listView.setOnChildClickListener(this);
//__________________Создадим объект для открытия БД________________________



        }

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
        int childPosition, long id) {
            ContactItem item = adapter.getChild(groupPosition, childPosition);

            String text = String.format("Last opened contact:\n%s",
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

    private void initContacts() {
        list = new ArrayList<ContactGroup>();

        ContactGroup group = new ContactGroup("My friends");
        group.addContact(new ContactItem(
                "Jacob Anderson", "412412411", R.drawable.a1, about));
        group.addContact(new ContactItem(
                "Emily Duncan", "161863187", R.drawable.a2, about));
        group.addContact(new ContactItem(
                "Michael Fuller", "896443658", R.drawable.a3, about));
        group.addContact(new ContactItem(
                "Emma Greenman", "964990543", R.drawable.a4, about));
        group.addContact(new ContactItem(
                "Joshua Harrison", "759285086", R.drawable.a5, about));
        list.add(group);

        group = new ContactGroup("My colleagues");
        group.addContact(new ContactItem(
                "Madison Johnson", "950285777", R.drawable.a6, about));
        group.addContact(new ContactItem(
                "Matthew Cotman", "687699999", R.drawable.a7, about));
        group.addContact(new ContactItem(
                "Olivia Lawson", "161863187", R.drawable.a8, about));
        group.addContact(new ContactItem(
                "Andrew Chapman", "546599645", R.drawable.a9, about));
        list.add(group);

        group = new ContactGroup("Others");
        group.addContact(new ContactItem(
                "Daniel Honeyman", "876545644", R.drawable.a10, about));
        group.addContact(new ContactItem(
                "Isabella Jackson", "907868756", R.drawable.a11, about));
        group.addContact(new ContactItem(
                "William Patterson", "687699693", R.drawable.a12, about));
        group.addContact(new ContactItem(
                "Joseph Godwin", "965467575", R.drawable.a13, about));
        group.addContact(new ContactItem(
                "Samantha Bush", "907865645", R.drawable.a14, about));
        group.addContact(new ContactItem(
                "Christopher Gateman", "896874556", R.drawable.a15, about));
        list.add(group);

//____________Делаем выборку из БД для ExbandableListAdapter_______________________________
        group = new ContactGroup("PLURAL");

//        if (column1 != null) // если курсор не пустой
//        {
            for (int i = 0; i < column1.length-1; i++) {
                group.addContact(new ContactItem(
                        column1[i], column2[i], R.drawable.a15, about));
            }

//        }
        list.add(group);

    }

    public void addColums(String tableName){
        // создаем коллекцию элементов для дочерней группы

        Cursor cursor = sqlHelper.getTable(tableName); // в классе DB должен быть метод
        column1 = new String[cursor.getCount()]; // объявляем массив
        column2 = new String[cursor.getCount()]; // объявляем массив
         // объявляем массив

        if (cursor.moveToFirst()) // если курсор не пустой
        {
            for (int i = 0; i < cursor.getCount(); i++)
            {
                column1[i] = cursor.getString(2); // заполняем
                column2[i] = cursor.getString(3); // заполняем


                cursor.moveToNext();
            }


        }
        cursor.close();
        sqlHelper.close();
    }


    private final String about = "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.";


}
