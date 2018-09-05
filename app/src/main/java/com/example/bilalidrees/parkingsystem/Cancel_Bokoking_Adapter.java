package com.example.bilalidrees.parkingsystem;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Cancel_Bokoking_Adapter extends RecyclerView.Adapter <Cancel_Bokoking_Adapter.cancel_book_view_holder>{

    List<Slots> slotlist= new ArrayList<Slots>();
    public String location;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mSlotDatabaseReference;
    RecyclerView.Adapter adapter;


    public Cancel_Bokoking_Adapter(List<Slots> slotlist,String Location,RecyclerView.Adapter Adapter){
            this.adapter=Adapter;
            this.slotlist=slotlist;
            this.location=Location;

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();

        mSlotDatabaseReference=mFirebaseDatabase.getReference().child(location);
        Log.v("bilal", mSlotDatabaseReference.toString());
        Log.v("list",this.slotlist.toString());

    }

    @NonNull
    @Override
    public cancel_book_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View   view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cancel_book_recycler_view,parent,false);
        cancel_book_view_holder cb=new cancel_book_view_holder(view);

            return cb;
    }

    @Override
    public void onBindViewHolder(@NonNull cancel_book_view_holder holder, int position) {

        Slots  sd=slotlist.get(position);
        holder.name.setText(sd.getName());
        holder.slot_id.setText(sd.getId());
        holder.email.setText(sd.getEmail());
        holder.date.setText(sd.getDate());
        holder.sttime.setText(sd.getStart_time());
        holder.endtime.setText(sd.getEnd_time());


    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {

        if(slotlist!=null){
            return slotlist.size();
        } else{
            return 0;
        }
    }


    public class cancel_book_view_holder extends RecyclerView.ViewHolder{

        TextView name,slot_id,email,date,sttime,endtime ;

        public cancel_book_view_holder(View view){
            super(view);
            name=(TextView)view.findViewById(R.id.name);
            slot_id=(TextView)view.findViewById(R.id.slot_id);
            email=(TextView)view.findViewById(R.id.email);
            date=(TextView)view.findViewById(R.id.date);
            sttime=(TextView)view.findViewById(R.id.Start_time);
            endtime=(TextView)view.findViewById(R.id.end_time);


            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    Slots slot=new Slots(slot_id.getText().toString(),"","","","","","","true");

                    mSlotDatabaseReference.child(slot_id.getText().toString()).setValue(slot);

                    return true;
                }
            });

        }

    }
}


