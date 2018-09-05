package com.example.bilalidrees.parkingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class user_Selection_view extends AppCompatActivity implements View.OnClickListener {

    public String  location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__selection_view);
        location=getIntent().getExtras().getString("data");

        findViewById(R.id.bookslot).setOnClickListener(this);
        findViewById(R.id.view_bookings).setOnClickListener(this);
        findViewById(R.id.feedback).setOnClickListener(this);

    }




    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.bookslot:


                Intent intent =new Intent(this,book_slot.class);
                intent.putExtra("data",location);
                startActivity(intent);

                break;

            case R.id.view_bookings:

                Intent intent1 =new Intent(this,Cancel_Booking.class);
                intent1.putExtra("data",location);
                startActivity(intent1);

                break;

            case R.id.feedback:

                Intent intent2 =new Intent(this,user_feedback.class);
                intent2.putExtra("data",location);
                startActivity(intent2);

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
