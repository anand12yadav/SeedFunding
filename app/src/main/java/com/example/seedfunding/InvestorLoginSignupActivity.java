package com.example.seedfunding;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.TestLooperManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.seedfunding.InvestorModel.Investor_user;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class InvestorLoginSignupActivity extends AppCompatActivity {
    Button Investor_login, Investor_signup;
    RelativeLayout rootlayout;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_login_signup);

        Investor_login = findViewById(R.id.investor_login);
        Investor_signup = findViewById(R.id.investor_signup);
        rootlayout = findViewById(R.id.rootlayout);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Investors");

        Investor_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterDailog();
            }
        });

        Investor_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoginDailog();
            }
        });

    }

    private void showLoginDailog() {
        AlertDialog.Builder dailog = new AlertDialog.Builder(this);
        dailog.setTitle("LOGIN");
        dailog.setMessage("Please use email and password to login");

        final LayoutInflater inflater = LayoutInflater.from(this);
        View login_investor_layout = inflater.inflate(R.layout.investor_login_layout, null);
        final EditText investor_email = login_investor_layout.findViewById(R.id.investor_email);
        final EditText investor_password = login_investor_layout.findViewById(R.id.investor_password);

        dailog.setView(login_investor_layout);

        //set button
        dailog.setPositiveButton("LOGIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                if (TextUtils.isEmpty(investor_email.getText().toString())) {
                   // Snackbar.make(rootlayout, "Please enter email address", Snackbar.LENGTH_SHORT)
                    //        .show();
                   // return;
                    Toast.makeText(getApplicationContext(),"Please enter email address",Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(investor_password.getText().toString())) {
                  //  Snackbar.make(rootlayout, "Please enter password", Snackbar.LENGTH_SHORT)
                   //         .show();
                  //  return;

                    Toast.makeText(getApplicationContext(),"Please enter password address",Toast.LENGTH_SHORT).show();
                }

                if (investor_password.getText().toString().length() < 6) {
                   // Snackbar.make(rootlayout, "Please enter more than 6 digit password", Snackbar.LENGTH_SHORT)
                    //        .show();
                  //  return;

                    Toast.makeText(getApplicationContext(),"Please enter password greater than 6 digits",Toast.LENGTH_SHORT).show();
                }

                //login Investor to main screen
                auth.signInWithEmailAndPassword(investor_email.getText().toString(), investor_password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent intent = new Intent(InvestorLoginSignupActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                               // Snackbar.make(rootlayout, "Failed", Snackbar.LENGTH_SHORT)
                               //         .show();
                                Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        dailog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dailog.show();
    }

    private void showRegisterDailog() {
        final AlertDialog.Builder dailog = new AlertDialog.Builder(this);
        dailog.setTitle("REGISTER");
        dailog.setMessage("Please use email to register");

        LayoutInflater inflater = LayoutInflater.from(this);
        View signup_investor_layout = inflater.inflate(R.layout.investor_signup_layout, null);
        final EditText investor_name = signup_investor_layout.findViewById(R.id.investor_name);
        final EditText investor_email = signup_investor_layout.findViewById(R.id.investor_email);
        final EditText investor_password = signup_investor_layout.findViewById(R.id.investor_password);

        dailog.setView(signup_investor_layout);

        //set button
        dailog.setPositiveButton("REGISTER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                //checking validations for all inputs from investor
                if (TextUtils.isEmpty(investor_name.getText().toString())) {
                   // Snackbar.make(rootlayout, "Please enter your name", Snackbar.LENGTH_SHORT)
                    //        .show();
                  //  return;
                    Toast.makeText(getApplicationContext(),"Please enter name",Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(investor_email.getText().toString())) {
                  //  Snackbar.make(rootlayout, "Please enter email adress", Snackbar.LENGTH_SHORT)
                   //         .show();
                  //  return;
                    Toast.makeText(getApplicationContext(),"Please enter email address",Toast.LENGTH_SHORT).show();
                }

                if (TextUtils.isEmpty(investor_password.getText().toString())) {
                   // Snackbar.make(rootlayout, "Please enter password address", Snackbar.LENGTH_SHORT)
                   //         .show();
                   // return;
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
                }

                if (investor_password.getText().toString().length() < 6) {
                   // Snackbar.make(rootlayout, "Please enter more than 6 digit password", Snackbar.LENGTH_SHORT)
                   //         .show();
                  //  return;
                    Toast.makeText(getApplicationContext(),"Please enter password more than 6 digits",Toast.LENGTH_SHORT).show();
                }

                auth.createUserWithEmailAndPassword(investor_email.getText().toString(), investor_password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                // saving investor(user) to database using model class
                                Investor_user user = new Investor_user();
                                user.setEmail(investor_email.getText().toString());
                                user.setPassword(investor_password.getText().toString());
                                user.setName(investor_name.getText().toString());

                                //using uid as key
                                users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                               // Snackbar.make(rootlayout, "Register Successful", Snackbar.LENGTH_SHORT)
                                                 //       .show();
                                                Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                //Snackbar.make(rootlayout, "Failed to Register" + e.getMessage(), Snackbar.LENGTH_SHORT)
                                                 //       .show();
                                                Toast.makeText(getApplicationContext(),"failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }

                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                              //  Snackbar.make(findViewById(R.id.content), "Failed"+ e.getMessage(), Snackbar.LENGTH_SHORT)
                               //        .show();
                                Toast.makeText(getApplicationContext(),"failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                }
            });

            dailog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
            }
        });

        dailog.show();
    }


}

