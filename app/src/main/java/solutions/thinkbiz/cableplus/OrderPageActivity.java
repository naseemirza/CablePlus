package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import solutions.thinkbiz.cableplus.sqlitebds.DBAdapter;
import solutions.thinkbiz.cableplus.sqlitebds.MyAdapter;
import solutions.thinkbiz.cableplus.sqlitebds.BillingItem;

public class OrderPageActivity extends AppCompatActivity {
    String Actname;
    TextView textname;
    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<BillingItem> billingItems = new ArrayList<>();
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
        adapter = new MyAdapter(this, billingItems);
        billingItems.clear();

        DBAdapter db = new DBAdapter(this);
        db.openDB();
        Cursor c = db.getTVShows();
        while (c.moveToNext()) {
            String name = c.getString(1);
            String url = c.getString(2);
            String qty = c.getString(3);
            String Pid = c.getString(4);

            final BillingItem tv = new BillingItem();
            tv.setName(name);
            tv.setImageUrl(url);
            tv.setPrice(qty);
            tv.setPid(Pid);

            billingItems.add(tv);
            checkoutbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String actname="Billing & Shipping Instructions";

                    Log.e("list", String.valueOf(qynty));
                    SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString("Actvname",actname);
                    edit.apply();
                    Intent intent = new Intent(OrderPageActivity.this, BillingActivity.class);
                    startActivity(intent);

                }
            });

        }

        if (billingItems.size() > 0) {
            rv.setAdapter(adapter);
            checkoutbtn.setVisibility(View.VISIBLE);

        }
        else if (billingItems.isEmpty())
        {
            checkoutbtn.setVisibility(View.GONE);
        }

        db.closeDB();
    }

}



