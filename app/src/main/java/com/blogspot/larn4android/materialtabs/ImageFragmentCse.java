package com.blogspot.larn4android.materialtabs;

/**
 * Created by minthu on 1/15/2017.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ImageFragmentCse extends Fragment {
    int fragVal;

    static ImageFragmentCse init(int val) {
        ImageFragmentCse truitonFrag = new ImageFragmentCse();
        // Supply val input as an argument.
        Bundle args = new Bundle();
        args.putInt("val", val);
        truitonFrag.setArguments(args);
        return truitonFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragVal = getArguments() != null ? getArguments().getInt("val") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layoutView = inflater.inflate(R.layout.fragment_cse, container,
                false);


        final View tv = layoutView.findViewById(R.id.text);

        DatabaseReference mConditionRef = FirebaseDatabase.getInstance().getReference().child("TopNewsCse");


        boolean connected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        if(connected) {


            mConditionRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text  = dataSnapshot.getValue(String.class);

                    ((TextView) tv).setText(text);
                    //tv.setText(text);
                    tv.setSelected(true);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else {
            Toast.makeText(getActivity(),"Please cheak your Internet Connection", Toast.LENGTH_LONG).show();
        }


        return layoutView;
    }


    @Override
    public void onStart() {
        super.onStart();

    }


}