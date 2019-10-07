package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.util.Objects;

import sahu.rohit.khata.Database.DatabseHelper;

public class Customer_registration extends AppCompatActivity {

    EditText fname,lname,mobile,address,whatsapp_number1;
    Button save_button,clear_button,id_button;
    private static int RESULT_LOAD_IMAGE_ID = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    ImageView id_proof,back;
    DatabseHelper dataBaseHelper;
    byte[] id_image;
    String picpath_profile;
    Spinner spinner;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    String first_name,last_name,phone,whatsapp,gender,address1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataBaseHelper = new DatabseHelper(this);
        spinner = findViewById(R.id.gender);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lastname);
        mobile = findViewById(R.id.phone);
        whatsapp_number1 = findViewById(R.id.whatsapp);
        address = findViewById(R.id.address);

        //buttons
        save_button = findViewById(R.id.save);
        clear_button = findViewById(R.id.clear);
        id_button = findViewById(R.id.photo);
        id_proof = findViewById(R.id.profile);

        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission())
                {
                    Intent id_button = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(id_button,RESULT_LOAD_IMAGE_ID);
                }
                else
                {
                    requestPermission();
                }
            }
        });

        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear_data();
            }
        });

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_name = fname.getText().toString().trim();
                last_name = lname.getText().toString().trim();
                phone = mobile.toString().trim();
                whatsapp = whatsapp_number1.getText().toString().trim();
                gender = spinner.getSelectedItem().toString();
                address1 = address.getText().toString().trim();
                id_image = imageViewToByte(id_proof);

                if( !first_name.isEmpty() && !last_name.isEmpty() && !phone.isEmpty() && !whatsapp.isEmpty() && !gender.isEmpty())
                {
                    if(phone.length() >= 10 && whatsapp.length() >= 10)
                    {
                        boolean chk = dataBaseHelper.insert_customer(first_name,last_name,gender,phone,whatsapp,address1,id_image);
                        if(chk)
                        {
                            Toast.makeText(getApplicationContext(),"Hurry Data Saved.....:)",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),Main_page.class);
                            startActivity(intent);
                            clear_data();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"There is Problem in saving data:(",Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Please Enter Valid Mobile Number",Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Valid Details",Toast.LENGTH_LONG).show();
                }
            }
        });

    }



    private byte[] imageViewToByte(ImageView image)
    {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,10,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_LOAD_IMAGE_ID && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            String[] path = {MediaStore.Images.Media.DATA};
            assert selectedImage != null;
            Cursor cursor = getContentResolver().query(selectedImage,path,null,null,null);
            assert cursor != null;
            cursor.moveToFirst();
            int columeIndex = cursor.getColumnIndex(path[0]);
            picpath_profile = cursor.getString(columeIndex);
            cursor.close();
            id_proof.setImageBitmap(BitmapFactory.decodeFile(picpath_profile));
        }
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    public void clear_data()
    {
        fname.setText("");
        lname.setText("");
        mobile.setText("");
        address.setText("");
        mobile.setText("");
        whatsapp_number1.setText("");
        id_proof.setImageResource(R.drawable.ic_photo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drawe_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(),Main_page.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
