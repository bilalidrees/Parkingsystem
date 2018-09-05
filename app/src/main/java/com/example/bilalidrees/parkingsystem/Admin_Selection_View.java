package com.example.bilalidrees.parkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Admin_Selection_View extends AppCompatActivity implements View.OnClickListener {
    public String  location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__selection__view);

        location=getIntent().getExtras().getString("data");

        findViewById(R.id.view_userdata).setOnClickListener(this);
        findViewById(R.id.feedback_reply).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.view_userdata:


                Intent intent =new Intent(this,Cancel_Booking.class);
                intent.putExtra("data",location);
                startActivity(intent);

                break;

            case R.id.feedback_reply:

                Intent intent1 =new Intent(this,feedback_view_reply.class);
                intent1.putExtra("data",location);
                startActivity(intent1);

                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.Signout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,Welcome.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
