package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<ProdModel> productList;
    RecyclerView recyclerView;
    RelativeLayout CartBtn;
    int contr=0;
    TextView CartItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorBlack));
        toggle.syncState();


        CartItem=(TextView) findViewById(R.id.cartcounter);
        CartBtn=(RelativeLayout)findViewById(R.id.CartRltv);
        CartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actname="Cart";
                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("Actvname",actname);

                edit.apply();
                Intent intent = new Intent(MainActivity.this, CartItemsActivity.class);
                startActivity(intent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        productList = new ArrayList<>();

        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));
        productList.add(new ProdModel("SuperFlex A1 Ultra-Flex","0001", R.drawable.splsh,1));

                                                                    //,display
        ProdAdapter adapter = new ProdAdapter(this, productList,asyncResult_addNewConnection);
        recyclerView.setAdapter(adapter);

    }

    AsyncResult<Integer> asyncResult_addNewConnection = new AsyncResult<Integer>() {
        @Override
        public void success(Integer click) {

                contr++;
                CartItem.setText(String.valueOf(contr));

            final Animation myAnim = AnimationUtils.loadAnimation(MainActivity.this, R.anim.bunce);

            // Use bounce interpolator with amplitude 0.2 and frequency 20
            MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
            myAnim.setInterpolator(interpolator);

            CartBtn.startAnimation(myAnim);

//            if (contr==0){
//                contr++;
//                CartItem.setText(String.valueOf(contr));
//
//                if (contr>0){
//                    contr--;
//                    CartItem.setText(String.valueOf(contr));
//                }
//            }
//            else {
//                Toast.makeText(MainActivity.this,"Already Added To Cart",Toast.LENGTH_LONG).show();
//            }



        }

        @Override
        public void error(String error) {

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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

            startActivity(new Intent(MainActivity.this,LoginActivity.class));
            // Handle the camera action
        }
        else if (id == R.id.nav_aboutus) {

        } else if (id == R.id.nav_history) {

        }
//        else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
