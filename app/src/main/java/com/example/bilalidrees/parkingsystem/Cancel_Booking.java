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

public class Cancel_Booking extends AppCompatActivity {
    public String  location;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mSlotDatabaseReference;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    final List<Slots> slotlist= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel__booking);
        location=getIntent().getExtras().getString("data");

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mSlotDatabaseReference = mFirebaseDatabase.getReference().child(location);
        Log.v("bilal", mSlotDatabaseReference.toString());

        mSlotDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Slots sd = postSnapshot.getValue(Slots.class);

                    Log.v("User  id", sd.getUid());
                    Log.v("dataid", mAuth.getUid());

                    if(mAuth.getCurrentUser().getEmail().equals("admin@gmail.com")){
                        Log.v("ADMIN", "Admin");
                        if(sd.getAvaliblity().equals("false")){
                            slotlist.add(sd);

                        }


                    }

                    else if(sd.getUid().equals(mAuth.getUid())){

                        slotlist.add(sd);


                       // adapter.notifyDataSetChanged();

                        Log.v("List", slotlist.toString());


                    }
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

        adapter = new Cancel_Bokoking_Adapter(slotlist,location,adapter);



        recyclerView.setAdapter(adapter);

    }
}
