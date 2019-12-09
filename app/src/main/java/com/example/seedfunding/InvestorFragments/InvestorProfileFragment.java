package com.example.seedfunding.InvestorFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.seedfunding.InvestorModel.Investor_upload;
import com.example.seedfunding.R;
import com.example.seedfunding.StartupModel.Startup_upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import de.hdodenhof.circleimageview.CircleImageView;


public class InvestorProfileFragment extends Fragment {

    CircleImageView startupProfileImage;
    TextView InvestorName,InvestorAbout,InvestorExperience,InvestorEducation,InvestorDomain,InvestorBudget;
    FirebaseAuth mAuth;
    Uri mImageUrl;
    DatabaseReference mDatabaseRef;
    FirebaseStorage storage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_investor_profile, container, false);

        InvestorName=view.findViewById(R.id.InvestorName);
        InvestorAbout=view.findViewById(R.id.InvestorAbout);
        InvestorExperience=view.findViewById(R.id.InvestorExperience);
        InvestorEducation=view.findViewById(R.id.InvestorEducation);
        InvestorDomain=view.findViewById(R.id.InvestorDomain);
        InvestorBudget=view.findViewById(R.id.InvestorBudget);

        mAuth=FirebaseAuth.getInstance();



        String currentUserId = mAuth.getCurrentUser().getUid();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference().child("Investors").child(currentUserId);
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Investor_upload  investor_upload=dataSnapshot.getValue(Investor_upload.class);
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
        return view;
    }


}
