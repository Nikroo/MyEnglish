package by.gsu.curiosity.mybd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import by.gsu.curiosity.mybd.adapter.TabsFragmentAdapter;
import by.gsu.curiosity.mybd.language.ChangeLanguage;


public class MaterialDesign extends AppCompatActivity {
    DatabaseHelper sqlHelper;
    private static final int LAYOUT = R.layout.material_design;
    private Toolbar toolbar;
    public static int theme_name;
    private ViewPager viewPager;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Получаем настройки данной activity
//        sp = PreferenceManager.getDefaultSharedPreferences(this);
//        int theme = sp.getInt("THEME",theme_name);

        setTheme(R.style.AppDefault);
        int theme = theme_name;
        setTheme(theme);

        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setBackgroundResource(Static.backgraund_main);


        initToolbar();
        initNavigationView();
        initTabs();

        // создаем базу данных 1ый раз для того что бы проинициализировать внутреннюю базу приложения значениями.
        // без этого при попытке прохождения теста без предворительного обращения к классу ListActivity приложение отваливается
        // КОСТЫЛЬ
        sqlHelper = new DatabaseHelper(getApplicationContext());

        sqlHelper.create_db();
//______________________________________________________________________________________________________
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu_menu);
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabsFragmentAdapter adapter = new TabsFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initNavigationView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//-----------------------------------------------------------------------------------------
// Вызываем активити (или фрагмент) из Drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.view_navigation_open, R.string.view_navigation_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                switch (menuItem.getItemId()) {

                    case R.id.locale:
                        Intent intent = new Intent(MaterialDesign.this, ChangeLanguage.class);
                        startActivity(intent);
                        break;
                    case R.id.dark:
                        theme_name = R.style.AppDefaultDark;
                        Static.backgraund_main = R.drawable.backgraund_dark;
//                        Static.backgraund_land = R.drawable.backgraund_land_dark;
                        // Перезагрузка Activity
                        restart();

                        break;
                    case R.id.norm:
                        theme_name = R.style.AppDefault;
//                        Static.backgraund_land = R.drawable.backgraund_land;
                        Static.backgraund_main = R.drawable.backgraund_main;
                        restart();

                        break;
                }
                return true;
            }
        });
    }
    private void restart() {
        Intent intent2 = getIntent();
        finish();
        startActivity(intent2);
    }

    //-----------------------------------------------------------------------------------------
    private void showNotificationTab() {
        viewPager.setCurrentItem(Constants.TAB_TWO);
    }


}
