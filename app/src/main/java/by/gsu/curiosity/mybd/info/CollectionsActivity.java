package by.gsu.curiosity.mybd.info;


import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import by.gsu.curiosity.mybd.R;

public class CollectionsActivity extends AppCompatActivity {


//    public static CollectionsActivity getInstance(){
//        Bundle args = new Bundle();
//        CollectionsActivity fragment = new CollectionsActivity();
//        fragment.setArguments(args);
//        return fragment;
//    }


    TextView text2;

    // названия компаний (групп)
    String[] groups = new String[]{"IRREGULAR PLURAL FORMS", "IRREGULAR VERBS", "IRREGULAR ADJECTIVES"};
    String[] column1;
    String[] column2;
    String[] column3;
    int[] colors = new int[2];
    // названия исключительных слов

//    String[] irrVerbs = new String[]{"speak", "write", "read"};
//    String[] irrPluar = new String[]{"man", "woman", "foot"};
//
//    String[] irrAny = new String[]{"word1", "word2", "word3"};
//    String[] irrAny2 = new String[]{"word4", "word5", "word6"};

    // коллекция для групп
    ArrayList<Map<String, String>> groupData;
    // коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;

    //общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    // в итоге получиться childData=ArrayList<childDataItem>

    // список атрибутов группы или элемента
    Map<String, String> m;

    ExpandableListView elvMain;
    ContactDbHelper sqlHelper;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);
        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        text2 = (TextView) findViewById(R.id.text2);


        sqlHelper = new ContactDbHelper(getApplicationContext());
        try {
            sqlHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        colors[0] = Color.parseColor("#dcdcdc");
        colors[1] = Color.parseColor("#0072bc");


        // заполняем коллекцию групп из массива с названиями груп

        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            // заполняем список атрибутов для каждой группы
            m = new HashMap<String, String>();
//            group = String.valueOf(cursor.getCount());
//            cursor.moveToNext();
            m.put("GROUPS", group); // имя компании
            groupData.add(m);
        }
        //список атрибутов групп для чтения
        String groupFrom[] = new String[]{"GROUPS"};
        //список ID view-элементов, в которые будут помещены аттрибуты групп
        int groupTo[] = new int[]{android.R.id.text1};

        //создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();
//-----------------------------------------------------------------

// добавляем в коллекцию коллекций
        addColums(ContactDbHelper.TABLE_IRREGULAR_PLURAL);
        childData.add(createItem(column1, column2, column3));
//      создаем коллекцию элементов для ВТОРОЙ группы
        addColums(ContactDbHelper.TABLE_IRREGULAR_VERBS);
        childData.add(createItem(column1, column2, column3));
//      создаем коллекцию элементов для ТРЕТЬЕЙ группы
        addColums(ContactDbHelper.TABLE_IRREGULAR_ADJECTIVES);
        childData.add(createItem(column1, column2, column3));


        // список аттрибутов элементов для чтения
        String childFrom[] = new String[]{"IPPANY", "VERBS", "PLUAR"};

        // список ID view- элементов, в которые будет помещены аттрибуты элементов
        int childTo[] = new int[]{R.id.text1, R.id.text2, R.id.text3};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                R.layout.simple_list_item_3,
                childFrom,
                childTo


        );

//        if (adapter.childPosition%2==0){
//            text2.setBackgroundColor(Color.parseColor("#6dcff6"));
//        } else {
//            if ((childPosition % 1) == 0) {
//                text2.setBackgroundColor(Color.parseColor("#444444"));
//            }
//        }


        elvMain.setAdapter(adapter);

        sqlHelper.close();

    }




    public ArrayList<Map<String, String>> createItem(String[] column1, String[] column2, String[] column3){
        // заполняем список аттрибутов для каждого элемента
        for (int i = 0; i < column1.length; i++) {
        m = new HashMap<String, String>();
        m.put("IPPANY", column1[i]);
        m.put("VERBS", column2[i]);
        m.put("PLUAR", column3[i]);

           childDataItem.add(m);

        }

        return childDataItem;
    }
    public void addColums(String tableName){
        // создаем коллекцию элементов для дочерней группы
        childDataItem = new ArrayList<Map<String, String>>();
        Cursor cursor = sqlHelper.getTable(tableName); // в классе DB должен быть метод
        column1 = new String[cursor.getCount()]; // объявляем массив
        column2 = new String[cursor.getCount()]; // объявляем массив
        column3 = new String[cursor.getCount()]; // объявляем массив

        if (cursor.moveToFirst()) // если курсор не пустой
        {
            for (int i = 0; i < cursor.getCount(); i++)
            {
                column1[i] = cursor.getString(1); // заполняем
                column2[i] = cursor.getString(2); // заполняем
                column3[i] = cursor.getString(3); // заполняем

                cursor.moveToNext();
            }


        }
        cursor.close();
    }
}





