package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import sahu.rohit.khata.Database.DatabseHelper;
import sahu.rohit.khata.Model.customer;

public class Main_page extends AppCompatActivity {

    List<customer> list;
    ArrayList<String> theList;
    ListView listView;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    DatabseHelper db;
    FloatingActionButton floatingActionButton;
    FragmentManager fm;
    LinearLayout list_view_fragment;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.listview);
        db = new DatabseHelper(this);
        floatingActionButton = findViewById(R.id.flow_button);
        fm = getSupportFragmentManager();
        list_view_fragment = findViewById(R.id.listview_fragment);
        theList = new ArrayList<>();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Main_page.class));
                        break;

                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext(),settint.class));
                        Toast.makeText(getApplicationContext(),"Settings",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(),App_info.class));
                        break;

                    case R.id.sync:
                        retrive();
                        Toast.makeText(getApplicationContext(),"Sync Successfully",Toast.LENGTH_LONG).show();
                        break;

                    case R.id.share:
                        share();
                        break;

                    case R.id.contact:
                        startActivity(new Intent(getApplicationContext(),contect_us.class));
                        break;

                    case R.id.stats:
                        startActivity(new Intent(getApplicationContext(),statistics.class));
                        break;
                }
                return false;
            }
        });

        retrive();

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),add_transaction.class));
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                customer Customer = list.get(i);
                Intent intent = new Intent(getApplicationContext(),customer_data_show.class);
                intent.putExtra("customer",Customer);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                customer Customer = list.get(i);
                final String fname = Customer.getFirst_name();
                final String lname = Customer.getLast_name();
                final String phone = Customer.getMobile();
                final String w_phone = Customer.getWhatsapp();

                builder.setMessage("Note:- Data will be permanently delete..!")
                        .setCancelable(true)
                        .setTitle("Do you really want to Delete ?")
                        .setIcon(R.drawable.ic_warning)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Boolean delete = db.delete(fname,lname,phone,w_phone);
                                if(delete == true)
                                {
                                    Toast.makeText(getApplicationContext(),"Record Deleted Successfully",Toast.LENGTH_LONG).show();
                                    retrive();
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
                return false;
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

        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.delete:
                delete_all();
                break;

            case R.id.add:
                startActivity(new Intent(this,Customer_registration.class));

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
                        boolean delete = db.delete_all();
                        if(delete)
                        {
                            Toast.makeText(getApplicationContext(),"Record Deleted Successfully",Toast.LENGTH_LONG).show();
                            retrive();
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

    public void retrive()
    {
        list = db.getDetails();
        ArrayAdapter<customer> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
    }

    public void share()
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"KHATA BOOK");
        intent.putExtra(Intent.EXTRA_TEXT,"link will be available soon");
        startActivity(Intent.createChooser(intent,"Share Application Link"));
    }
}
