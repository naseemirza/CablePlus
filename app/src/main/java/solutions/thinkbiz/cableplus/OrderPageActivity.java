package solutions.thinkbiz.cableplus;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import solutions.thinkbiz.cableplus.sqlitebds.Constants;
import solutions.thinkbiz.cableplus.sqlitebds.DBAdapter;
import solutions.thinkbiz.cableplus.sqlitebds.MyAdapter;
import solutions.thinkbiz.cableplus.sqlitebds.TVShow;

public class OrderPageActivity extends AppCompatActivity {

    String Actname;
    TextView textname;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<TVShow> tvShows = new ArrayList<>();

    Button checkoutbtn;
    int qynty;
    TextView datalist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.backbar);
        View view = getSupportActionBar().getCustomView();

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Actname = pref.getString("Actvname", "");
       // Cartitem = Integer.parseInt(pref.getString("Counter", ""));

        textname = (TextView) findViewById(R.id.textname);
        textname.setText(Actname);

        checkoutbtn=(Button)findViewById(R.id.checkout);

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.action_bar_back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OrderPageActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


        datalist=(TextView)findViewById(R.id.datavalbl);

        rv = (RecyclerView) findViewById(R.id.myRecyclerID);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        //ADAPTER
        adapter = new MyAdapter(this, tvShows);


//        rv.setAdapter(adapter);
        // get all data from sqlite

        tvShows.clear();

        DBAdapter db = new DBAdapter(this);
        db.openDB();

        Cursor c = db.getTVShows();
        while (c.moveToNext()) {

            //  i+=1;
            String name = c.getString(1);
            String url = c.getString(2);
            String qty = c.getString(3);
            String Pid = c.getString(4);


            final TVShow tv = new TVShow();
            tv.setName(name);
            tv.setImageUrl(url);
            tv.setPrice(qty);
            tv.setPid(Pid);

            tvShows.add(tv);


            checkoutbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String actname="Billing & Shipping Instructions";

                    Log.e("list", String.valueOf(qynty));
                    SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("Actvname",actname);
                    //edit.putString("CartItems", String.valueOf(tvShows));
                    edit.apply();
                    Intent intent = new Intent(OrderPageActivity.this, BillingActivity.class);
                    startActivity(intent);

                }
            });

        }

        if (tvShows.size() > 0) {
            rv.setAdapter(adapter);
            checkoutbtn.setVisibility(View.VISIBLE);
            //datalist.setVisibility(View.GONE);

        }
        else if (tvShows.isEmpty())
        {
            checkoutbtn.setVisibility(View.GONE);
            //datalist.setVisibility(View.VISIBLE);
        }

        db.closeDB();
    }

//    public void Quantitymethod(int finali) {
//
//        for (int k =0;k<tvShows.size();k++) {
//            int qunty= Integer.parseInt(tvShows.get(k).getPrice());
//            quantitys.add(tvShows.get(k).getPrice());
//            Log.e("qunty", String.valueOf(qunty));
//        }
//    }
//    @Override
//    public void onBackPressed() {
//        Intent intent=new Intent(OrderPageActivity.this, MainActivity.class);
//        startActivity(intent);
//    }



}



