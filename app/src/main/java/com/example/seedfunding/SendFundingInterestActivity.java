package com.example.seedfunding;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SendFundingInterestActivity extends AppCompatActivity {

    TextView startupname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_funding_interest);

        startupname=findViewById(R.id.startupname);

        //receive data
        Intent intent=getIntent();
        String startupnamee=intent.getExtras().getString("StartupName");


        //setting values
        startupname.setText(startupnamee);


    }
}
