package com.example.seedfunding;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.seedfunding.InvestorModel.Investor_upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowInvestorProfileFromNotificationActivity extends AppCompatActivity {
    CircleImageView startupProfileImage;
    TextView InvestorName,InvestorAbout,InvestorExperience,InvestorEducation,InvestorDomain,InvestorBudget;
    FirebaseAuth mAuth;
    Uri mImageUrl;
    DatabaseReference mDatabaseRef;
    FirebaseStorage storage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_investor_profile_from_notification);

         Intent intent=getIntent();
         String currentInvestorReceiverId=intent.getStringExtra("currentInvestorReceiverId");

        InvestorName=findViewById(R.id.InvestorName);
        InvestorAbout=findViewById(R.id.InvestorAbout);
        InvestorExperience=findViewById(R.id.InvestorExperience);
        InvestorEducation=findViewById(R.id.InvestorEducation);
        InvestorDomain=findViewById(R.id.InvestorDomain);
        InvestorBudget=findViewById(R.id.InvestorBudget);

        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Investors").child(currentInvestorReceiverId);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Investor_upload investor_upload=dataSnapshot.getValue(Investor_upload.class);
                //  startup_upload.setStartupName(dataSnapshot.child("Startups").getValue(Startup_upload.class).getStartupName());
                // String startupProfileImage1 = dataSnapshot.child("mImageUrl").getValue().toString();
                //   Startup_upload profileImage = dataSnapshot.getValue(Startup_upload.class);
                String investorName=investor_upload.getInvestorName();
                String investorAbout=investor_upload.getInvestorAbout();
                String investorExperience=investor_upload.getInvestorExperience();
                String investorEducation=investor_upload.getInvestorEducation();
                String investorDomain=investor_upload.getInvestorDomain();
                String investorBudget=investor_upload.getInvestorBudget();


                // Picasso.get().load(startupProfileImage1).into(startupProfileImage);
                //  Glide.with(getContext()).load(profileImage.getmImageUrl()).into(startupProfileImage);
                InvestorName.setText(investorName);
                InvestorAbout.setText(investorAbout);
                InvestorExperience.setText(investorExperience);
                InvestorEducation.setText(investorEducation);
                InvestorDomain.setText(investorDomain);
                InvestorBudget.setText(investorBudget);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
