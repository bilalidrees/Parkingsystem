
package com.example.bilalidrees.parkingsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

import static android.support.v4.content.ContextCompat.startActivity;
import static android.widget.Toast.LENGTH_SHORT;

public class book_slot_adapter extends RecyclerView.Adapter<book_slot_adapter.book_slot_view_holder> {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mSlotDatabaseReference,mUserDatabaseReference;
    public String  DATE,START_TIME,END_TIME,U_name;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TimePickerDialog.OnTimeSetListener  mst_listner,end_listner;

    public String location;
    private  Context context;
    RecyclerView.Adapter adapter;

    private String slots_name[] = {"Slot_1", "Slot_2", "Slot_3", "Slot_4", "Slot_5", "Slot_6"};
    private int[] images = {R.drawable.car, R.drawable.car2, R.drawable.car3, R.drawable.car4, R.drawable.car5
            , R.drawable.car6};


    public book_slot_adapter(String Location) {
        this.location = Location;
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mSlotDatabaseReference = mFirebaseDatabase.getReference().child(location);
        mUserDatabaseReference=mFirebaseDatabase.getReference().child("Users");

        Log.v("bilal", mSlotDatabaseReference.toString());
        Log.v("bilal", mUserDatabaseReference.toString());



    }


    @NonNull
    @Override
    public book_slot_view_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slot_recycler_view, parent, false);
        book_slot_view_holder bookslotviewholder = new book_slot_view_holder(view);
        return bookslotviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull book_slot_view_holder holder, int position) {

        holder.slots.setText(slots_name[position]);
        //Log.v("SSSLLLOOOTTSSSS",holder.slots.getText().toString());
        holder.imageView.setImageResource(images[position]);

        holder.color.setText("BOOK");


    }

    @Override
    public int getItemCount() {
        return slots_name.length;
    }


    public class book_slot_view_holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView slots, color;


        public book_slot_view_holder(final View itemView) {
            super(itemView);
            context=itemView.getContext();
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
            slots = (TextView) itemView.findViewById(R.id.item_title);
            color = (TextView) itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {


                    mSlotDatabaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {




                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                                Slots sd = postSnapshot.getValue(Slots.class);

                                if (sd.getId().equals(slots.getText().toString())) {

                                    if (sd.getAvaliblity().equals("true")) {
                                        color.setText("Available");
                                        //fetch_data(sd.getId());
                                       // endtime();
                                        //starttime();
                                        start(sd.getId());
                                        break;
                                        } else if (sd.getAvaliblity().equals("false")) {

                                        color.setBackgroundColor(Color.YELLOW);
                                        color.setText("BOOKED");
                                    }
                                }

                            }
                        }

                        private void start(final String s_id) {

                            Calendar cal = Calendar.getInstance();
                            int year = cal.get(Calendar.YEAR);
                            int month = cal.get(Calendar.MONTH);
                            int day = cal.get(Calendar.DAY_OF_MONTH);

                            DatePickerDialog dialog = new DatePickerDialog(
                                    context,
                                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                                    mDateSetListener,
                                    year,month,day);
                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                            dialog.setTitle("BOOKING DATE");
                            dialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);

                                Log.v("CRASHED","CRASHED");
                                dialog.show();



                            mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                    month = month + 1;
                                    Log.d("DATE", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                                    DATE= month + "/" + day + "/" + year;
                                    Log.v("DATE ",DATE);

//start time
                                    Calendar cal = Calendar.getInstance();
                                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                                    int minute = cal.get(Calendar.MINUTE);


                                    TimePickerDialog tp=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                                        @Override
                                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                            START_TIME=hourOfDay+":"+minute;

                                            Log.v("start_time",START_TIME);


                                                    //endtime

                                            Calendar cal = Calendar.getInstance();
                                            int hours = cal.get(Calendar.HOUR_OF_DAY);
                                            int minutes = cal.get(Calendar.MINUTE);

                                            TimePickerDialog tps=new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                                                @Override
                                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


                                                    END_TIME =hourOfDay+":"+minute;

                                                    Log.v("end_time",END_TIME);

                                                    fetch_data(s_id);

                                                }
                                            }, hours,minutes,false);

                                            tps.setTitle("End_Time");
                                            //set MinTime fucntion/will be  use
                                            
                                            tps.show();
                                                        //



                                        }
                                    }, hour,minute,false);

                                    tp.setTitle("Start_Time");
                                    tp.show();



                                }
                                };
                                }





       public void fetch_data(final String s_id){

           final String  uid= mAuth.getCurrentUser().getUid();
           final String email=mAuth.getCurrentUser().getEmail();

                  Log.v("EMAIL",email);

           mUserDatabaseReference=mFirebaseDatabase.getReference().child("Users");
           Log.v("refrence", mUserDatabaseReference.toString());

           mUserDatabaseReference.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {

                   for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                       User us = postSnapshot.getValue(User.class);
                       //Log.v("EMAIL",postSnapshot.getValue(String.class));
                       //Log.v("EMAIL",us.getEmail());

                       if(us.getEmail().equals(email)){

                           Log.v("name","BILAL");
                           U_name =us.getName();
                           Log.v("NAME",us.getName());
                           Log.v("name",U_name);

                           push(s_id,uid,us.getName(),us.getEmail());


                       }
                   }
               }


               @Override
               public void onCancelled(DatabaseError databaseError) {

                   }
           }

           );

       }


       public  void push(String s_id,String U_id,String u_name,String email){


                            Log.v("name",u_name);
           Slots slot=new Slots(s_id,U_id,u_name,email,DATE,START_TIME,END_TIME,"false");
           mSlotDatabaseReference.child("Slot_1").setValue(slot);

       }





                                                                     @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }

                    }
                    );

                }
            });








        }



    }





}
