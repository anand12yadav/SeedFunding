package com.example.seedfunding;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class SendFundingInterestActivity extends AppCompatActivity {

   TextView startupName_inInvestor ,startupDomain_inInvestor,foundersName_inInvestor,teamMember1_inInvestor, teamMember2_inInvestor,teamMember3_inInvestor,teamMember4_inInvestor,teamMember5_inInvestor,StartupSummary_inInvestor;
   Button sendFundingInterest;
    FirebaseUser fuser;
    DatabaseReference reference;
    String currentStartupId;
    Intent intent;
    //ArrayList<String>StartupCurrentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_funding_interest);

       // startupname=findViewById(R.id.startupname);
        startupName_inInvestor=findViewById(R.id.startupName_inInvestor);
        startupDomain_inInvestor=findViewById(R.id.startupDomain_inInvestor);
        foundersName_inInvestor=findViewById(R.id.foundersName_inInvestor);
        teamMember1_inInvestor=findViewById(R.id.teamMember1_inInvestor);
        teamMember2_inInvestor=findViewById(R.id.teamMember2_inInvestor);
        teamMember3_inInvestor=findViewById(R.id.teamMember3_inInvestor);
        teamMember4_inInvestor=findViewById(R.id.teamMember4_inInvestor);
        teamMember5_inInvestor=findViewById(R.id.teamMember5_inInvestor);
        StartupSummary_inInvestor=findViewById(R.id.StartupSummary_inInvestor);

        sendFundingInterest=findViewById(R.id.sendFundingInterest);
       // StartupCurrentId=new ArrayList<>();

        fuser=FirebaseAuth.getInstance().getCurrentUser();
        intent=getIntent();
        currentStartupId=intent.getStringExtra("currentStartupUserId");
        reference= FirebaseDatabase.getInstance().getReference("Startups");



        //receive data
       // Intent intent=getIntent();
        String startupName_inInvestorr =intent.getExtras().getString("StartupName");
        String startupDomain_inInvestorr=intent.getExtras().getString("StartupDomain");
        String foundersName_inInvestorr =intent.getExtras().getString("FoundersName");
        String teamMember1_inInvestorr =intent.getExtras().getString("TeamMember1");
        String teamMember2_inInvestorr =intent.getExtras().getString("TeamMember2");
        String teamMember3_inInvestorr =intent.getExtras().getString("TeamMember3");
        String teamMember4_inInvestorr =intent.getExtras().getString("TeamMember4");
        String teamMember5_inInvestorr =intent.getExtras().getString("TeamMember5");
        String StartupSummary_inInvestorr =intent.getExtras().getString("StartupSummary");
       // final String StartupCurrentUserid=intent.getStringExtra("currentStartupUserId");





        //setting values
        startupName_inInvestor.setText(startupName_inInvestorr);
        startupDomain_inInvestor.setText(startupDomain_inInvestorr);
        foundersName_inInvestor.setText(foundersName_inInvestorr);
        teamMember1_inInvestor.setText(teamMember1_inInvestorr);
        teamMember2_inInvestor.setText(teamMember2_inInvestorr);
        teamMember3_inInvestor.setText(teamMember3_inInvestorr);
        teamMember4_inInvestor.setText(teamMember4_inInvestorr);
        teamMember5_inInvestor.setText(teamMember5_inInvestorr);
        StartupSummary_inInvestor.setText(StartupSummary_inInvestorr);

        sendFundingInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String msg="This Investor has shown interest to fund your startup";
                 sendNotificationMessage(fuser.getUid(),currentStartupId,msg);
            }
        });






/*      reference.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot d: dataSnapshot.getChildren()){
                  StartupCurrentId.add(d.getKey());
              }
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });
*/

    }

  private void sendNotificationMessage(String sender,String receiver,String message){
       intent=getIntent();
       currentStartupId=(String) getIntent().getExtras().get("currentStartupUserId");
     //  reference= FirebaseDatabase.getInstance().getReference().child("Startups").child(currentStartupId);

        DatabaseReference reference1= FirebaseDatabase.getInstance().getReference("Startups").child(currentStartupId);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);
        reference1.child("Notifications").setValue(hashMap);



  }
}
