package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import sahu.rohit.khata.Database.DatabseHelper;
import sahu.rohit.khata.Model.customer;
import sahu.rohit.khata.Model.transaction;

public class show_transaction extends AppCompatActivity {

    TextView id,name,amount,desc,date;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_transaction);

        id = findViewById(R.id.transaction_id);
        name = findViewById(R.id.transaction_username);
        amount = findViewById(R.id.transaction_amount);
        desc = findViewById(R.id.transaction_desc);
        date = findViewById(R.id.transaction_date);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setdata();

    }

    public void setdata() {
        transaction Customer = (transaction) Objects.requireNonNull(getIntent().getExtras()).getSerializable("transaction");
        assert Customer != null;
        //id.setText(Customer.getId());
        name.setText(Customer.getUsername());
        amount.setText(Customer.getAmount());
        desc.setText(Customer.getDesc());
        date.setText(Customer.getDate());
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
                startActivity(new Intent(this,transaction_list.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
