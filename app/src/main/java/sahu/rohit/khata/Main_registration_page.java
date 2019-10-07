package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

import sahu.rohit.khata.Database.DatabseHelper;

public class Main_registration_page extends AppCompatActivity {

    EditText username,email,password,cpasswors;
    Button register;
    TextView goto_login;
    String uname,email1,pass,cpass;
    DatabseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registration_page);


        db = new DatabseHelper(this);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpasswors = findViewById(R.id.cpassword);
        register = findViewById(R.id.register);
        goto_login = findViewById(R.id.goto_login);


        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login_page.class));
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uname = username.getText().toString().trim();
                email1 = email.getText().toString().trim();
                pass = password.getText().toString().trim();
                cpass = cpasswors.getText().toString().trim();

                if((uname.isEmpty()) || (email1.isEmpty()) || (pass.isEmpty()) || (cpass.isEmpty()))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter the Valid Input", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(cpass))
                    {
                        Boolean chkmail = db.chkemail(email1);
                        if(chkmail == true)
                        {
                            Boolean insert = db.insert(uname,email1,pass);
                            if(insert == true)
                            {
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),Login_page.class));
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"There is problem in Registered",Toast.LENGTH_LONG).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Email is already Registered",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Password is Not matched",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

}
