package com.example.seedfunding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RequestFundingActivity extends AppCompatActivity {

    TextView investorName,investorDomain,investorAbout,investorBudget,investorEducation,investorExperience;

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

        //receive data
        Intent intent=getIntent();
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

    }
}
