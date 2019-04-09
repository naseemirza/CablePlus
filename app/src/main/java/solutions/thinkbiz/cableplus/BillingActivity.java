package solutions.thinkbiz.cableplus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

import solutions.thinkbiz.cableplus.sqlitebds.BillingItem;
import solutions.thinkbiz.cableplus.sqlitebds.DBAdapter;

public class BillingActivity extends AppCompatActivity {

    String Actname , userid,name,email,phone, city, state, zipcode, address, message ;
    TextView textname;
    Button Submit;
    EditText editTextname,emailtxt,editTextphone,citytext,ziptext,addrsstext,msgtext,statetext;
    ProgressDialog progressDialog;
    ArrayList<BillingItem> billingItems = new ArrayList<>();
    ArrayList<String> pids=new ArrayList<String>();
    ArrayList<String> names=new ArrayList<String>();
    ArrayList<String> quantitys=new ArrayList<String>();
    DBAdapter db;
    //final String name, email, phone, city, state, zipcode, address, message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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

        city = pref.getString("citytxt", "");
        state = pref.getString("statetxt", "");
        zipcode = pref.getString("ziptxt", "");
        address = pref.getString("addresstxt", "");
        message = pref.getString("msgtxt", "");

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

        billingItems.clear();
        DBAdapter db = new DBAdapter(this);
        db.openDB();

        Cursor c = db.getTVShows();
        while (c.moveToNext()) {

            //  i+=1;
            String name = c.getString(1);
            String url = c.getString(2);
            String price = c.getString(3);
            String Pid = c.getString(4);

            final BillingItem tv = new BillingItem();
            tv.setName(name);
            tv.setImageUrl(url);
            tv.setPrice(price);
            tv.setPid(Pid);

            billingItems.add(tv);
            Log.e("resp", String.valueOf(price));

        }

        for (int k = 0; k< billingItems.size(); k++){

            pids.add(billingItems.get(k).getPid());
            names.add(billingItems.get(k).getName());
            quantitys.add(billingItems.get(k).getPrice());

        }
            editTextname = (EditText) findViewById(R.id.editTextNm);
            emailtxt = (EditText) findViewById(R.id.editTextem);
            editTextphone = (EditText) findViewById(R.id.phone);
            citytext = (EditText) findViewById(R.id.city);
            statetext = (EditText) findViewById(R.id.state);
            ziptext = (EditText) findViewById(R.id.zipcode);
            addrsstext = (EditText) findViewById(R.id.address);
            msgtext = (EditText) findViewById(R.id.message);

        editTextname.setText(name);
        emailtxt.setText(email);
        editTextphone.setText(phone);
        citytext.setText(city);
        statetext.setText(state);
        ziptext.setText(zipcode);
        addrsstext.setText(address);
        msgtext.setText(message);



            Submit = (Button) findViewById(R.id.buttonS);
            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

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
        if (addrsstext.getText().toString().length() == 0) {
            addrsstext.setError("Address not entered");
            addrsstext.requestFocus();
            return false;
        }

        if (citytext.getText().toString().length() == 0) {
            citytext.setError("City not entered");
            citytext.requestFocus();
            return false;
        }
        if (statetext.getText().toString().length() == 0) {
            statetext.setError("State not entered");
            statetext.requestFocus();
            return false;
        }

        if (ziptext.getText().toString().length() == 0) {
            ziptext.setError("Zip code not entered");
            ziptext.requestFocus();
            return false;
        }


        return true;
    }

    private void SubmitDetails(){
        progressDialog = new ProgressDialog(BillingActivity.this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

         name = editTextname.getText().toString().trim();
         email = emailtxt.getText().toString().trim();
         phone = editTextphone.getText().toString().trim();
         city = citytext.getText().toString().trim();
         state = statetext.getText().toString().trim();
         zipcode = ziptext.getText().toString().trim();
         address = addrsstext.getText().toString().trim();
         message = msgtext.getText().toString().trim();

        String url="http://cableplus.superflexdirect.com/webservices/order?";
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
                                edit.putString("citytxt",city);
                                edit.putString("ziptxt",zipcode);
                                edit.putString("addresstxt",address);
                                edit.putString("msgtxt",message);
                                edit.putString("statetxt",state);
                                edit.apply();
                                Intent intent=new Intent(BillingActivity.this, ThanksActivity.class);
                                Toast.makeText(BillingActivity.this, msg, Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                progressDialog.dismiss();
//                                editTextname.setText("");
//                                emailtxt.setText("");
//                                editTextphone.setText("");
//                                citytext.setText("");
//                                ziptext.setText("");
//                                addrsstext.setText("");
//                                msgtext.setText("");
//                                statetext.setText("");
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
                params.put("phone", phone);
                params.put("address", address);
                params.put("city", city);
                params.put("state", state);
                params.put("zip_code", zipcode);
                params.put("message", message);
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
