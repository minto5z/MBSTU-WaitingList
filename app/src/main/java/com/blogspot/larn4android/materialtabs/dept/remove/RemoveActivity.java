package com.blogspot.larn4android.materialtabs.dept.remove;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.blogspot.larn4android.materialtabs.R;
import com.blogspot.larn4android.materialtabs.dept.sign.LoginActivity;
import com.blogspot.larn4android.materialtabs.model.CardArrayAdapter;
import com.blogspot.larn4android.materialtabs.model.Choose;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RemoveActivity extends AppCompatActivity {



    private ArrayList<Choose> cse_first_choose_list = new ArrayList<>();


    private DatabaseReference first_cse_ref = FirebaseDatabase.getInstance().getReference().child("cse/firstcse");
    private DatabaseReference second_cse_ref = FirebaseDatabase.getInstance().getReference().child("cse/secondcse");
    private DatabaseReference cse_third_ref = FirebaseDatabase.getInstance().getReference().child("cse/thirdcse");


    private DatabaseReference remove_ref = FirebaseDatabase.getInstance().getReference().child("remove/removelist");
    private ArrayList<Integer> mc1 = new ArrayList<Integer>();

    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;
    private RecyclerView mteStudentList;
    private DatabaseReference mteDatabase;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private String one ,two,three;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);



        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(RemoveActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
        //update_value();

        mteStudentList = (RecyclerView) findViewById(R.id.remove_student_recycler_view);
        mteStudentList.setHasFixedSize(true);
        mteStudentList.setLayoutManager(new LinearLayoutManager(this));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                update_value();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void update_value() {


//        if(!cse_first_choose_list.isEmpty()&&!mc1.isEmpty()){
//            cse_first_choose_list.removeAll(cse_first_choose_list);
//            mc1.removeAll(mc1);
//        }

        first_cse_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cse_first_choose_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        second_cse_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cse_first_choose_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        cse_third_ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    cse_first_choose_list.add(snapshot.getValue(Choose.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        for (Choose ch : cse_first_choose_list) {
            mc1.add(ch.getMerit_position());
        }
        insertionSort(mc1);


        Choose student;
        for(int c:mc1) {

            for (Choose ch : cse_first_choose_list) {
                if(c==ch.getMerit_position()) {
                    student = new Choose(ch.getApplicant_name(), ch.getFather_name(), ch.getMerit_position(), ch.getAd_exam_roll());
                    remove_ref.push().setValue(student);
                }


            }
        }
        if(!cse_first_choose_list.isEmpty()&&!mc1.isEmpty()){
            cse_first_choose_list.removeAll(cse_first_choose_list);
            mc1.removeAll(mc1);
        }
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

    @Override
    protected void onStart() {
        super.onStart();

        auth.addAuthStateListener(authListener);
        FirebaseRecyclerAdapter<Choose,RemoveViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Choose, RemoveActivity.RemoveViewHolder>(

                Choose.class,
                R.layout.list_item_card,
                RemoveActivity.RemoveViewHolder.class,
                remove_ref
        )
        {
            @Override
            protected void populateViewHolder(RemoveActivity.RemoveViewHolder viewHolder, Choose model, int position) {

                viewHolder.setApplicant_name(model.getApplicant_name());
                viewHolder.setFather_name(model.getFather_name());
                viewHolder.setMerit_position(model.getMerit_position());
                viewHolder.setAd_exam_roll(model.getAd_exam_roll());


            }
        };
        mteStudentList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class RemoveViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        View mView;
        Context mContext;
        private DatabaseReference first_te_ref = FirebaseDatabase.getInstance().getReference().child("te/firstte");
        private DatabaseReference second_te_ref = FirebaseDatabase.getInstance().getReference().child("te/secondte");
        private DatabaseReference te_third_ref = FirebaseDatabase.getInstance().getReference().child("te/thirdte");

        private DatabaseReference first_ict_ref = FirebaseDatabase.getInstance().getReference().child("ict/firstict");
        private DatabaseReference second_ict_ref = FirebaseDatabase.getInstance().getReference().child("ict/secondict");
        private DatabaseReference ict_third_ref = FirebaseDatabase.getInstance().getReference().child("ict/thirdict");

        private DatabaseReference first_cse_ref = FirebaseDatabase.getInstance().getReference().child("cse/firstcse");
        private DatabaseReference second_cse_ref = FirebaseDatabase.getInstance().getReference().child("cse/secondcse");
        private DatabaseReference cse_third_ref = FirebaseDatabase.getInstance().getReference().child("cse/thirdcse");
        private ArrayList<Choose> choose_list = new ArrayList<>();

        public RemoveViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void setApplicant_name(String applicant_name){

            TextView post_title = (TextView)mView.findViewById(R.id.line1);
            post_title.setText("Applicant's Name : "+applicant_name);
        }

        public void setFather_name(String father_name) {
            TextView post_title = (TextView)mView.findViewById(R.id.line2);
            post_title.setText("Father's Name : "+father_name);
        }

        public void setMerit_position(int merit_position){

            TextView post_title = (TextView)mView.findViewById(R.id.line3);
            post_title.setText("Roll : "+merit_position);
        }
        public void setAd_exam_roll(int ad_exam_roll){

            TextView post_title = (TextView)mView.findViewById(R.id.line4);
            post_title.setText("Ad Roll : "+ad_exam_roll);
        }

        @Override
        public void onClick(final View v) {

            final ArrayList<Choose> animals = new ArrayList<>();
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("remove/removelist");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(final DataSnapshot dataSnapshot) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        animals.add(snapshot.getValue(Choose.class));
                    }

                    int itemPosition = getLayoutPosition();
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {

                        if(count==itemPosition) {
                            Choose value = appleSnapshot.getValue(Choose.class);
                            Log.d("RemoveActivity.this",value.getMerit_position() +"::");
                            remove_value(value.getMerit_position());
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        private void remove_value(final int position) {

            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);
            first_cse_ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int item=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        choose_list.add(snapshot.getValue(Choose.class));
                    }
                    for(Choose ch : choose_list){
                        if(ch.getMerit_position()==position){
                            break;
                        }
                        item++;
                    }
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        //Log.d("RemoveActivity.this",count +" C ::"+item +" I::"+"  ::::::::::"+appleSnapshot.getKey());
                        if(count==item) {
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);

            second_cse_ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int item=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        choose_list.add(snapshot.getValue(Choose.class));
                    }
                    for(Choose ch : choose_list){
                        if(ch.getMerit_position()==position){
                            break;
                        }
                        item++;
                    }
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        //Log.d("RemoveActivity.this",count +" C ::"+item +" I::"+"  ::::::::::"+appleSnapshot.getKey());
                        if(count==item) {
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);

            cse_third_ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int item=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        choose_list.add(snapshot.getValue(Choose.class));
                    }
                    for(Choose ch : choose_list){
                        if(ch.getMerit_position()==position){
                            break;
                        }
                        item++;
                    }
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        //Log.d("RemoveActivity.this",count +" C ::"+item +" I::"+"  ::::::::::"+appleSnapshot.getKey());
                        if(count==item) {
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);
            first_ict_ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int item=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        choose_list.add(snapshot.getValue(Choose.class));
                    }
                    for(Choose ch : choose_list){
                        if(ch.getMerit_position()==position){
                            break;
                        }
                        item++;
                    }
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        //Log.d("RemoveActivity.this",count +" C ::"+item +" I::"+"  ::::::::::"+appleSnapshot.getKey());
                        if(count==item) {
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);
            second_ict_ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int item=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        choose_list.add(snapshot.getValue(Choose.class));
                    }
                    for(Choose ch : choose_list){
                        if(ch.getMerit_position()==position){
                            break;
                        }
                        item++;
                    }
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        //Log.d("RemoveActivity.this",count +" C ::"+item +" I::"+"  ::::::::::"+appleSnapshot.getKey());
                        if(count==item) {
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);
            ict_third_ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int item=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        choose_list.add(snapshot.getValue(Choose.class));
                    }
                    for(Choose ch : choose_list){
                        if(ch.getMerit_position()==position){
                            break;
                        }
                        item++;
                    }
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        //Log.d("RemoveActivity.this",count +" C ::"+item +" I::"+"  ::::::::::"+appleSnapshot.getKey());
                        if(count==item) {
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);
            first_te_ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int item=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        choose_list.add(snapshot.getValue(Choose.class));
                    }
                    for(Choose ch : choose_list){
                        if(ch.getMerit_position()==position){
                            break;
                        }
                        item++;
                    }
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        //Log.d("RemoveActivity.this",count +" C ::"+item +" I::"+"  ::::::::::"+appleSnapshot.getKey());
                        if(count==item) {
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);
            second_te_ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int item=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        choose_list.add(snapshot.getValue(Choose.class));
                    }
                    for(Choose ch : choose_list){
                        if(ch.getMerit_position()==position){
                            break;
                        }
                        item++;
                    }
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        //Log.d("RemoveActivity.this",count +" C ::"+item +" I::"+"  ::::::::::"+appleSnapshot.getKey());
                        if(count==item) {
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);
            te_third_ref.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    int item=0;
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        choose_list.add(snapshot.getValue(Choose.class));
                    }
                    for(Choose ch : choose_list){
                        if(ch.getMerit_position()==position){
                            break;
                        }
                        item++;
                    }
                    int count=0;
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        //Log.d("RemoveActivity.this",count +" C ::"+item +" I::"+"  ::::::::::"+appleSnapshot.getKey());
                        if(count==item) {
                            appleSnapshot.getRef().removeValue();
                        }
                        count++;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            if(!choose_list.isEmpty())choose_list.removeAll(choose_list);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

}
