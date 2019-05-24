package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

public class SFProdInfoActivity extends AppCompatActivity {


    String Actname;
    TextView textname;
    CardView cardView1, cardView2, cardView3;

    private static final String FILENAME = "SF_12.2_Ultra_Lite_CCA_Specs.pdf";
    private static final String FILENAME1 = "SF_12.2_Ultra_Flex_Specs.pdf";
    private static final String FILENAME2 = "SF_14.2_Ultra_Flex_Specs.pdf";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfprod_info);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.backbar);
        View view =getSupportActionBar().getCustomView();

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Actname=pref.getString("Actvname","");
        textname=(TextView)findViewById(R.id.textname);
        textname.setText(Actname);

        // Log.e("pid",pid);

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cardView1=(CardView)findViewById(R.id.cardviw1);
        cardView2=(CardView)findViewById(R.id.cardviw2);
        cardView3=(CardView)findViewById(R.id.cardviw3);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actname="12/2 SuperFlex Ultra-Lite CCA";
                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("Actvname",actname);
                edit.putString("FILENAME",FILENAME);

                edit.apply();
                Intent intent = new Intent(SFProdInfoActivity.this, SpecificationActivity.class);
                startActivity(intent);

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actname="12/2 SuperFlex Ultra-Flex";
                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("Actvname",actname);
                edit.putString("FILENAME",FILENAME1);
                edit.apply();
                Intent intent = new Intent(SFProdInfoActivity.this, SpecificationActivity.class);
                startActivity(intent);

            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actname="14/2 SuperFlex Ultra-Flex";
                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("Actvname",actname);
                edit.putString("FILENAME",FILENAME2);
                edit.apply();
                Intent intent = new Intent(SFProdInfoActivity.this, SpecificationActivity.class);
                startActivity(intent);

            }
        });
    }
}
