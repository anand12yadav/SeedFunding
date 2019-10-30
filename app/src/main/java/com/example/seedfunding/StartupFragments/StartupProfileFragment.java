package com.example.seedfunding.StartupFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.seedfunding.R;
import com.example.seedfunding.StartupModel.Startup_upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class StartupProfileFragment extends Fragment {

     CircleImageView startupProfileImage;
     TextView startupName,foundersName,teamMember1,teamMember2,teamMember3,teamMember4,teamMember5,StartupSummary,txtstartupDomain;
FirebaseAuth mAuth;
     Uri mImageUrl;
     DatabaseReference mDatabaseRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_startup_profile, container, false);
        startupProfileImage=view.findViewById(R.id.startupProfileImage);
        startupName=view.findViewById(R.id.startupName);
        foundersName=view.findViewById(R.id.foundersName);
        teamMember1=view.findViewById(R.id.teamMember1);
        teamMember2=view.findViewById(R.id.teamMember2);
        teamMember3=view.findViewById(R.id.teamMember3);
        teamMember4=view.findViewById(R.id.teamMember4);
        teamMember5=view.findViewById(R.id.teamMember5);
        StartupSummary=view.findViewById(R.id.StartupSummary);
        txtstartupDomain=view.findViewById(R.id.txtstartupDomain);
        mAuth=FirebaseAuth.getInstance();


         String currentUserId = mAuth.getCurrentUser().getUid();
         mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Startups").child(currentUserId);
         mDatabaseRef.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Startup_upload startup_upload=dataSnapshot.getValue(Startup_upload.class);
                 //  startup_upload.setStartupName(dataSnapshot.child("Startups").getValue(Startup_upload.class).getStartupName());
                 //  String startupProfileImage1 = dataSnapshot.child("mImageUrl").getValue().toString();
                 String startupName1=startup_upload.getStartupName();
                 String foundersName1=startup_upload.getFoundersName();
                 String teamMember11=startup_upload.getTeamMember1();
                 String teamMember22=startup_upload.getTeamMember2();
                 String teamMember33=startup_upload.getTeamMember3();
                 String teamMember44=startup_upload.getTeamMember4();
                 String teamMember55=startup_upload.getTeamMember5();
                 String StartupSummary1=startup_upload.getStartupSummary();
                 String txtstartupDomain1=startup_upload.getstartupDomain();



               //  Picasso.get().load(startupProfileImage1).into(startupProfileImage);
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



        return view;
    }


}
