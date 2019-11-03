package sahu.rohit.khata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import sahu.rohit.khata.Database.DatabseHelper;
import sahu.rohit.khata.Model.customer;

public class add_transaction extends AppCompatActivity {

    DatabseHelper db;
    Spinner customer_name;
    EditText amount,desc;
    Button submit;
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

        list = db.getUsername();
        ArrayAdapter<customer> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,list);
        customer_name.setAdapter(arrayAdapter);



    }
}
