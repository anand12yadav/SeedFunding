package com.example.seedfunding.InvestorFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seedfunding.InvestorAdapter.investorAdapter;
import com.example.seedfunding.InvestorAdapter.investorNotificationAdapter;
import com.example.seedfunding.InvestorModel.InvestorNotificationModel;
import com.example.seedfunding.R;
import com.example.seedfunding.StartupModel.Startup_upload;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class InvestorNotificationFragment extends Fragment {
     investorNotificationAdapter adapter;
    // List<InvestorNotificationModel> investorNotificationModel;
     RecyclerView notification_recyclerview;
     DatabaseReference reference;

     ArrayList<InvestorNotificationModel> investorNotificationModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_investor_notification, container, false);
        notification_recyclerview=view.findViewById(R.id.notification_recyclerview);
        notification_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        investorNotificationModel=new ArrayList<>();

        final FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Investors").child(firebaseUser.getUid());
        reference.child("Notifications").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                investorNotificationModel.clear();
                InvestorNotificationModel model= dataSnapshot.getValue(InvestorNotificationModel.class);
              //  model.setSender(dataSnapshot.getKey());
               // model.setReceiver(dataSnapshot.getKey());
                investorNotificationModel.add(model);


                 adapter=new investorNotificationAdapter(getContext(), investorNotificationModel);
                notification_recyclerview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return view;
    }


}
