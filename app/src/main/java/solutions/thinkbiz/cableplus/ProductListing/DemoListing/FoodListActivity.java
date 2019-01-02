package solutions.thinkbiz.cableplus.ProductListing.DemoListing;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

import solutions.thinkbiz.cableplus.MainActivity;
import solutions.thinkbiz.cableplus.ProductListing.ProdAdapter;
import solutions.thinkbiz.cableplus.R;
import solutions.thinkbiz.cableplus.sqlitebds.DBAdapter;
import solutions.thinkbiz.cableplus.sqlitebds.MyAdapter;
import solutions.thinkbiz.cableplus.sqlitebds.TVShow;

public class FoodListActivity extends AppCompatActivity {

    RecyclerView rv;
    MyAdapter adapter;
    ArrayList<TVShow> tvShows = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

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
            String name = c.getString(1);
            String url = c.getString(2);
            String price = c.getString(3);

            TVShow tv = new TVShow();
            tv.setName(name);
            tv.setImageUrl(url);
            tv.setPrice(price);

            tvShows.add(tv);
        }

        if (tvShows.size() > 0) {
            rv.setAdapter(adapter);
        }

        db.closeDB();

    }
}

