package com.example.seedfunding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RequestFundingActivity extends AppCompatActivity {

    TextView investorName,investorDomain,investorAbout,investorBudget,investorEducation,investorExperience;
    DatabaseReference reference;
    String currentInvestorId;
    Intent intent;
    FirebaseUser fuser;
    Button sendFundingRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_funding);

        investorName=findViewById(R.id.InvestorName);
        investorDomain=findViewById(R.id.InvestorDomain);
        investorAbout=findViewById(R.id.InvestorAbout);
        investorBudget=findViewById(R.id.InvestorBudget);
        investorEducation=findViewById(R.id.InvestorEducation);
        investorExperience=findViewById(R.id.InvestorExperience);

        sendFundingRequest=findViewById(R.id.sendFundingRequest);

        fuser= FirebaseAuth.getInstance().getCurrentUser();
        intent=getIntent();
        currentInvestorId=intent.getStringExtra("currentInvestorUserId");
        reference= FirebaseDatabase.getInstance().getReference("Investors");

        //receive data
        //Intent intent=getIntent();
        String investorName_inStartup =intent.getExtras().getString("InvestorName");
        String investorDomain_inStartup=intent.getExtras().getString("InvestorDomain");
        String investorAbout_inStartup =intent.getExtras().getString("InvestorAbout");
        String investorBudget_inStartup =intent.getExtras().getString("InvestorBudget");
        String investorEducation_inStartup =intent.getExtras().getString("InvestorEducation");
        String investorExperience_instartup =intent.getExtras().getString("InvestorExperience");

        //setting values
        investorName.setText(investorName_inStartup);
        investorDomain.setText(investorDomain_inStartup);
        investorBudget.setText(investorBudget_inStartup);
        investorEducation.setText(investorEducation_inStartup);
        investorExperience.setText(investorExperience_instartup);
        investorAbout.setText(investorAbout_inStartup);

        sendFundingRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg="This Startup has requested for funding";
                sendNotificationMessage(fuser.getUid(),currentInvestorId,msg);
            }
        });

    }

    private void sendNotificationMessage(String sender,String receiver,String message){
        intent=getIntent();
        currentInvestorId=(String) getIntent().getExtras().get("currentInvestorUserId");

        DatabaseReference reference1= FirebaseDatabase.getInstance().getReference("Investors").child(currentInvestorId);
        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);
        reference1.child("Notifications").setValue(hashMap);



    }
}
