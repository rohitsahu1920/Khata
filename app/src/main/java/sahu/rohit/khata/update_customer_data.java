package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.Objects;

import sahu.rohit.khata.Database.DatabseHelper;

public class update_customer_data extends AppCompatActivity {

    EditText fname,lname,father_name,mobile,parents_mobile,occupation,permanent_address,working_address,pg_name,room_name,bed_number,whatsapp_number1;
    Button update_button,clear_button,id_button;
    RadioButton male,female;
    ImageView id_proof,back;
    private static int RESULT_LOAD_IMAGE_ID = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    DatabseHelper dataBaseHelper;
    byte[] id_image;
    String picpath_profile;
    Spinner gender;
    Toolbar toolbar;


    String renti_fname,renti_lname,renti_gender = "male",renti_father_name, renti_ocuupation,renti_permanent_address,renti_work_address,renti_pg_name,renti_room_name,renti_bed_number;
    String renti_mobile,renti_parents_mobile,renti_whatsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer_data);

        dataBaseHelper = new DatabseHelper(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //edit Text
        fname = findViewById(R.id.udfname);
        lname = findViewById(R.id.udlname);
        mobile = findViewById(R.id.udphone);
        whatsapp_number1 = findViewById(R.id.udwhatsapp);
        working_address = findViewById(R.id.udaddress);

        //buttons
        update_button = findViewById(R.id.udupdate);
        id_button = findViewById(R.id.udprofile_button);

        //image_view
        id_proof = findViewById(R.id.udprofile);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        if(b != null)
        {

            String fname1 = (String) b.get("fname");
            String lname1 = (String) b.get("lname");
            String gender1 = (String) b.get("gender");
            String pnone1 = (String) b.get("phone");
            String w_phone1 = (String) b.get("w_phone");
            String c_address1 = (String) b.get("c_address");
            byte[] id_img = (byte[]) b.get("id_img");

            mobile.setText(pnone1);
            whatsapp_number1.setText(w_phone1);
            working_address.setText(c_address1);
            id_proof.setImageBitmap(BitmapFactory.decodeByteArray(id_img,0,id_img.length));
            fname.setText(fname1);
            lname.setText(lname1);
        }

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

            case R.id.delete:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
