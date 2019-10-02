package com.example.seedfunding;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.seedfunding.InvestorFragments.InvestorHomeFragment;
import com.example.seedfunding.InvestorFragments.InvestorNotificationFragment;
import com.example.seedfunding.InvestorFragments.InvestorProfileFragment;
import com.example.seedfunding.StartupFragments.StartupHomeFragment;
import com.example.seedfunding.StartupFragments.StartupNotificationFragment;
import com.example.seedfunding.StartupFragments.StartupProfileFragment;

public class StartupActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        bottomNavigationView=findViewById(R.id.bottomNavigationButtons);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new StartupHomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.Home:
                            selectedFragment=new StartupHomeFragment();
                            break;

                        case R.id.Notification:
                            selectedFragment=new StartupNotificationFragment();
                            break;

                        case R.id.Profile:
                            selectedFragment=new StartupProfileFragment();
                            break;
                    }

                    if(selectedFragment != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,selectedFragment).commit();
                    }
                    return true;
                }

            };
}
