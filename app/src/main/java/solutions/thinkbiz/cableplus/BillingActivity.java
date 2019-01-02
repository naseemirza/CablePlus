package solutions.thinkbiz.cableplus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import solutions.thinkbiz.cableplus.sqlitebds.DBAdapter;
import solutions.thinkbiz.cableplus.sqlitebds.TVShow;

public class BillingActivity extends AppCompatActivity {

    String Actname , userid,name,email,phone ;
    TextView textname;
    Button Submit;
    EditText editTextname,emailtxt,editTextphone,citytext,ziptext,addrsstext,msgtext;
    ProgressDialog progressDialog;
    ArrayList<TVShow> tvShows = new ArrayList<>();
    ArrayList<String> pids=new ArrayList<String>();
    ArrayList<String> names=new ArrayList<String>();
    ArrayList<String> quantitys=new ArrayList<String>();
    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.backbar);
        View view = getSupportActionBar().getCustomView();

        SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Actname = pref.getString("Actvname", "");
        userid = pref.getString("user_id", "");
        name = pref.getString("name", "");
        email = pref.getString("email", "");
        phone = pref.getString("phone", "");
        textname = (TextView) findViewById(R.id.textname);
        textname.setText(Actname);

        ImageButton imageButton = (ImageButton) view.findViewById(R.id.action_bar_back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                String actname="My Cart";
                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                edit.putString("Actvname",actname);

                edit.apply();
                Intent intent = new Intent(BillingActivity.this, OrderPageActivity.class);
                startActivity(intent);

            }
        });

       // db.deleteAll();

        tvShows.clear();
        DBAdapter db = new DBAdapter(this);
        db.openDB();

        Cursor c = db.getTVShows();
        while (c.moveToNext()) {

            //  i+=1;
            String name = c.getString(1);
            String url = c.getString(2);
            String price = c.getString(3);
            String Pid = c.getString(4);

            final TVShow tv = new TVShow();
            tv.setName(name);
            tv.setImageUrl(url);
            tv.setPrice(price);
            tv.setPid(Pid);

            tvShows.add(tv);
            Log.e("resp", String.valueOf(price));

        }

        for (int k =0;k<tvShows.size();k++){

            pids.add(tvShows.get(k).getPid());
            names.add(tvShows.get(k).getName());
            quantitys.add(tvShows.get(k).getPrice());


        }

       // Log.e("resp", String.valueOf(names));
       // Log.e("resp", String.valueOf(pid));
        //Log.e("resp", String.valueOf(quantity));


            editTextname = (EditText) findViewById(R.id.editTextNm);
            emailtxt = (EditText) findViewById(R.id.editTextem);
            editTextphone = (EditText) findViewById(R.id.phone);
            citytext = (EditText) findViewById(R.id.city);
            ziptext = (EditText) findViewById(R.id.zipcode);
            addrsstext = (EditText) findViewById(R.id.address);
            msgtext = (EditText) findViewById(R.id.message);

        editTextname.setText(name);
        emailtxt.setText(email);
        editTextphone.setText(phone);

            Submit = (Button) findViewById(R.id.buttonS);
            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                String actname="Thank You";
//                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor edit = pref.edit();
//
//                    edit.putString("Actvname",actname);
//                    edit.putString("prod_id", String.valueOf(pids));
//                    edit.putString("prod_name", String.valueOf(names));
//                    edit.putString("prod_quantity", String.valueOf(quantitys));
//
//                edit.apply();
                //Intent intent = new Intent(BillingActivity.this, ThanksActivity.class);
               // startActivity(intent);

                    if (isValidate()) {
                        SubmitDetails();
                    }
                }
            });
        }


    private boolean isValidate()
    {
        final String email = emailtxt.getText().toString().trim();

        if (editTextname.getText().toString().length() == 0) {
            editTextname.setError("Name not entered");
            editTextname.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            emailtxt.setError("Please enter your email");
            emailtxt.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailtxt.setError("Enter a valid email");
            emailtxt.requestFocus();
            return false;
        }
        if (editTextphone.getText().toString().length() == 0) {
            editTextphone.setError("Phone number not entered");
            editTextphone.requestFocus();
            return false;
        }

        if (citytext.getText().toString().length() == 0) {
            citytext.setError("City not entered");
            citytext.requestFocus();
            return false;
        }
        if (ziptext.getText().toString().length() == 0) {
            ziptext.setError("Zip code not entered");
            ziptext.requestFocus();
            return false;
        }
        if (addrsstext.getText().toString().length() == 0) {
            addrsstext.setError("Address not entered");
            addrsstext.requestFocus();
            return false;
        }
//        if (msgtext.getText().toString().length() == 0) {
//            msgtext.setError("Address not entered");
//            msgtext.requestFocus();
//            return false;
//        }
        return true;
    }

    private void SubmitDetails(){
        progressDialog = new ProgressDialog(BillingActivity.this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        final String name = editTextname.getText().toString().trim();
        final String email = emailtxt.getText().toString().trim();
        final String phone = editTextphone.getText().toString().trim();
        final String city = citytext.getText().toString().trim();
        final String zipcode = ziptext.getText().toString().trim();
        final String address = addrsstext.getText().toString().trim();
        final String message = msgtext.getText().toString().trim();

        //Log.e("resp",Uroll);
        String url="http://demotbs.com/dev/cpe/webservices/order?";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("resp",response);
                        progressDialog.dismiss();
                        try {

                            JSONObject obj = new JSONObject(response);
                            String success= obj.getString("s");
                            String error= obj.getString("e");
                            String msg=obj.getString("m");

                            if (success.equalsIgnoreCase("1"))
                            {
                                DBAdapter db = new DBAdapter(BillingActivity.this);
                                db.openDB();
                                String actname="Thank You";
                                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor edit = pref.edit();

                                edit.putString("Actvname",actname);
                                edit.apply();
                                Intent intent=new Intent(BillingActivity.this, ThanksActivity.class);
                                Toast.makeText(BillingActivity.this, msg, Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                progressDialog.dismiss();
                                editTextname.setText("");
                                emailtxt.setText("");
                                editTextphone.setText("");
                                citytext.setText("");
                                ziptext.setText("");
                                addrsstext.setText("");
                                msgtext.setText("");
                                db.deleteAll();

                            }
                            else {
                                Toast.makeText(BillingActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BillingActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BillingActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("pho", phone);
                params.put("city", city);
                params.put("zip_code", zipcode);
                params.put("address", address);
                params.put("user_id", userid);
                params.put("product_id", String.valueOf(pids));
                params.put("productQuantity", String.valueOf(quantitys));

                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
