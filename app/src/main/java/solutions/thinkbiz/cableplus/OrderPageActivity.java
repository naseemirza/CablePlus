package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderPageActivity extends AppCompatActivity {

    String Actname;
    TextView textname;
    List<OrderPageModel> productList;
    RecyclerView recyclerView;
    int Cartitem;

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
        Cartitem = Integer.parseInt(pref.getString("Counter", ""));
        textname = (TextView) findViewById(R.id.textname);
        textname.setText(Actname);

        //Log.e("counter", String.valueOf(Cartitem));

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.action_bar_back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        productList = new ArrayList<>();
        ListData();

//        productList.add(new OrderPageModel("SuperFlex A1 Ultra-Flex", R.drawable.splsh, 1));
//        productList.add(new OrderPageModel("SuperFlex A1 Ultra-Flex", R.drawable.splsh, 1));
//        productList.add(new OrderPageModel("SuperFlex A1 Ultra-Flex", R.drawable.splsh, 1));
//
//        OrderPageAdapter adapter = new OrderPageAdapter(this, productList);
//        recyclerView.setAdapter(adapter);
    }

    private void ListData() {

        for (int i = 0; i < Cartitem; i++) {
            productList.add(new OrderPageModel("SuperFlex A1 Ultra-Flex", R.drawable.splsh, 1));

        }
        OrderPageAdapter adapter = new OrderPageAdapter(this, productList);
        recyclerView.setAdapter(adapter);


    }


}
