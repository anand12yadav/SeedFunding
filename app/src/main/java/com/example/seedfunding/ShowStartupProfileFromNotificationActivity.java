package com.example.seedfunding;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.seedfunding.StartupModel.Startup_upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShowStartupProfileFromNotificationActivity extends AppCompatActivity {

    CircleImageView startupProfileImage;
    TextView startupName,foundersName,teamMember1,teamMember2,teamMember3,teamMember4,teamMember5,StartupSummary,txtstartupDomain;
    FirebaseAuth mAuth;
    Uri mImageUrl;
    DatabaseReference mDatabaseRef;
    FirebaseStorage storage;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_startup_profile_from_notification);

        Intent intent=getIntent();
        String currentStartupReceiverId=intent.getStringExtra("currentStartupReceiverId");


        startupProfileImage=findViewById(R.id.startupProfileImage);
        startupName=findViewById(R.id.startupName);
        foundersName=findViewById(R.id.foundersName);
        teamMember1=findViewById(R.id.teamMember1);
        teamMember2=findViewById(R.id.teamMember2);
        teamMember3=findViewById(R.id.teamMember3);
        teamMember4=findViewById(R.id.teamMember4);
        teamMember5=findViewById(R.id.teamMember5);
        StartupSummary=findViewById(R.id.StartupSummary);
        txtstartupDomain=findViewById(R.id.txtstartupDomain);

        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Startups").child(currentStartupReceiverId);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Startup_upload startup_upload=dataSnapshot.getValue(Startup_upload.class);
                //  startup_upload.setStartupName(dataSnapshot.child("Startups").getValue(Startup_upload.class).getStartupName());
                // String startupProfileImage1 = dataSnapshot.child("mImageUrl").getValue().toString();
                //   Startup_upload profileImage = dataSnapshot.getValue(Startup_upload.class);
                String startupName1=startup_upload.getStartupName();
                String foundersName1=startup_upload.getFoundersName();
                String teamMember11=startup_upload.getTeamMember1();
                String teamMember22=startup_upload.getTeamMember2();
                String teamMember33=startup_upload.getTeamMember3();
                String teamMember44=startup_upload.getTeamMember4();
                String teamMember55=startup_upload.getTeamMember5();
                String StartupSummary1=startup_upload.getStartupSummary();
                String txtstartupDomain1=startup_upload.getstartupDomain();
                // String link=dataSnapshot.getValue(String.class);


                // Picasso.get().load(link).into(startupProfileImage);
                //  Glide.with(getContext()).load(profileImage.getmImageUrl()).into(startupProfileImage);
                foundersName.setText(foundersName1);
                txtstartupDomain.setText(txtstartupDomain1);
                startupName.setText(startupName1);
                teamMember1.setText(teamMember11);
                teamMember2.setText(teamMember22);
                teamMember3.setText(teamMember33);
                teamMember4.setText(teamMember44);
                teamMember5.setText(teamMember55);
                StartupSummary.setText(StartupSummary1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
