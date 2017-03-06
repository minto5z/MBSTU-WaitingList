package com.blogspot.larn4android.materialtabs.dept.update;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.blogspot.larn4android.materialtabs.R;
import com.blogspot.larn4android.materialtabs.model.student_info;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateActivity extends AppCompatActivity {


    private RecyclerView mAnimalList;
    private DatabaseReference mDatabase;
    public static String dept="";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        String dep = getIntent().getStringExtra("upgrade");
        if(dep!=null&&(dep.equalsIgnoreCase("CSE")||dep.equalsIgnoreCase("cse")||dep.equalsIgnoreCase("Cse"))){
            dept="CSE";
        }
        if(dep!=null&&(dep.equalsIgnoreCase("ICT")||dep.equalsIgnoreCase("ict")||dep.equalsIgnoreCase("Ict"))){
            dept="ICT";
        }
        if(dep!=null&&(dep.equalsIgnoreCase("TE")||dep.equalsIgnoreCase("te")||dep.equalsIgnoreCase("Te"))){
            dept="TE";
        }


        mDatabase = FirebaseDatabase.getInstance().getReference().child(dept);
        mAnimalList = (RecyclerView)findViewById(R.id.upgrade_recycler_view);
        mAnimalList.setHasFixedSize(true);
        mAnimalList.setLayoutManager(new LinearLayoutManager(this));
        
        
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



    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<student_info,UpdateActivity.UpdateViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<student_info, UpdateActivity.UpdateViewHolder>(

                student_info.class,
                R.layout.recycler_cse,
                UpdateActivity.UpdateViewHolder.class,
                mDatabase
        )
        {
            @Override
            protected void populateViewHolder(UpdateActivity.UpdateViewHolder viewHolder, student_info model, int position) {

                viewHolder.setApplicant_name(model.getApplicant_name());
                viewHolder.setFather_name(model.getFather_name());
                viewHolder.setDepartment_name(model.getDepartment_name());
                viewHolder.setHall_name(model.getHall_name());


            }
        };
        mAnimalList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class UpdateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        Context mContext;


        private EditText et_applicant_name,et_father_name,et_hall_name,et_department_name,et_student_id;

        private EditText et_serial_no,et_merit_position,et_ad_exam_roll_no;
        
        
        public UpdateViewHolder(View itemView){
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
        public void setHall_name(String hall_name){

            TextView post_title = (TextView)mView.findViewById(R.id.tv_hall_name);
            post_title.setText("Hall : "+hall_name);
        }
        public void setDepartment_name(String department_name){

            TextView post_title = (TextView)mView.findViewById(R.id.tv_dept_name);
            post_title.setText("Department : "+department_name);
        }

        @Override
        public void onClick(View v) {

            final ArrayList<student_info> animals = new ArrayList<>();
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(UpdateActivity.dept);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        animals.add(snapshot.getValue(student_info.class));
                    }

                    int itemPosition = getLayoutPosition();
                    int count=0;
                    for (final DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {



                        if(count==itemPosition) {

                            Log.d("UpdateActivity.this",appleSnapshot.getRef()+" ref ::::::::::  key"+appleSnapshot.getKey());
                            //appleSnapshot.getRef().removeValue();
                            final HashMap<String, Object> result = new HashMap<>();

                            student_info studentInfo = appleSnapshot.getValue(student_info.class);



                            // get prompts.xml view
                            LayoutInflater li = LayoutInflater.from(mContext);
                            View promptsView = li.inflate(R.layout.prompts_upgrade, null);

                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                    mContext);

                            // set prompts.xml to alertdialog builder
                            alertDialogBuilder.setView(promptsView);


                            et_applicant_name = (EditText)promptsView.findViewById(R.id.et_up_applicants_name);
                            et_father_name = (EditText)promptsView.findViewById(R.id.et_up_fathers_name);
                            et_hall_name = (EditText)promptsView.findViewById(R.id.et_up_hall_name);
                            et_department_name = (EditText)promptsView.findViewById(R.id.et_up_dept);
                            et_student_id = (EditText)promptsView.findViewById(R.id.et_up_student_id);
                            et_serial_no = (EditText)promptsView.findViewById(R.id.et_up_serial_no);
                            et_merit_position = (EditText)promptsView.findViewById(R.id.et_up_merit_position);
                            et_ad_exam_roll_no = (EditText)promptsView.findViewById(R.id.et_up_ad_exam_roll);

                            et_applicant_name.setText(studentInfo.getApplicant_name());
                            et_father_name .setText(studentInfo.getFather_name());
                            et_hall_name .setText(studentInfo.getHall_name());
                            et_department_name.setText(studentInfo.getDepartment_name());
                            et_student_id.setText(studentInfo.getStudent_id());
                            et_serial_no.setText(studentInfo.getSerial_no());
                            et_merit_position .setText(studentInfo.getMerit_position());
                            et_ad_exam_roll_no .setText(studentInfo.getAd_exam_roll_no());

                            // set dialog message

                            alertDialogBuilder
                                    .setCancelable(false)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog,int id) {
                                                    // get user input and set it to result
                                                    // edit text
                                                    final String name = et_applicant_name.getText().toString().trim();
                                                    final String father = et_father_name.getText().toString().trim();
                                                    final String hall = et_hall_name.getText().toString().trim();
                                                    final String sdept = et_department_name.getText().toString().trim();
                                                    final String stu_id = et_student_id.getText().toString().trim();
                                                    final String serial = et_serial_no.getText().toString().trim();
                                                    final String merit = et_merit_position.getText().toString().trim();
                                                    final String adroll = et_ad_exam_roll_no.getText().toString().trim();
                                                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(father)
                                                            && !TextUtils.isEmpty(hall)
                                                            && !TextUtils.isEmpty(sdept)
                                                            && !TextUtils.isEmpty(stu_id)
                                                            && !TextUtils.isEmpty(serial)
                                                            && !TextUtils.isEmpty(merit)
                                                            && !TextUtils.isEmpty(adroll)
                                                            ) {

//applicant_name,father_name,,,,,,
                                                        result.put("applicant_name",name);
                                                        result.put("father_name", father);
                                                        result.put("hall_name",hall);
                                                        result.put("department_name", sdept);
                                                        result.put("student_id",stu_id);
                                                        result.put("serial_no", serial);
                                                        result.put("merit_position",merit);
                                                        result.put("ad_exam_roll_no", adroll);
                                                        ref.child(appleSnapshot.getKey()).updateChildren(result);
                                                    }
                                                }
                                            })
                                    .setNegativeButton("Cancel",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });

                            // create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            // show it
                            alertDialog.show();
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            

                            
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                    Log.d("RemoveActivity.this",databaseError.toString());
                }
            });
        }
    }


}
