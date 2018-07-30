package com.example.david.entregavel2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        initNavigationDrawer();
        initNavigationDrawerHeader();
        initDrawerListener(savedInstanceState);
    }

    private void initNavigationDrawer()
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        navigationView = (NavigationView) findViewById(R.id.navView);

        if (navigationView != null)
        {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    private void initNavigationDrawerHeader()
    {
        View header = navigationView.getHeaderView(0);
        name = (TextView) header.findViewById(R.id.name);

        setupUserInformations();
    }

    private void setupUserInformations()
    {
        name.setText(R.string.name);
    }

    private void initDrawerListener(Bundle savedInstanceState)
    {
        if (savedInstanceState == null)
        {
            MenuItem item = navigationView.getMenu().getItem(0);
            onNavigationItemSelected(item);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        if (isNavigationDrawerOpen())
        {
            closeNavigationDrawer();
        }
        else
        {
            super.onBackPressed();
        }
    }

    protected boolean isNavigationDrawerOpen()
    {
        return drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START);
    }

    protected void closeNavigationDrawer()
    {
        if (drawerLayout != null)
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        item.setChecked(true);
        drawerLayout.closeDrawers();
        selectDrawerItem(item);

        return true;
    }

    public void selectDrawerItem(MenuItem menuItem)
    {
        Fragment fragmento = null;

        switch (menuItem.getItemId())
        {
            case R.id.fragmento1:
                fragmento = new Fragment();
                break;

            case R.id.fragmento2:
                fragmento = new Fragment();
                break;

            default:
                break;
        }

        if(fragmento != null)
        {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.drawer_content, fragmento).commit();

            setTitle(menuItem.getTitle());
        }
    }
}