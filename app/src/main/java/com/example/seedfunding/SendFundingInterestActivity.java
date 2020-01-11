package com.example.seedfunding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SendFundingInterestActivity extends AppCompatActivity {

   TextView startupName_inInvestor ,startupDomain_inInvestor,foundersName_inInvestor,teamMember1_inInvestor, teamMember2_inInvestor,teamMember3_inInvestor,teamMember4_inInvestor,teamMember5_inInvestor,StartupSummary_inInvestor;

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

        //receive data
        Intent intent=getIntent();
        String startupName_inInvestorr =intent.getExtras().getString("StartupName");
        String startupDomain_inInvestorr=intent.getExtras().getString("StartupDomain");
        String foundersName_inInvestorr =intent.getExtras().getString("FoundersName");
        String teamMember1_inInvestorr =intent.getExtras().getString("TeamMember1");
        String teamMember2_inInvestorr =intent.getExtras().getString("TeamMember2");
        String teamMember3_inInvestorr =intent.getExtras().getString("TeamMember3");
        String teamMember4_inInvestorr =intent.getExtras().getString("TeamMember4");
        String teamMember5_inInvestorr =intent.getExtras().getString("TeamMember5");
        String  StartupSummary_inInvestorr =intent.getExtras().getString("StartupSummary");





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


    }
}
