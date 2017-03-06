package com.blogspot.larn4android.materialtabs.dept.show;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.larn4android.materialtabs.R;

public class ShowActivity extends AppCompatActivity {

    //private TextView tv_applicant_name,tv_father_name,tv_hall_name,tv_department_name,tv_student_id;
    private TextView tv_applicant_name,tv_father_name;

    private TextView tv_merit_position,tv_ad_exam_roll_no;
    //private ArrayList<Choose> mRestaurants = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        ImageView image = (ImageView)findViewById(R.id.image_show);
        tv_applicant_name = (TextView) findViewById(R.id.et_show_applicants_name);
        tv_father_name = (TextView)findViewById(R.id.et_show_fathers_name);
        tv_merit_position = (TextView) findViewById(R.id.et_show_merit_position);
        tv_ad_exam_roll_no = (TextView) findViewById(R.id.et_show_ad_exam_roll);

        String applicantName = getIntent().getStringExtra("name");
        String fatherName = getIntent().getStringExtra("father");
        String adExamRollNo = getIntent().getStringExtra("adroll");
        String meritPosition = getIntent().getStringExtra("merit");
        String departmentName = getIntent().getStringExtra("dept");
        if(departmentName.equals("ICT")||departmentName.equals("Ict")||departmentName.equals("ict")){

            Resources res = getResources(); /** from an Activity */
            image.setImageDrawable(res.getDrawable(R.drawable.welcome_ict));
        }
        if(departmentName.equals("CSE")||departmentName.equals("Cse")||departmentName.equals("cse")){

            Resources res = getResources(); /** from an Activity */
            image.setImageDrawable(res.getDrawable(R.drawable.welcome_cse));
        }
        if(departmentName.equals("Te")||departmentName.equals("TE")||departmentName.equals("te")){

            Resources res = getResources(); /** from an Activity */
            image.setImageDrawable(res.getDrawable(R.drawable.welcome_te));
        }

        tv_applicant_name.setText(applicantName);
        tv_father_name.setText(fatherName);
        tv_merit_position.setText(meritPosition+"");
        tv_ad_exam_roll_no.setText(adExamRollNo+"");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}

