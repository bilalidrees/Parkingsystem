package com.example.bilalidrees.parkingsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class user_feedback extends AppCompatActivity {

    String location,U_name;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mAdminDatabaseReference,mUserDatabaseReference;
    private Button submit;
    private EditText fed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feedback);
        location=getIntent().getExtras().getString("data");
        submit= (Button) findViewById(R.id.submit);
        fed=(EditText) findViewById(R.id.feedbacks);

        mAuth = FirebaseAuth.getInstance();

        mFirebaseDatabase=FirebaseDatabase.getInstance();
        mAdminDatabaseReference=mFirebaseDatabase.getReference().child("Admin");
        mUserDatabaseReference=mFirebaseDatabase.getReference().child("Users");
        Log.v("refrence", mUserDatabaseReference.toString());
        Log.v("refrence", mAdminDatabaseReference.toString());


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  uid= mAuth.getCurrentUser().getUid();
                String email=mAuth.getCurrentUser().getEmail();



                mUserDatabaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                            User us = postSnapshot.getValue(User.class);

                            if(us.getEmail().equals(mAuth.getCurrentUser().getEmail())){

                                U_name =us.getName();
                                Log.v("feedback",fed.getText().toString());
                                push(us.getName(),fed.getText().toString());

                                break;
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                private  void push(String name,String feed){

                    User us=new User(name,mAuth.getCurrentUser().getEmail(),feed);
                    mAdminDatabaseReference.child(mAuth.getUid()).setValue(us);
                }

                }


                );
                }


        });



    }
}
