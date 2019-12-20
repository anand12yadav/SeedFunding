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
import com.example.seedfunding.InvestorModel.Investor_upload;
import com.example.seedfunding.R;
import com.example.seedfunding.StartupAdapter.startupAdapter;
import com.example.seedfunding.StartupModel.Startup_upload;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class InvestorHomeFragment extends Fragment {


    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Startup_upload> list;
    investorAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_investor_home, container, false);

        recyclerView=view.findViewById(R.id.investorRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        reference= FirebaseDatabase.getInstance().getReference().child("Startups");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list=new ArrayList<Startup_upload>();

                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                    Startup_upload startup_upload=dataSnapshot1.getValue(Startup_upload.class);
                    list.add(startup_upload);
                }
                adapter=new investorAdapter(getContext(),list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }



}
