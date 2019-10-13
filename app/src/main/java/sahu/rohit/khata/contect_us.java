package sahu.rohit.khata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;


public class contect_us extends AppCompatActivity {

    Toolbar toolbar;
    EditText email,title,contact,issue,suggestion;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contect_us);

        email = findViewById(R.id.email);
        title = findViewById(R.id.title);
        contact = findViewById(R.id.phone);
        issue = findViewById(R.id.issue);
        suggestion = findViewById(R.id.suggestion);
        submit = findViewById(R.id.submit);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1 = email.getText().toString().trim();
                String title1 = title.getText().toString().trim();
                String contact1 = contact.getText().toString().trim();
                String issue1 = issue.getText().toString().trim();
                String suggestion1 = suggestion.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL,"uic.mca8152@gmail.com");
                intent.putExtra(Intent.EXTRA_SUBJECT,title1);
                intent.putExtra(Intent.EXTRA_TEXT,"Email :- "+email1+"\n Contact :- "+contact1+"\n Issue :- "+issue1+"\nSuggestion :- "+suggestion1);
                startActivity(Intent.createChooser(intent,"Send Email"));

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
