package com.example.bilalidrees.parkingsystem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class parking_selection extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_selection);
        findViewById(R.id.Location1).setOnClickListener(this);
        findViewById(R.id.Location2).setOnClickListener(this);
        findViewById(R.id.Location3).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

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
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.Location1:

    if(mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")){
        Intent intent =new Intent(this,Admin_Selection_View.class);
        intent.putExtra("data","Location1");
        startActivity(intent);
        break;
        }
        else {

        Intent intent =new Intent(this,user_Selection_view.class);
        intent.putExtra("data","Location1");
        startActivity(intent);
        break;
        }



            case R.id.Location2:
                if(mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")){
                    Intent intent =new Intent(this,Admin_Selection_View.class);
                    intent.putExtra("data","Location2");
                    startActivity(intent);
                    break;
                }
                else {

                    Intent intent =new Intent(this,user_Selection_view.class);
                    intent.putExtra("data","Location2");
                    startActivity(intent);
                    break;
                }




            case R.id.Location3:

                if(mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")){
                    Intent intent =new Intent(this,Admin_Selection_View.class);
                    intent.putExtra("data","Location3");
                    startActivity(intent);
                    break;
                }
                else {

                    Intent intent =new Intent(this,user_Selection_view.class);
                    intent.putExtra("data","Location3");
                    startActivity(intent);
                    break;
                }



        }

    }

    @Override
    public void onStop()
    {
        super.onStop();
        this.setResult(0);
    }


    @Override
    public void onDestroy()
    {
        super.onDestroy();
        this.setResult(0);
    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder a_builder = new AlertDialog.Builder(parking_selection.this);
        a_builder.setMessage("Do you want to Close this App !!!")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle("Alert !!!");
        alert.show();


    }
}
