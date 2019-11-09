package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

import sahu.rohit.khata.Database.DatabseHelper;
import sahu.rohit.khata.Model.customer;
import sahu.rohit.khata.Model.transaction;

public class transaction_list extends AppCompatActivity {

    ListView listView;
    DatabseHelper db;
    Toolbar toolbar;
    ArrayList<sahu.rohit.khata.Model.transaction> transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);

        listView = findViewById(R.id.list);
        db =new DatabseHelper(this);
        toolbar = findViewById(R.id.toolbar);
        transaction = new ArrayList<>();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        transaction= db.gettransaction();
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,transaction);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sahu.rohit.khata.Model.transaction transaction1 = transaction.get(i);
                Intent intent = new Intent(getApplicationContext(),show_transaction.class);
                intent.putExtra("transaction",transaction1);
                startActivity(intent);
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
