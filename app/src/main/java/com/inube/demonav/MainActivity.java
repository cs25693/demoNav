package com.inube.demonav;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private NavigationView mNavigationDrawer;
    private ActionBarDrawerToggle mDrawerToggle;
    FloatingActionButton fab;
    private TextView userName;
    private RecyclerView nav_drawer_recycler_view;
    NavDrawerAdapter navDrawerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setToolbar();
    }

    private void initUI() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mNavigationDrawer = (NavigationView) findViewById(R.id.navigation_view);
        userName = (TextView) mNavigationDrawer.findViewById(R.id.useName);
        userName.setText("513115");
        nav_drawer_recycler_view=(RecyclerView)mNavigationDrawer.findViewById(R.id.nav_drawer_recycler_view);
        nav_drawer_recycler_view.setLayoutManager(new LinearLayoutManager(this));
        navDrawerAdapter = new NavDrawerAdapter(this);
        nav_drawer_recycler_view.setAdapter(navDrawerAdapter);
        List<NavDrawerModel> notificationData = getAllData();
        navDrawerAdapter.addData(notificationData);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = setupDrawerToggle();
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setupDrawerContent(mNavigationDrawer);
       ((TextView) mNavigationDrawer.findViewById(R.id.txtversion)).setText(getString(R.string.copy_right_string, BuildConfig.VERSION_NAME));

    }

    private List<NavDrawerModel> getAllData() {
        List<NavDrawerModel> models=new ArrayList<>();
        models.add(new NavDrawerModel(getResources().getString(R.string.lead_camel_card),R.drawable.ic_suspect));
        models.add(new NavDrawerModel(getResources().getString(R.string.prospect_camel_card),R.drawable.ic_prospect));
        models.add(new NavDrawerModel(getResources().getString(R.string.need_analysis_card),R.drawable.ic_need_analysis));
        models.add(new NavDrawerModel(getResources().getString(R.string.quotation_camel_card),R.drawable.ic_quotation));
        models.add(new NavDrawerModel(getResources().getString(R.string.proposal_camel_card),R.drawable.ic_proposal));
        models.add(new NavDrawerModel(getResources().getString(R.string.knowledge_center_card),R.drawable.ic_resources));
        models.add(new NavDrawerModel(getResources().getString(R.string.update_camel_card),R.drawable.update));
        models.add(new NavDrawerModel(getResources().getString(R.string.notification_camel_card),R.drawable.ic_quotation));
        models.add(new NavDrawerModel(getResources().getString(R.string.logout_string_card),R.drawable.ic_logout_white));
        return models;
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
    }
    private void setupDrawerContent(NavigationView navigationView) {
//        navigationView.setNavigationItemSelectedListener(
//                new NavigationView.OnNavigationItemSelectedListener() {
//                    @Override
//                    public boolean onNavigationItemSelected(MenuItem menuItem) {
////                        selectDrawerItem(menuItem);
//                        return false;
//                    }
//                });
        navDrawerAdapter.setOnItemClickLister(new NavDrawerAdapter.OnItemSelectListener() {
            @Override
            public void onItemSelected(NavDrawerModel navDrawerModel) {
                switch (navDrawerModel.getTitle()) {

                    case  "Lead":
                        Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                        Intent suspectIntent = new Intent(MainActivity.this, ProductActivity.class);
                        startActivity(suspectIntent);

                        break;
                    case  "Prospect":
                        Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case  "Resource Center":
                        Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case  "Quotation":
                        Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case  "Proposal":
                        Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case  "Need Analysis":
                        Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case  "Notification":
                        Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case  "Update App":
                        Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case  "Logout":
                        Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }

    private void setToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(false);
            mDrawerToggle.setDrawerIndicatorEnabled(false);
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_menu_white_24dp, getTheme());
            mDrawerToggle.setHomeAsUpIndicator(drawable);
            actionBar.setTitle("HOME");
            mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    /*@Override
    public void onItemSelected(NavDrawerModel navDrawerModel) {
        switch (navDrawerModel.getTitle()) {

            case  "Lead":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Prospect":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Resource Center":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Quotation":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Proposal":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Need Analysis":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Notification":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Update App":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Logout":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;

        }
    }*//*@Override
    public void onItemSelected(NavDrawerModel navDrawerModel) {
        switch (navDrawerModel.getTitle()) {

            case  "Lead":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Prospect":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Resource Center":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Quotation":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Proposal":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Need Analysis":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Notification":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Update App":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case  "Logout":
                Toast.makeText(MainActivity.this, "You clicked at position: "+ navDrawerModel.getTitle(), Toast.LENGTH_SHORT).show();
                break;

        }
    }*/
}
