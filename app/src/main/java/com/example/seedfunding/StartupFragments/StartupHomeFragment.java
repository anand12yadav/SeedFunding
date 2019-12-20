package com.example.seedfunding.StartupFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.seedfunding.InvestorModel.Investor_upload;
import com.example.seedfunding.R;
import com.example.seedfunding.RequestFundingActivity;
import com.example.seedfunding.StartupAdapter.startupAdapter;
import com.example.seedfunding.StartupInfoSetupActivity;
import com.example.seedfunding.StartupLoginSignupActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class StartupHomeFragment extends Fragment {

   DatabaseReference reference;
   RecyclerView recyclerView;
   ArrayList<Investor_upload> list;
   startupAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_startup_home, container, false);
        // Inflate the layout for this fragment


        recyclerView=view.findViewById(R.id.startupRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<Investor_upload>();



        reference= FirebaseDatabase.getInstance().getReference().child("Investors");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<Investor_upload>();
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Investor_upload investor_upload=dataSnapshot1.getValue(Investor_upload.class);
                    list.add(investor_upload);
                }
               adapter=new startupAdapter(getContext(),list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }


}
