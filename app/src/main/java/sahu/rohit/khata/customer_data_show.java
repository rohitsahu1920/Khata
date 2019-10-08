package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import sahu.rohit.khata.Model.customer;

public class customer_data_show extends AppCompatActivity {

    ImageView profile;
    TextView fname,lname,gender,phone,whatsapp,address;
    Button update,pdf,alert;
    byte[] image;
    String fname1,lname1,gender1,phone1,whatsapp1,address1;
    int image_len;
    Bitmap id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_data_show);

        profile = findViewById(R.id.profile);
        fname = findViewById(R.id.fname_result);
        lname = findViewById(R.id.lname_result);
        gender = findViewById(R.id.gender_result);
        phone = findViewById(R.id.mobile_result);
        whatsapp = findViewById(R.id.whatsapp_mobile_result);
        address = findViewById(R.id.add_result);

        update = findViewById(R.id.update);
        pdf = findViewById(R.id.pdf);
        alert = findViewById(R.id.alert);

        setdata();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer Customer = (customer) getIntent().getExtras().getSerializable("renti");
                fname1 = Customer.getFirst_name();
                lname1 = Customer.getLast_name();
                gender1 = Customer.getGender();
                phone1 = Customer.getMobile();
                whatsapp1 = Customer.getWhatsapp();
                address1 = Customer.getAddress();
                image = Customer.getId_image();


                Intent intent = new Intent(getApplicationContext(),update_customer_data.class);
                intent.putExtra("fname",fname1);
                intent.putExtra("lname",lname1);
                intent.putExtra("gender",gender1);
                intent.putExtra("phone",phone1);
                intent.putExtra("w_phone",whatsapp1);
                intent.putExtra("c_address",address1);
                intent.putExtra("id_img",image);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.customer_detail_show,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.delete:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setdata()
    {
        customer Customer = (customer) getIntent().getExtras().getSerializable("renti");
        fname.setText(Customer.getFirst_name());
        lname.setText(Customer.getLast_name());
        //gender.setText(Customer.getGender());
        phone.setText(Customer.getMobile());
        whatsapp.setText(Customer.getWhatsapp());
        address.setText(Customer.getAddress());
        image_len = Customer.getId_image().length;
        image = Customer.getId_image();
        id1 = BitmapFactory.decodeByteArray(Customer.getId_image(),0,Customer.getId_image().length);
        profile.setImageBitmap(id1);
    }
}
