package com.blogspot.larn4android.materialtabs;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import mehdi.sakout.fancybuttons.FancyButton;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        FancyButton btn = (FancyButton)findViewById(R.id.btn_instagram);
        FancyButton btn1 = (FancyButton)findViewById(R.id.btn_gplus);
        FancyButton btn2 = (FancyButton)findViewById(R.id.btn_dropbox);
        FancyButton btn3 = (FancyButton)findViewById(R.id.btn_android);
        FancyButton btn4 = (FancyButton)findViewById(R.id.btn_sound);
        FancyButton btn5 = (FancyButton)findViewById(R.id.btn_facebook);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.facebook.com/minto5z?ref_component=mbasic_home_header&ref_page=%2Fwap%2Fhome.php&refid=7"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.facebook.com/minto5z?ref_component=mbasic_home_header&ref_page=%2Fwap%2Fhome.php&refid=7"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.facebook.com/minto5z?ref_component=mbasic_home_header&ref_page=%2Fwap%2Fhome.php&refid=7"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.facebook.com/minto5z?ref_component=mbasic_home_header&ref_page=%2Fwap%2Fhome.php&refid=7"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://m.facebook.com/minto5z?ref_component=mbasic_home_header&ref_page=%2Fwap%2Fhome.php&refid=7"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://plus.google.com/115758803525465286698"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });


    }
}
