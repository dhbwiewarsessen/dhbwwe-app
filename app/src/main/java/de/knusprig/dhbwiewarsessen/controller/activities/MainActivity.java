package de.knusprig.dhbwiewarsessen.controller.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.fragments.UserRatingFragment;
import de.knusprig.dhbwiewarsessen.httprequest.RetrieveMenuRequest;
import de.knusprig.dhbwiewarsessen.httprequest.RetrieveRatingsRequest;
import de.knusprig.dhbwiewarsessen.model.Dish;
import de.knusprig.dhbwiewarsessen.model.Rating;
import de.knusprig.dhbwiewarsessen.model.User;
import de.knusprig.dhbwiewarsessen.model.Menu;
import de.knusprig.dhbwiewarsessen.controller.fragments.CreateRatingFragment;
import de.knusprig.dhbwiewarsessen.controller.fragments.MainPageFragment;
import de.knusprig.dhbwiewarsessen.controller.fragments.RatingsFragment;

public class MainActivity extends AppCompatActivity implements Observer {

    private DrawerLayout mDrawerLayout;
    private SharedPreferences prefs;
    private User currentUser;
    private Menu menu;
    private List<Rating> listRating = new ArrayList<>();

    private MainPageFragment mainPageFragment;
    private CreateRatingFragment createRatingFragment;
    private RatingsFragment ratingsFragment;
    private UserRatingFragment userRatingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPageFragment = new MainPageFragment();
        createRatingFragment = new CreateRatingFragment();
        ratingsFragment = new RatingsFragment();
        userRatingFragment = new UserRatingFragment();

        restoreSavedData();

        mDrawerLayout = findViewById(R.id.drawer_layout);

        final NavigationView navigationView = findViewById(R.id.nav_view);
        setupNavigationDrawer(navigationView);


        if (savedInstanceState == null) {
            switchToMainPageFragment();
            navigationView.setCheckedItem(R.id.nav_main);
        }

        initializeMainPageFragment();
        initializeRatingsFragment();
    }

    private void initializeMainPageFragment() {
        mainPageFragment.setMenu(menu);
        getMenuFromServer();
    }

    private void initializeRatingsFragment(){
        ratingsFragment.setListRating(listRating);
        getAllRatings();
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
                                switchToUserRatingsFragment();
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
                            case R.id.nav_logout:
                                logout();
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

    private void logout() {
        currentUser = new User(0, "","","","");
        currentUser.addObserver(this);
        invalidateOptionsMenu();
    }

    private void forwardToLoginActivity() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(intent, 123);
    }

    private void switchToMainPageFragment() {
        mainPageFragment.setMain(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                mainPageFragment).commit();
    }

    private void switchToRatingsFragment() {
        ratingsFragment.setMainActivity(this);
        ratingsFragment.setListRating(listRating);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                ratingsFragment).commit();
    }

    private void switchToUserRatingsFragment() {
        userRatingFragment.setMainActivity(this);
        userRatingFragment.setListRating(listRating);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                userRatingFragment).commit();
    }

    private void switchToCreateRatingsFragment() {
        createRatingFragment.setMain(this);
        createRatingFragment.setMenu(menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                createRatingFragment).commit();
    }

    public void switchToCreateRatingsFragment(int id) {
        createRatingFragment.setSelectedMenu(id);
        switchToCreateRatingsFragment();
    }

    private void getMenuFromServer() {
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        System.out.println("menus received");
                        for (int i = 0; i < 3; i++) {
                            Dish d = menu.getDishes().get(i);
                            d.setTitle(jsonResponse.getString("dish" + (i + 1)));
                            int price = jsonResponse.getInt("price" + (i + 1));
                            d.setPrice(((float) price) / 100);
                            System.out.println(i + ": " + d.getTitle() + ", " + d.getPrice() + "€");
                        }
                    } else {
                        System.out.println("couldn't get menus from Server");
                        System.out.println(jsonResponse);
                    }
                } catch (JSONException e) {
                    System.out.println("JSON Exception");
                    e.printStackTrace();
                }
            }
        };
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        System.out.println(date);
        System.out.println(new Date());
        RetrieveMenuRequest menuRequest = new RetrieveMenuRequest(date, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }

    private void getAllRatings() {
        final Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        System.out.println("ratings received");
                        //{"success":true,"rating_id":17,"dish":"Paniertes Seelachsfilet Zitronendip","rating":50,"comment":"delicious, finally some good fucking food","user_id":60}
                        int rating_id = jsonResponse.getInt("rating_id");
                        String dish = jsonResponse.getString("dish");
                        int rating = jsonResponse.getInt("rating");
                        String comment  = jsonResponse.getString("comment");
                        int user_id = jsonResponse.getInt("user_id");

                        listRating.add(new Rating(rating_id, new GregorianCalendar(), dish, rating, comment, user_id));
                    } else {
                        System.out.println("couldn't get menus from Server");
                        System.out.println(jsonResponse);
                    }
                } catch (JSONException e) {
                    System.out.println("JSON Exception");
                    e.printStackTrace();
                }
            }
        };
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        System.out.println(date);
        System.out.println(new Date());
        RetrieveRatingsRequest ratingsRequest = new RetrieveRatingsRequest(date, responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(ratingsRequest);
    }

    private void restoreSavedData() {
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int userId = prefs.getInt("userId", 0);
        String username = prefs.getString("username", "");
        String password = prefs.getString("password", "");
        String name = prefs.getString("name", ""); //changed to empty string for better displaying on the mainPage
        String email = prefs.getString("email", "");
        currentUser = new User(userId, username, email, name, password);
        currentUser.addObserver(this);

        List<Dish> dishes = new ArrayList<>();
        String[] dish = new String[3];
        float[] price = new float[3];
        for (int i = 0; i < 3; i++) {
            dish[i] = prefs.getString("dish" + i, "default-dish" + (i + 1));
            price[i] = prefs.getFloat("price" + i, (float) 13.37);
            Dish d = new Dish(dish[i], price[i]);
            dishes.add(d);
        }
        menu = new Menu(dishes);
        menu.addObserver(MainActivity.this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
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
        editPrefs.putInt("userId", currentUser.getUserId());
        editPrefs.putString("username", currentUser.getUsername());
        editPrefs.putString("password", currentUser.getPassword());
        editPrefs.putString("name", currentUser.getName());
        editPrefs.putString("email", currentUser.getEmail());
        for (int i = 0; i < menu.getDishes().size(); i++) {
            Dish d = menu.getDishes().get(i);
            editPrefs.putString("dish" + i, d.getTitle());
            editPrefs.putFloat("price" + i, d.getPrice());
        }
        editPrefs.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            currentUser.setUserId(data.getIntExtra("userId", 0));
            currentUser.setUsername(data.getStringExtra("username"));
            currentUser.setPassword(data.getStringExtra("password"));
            currentUser.setName(data.getStringExtra("name"));
            currentUser.setEmail(data.getStringExtra("email"));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o.getClass().equals(User.class)) {
            //update User on NavigationHeader
            invalidateOptionsMenu();
            //update User on MainPageFragment
            mainPageFragment.update();
            userRatingFragment.refreshList();
        } else if (o.getClass().equals(Menu.class)) {
            //update Menu on MainPageFragment
            mainPageFragment.setMenu(menu);
            mainPageFragment.update();
        }
    }

    public void refreshRatingLists()
    {
        ratingsFragment.refreshList();
        userRatingFragment.refreshList();
    }

    public void btnSendClicked(View view) {
        createRatingFragment.attemptAddRating(view);
        switchToUserRatingsFragment();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addRating(int rating, String comment, User user, Calendar date, String dish ){
        listRating.add(new Rating(date, dish, rating, comment, user.getUserId()));
    }

    public void addRating(int id, int rating, String comment, User user, Calendar date, String dish ){
        listRating.add(new Rating(id, date, dish, rating, comment, user.getUserId()));
    }

}
