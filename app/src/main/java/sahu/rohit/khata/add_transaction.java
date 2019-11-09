package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import sahu.rohit.khata.Database.DatabseHelper;
import sahu.rohit.khata.Model.customer;

public class add_transaction extends AppCompatActivity {

    DatabseHelper db;
    Spinner customer_name;
    EditText amount,desc;
    Button submit;
    Toolbar toolbar;
    List<customer> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        db = new DatabseHelper(this);
        customer_name = findViewById(R.id.username);
        amount = findViewById(R.id.amount);
        desc = findViewById(R.id.description);
        submit = findViewById(R.id.submit);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        @SuppressLint("SimpleDateFormat") final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy G 'at' HH:mm:ss z");

        list = db.getUsername();
        ArrayAdapter<customer> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,list);
        customer_name.setAdapter(arrayAdapter);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = customer_name.getSelectedItem().toString();
                String price = amount.getText().toString();
                String desc1 = desc.getText().toString();
                String current_date = sdf.format(new Date());
                boolean chk = db.insert_transaction(name,price,desc1,current_date);
                if(chk)
                {
                    Toast.makeText(getApplicationContext(),"Transaction Saved",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Main_page.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Problem While Inserting Data",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_view,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                startActivity(new Intent(this,Main_page.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
