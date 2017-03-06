package com.blogspot.larn4android.materialtabs;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.blogspot.larn4android.materialtabs.dept.activity.CseActivity;
import com.blogspot.larn4android.materialtabs.dept.post.PostActivity;
import com.blogspot.larn4android.materialtabs.dept.remove.RemoveActivity;
import com.blogspot.larn4android.materialtabs.dept.update.UpdateActivity;

public class LogInActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private Button  btnLogin;

    String remove_result,upgrade_result;
    private int one,two,three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnLogin = (Button) findViewById(R.id.btn_login);




        //if(one==0&&two==0&&three==0) {
            one = getIntent().getIntExtra("testadd", 0);
            two = getIntent().getIntExtra("testremove", 0);
            three = getIntent().getIntExtra("testupdate", 0);
       // }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                Intent i;
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(one!=0&&one==1){
                    if(email!=null&&email.equalsIgnoreCase("cse")&&password!=null&&password.equalsIgnoreCase("cse")){


                        Log.d("LogInActivity.this",email +"  "+password);
                        i = new Intent(LogInActivity.this,PostActivity.class);
                        startActivity(i);
                    }
                    else {
                        i = new Intent(LogInActivity.this,MainActivity.class);
                        Toast.makeText(getApplicationContext(),"The username or password is incorrect, please input again.",Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }
                }
                else if(two!=0&&two==2){

                    if(email!=null&&email.equalsIgnoreCase("cse")&&password!=null&&password.equalsIgnoreCase("cse")){


                        Log.d("LogInActivity.this",email +"  "+password);



                        // get prompts.xml view
                        LayoutInflater li = LayoutInflater.from(LogInActivity.this);
                        View promptsView = li.inflate(R.layout.prompts, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                LogInActivity.this);

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);


                        final EditText userInput = (EditText) promptsView
                                .findViewById(R.id.editTextDialogUserInput);

                        // set dialog message

                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                // get user input and set it to result
                                                // edit text
                                                remove_result = userInput.getText().toString();
                                                Log.d("LogInActivity.this",remove_result);
                                                if(remove_result!=null) {
                                                    Intent ii;
                                                    ii = new Intent(LogInActivity.this, RemoveActivity.class);
                                                    ii.putExtra("remove", remove_result);
                                                    startActivity(ii);
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
                    else {
                        i = new Intent(LogInActivity.this,MainActivity.class);
                        Toast.makeText(getApplicationContext(),"The username or password is incorrect, please input again.",Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }

                }
                else if(three!=0&&three==3){


                    if(email!=null&&email.equalsIgnoreCase("cse")&&password!=null&&password.equalsIgnoreCase("cse")){


                        Log.d("LogInActivity.this",email +"  "+password);






                        // get prompts.xml view
                        LayoutInflater li = LayoutInflater.from(LogInActivity.this);
                        View promptsView = li.inflate(R.layout.prompts, null);

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                LogInActivity.this);

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);


                        final EditText userInput_up = (EditText) promptsView
                                .findViewById(R.id.editTextDialogUserInput);

                        // set dialog message

                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                // get user input and set it to result
                                                // edit text
                                                upgrade_result = userInput_up.getText().toString();
                                                Log.d("LogInActivity.this",upgrade_result);

                                                if(upgrade_result!=null) {
                                                    Intent ii;
                                                    ii = new Intent(LogInActivity.this, UpdateActivity.class);
                                                    ii.putExtra("upgrade", upgrade_result);
                                                    startActivity(ii);
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
                    else {
                        i = new Intent(LogInActivity.this,MainActivity.class);
                        Toast.makeText(getApplicationContext(),"The username or password is incorrect, please input again.",Toast.LENGTH_LONG).show();
                        startActivity(i);
                    }


                }
            }
        });
    }
}
