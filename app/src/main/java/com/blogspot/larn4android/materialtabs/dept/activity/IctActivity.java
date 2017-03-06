package com.blogspot.larn4android.materialtabs.dept.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.blogspot.larn4android.materialtabs.R;
import com.blogspot.larn4android.materialtabs.dept.show.ShowActivity;
import com.blogspot.larn4android.materialtabs.model.Choose;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IctActivity extends AppCompatActivity {

    private RecyclerView mictStudentList;
    private DatabaseReference mictDatabase;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ict);


        mictDatabase = FirebaseDatabase.getInstance().getReference().child("ict/finallist");
        mictStudentList = (RecyclerView) findViewById(R.id.recycler_view_stu_info);
        mictStudentList.setHasFixedSize(true);
        mictStudentList.setLayoutManager(new LinearLayoutManager(this));
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        update_value();
        progressDialog.dismiss();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Developed By Mahadiur Jaman Minto", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




    }


    private void update_value() {


        FirebaseRecyclerAdapter<Choose,IctActivity.BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Choose, IctActivity.BlogViewHolder>(

                Choose.class,
                R.layout.recycler_ict,
                IctActivity.BlogViewHolder.class,
                mictDatabase
        )
        {
            @Override
            protected void populateViewHolder(IctActivity.BlogViewHolder viewHolder, Choose model, int position) {

                viewHolder.setApplicant_name(model.getApplicant_name());
                viewHolder.setFather_name(model.getFather_name());
                viewHolder.setMerit_position(model.getMerit_position());
                viewHolder.setAd_exam_roll(model.getAd_exam_roll());
            }
        };
        mictStudentList.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        update_value();
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        Context mContext;
        ArrayList<Choose> ict_list = new ArrayList<>();
        public BlogViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void setApplicant_name(String applicant_name){

            TextView post_title = (TextView)mView.findViewById(R.id.tv_student_name);
            post_title.setText("Applicant's Name : "+applicant_name);
        }

        public void setFather_name(String father_name) {
            TextView post_title = (TextView)mView.findViewById(R.id.tv_father_name);
            post_title.setText("Father's Name : "+father_name);
        }

        public void setMerit_position(int merit_position){

            TextView post_title = (TextView)mView.findViewById(R.id.tv_hall_name);
            post_title.setText("Merit Position : "+merit_position);
        }
        public void setAd_exam_roll(int ad_exam_roll){

            TextView post_title = (TextView)mView.findViewById(R.id.tv_dept_name);
            post_title.setText("Ad.Exam.Roll No : "+ad_exam_roll);
        }

        @Override
        public void onClick(View v) {


            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("ict/finallist");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        ict_list.add(snapshot.getValue(Choose.class));
                    }

                    int itemPosition = getLayoutPosition();

                    Intent intent = new Intent(mContext, ShowActivity.class);
                    int i=0;
                    for(Choose ch:ict_list){
                        if(i==itemPosition){
                            intent.putExtra("name",ch.getApplicant_name());
                            intent.putExtra("father",ch.getFather_name());
                            intent.putExtra("merit",String.valueOf(ch.getMerit_position()));
                            intent.putExtra("adroll",String.valueOf(ch.getAd_exam_roll()));
                            intent.putExtra("dept","ict");
                            mContext.startActivity(intent);
                            break;
                        }
                        i++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
    }
}