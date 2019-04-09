package solutions.thinkbiz.cableplus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    String Actname ;
    TextView textname;
    EditText editTextname,editTextEmail,editTextPhn,editTextPass,editTextConfPass;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonReg;
    String Uroll;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        ImageButton imageButton= (ImageButton)view.findViewById(R.id.action_bar_back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editTextname=(EditText)findViewById(R.id.editTextNm);
        editTextEmail=(EditText)findViewById(R.id.editTextem);
        editTextPhn=(EditText)findViewById(R.id.editTextphn);
        editTextPass=(EditText)findViewById(R.id.editTextpas);
        editTextConfPass=(EditText)findViewById(R.id.editTextps1);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton = (RadioButton) findViewById(R.id.rb1);

        buttonReg=(Button)findViewById(R.id.buttonL);
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValidate())
                {
                    Regst();
                }

            }
        });

    }

    private boolean isValidate()
    {
        final String email = editTextEmail.getText().toString().trim();

        if (editTextname.getText().toString().length() == 0) {
            editTextname.setError("Name not entered");
            editTextname.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return false;
        }
        if (editTextPhn.getText().toString().length() == 0) {
            editTextPhn.setError("Phone number not entered");
            editTextPhn.requestFocus();
            return false;
        }


        if (editTextPass.getText().toString().length() == 0) {
            editTextPass.setError("Password not entered");
            editTextPass.requestFocus();
            return false;
        }

        if (editTextConfPass.getText().toString().length() == 0) {
            editTextConfPass.setError("Please confirm password");
            editTextConfPass.requestFocus();
            return false;
        }

        if (!editTextPass.getText().toString().equals(editTextConfPass.getText().toString())) {
            editTextConfPass.setError("Password Not matched");
            editTextConfPass.requestFocus();
            return false;
        }

        if (editTextPass.getText().toString().length() < 5) {
            editTextPass.setError("Password should be atleast of 5 charactors");
            editTextPass.requestFocus();
            return false;
        }

        if (radioGroup.getCheckedRadioButtonId() == -1) {
            radioButton.setError("Please select one option");
            radioGroup.requestFocus();
            return false;
        }
        return true;
    }

    private void Regst(){
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        final String name = editTextname.getText().toString().trim();
        final String email = editTextEmail.getText().toString().trim();
        final String phone = editTextPhn.getText().toString().trim();
        final String password = editTextPass.getText().toString().trim();
        final String usertype = ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();

        if (usertype.equals("Dealer"))
        {
            Uroll="1";
        }
        else if (usertype.equals("Distributor")){
            Uroll="2";
        }
        else if (usertype.equals("Wholesaler")){
            Uroll="3";
        }
        else if (usertype.equals("Manufacturer")){
            Uroll="4";
        }
        else if (usertype.equals("Franchise")){
            Uroll="5";
        }

      // Log.e("resp",Uroll);
       // String url="http://demotbs.com/dev/cpe/webservices/registration?";
         String url="http://cableplus.superflexdirect.com/webservices/registration?";
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

                                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginPageActivity.class));
                                progressDialog.dismiss();
                                editTextname.setText("");
                                editTextEmail.setText("");
                                editTextPhn.setText("");
                                editTextPass.setText("");
                                editTextConfPass.setText("");
                                radioGroup.clearCheck();

                            }
                            else {
                                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }) {
            @Override

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("phone", phone);
                params.put("password", password);
                params.put("group_id", Uroll);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }



}
