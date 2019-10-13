package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

import sahu.rohit.khata.Database.DatabseHelper;


public class settint extends AppCompatActivity {

    Toolbar toolbar;
    Button wipe_customer_data,wipe_all_data,app_info;
    DatabseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settint);

        wipe_customer_data = findViewById(R.id.customer);
        wipe_all_data = findViewById(R.id.all);
        app_info = findViewById(R.id.info);
        db = new DatabseHelper(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        wipe_customer_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_all();
            }
        });

        wipe_all_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_all_data();
            }
        });

        app_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), App_info.class));
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

    public void delete_all()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Note:- Data will be permanently delete all DATA...!")
                .setCancelable(true)
                .setTitle("Do you really want to Delete ?")
                .setIcon(R.drawable.ic_warning)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Boolean delete = db.delete_all();
                        if(delete == true)
                        {
                            Toast.makeText(getApplicationContext(),"Record Deleted Successfully",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Problem in deleting Record",Toast.LENGTH_LONG).show();
                        }
                    }
                });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    public void delete_all_data()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Note:- Data will be permanently delete all DATA...!")
                .setCancelable(true)
                .setTitle("Do you really want to Delete ?")
                .setIcon(R.drawable.ic_warning)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Boolean delete = db.delete_all();
                        Boolean user = db.delete_all_user();
                        if(delete && user)
                        {
                            Toast.makeText(getApplicationContext(),"Record Deleted Successfully",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Problem in deleting Record",Toast.LENGTH_LONG).show();
                        }
                    }
                });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog alert11 = builder.create();
        alert11.show();
    }
}
