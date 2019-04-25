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
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import de.knusprig.dhbwiewarsessen.R;
import de.knusprig.dhbwiewarsessen.controller.fragments.EditRatingFragment;
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
    private List<String> defValues = new ArrayList<>();

    private MainPageFragment mainPageFragment;
    private CreateRatingFragment createRatingFragment;
    private RatingsFragment ratingsFragment;
    private UserRatingFragment userRatingFragment;
    private EditRatingFragment editRatingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPageFragment = new MainPageFragment();
        createRatingFragment = new CreateRatingFragment();
        ratingsFragment = new RatingsFragment();
        userRatingFragment = new UserRatingFragment();
        editRatingFragment = new EditRatingFragment();

        restoreSavedData();

        defValues.add("Date");
        defValues.add("Dish");
        defValues.add("Name");

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
                menuItem -> {
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

    public void switchToEditRatingsFragment(Rating rating)
    {
        editRatingFragment.setRating(rating);
        editRatingFragment.setMain(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                editRatingFragment).commit();
    }

    private void getMenuFromServer() {
        final Response.Listener<String> responseListener = response -> {
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
        final Response.Listener<String> responseListener = response -> {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                boolean success = jsonResponse.getBoolean("success");
                if (success) {
                    listRating.clear();
                    for (int i = 0; i<jsonResponse.length()-1; i++) {
                        System.out.println("ratings received");
                        JSONArray jsonRating = jsonResponse.getJSONArray(""+i);
                        int rating_id = jsonRating.getInt(0);
                        String date = jsonRating.getString(1);
                        String dish = jsonRating.getString(2);
                        int rating = jsonRating.getInt(3);
                        String comment = jsonRating.getString(4);
                        String username = jsonRating.getString(5);

                        listRating.add(new Rating(rating_id, new GregorianCalendar(), dish, rating, comment, username));
                    }
                } else {
                    System.out.println("couldn't get menus from Server");
                    System.out.println(jsonResponse);
                }
            } catch (JSONException e) {
                System.out.println("JSON Exception");
                e.printStackTrace();
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            System.out.println("BACK BUTTON PRESSED");
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public void refreshRatingLists()
    {
        ratingsFragment.refreshList();
        userRatingFragment.refreshList();
    }

    public void updateLocalRating(Rating rating)
    {
        Rating matchingRating  = listRating.stream().filter(r -> r.getId() == rating.getId()).findFirst().orElse(null);
        Collections.replaceAll(listRating, matchingRating, rating);
    }

    public void btnSendClicked(View view) {
        if(createRatingFragment.attemptAddRating()){
            switchToUserRatingsFragment();
        }
    }

    public void btnEditClicked(View view)
    {
        editRatingFragment.attemptEditRating(view);
        switchToUserRatingsFragment();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void addRating(int rating, String comment, User user, Calendar date, String dish ){
        listRating.add(new Rating(date, dish, rating, comment, user.getUsername()));
    }

    public void addRating(int id, int rating, String comment, User user, Calendar date, String dish ){
        listRating.add(new Rating(id, date, dish, rating, comment, user.getUsername()));
    }

    public List<String> getDefValues() {
        return defValues;
    }

    public void refeshDataFromServer(){
        getAllRatings();
    }
}
