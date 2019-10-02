package com.example.seedfunding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InvestorOrStartupActivity extends AppCompatActivity {

    Button Investor, Startup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_or_startup);

        Investor=findViewById(R.id.investor);
        Startup=findViewById(R.id.startup);

        // to move to login signup activity for investors
        Investor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InvestorOrStartupActivity.this,InvestorLoginSignupActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // to move to login signup activity for startups
        Startup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InvestorOrStartupActivity.this,StartupLoginSignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
