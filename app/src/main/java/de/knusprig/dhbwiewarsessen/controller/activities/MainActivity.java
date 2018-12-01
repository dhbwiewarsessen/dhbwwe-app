package de.knusprig.dhbwiewarsessen.controller.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.model.User;
import de.knusprig.dhbwiewarsessen.controller.fragments.CreateRatingFragment;
import de.knusprig.dhbwiewarsessen.controller.fragments.MainPageFragment;
import de.knusprig.dhbwiewarsessen.controller.fragments.RatingsFragment;

public class MainActivity extends AppCompatActivity implements Observer {

    private DrawerLayout mDrawerLayout;
    private SharedPreferences prefs;
    private User currentUser;
    private MainPageFragment mainPageFragment;
    private CreateRatingFragment createRatingFragment;
    private RatingsFragment ratingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPageFragment = new MainPageFragment();
        createRatingFragment = new CreateRatingFragment();
        ratingsFragment = new RatingsFragment();

        createUser();

        mDrawerLayout = findViewById(R.id.drawer_layout);

        final NavigationView navigationView = findViewById(R.id.nav_view);
        setupNavigationDrawer(navigationView);


        if(savedInstanceState == null) {
            switchToMainPageFragment();
            navigationView.setCheckedItem(R.id.nav_main);
        }
    }

    private void setupNavigationDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.nav_main:
                                switchToMainPageFragment();
                                break;
                            case R.id.nav_my_ratings:
                                switchToRatingsFragment();
                                break;
                            case R.id.nav_all_ratings:
                                switchToRatingsFragment();
                                break;
                            case R.id.nav_create_rating:
                                switchToCreateRatingsFragment();
                                break;

                            case R.id.nav_login:
                                forwardToLoginActivity();
                                break;
                            case R.id.nav_retrieve_menu:
                                Intent intent2 = new Intent(MainActivity.this, TestActivity.class);
                                startActivityForResult(intent2, 125);
                                break;
                        }
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        mDrawerLayout.addDrawerListener(
                new DrawerLayout.DrawerListener() {
                    @Override
                    public void onDrawerSlide(View drawerView, float slideOffset) {
                        // Respond when the drawer's position changes
                    }

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        // Respond when the drawer is opened
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        // Respond when the drawer is closed
                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        // Respond when the drawer motion state changes
                    }
                }
        );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void forwardToLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(intent,123);
    }

    private void switchToMainPageFragment() {
        mainPageFragment.setName(currentUser.getName());
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                mainPageFragment).commit();
    }

    private void switchToRatingsFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                ratingsFragment).commit();
    }

    private void switchToCreateRatingsFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                createRatingFragment).commit();
    }

    private void createUser() {
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = prefs.getString("username","default-username");
        String password = prefs.getString("password", "default-password");
        String name = prefs.getString("name","default-name");
        String email = prefs.getString("email","default-email");
        currentUser = new User(username,email,name,password);
        currentUser.addObserver(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        ((TextView) findViewById(R.id.header_name)).setText(currentUser.getName());
        ((TextView) findViewById(R.id.header_email)).setText(currentUser.getEmail());
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

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editPrefs = prefs.edit();
        editPrefs.putString("username", currentUser.getUsername());
        editPrefs.putString("password", currentUser.getPassword());
        editPrefs.putString("name", currentUser.getName());
        editPrefs.putString("email", currentUser.getEmail());
        editPrefs.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            currentUser.setUsername(data.getStringExtra("username"));
            currentUser.setPassword(data.getStringExtra("password"));
            currentUser.setName(data.getStringExtra("name"));
            currentUser.setEmail(data.getStringExtra("email"));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().equals(User.class)){
            //update User on View
            invalidateOptionsMenu();
            mainPageFragment.setName(currentUser.getName());
            mainPageFragment.update();
        }
    }
}
