package com.example.bilalidrees.parkingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class book_slot extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public String  location;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mSlotDatabaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_slot);
        location=getIntent().getExtras().getString("data");

        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase=FirebaseDatabase.getInstance();

        mSlotDatabaseReference=mFirebaseDatabase.getReference().child(location);
        Log.v("bilal",mSlotDatabaseReference.toString());
        FirebaseUser user=mAuth.getCurrentUser();
        Log.v("bilal", user.getDisplayName());





      /* Slots slot=new Slots("Slot_1","","","","","","","true");

        mSlotDatabaseReference.child("Slot_1").setValue(slot).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    startActivity(new Intent(getApplicationContext(), parking_selection.class));
                }
                else{
                    Toast.makeText(getApplicationContext(), "Signed  up  failed", Toast.LENGTH_SHORT).show();

                }
            }
        });*/

        recyclerView =
                (RecyclerView) findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new book_slot_adapter(location);

            adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);




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
