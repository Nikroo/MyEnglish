package by.gsu.curiosity.mybd.test;

        import android.content.ContentValues;
        import android.database.Cursor;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.content.Intent;
        import java.sql.SQLException;

        import by.gsu.curiosity.mybd.DatabaseHelper;
        import by.gsu.curiosity.mybd.R;
        import by.gsu.curiosity.mybd.test.ListActivity;

public class UserActivity extends AppCompatActivity {

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        long userId=0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            userId = extras.getLong("id");
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, PlaceholderFragment.newInstance(userId))
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        EditText ruBox;
        EditText enBox;
        Button delButton;
        Button saveButton;
        Button add;
        String table;



        DatabaseHelper sqlHelper;
        Cursor userCursor;

        public static PlaceholderFragment newInstance(long id) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args=new Bundle();
            args.putLong("id", id);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);

        }
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_user, container, false);
            ruBox = (EditText) rootView.findViewById(R.id.ru_word);
            enBox = (EditText) rootView.findViewById(R.id.en_word);
            delButton = (Button) rootView.findViewById(R.id.delete);
            saveButton = (Button) rootView.findViewById(R.id.save);
            add = (Button) rootView.findViewById(R.id.add);

            final long id = getArguments() != null ? getArguments().getLong("id") : 0;
            sqlHelper = new DatabaseHelper(getActivity());
            try {

                sqlHelper.open();
//                table = sqlHelper.table;

// кнопка удаления
                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sqlHelper.database.delete(DatabaseHelper.TABLE, "_id = ?",
                                new String[]{String.valueOf(id)});
                        goHome();
                    }
                });

// кнопка добавления
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues cv = new ContentValues();
                        cv.put(DatabaseHelper.EN_WORD, ruBox.getText().toString());
                        cv.put(DatabaseHelper.RU_WORD, enBox.getText().toString());
                        cv.put("wordUnit",DatabaseHelper.UNIT);
                        cv.put("wordLevel",DatabaseHelper.LEVEL);
                        sqlHelper.database.insert(DatabaseHelper.TABLE, null, cv);
                        goHome();
                    }

                });
// кнопка сохранения
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues cv = new ContentValues();
                        cv.put(DatabaseHelper.EN_WORD, ruBox.getText().toString());
                        cv.put(DatabaseHelper.RU_WORD, enBox.getText().toString());

                        if (id > 0) {
                            sqlHelper.database.update(DatabaseHelper.TABLE, cv,
                                    DatabaseHelper.COLUMN_ID + "=" + String.valueOf(id), null);
                        } else {
                            sqlHelper.database.insert(table, null, cv);
                        }
                        goHome();
                    }
                });
//  // если 0, то добавление

                if (id > 0) {

                    // получаем элемент по id из бд
                    userCursor = sqlHelper.database.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                            DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
                    userCursor.moveToFirst();
                    ruBox.setText(userCursor.getString(1));
                    enBox.setText(userCursor.getString(2));
                    userCursor.close();
                } else {
                    // скрываем кнопку удаления
                    delButton.setVisibility(View.GONE);
                }

            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            return rootView;
        }
        public void goHome(){

            sqlHelper.database.close();
            Intent intent = new Intent(getActivity(), ListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }


}