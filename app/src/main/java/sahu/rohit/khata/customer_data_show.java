package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import sahu.rohit.khata.Model.customer;

public class customer_data_show extends AppCompatActivity {

    ImageView profile;
    TextView fname,lname,gender,phone,whatsapp,address;
    Button update,pdf,alert;
    byte[] image;
    String fname1,lname1,gender1,phone1,whatsapp1,address1;

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
        address = findViewById(R.id.p_add_result);

        update = findViewById(R.id.update);
        pdf = findViewById(R.id.pdf);
        alert = findViewById(R.id.alert);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customer Renti_model = (customer) getIntent().getExtras().getSerializable("renti");
                fname = Renti_model.getFirst_name();
                lname = Renti_model.getLast_name();
                gender1 = Renti_model.getGender();
                father = Renti_model.getFather_name();
                pnone = Renti_model.getMobile();
                w_phone = Renti_model.getWhatsapp();
                f_mobile = Renti_model.getP_mobile();
                occu = Renti_model.getOccupation();
                p_address = Renti_model.getPermanent_address();
                c_address = Renti_model.getCurrent_address();
                pg = Renti_model.getPg_name();
                room = Renti_model.getRoom_no();
                bed = Renti_model.getBed_no();
                id_img = Renti_model.getId_image();

                Intent intent = new Intent(getApplicationContext(),update_customer_data.class);
                intent.putExtra("fname",fname);
                intent.putExtra("lname",lname);
                intent.putExtra("gender",gender1);
                intent.putExtra("father",father);
                intent.putExtra("phone",pnone);
                intent.putExtra("w_phone",w_phone);
                intent.putExtra("f_mobile",f_mobile);
                intent.putExtra("occu",occu);
                intent.putExtra("p_address",p_address);
                intent.putExtra("c_address",c_address);
                intent.putExtra("pg",pg);
                intent.putExtra("room",room);
                intent.putExtra("bed",bed);
                intent.putExtra("id_img",id_img);

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
}
