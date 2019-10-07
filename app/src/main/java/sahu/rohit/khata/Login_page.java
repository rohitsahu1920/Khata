package sahu.rohit.khata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import sahu.rohit.khata.Database.DatabseHelper;

public class Login_page extends AppCompatActivity {

    Button login;
    TextView goto_registration,forget;
    EditText username,password;
    DatabseHelper db;
    String uname,pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = findViewById(R.id.login);
        goto_registration = findViewById(R.id.goto_registration);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        forget = findViewById(R.id.forget);
        db = new DatabseHelper(this);

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Forgot_page.class));
            }
        });

        goto_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Main_registration_page.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = username.getText().toString().trim();
                pwd = password.getText().toString().trim();

                if(uname.isEmpty() || pwd.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the Valid Input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean chk = db.emailpass(uname,pwd);
                    if(chk == true)
                    {
                        Toast.makeText(getApplicationContext(),"Login Successfuly",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Main_page.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"No such Details are found please register your self",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}
