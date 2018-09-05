package com.example.bilalidrees.parkingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class feedback_view_reply extends AppCompatActivity {
    public String  location;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mUserDatabaseReference;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    final List<User> userlist= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_view_reply);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        location=getIntent().getExtras().getString("data");

        mUserDatabaseReference = mFirebaseDatabase.getReference().child("Admin");
        Log.v("bilal", mUserDatabaseReference.toString());

        mUserDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    User us = postSnapshot.getValue(User.class);

                    Log.v("User  id", us.getName());
                    Log.v("User  id", us.getEmail());
                    Log.v("User  id", us.getFeedback());

                    userlist.add(us);

                        // adapter.notifyDataSetChanged();



                    }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        recyclerView =
                (RecyclerView) findViewById(R.id.recyclerview);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new feedback_view_reply_Adapter(userlist);
        recyclerView.setAdapter(adapter);


    }
}
