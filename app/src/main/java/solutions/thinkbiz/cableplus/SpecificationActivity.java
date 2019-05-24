package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.io.InputStream;

public class SpecificationActivity extends AppCompatActivity {

    String Actname,filename;
    TextView textname;

   // private static final String FILENAME = "SF_A112.2_Ultra_Lite_Spec_Sheet.pdf";
    PDFView ReadTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specification);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.backbar);
        View view = getSupportActionBar().getCustomView();

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Actname = pref.getString("Actvname", "");
        filename = pref.getString("FILENAME", "");
        textname = (TextView) findViewById(R.id.textname);
        textname.setText(Actname);

      //  Log.e("file",filename);
//        Log.e("file",filename1);
//        Log.e("file",filename2);



        ImageButton imageButton = (ImageButton) view.findViewById(R.id.action_bar_back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
               // Intent intent=new Intent(SpecificationActivity.this, MainActivity.class);
               // startActivity(intent);

            }
        });

        ReadTxt=(PDFView)findViewById(R.id.pdfView);
        File file = new File(this.getCacheDir(), filename);

        if (!file.exists()) {
            try {
                InputStream asset = this.getAssets().open(filename);
                ReadTxt.fromStream(asset)
                        .pages(0)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true)
                        .defaultPage(0)
                        .enableAnnotationRendering(false)
                        .password(null)
                        .scrollHandle(null)
                        .enableAntialiasing(true)
                        .spacing(0)
                        .load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(getApplicationContext(),

                    "File not found",Toast.LENGTH_LONG).show();
        }
    }

    @Override    protected void onStart() {
        super.onStart();
    }
}
