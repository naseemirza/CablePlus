package solutions.thinkbiz.cableplus;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginPageActivity extends AppCompatActivity {

    private Button buttonLogin;
    private TextView reg, forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        reg = (TextView) findViewById(R.id.textViewRgs);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actname="Register Here";
                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.putString("Actvname",actname);

                edit.apply();
                Intent intent = new Intent(LoginPageActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgotpass = (TextView) findViewById(R.id.textViewfrgt);

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String actname="Forgot Password";
                SharedPreferences pref = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                edit.putString("Actvname",actname);

                edit.apply();
                Intent intent = new Intent(LoginPageActivity.this, ForgotPassActivity.class);
                startActivity(intent);
            }
        });

        buttonLogin = (Button) findViewById(R.id.buttonL);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(LoginPageActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
