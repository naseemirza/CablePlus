package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import solutions.thinkbiz.cableplus.History.HistoryActivity;
import solutions.thinkbiz.cableplus.ProductListing.ProdAdapter;
import solutions.thinkbiz.cableplus.ProductListing.ProdModel;
import solutions.thinkbiz.cableplus.sqlitebds.DBAdapter;
import solutions.thinkbiz.cableplus.sqlitebds.DBHelper;
import solutions.thinkbiz.cableplus.sqlitebds.MyAdapter;
import solutions.thinkbiz.cableplus.sqlitebds.TVShow;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ProdAdapter mExampleAdapter1;
    private ArrayList<ProdModel> mExampleList1;
    private RequestQueue mRequestQueue1;
    private RecyclerView mRecyclerview1;

    RelativeLayout CartBtn;
    int contr=0;
    TextView CartItem;
    String usermail;
    TextView textViewemail;

    String title,img;
    int i = 0;
    String userid;

    SQLiteDatabase db;
    DBHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        //usertype = pref.getString("usertype", "");
        usermail = pref.getString("email", "");
        title = pref.getString("title", "");
        img = pref.getString("image", "");
        userid=pref.getString("user_id","");

        mExampleList1 = new ArrayList<>();
        mRequestQueue1 = Volley.newRequestQueue(this);
        mRecyclerview1=(RecyclerView)findViewById(R.id.my_recycler_jobs);
        mRecyclerview1.setNestedScrollingEnabled(false);
        mRecyclerview1.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerview1.setHasFixedSize(true);

        parseJSON1();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //textusertype= (TextView) navigationView.getHeaderView(0).findViewById(R.id.usernametext);
        textViewemail= (TextView) navigationView.getHeaderView(0).findViewById(R.id.textViewmail);
        //textusertype.setText(usertype);
        textViewemail.setText(usermail);

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorBlack));
        toggle.syncState();

        CartItem=(TextView) findViewById(R.id.cartcounter);
        CounterItems();

        CartBtn=(RelativeLayout)findViewById(R.id.CartRltv);
        CartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((contr+i)>0){
                String actname="My Cart";
                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("Actvname",actname);

                edit.apply();                                               //FoodListActivity
                Intent intent = new Intent(MainActivity.this, OrderPageActivity.class);
                startActivity(intent);
                }
             else {
                    Toast.makeText(MainActivity.this,"No Item Added",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void CounterItems() {

        DBAdapter db = new DBAdapter(this);
        db.openDB();

        Cursor c = db.getTVShows();
        while (c.moveToNext()) {

            i += 1;

            String name = c.getString(1);
            String url = c.getString(2);
            String price = c.getString(3);
            String Pid = c.getString(4);
        }
        if (i==0)
        {
            int j=0;
            CartItem.setText(String.valueOf(j));
        }
        if (i!=0){
            CartItem.setText(String.valueOf(i));

        }
         //Log.e("size", String.valueOf(i));
        db.closeDB();
    }

    private void parseJSON1() {

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        String url="http://demotbs.com/dev/cpe/webservices/product";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Log.e("rootJsonArray",response);
                        progressBar.setVisibility(View.INVISIBLE);

                        try {
                            Log.e("rootJsonArray",response);
                            JSONArray rootJsonArray = new JSONArray(response);

                            Log.e("rootJsonArrayLength",rootJsonArray.length()+"");

                            for (int i = 0; i < rootJsonArray.length(); i++) {
                                JSONObject object = rootJsonArray.getJSONObject(i);

                                mExampleList1.add(new ProdModel(object.optString("id"),
                                        object.optString("product_name"),
                                        object.optString("sku"),
                                        object.optString("spool"),
                                        object.optString("image"),
                                        object.optString("quantity")));

                            }

                            Log.e("rootJsonArray",mExampleList1.size()+"");
                                                                                                //,asyncResult_addNewConnection
                            mExampleAdapter1 = new ProdAdapter(MainActivity.this, mExampleList1,asyncResult_addNewConnection);
                            mRecyclerview1.setAdapter(mExampleAdapter1);
                            mExampleAdapter1.notifyDataSetChanged();
                            mRecyclerview1.setHasFixedSize(true);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("TAg",error.getMessage());
                    }
                });

        mRequestQueue1 = Volley.newRequestQueue(this);
        mRequestQueue1.add(stringRequest);
    }

    AsyncResult<Integer> asyncResult_addNewConnection = new AsyncResult<Integer>() {
        @Override
        public void success(Integer click) {

                contr++;
                CartItem.setText(String.valueOf(i+contr));

            final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bunce);

            // Use bounce interpolator with amplitude 0.2 and frequency 20
            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
            myAnim.setInterpolator(interpolator);

            CartBtn.startAnimation(myAnim);

        }

        @Override
        public void error(String error) {

        }

        @Override
        public void SendDataMethod(String name, String image, String price, String Pid) {
          save(name,image,price,Pid);
        }

    };

    class MyBounceInterpolator implements android.view.animation.Interpolator {
        private double mAmplitude = 1;
        private double mFrequency = 10;

        MyBounceInterpolator(double amplitude, double frequency) {
            mAmplitude = amplitude;
            mFrequency = frequency;
        }

        public float getInterpolation(float time) {
            return (float) (-1 * Math.pow(Math.E, -time/ mAmplitude) *
                    Math.cos(mFrequency * time) + 1);
        }
    }

    private void save(String name, String url, String price, String Pid) {
        DBAdapter db = new DBAdapter(this);
        db.openDB();

        long result = db.add(name, url, price, Pid);
        if (result == 1) {

            Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(getApplicationContext(), "Not Added", Toast.LENGTH_SHORT).show();
        }

        db.closeDB();
    }

    @Override
    public void onBackPressed() {

//        Intent startMain = new Intent(Intent.ACTION_MAIN);
//        startMain.addCategory(Intent.CATEGORY_HOME);
//        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startMain);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        if (id == R.id.action_profile) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {

            startActivity(new Intent(MainActivity.this,LoginPageActivity.class));
            // Handle the camera action
        }
        else if (id == R.id.nav_aboutus) {

        } else if (id == R.id.nav_history) {
            String actname="History";
            SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            edit.putString("Actvname",actname);
            edit.apply();
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
