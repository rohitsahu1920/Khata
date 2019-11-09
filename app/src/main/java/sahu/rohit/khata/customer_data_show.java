package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import sahu.rohit.khata.Database.DatabseHelper;
import sahu.rohit.khata.Model.customer;

public class customer_data_show extends AppCompatActivity {

    private static final int REQUEST_PHONE_CALL = 1;
    ImageView profile;
    Toolbar toolbar;
    TextView fname, lname, gender, phone, whatsapp, address;
    Button update, pdf, alert;
    byte[] image;
    String fname1, lname1, gender1, phone1, whatsapp1, address1;
    int image_len;
    Bitmap id1;
    DatabseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_data_show);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        profile = findViewById(R.id.profile);
        fname = findViewById(R.id.fname_result);
        lname = findViewById(R.id.lname_result);
        gender = findViewById(R.id.gender_result);
        phone = findViewById(R.id.mobile_result);
        whatsapp = findViewById(R.id.whatsapp_mobile_result);
        address = findViewById(R.id.add_result);
        db = new DatabseHelper(this);
        update = findViewById(R.id.update);
        pdf = findViewById(R.id.pdf);
        alert = findViewById(R.id.alert);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setdata();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer Customer = (customer) getIntent().getExtras().getSerializable("customer");
                fname1 = Customer.getFirst_name();
                lname1 = Customer.getLast_name();
                gender1 = Customer.getGender();
                phone1 = Customer.getMobile();
                whatsapp1 = Customer.getWhatsapp();
                address1 = Customer.getAddress();
                image = Customer.getId_image();


                Intent intent = new Intent(getApplicationContext(), update_customer_data.class);
                intent.putExtra("fname", fname1);
                intent.putExtra("lname", lname1);
                intent.putExtra("gender", gender1);
                intent.putExtra("phone", phone1);
                intent.putExtra("w_phone", whatsapp1);
                intent.putExtra("c_address", address1);
                intent.putExtra("id_img", image);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customer_detail_show, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.delete:
                delete_data();
                break;
            case R.id.phone:
                call();
                break;
            case R.id.whatsapp:
                whatsapp();
                break;
            case android.R.id.home:
                startActivity(new Intent(this, Main_page.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setdata() {
        customer Customer = (customer) getIntent().getExtras().getSerializable("customer");
        fname.setText(Customer.getFirst_name());
        lname.setText(Customer.getLast_name());
        //gender.setText(Customer.getGender());
        phone.setText(Customer.getMobile());
        whatsapp.setText(Customer.getWhatsapp());
        address.setText(Customer.getAddress());
        image_len = Customer.getId_image().length;
        image = Customer.getId_image();
        id1 = BitmapFactory.decodeByteArray(Customer.getId_image(), 0, Customer.getId_image().length);
        profile.setImageBitmap(id1);
    }

    public void delete_data()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        fname1 = fname.getText().toString().trim();
        lname1 = lname.getText().toString().trim();
        phone1 = phone.getText().toString().trim();
        whatsapp1 = whatsapp.getText().toString().trim();

        builder.setMessage("Note:- Data will be permanently delete..!")
                .setCancelable(true)
                .setTitle("Do you really want to Delete ?")
                .setIcon(R.drawable.ic_warning)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Boolean delete = db.delete(fname1, lname1, phone1, whatsapp1);
                        if (delete == true) {
                            Toast.makeText(getApplicationContext(), "Record Deleted Successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),Main_page.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Problem in deleting Record", Toast.LENGTH_LONG).show();
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



    @RequiresApi(api = Build.VERSION_CODES.M)
    public void call()
    {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse("tel:" + phone.getText().toString()));
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(getApplicationContext(),"Please provide Calling Permission",Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please provide Calling Permission",Toast.LENGTH_LONG).show();
        }
        startActivity(i);
    }

    public void whatsapp()
    {
        try
        {
            String number = "+91 "+ whatsapp.getText().toString();
            Intent what = new  Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+""+number+"?body="+""));
            what.setPackage("com.whatsapp");
            startActivity(what);
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
        }
    }
}
