package com.example.bilalidrees.parkingsystem;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class feedback_view_reply_Adapter  extends RecyclerView.Adapter <feedback_view_reply_Adapter.feeback_reply_view_Holder>{


    List<User> userlist= new ArrayList<User>();
    public String location;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mSlotDatabaseReference;
    RecyclerView.Adapter adapter;


    public feedback_view_reply_Adapter(List<User> Userlist){

        this.userlist=Userlist;
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();

        }

    @NonNull
    @Override
    public feeback_reply_view_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View   view= LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_recycler_view,parent,false);
        feeback_reply_view_Holder  fb=new feeback_reply_view_Holder(view);

        return fb;
    }

    @Override
    public void onBindViewHolder(@NonNull feeback_reply_view_Holder holder, int position) {
        User  sd=this.userlist.get(position);

        holder.name.setText(sd.getName());
        holder.email.setText(sd.getEmail());
        holder.feedb.setText(sd.getFeedback());

    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {

        if(this.userlist!=null){
            return this.userlist.size();
        } else{
            return 0;
        }
    }

    public class feeback_reply_view_Holder extends RecyclerView.ViewHolder{
        TextView name,email,feedb,data;
        Button sub;
        public feeback_reply_view_Holder(View itemView) {

            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            email=(TextView)itemView.findViewById(R.id.email_id);
            feedb=(TextView)itemView.findViewById(R.id.reply_feed);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                AlertDialog.Builder  dialogBuilder=new AlertDialog.Builder(v.getContext());
                final LayoutInflater inflater =  (LayoutInflater) v.getContext().getSystemService( v.getContext().LAYOUT_INFLATER_SERVICE );
                final View dialogView = inflater.inflate(R.layout.activity_user_feedback, null);
                dialogBuilder.setView(dialogView);
                    data=(TextView)dialogView.findViewById(R.id.feedbacks);
                    sub=(Button)dialogView.findViewById(R.id.submit);

                sub.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.setData(Uri.parse("mailto:"));
                        String [] mail={"bilalidreesbilal2@gmail.com","bilalidreesbilal2@gmail.com"};
                        intent.putExtra(Intent.EXTRA_EMAIL,mail);
                        intent.putExtra(Intent.EXTRA_SUBJECT,"your feedback reply");
                        intent.putExtra(Intent.EXTRA_TEXT,data.getText().toString());
                        intent.setType("message/rfc822");
                        v.getContext().startActivity(intent);
                    }
                    }
                    );

                    final AlertDialog b = dialogBuilder.create();
                    b.show();

                    return true;
                }
            });


        }

    }

    /*public void dialogbox(String  Email){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_student, null);
        dialogBuilder.setView(dialogView);
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String  mail="bilalidreesbilal2@gmail.com";
        intent.putExtra(Intent.EXTRA_EMAIL,mail);
        intent.putExtra(Intent.EXTRA_SUBJECT,"your feedback reply");

    }*/


}

