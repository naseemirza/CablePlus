package solutions.thinkbiz.cableplus;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class ComparChartActivity extends AppCompatActivity {

    private static final String FILENAME = "SF_Product_Comparision_Chart.pdf";
    PDFView ReadTxt;
    String Actname;
    TextView textname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compar_chart);

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

       // getSupportActionBar().setTitle("Product Comparision Chart");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ReadTxt=(PDFView)findViewById(R.id.pdfView);
        File file = new File(this.getCacheDir(), FILENAME);

        if (!file.exists()) {
            try {
                InputStream asset = this.getAssets().open(FILENAME);
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
