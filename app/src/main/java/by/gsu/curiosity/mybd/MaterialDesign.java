package by.gsu.curiosity.mybd;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import by.gsu.curiosity.mybd.adapter.TabsPagerFragmentAdapter;

public class MaterialDesign extends AppCompatActivity{
    DatabaseHelper sqlHelper;

    private static final int LAYOUT = R.layout.material_design;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    //    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppDefault);
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        initToolbar();
        initNavigationView();
        initTabs();

        // создаем базу данных 1ый раз для того что бы проинициализировать внутреннюю базу приложения значениями.
        // без этого при попытке прохождения теста без предворительного обращения к классу MainActivity приложение отваливается
        // КОСТЫЛЬ
//        sqlHelper = new DatabaseHelper(getApplicationContext());
//        sqlHelper.create_db();
//______________________________________________________________________________________________________
    }



    private void initToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        toolbar.inflateMenu(R.menu.menu_menu);
    }

    private void initTabs() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        TabsPagerFragmentAdapter adapter = new TabsPagerFragmentAdapter(getSupportFragmentManager());
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
                switch (menuItem.getItemId()){
                    case R.id.actionDictionary:
                        showNotificationTab();

                }
                return true;
            }
        });
//-----------------------------------------------------------------------------------------
    }
    //-----------------------------------------------------------------------------------------
    private void showNotificationTab (){
        viewPager.setCurrentItem(Constants.TAB_TWO);
    }
// -----------------------------------------------------------------------------------------

}
