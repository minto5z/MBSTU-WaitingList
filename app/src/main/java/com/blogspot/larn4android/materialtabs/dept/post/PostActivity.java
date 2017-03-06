package com.blogspot.larn4android.materialtabs.dept.post;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.blogspot.larn4android.materialtabs.R;
import com.blogspot.larn4android.materialtabs.model.Choose;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PostActivity extends AppCompatActivity {

    private EditText etApplicantName, etFatherName, etMeritPosition, etAdExamRoll;
    private String first, second, third, f_dept, s_dept, t_dept;
    private ProgressDialog mProgress;
    private Spinner spinner_first, spinner_second, spinner_third;
    private DatabaseReference mDatabase;
    private static ArrayList<Choose> cse_first_choose_list = new ArrayList<>();
    private static ArrayList<Choose> cse_first_ref_list = new ArrayList<>();
    private static ArrayList<Choose> cse_second_ref_list = new ArrayList<>();
    private static ArrayList<Choose> cse_third_ref_list = new ArrayList<>();


    private DatabaseReference first_te_ref = FirebaseDatabase.getInstance().getReference().child("te/firstte");
    private DatabaseReference second_te_ref = FirebaseDatabase.getInstance().getReference().child("te/secondte");
    private DatabaseReference te_third_ref = FirebaseDatabase.getInstance().getReference().child("te/thirdte");

    private DatabaseReference cse_final_ref = FirebaseDatabase.getInstance().getReference().child("cse/finallist");
    private DatabaseReference ict_final_ref = FirebaseDatabase.getInstance().getReference().child("ict/finallist");
    private DatabaseReference te_final_ref = FirebaseDatabase.getInstance().getReference().child("te/finallist");

    private DatabaseReference first_ict_ref = FirebaseDatabase.getInstance().getReference().child("ict/firstict");
    private DatabaseReference second_ict_ref = FirebaseDatabase.getInstance().getReference().child("ict/secondict");
    private DatabaseReference ict_third_ref = FirebaseDatabase.getInstance().getReference().child("ict/thirdict");

    private DatabaseReference first_cse_ref = FirebaseDatabase.getInstance().getReference().child("cse/firstcse");
    private DatabaseReference second_cse_ref = FirebaseDatabase.getInstance().getReference().child("cse/secondcse");
    private DatabaseReference cse_third_ref = FirebaseDatabase.getInstance().getReference().child("cse/thirdcse");


    private String str_first="",str_second="",str_third="";
    private ArrayList<Choose> ict_first_ref_list = new ArrayList<>();
    private ArrayList<Choose> ict_second_ref_list = new ArrayList<>();
    private ArrayList<Choose> ict_third_ref_list = new ArrayList<>();
    private ArrayList<Choose> te_first_ref_list = new ArrayList<>();
    private ArrayList<Choose> te_second_ref_list = new ArrayList<>();
    private ArrayList<Choose> te_third_ref_list = new ArrayList<>();
    private ArrayList<Integer> mc1 = new ArrayList<Integer>();

    private ArrayList<Choose> cse_final_list = new ArrayList<>();
    private ArrayList<Choose> ict_final_list = new ArrayList<>();
    private ArrayList<Choose> te_final_list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mProgress = new ProgressDialog(this);
        //EditText element
        etApplicantName = (EditText) findViewById(R.id.et_choose_applicant_name);
        etFatherName = (EditText) findViewById(R.id.et_choose_father_name);
        etMeritPosition = (EditText) findViewById(R.id.et_choose_merit_position);
        etAdExamRoll = (EditText) findViewById(R.id.et_choose_ad_exam_roll);


        // Spinner element
        spinner_first = (Spinner) findViewById(R.id.spinner_first);
        spinner_second = (Spinner) findViewById(R.id.spinner_second);
        spinner_third = (Spinner) findViewById(R.id.spinner_third);

        Button sub = (Button)findViewById(R.id.btn_sub);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//
//            }
//        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String categories[] = new String[]{"CSE", "ICT", "TE"};
        ArrayAdapter<String> firstAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        firstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_first.setAdapter(firstAdapter);
        ArrayAdapter<String> secondAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        secondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_second.setAdapter(secondAdapter);
        final ArrayAdapter<String> thirdAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        thirdAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_third.setAdapter(thirdAdapter);
        spinner_first.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                first = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + first, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner_second.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                second = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + second, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_third.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                third = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Selected: " + third, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void insertionSort(ArrayList<Integer> array) {
        int n = array.size();
        for (int j = 1; j < n; j++) {
            int key = array.get(j);
            int i = j - 1;
            while ((i > -1) && (array.get(i) > key)) {
                array.set(i + 1, array.get(i));
                i--;
            }
            array.set(i + 1, key);
        }
    }


    private void startPosting() {

        mProgress.setMessage("Uploading...");


        final String name = etApplicantName.getText().toString().trim();
        final String father = etFatherName.getText().toString().trim();
        final String merit = etMeritPosition.getText().toString().trim();
        final String adroll = etAdExamRoll.getText().toString().trim();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(father)
                && !TextUtils.isEmpty(merit)
                && !TextUtils.isEmpty(adroll)&&!first.equals(second)&&!second.equals(third)&&!first.equals(third)
                ) {
            mProgress.show();

            switch (first) {
                case "CSE":
                    f_dept = "cse/firstcse";
                    break;
                case "ICT":
                    f_dept = "ict/firstict";
                    break;
                case "TE":
                    f_dept = "te/firstte";
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Selected Department", Toast.LENGTH_SHORT).show();
                    break;
            }
            switch (second) {
                case "CSE":
                    s_dept = "cse/secondcse";
                    break;
                case "ICT":
                    s_dept = "ict/secondict";
                    break;
                case "TE":
                    s_dept = "te/secondte";
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Selected Department", Toast.LENGTH_SHORT).show();
                    break;
            }
            switch (third) {
                case "CSE":
                    t_dept = "cse/thirdcse";
                    break;
                case "ICT":
                    t_dept = "ict/thirdict";
                    break;
                case "TE":
                    t_dept = "te/thirdte";
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Selected Department", Toast.LENGTH_SHORT).show();
                    break;
            }

            Choose student;
            mDatabase = FirebaseDatabase.getInstance().getReference().child(f_dept);
            student = new Choose(name, father, Integer.parseInt(merit), Integer.parseInt(adroll));
            mDatabase.push().setValue(student);


            mDatabase = FirebaseDatabase.getInstance().getReference().child(s_dept);
            student = new Choose(name, father, Integer.parseInt(merit), Integer.parseInt(adroll));
            mDatabase.push().setValue(student);

            mDatabase = FirebaseDatabase.getInstance().getReference().child(t_dept);
            student = new Choose(name, father, Integer.parseInt(merit), Integer.parseInt(adroll));
            mDatabase.push().setValue(student);
            update_dept();
            etApplicantName.setText("");
            etFatherName.setText("");
            etMeritPosition.setText("");
            etAdExamRoll.setText("");
            spinner_first.setSelection(0);
            spinner_second.setSelection(0);
            spinner_third.setSelection(0);
            mProgress.dismiss();


        } else {
            update_dept();
            Toast.makeText(getApplicationContext(), "Something Wrong", Toast.LENGTH_SHORT).show();

        }
    }

    private void function_check()
    {
        Log.d("PostActivity.this",cse_first_ref_list.size() +" function_check_________________");
        for(Choose ch : cse_first_ref_list){
            Log.d("PostActivity.this",ch.getApplicant_name() +" function_check_________________");
        }
    }

    private void remove_function(ArrayList<Choose> checklist)
    {
        cse_first_ref_list.addAll(checklist);
        for(Choose ch: checklist)
            Log.d("PostActivity.this",ch.getApplicant_name() +" function");
        Log.d("PostActivity.this",cse_first_ref_list.size()+"cse_first");
    }
    String one ,two,three;
    public void update_dept()
    {
        //remove_function();
        if(!cse_first_ref_list.isEmpty())cse_first_ref_list.removeAll(cse_first_ref_list);
        final ArrayList<Choose> checklist = new ArrayList<>();
        first_cse_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //if(!cse_first_ref_list.isEmpty())cse_first_ref_list.removeAll(cse_first_ref_list);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    checklist.add(snapshot.getValue(Choose.class));
                }
                remove_function(checklist);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
        function_check();
        if(!ict_first_ref_list.isEmpty())ict_first_ref_list.removeAll(ict_first_ref_list);
        first_ict_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //if(!ict_first_ref_list.isEmpty())ict_first_ref_list.removeAll(ict_first_ref_list);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ict_first_ref_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        if(!te_first_ref_list.isEmpty())te_first_ref_list.removeAll(te_first_ref_list);
        first_te_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //if(!te_first_ref_list.isEmpty())te_first_ref_list.removeAll(te_first_ref_list);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    te_first_ref_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        ///~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Second list~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


        if(!cse_second_ref_list.isEmpty())cse_second_ref_list.removeAll(cse_second_ref_list);
        second_cse_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //if(!cse_second_ref_list.isEmpty())cse_second_ref_list.removeAll(cse_second_ref_list);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cse_second_ref_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

        if(!ict_second_ref_list.isEmpty())ict_second_ref_list.removeAll(ict_second_ref_list);
        second_ict_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //if(!ict_second_ref_list.isEmpty())ict_second_ref_list.removeAll(ict_second_ref_list);
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ict_second_ref_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        if(!te_second_ref_list.isEmpty())te_second_ref_list.removeAll(te_second_ref_list);
        second_te_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    te_second_ref_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~third list~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        if(!cse_third_ref_list.isEmpty())cse_third_ref_list.removeAll(cse_third_ref_list);
        cse_third_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cse_third_ref_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });

        if(!ict_third_ref_list.isEmpty())ict_third_ref_list.removeAll(ict_third_ref_list);
        ict_third_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ict_third_ref_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError){
            }
        });

        if(!te_third_ref_list.isEmpty())te_third_ref_list.removeAll(te_third_ref_list);
        te_third_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    te_third_ref_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });



        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Final list~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        if(!cse_final_list.isEmpty())cse_final_list.removeAll(cse_final_list);
        cse_final_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cse_final_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        if(!ict_final_list.isEmpty())ict_final_list.removeAll(ict_final_list);
        ict_final_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ict_final_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        if(!te_final_list.isEmpty())te_final_list.removeAll(te_final_list);
        te_final_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    te_final_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        if(!cse_first_choose_list.isEmpty())cse_first_choose_list.removeAll(cse_first_choose_list);
        for(Choose ch : cse_first_ref_list)
            cse_first_choose_list.add(ch);
        for(Choose ch: cse_second_ref_list)
            cse_first_choose_list.add(ch);
        for(Choose ch: cse_third_ref_list)
            cse_first_choose_list.add(ch);

        if(!mc1.isEmpty())mc1.removeAll(mc1);
        for (Choose ch : cse_first_choose_list) {
            mc1.add(ch.getMerit_position());
            Log.d("PostActivity.this",String.valueOf(ch.getMerit_position())+"------>ch");
        }
        insertionSort(mc1);

        cse_final_ref.removeValue();
        ict_final_ref.removeValue();
        te_final_ref.removeValue();

        Set<Integer> s = new HashSet<>();
        for(int c : mc1) {
            s.add(c);

            Log.d("PostActivity.this",String.valueOf(c)+"c");
        }

        for(int c : s){

            str_first = first_choose_fun(c);
            str_second = second_choose_fun(c);
            str_third = third_choose_fun(c);
            if(!str_first.equals("")&&!str_second.equals("")&&!str_third.equals(""))
                Log.d("PostActivity.this",str_first+"---------> "+str_second+"---------> "+str_third+"---------> "+String.valueOf(c));
            switch (str_first)
            {
                case "cse":
                    if(cse_final_list.size()<55){
                        //cse post
                        post_cse_function(c);
                    }
                    else{
                        switch (str_second)
                        {
                            case "ict":
                                if(ict_final_list.size()<55){
                                    //ict post
                                    post_ict_function(c);
                                }
                                else{
                                    if(te_final_list.size()<55)
                                        post_te_function(c);
                                }
                                break;
                            case "te":
                                if(te_final_list.size()<55){
                                    //te post
                                    post_te_function(c);
                                }
                                else {
                                    if (ict_final_list.size() < 55)
                                        post_ict_function(c);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case "ict":
                    if(ict_final_list.size()<55){
                        post_ict_function(c);
                    }
                    else{
                        switch (str_second)
                        {
                            case "cse":
                                if(cse_final_list.size()<55){
                                    //cse post
                                    post_cse_function(c);
                                }
                                else {
                                    if(te_final_list.size()<55)
                                        post_te_function(c);
                                }
                                break;
                            case "te":
                                if(te_final_list.size()<55){
                                    //te post
                                    post_te_function(c);
                                }
                                else{
                                    if(cse_final_list.size()<55)
                                        post_cse_function(c);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                case "te":
                    if(te_final_list.size()<55){
                        //te post
                        post_te_function(c);
                    }
                    else{
                        switch (str_second)
                        {
                            case "ict":
                                if(ict_final_list.size()<55){
                                    //ict post
                                    post_ict_function(c);
                                }
                                else{
                                    if(cse_final_list.size()<55)
                                        post_cse_function(c);
                                }
                                break;
                            case "cse":
                                if(cse_final_list.size()<55){
                                    //cse post
                                  post_cse_function(c);
                                }
                                else{
                                    if(ict_final_list.size()<55)
                                        post_ict_function(c);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }


            one="";
            two ="";
            three="";
        }
    }

    Choose student;
    private void post_cse_function(int c)
    {
        for (Choose ch : cse_first_choose_list) {

            if(ch.getMerit_position()==c) {
                student = new Choose(ch.getApplicant_name(), ch.getFather_name(), ch.getMerit_position(), ch.getAd_exam_roll());
                cse_final_ref.push().setValue(student);
            }
        }
    }

    private void post_ict_function(int c)
    {

        for (Choose ch : cse_first_choose_list) {

            if(ch.getMerit_position()==c) {
                student = new Choose(ch.getApplicant_name(), ch.getFather_name(), ch.getMerit_position(), ch.getAd_exam_roll());
                ict_final_ref.push().setValue(student);
            }
        }
    }
    private void post_te_function(int c)
    {
        for (Choose ch : cse_first_choose_list) {

            if(ch.getMerit_position()==c) {
                student = new Choose(ch.getApplicant_name(), ch.getFather_name(), ch.getMerit_position(), ch.getAd_exam_roll());
                te_final_ref.push().setValue(student);
            }
        }
    }


    private String first_choose_fun(final int position) {

        for (Choose ch : cse_first_ref_list) {
            if (ch.getMerit_position() == position) {
                one = "cse";
                break;
            }
        }
        for (Choose ch : ict_first_ref_list) {
            if (ch.getMerit_position() == position) {
                one="ict";
                break;
            }
        }
        for (Choose ch : te_first_ref_list) {
            if (ch.getMerit_position() == position) {
                one="te";
                break;
            }
        }
        if(one.equals("cse")||one.equals("ict")||one.equals("te"))return one;
        return "";
    }

    private String second_choose_fun(final int position) {

        for (Choose ch : cse_second_ref_list) {
            if (ch.getMerit_position() == position) {
                two = "cse";
                break;
            }
        }
        for (Choose ch : ict_second_ref_list) {
            if (ch.getMerit_position() == position) {
                two="ict";
                break;
            }
        }
        for (Choose ch : te_second_ref_list) {
            if (ch.getMerit_position() == position) {
                two="te";
                break;
            }
        }
        if(two.equals("cse")||two.equals("ict")||two.equals("te"))return two;
        return "";
    }

    private String third_choose_fun(final int position) {

        for (Choose ch : cse_third_ref_list) {
            if (ch.getMerit_position() == position) {
                three = "cse";
                break;
            }
        }
        for (Choose ch : ict_third_ref_list) {
            if (ch.getMerit_position() == position) {
                three="ict";
                break;
            }
        }
        for (Choose ch : te_third_ref_list) {
            if (ch.getMerit_position() == position) {
                three="te";
                break;
            }
        }
        if(three.equals("cse")||three.equals("ict")||three.equals("te"))return three;
        return "";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

}
