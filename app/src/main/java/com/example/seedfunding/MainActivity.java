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

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottomNavigationButtons);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,new InvestorHomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()){
                        case R.id.Home:
                            selectedFragment=new InvestorHomeFragment();
                            break;

                        case R.id.Notification:
                            selectedFragment=new InvestorNotificationFragment();
                            break;

                        case R.id.Profile:
                            selectedFragment=new InvestorProfileFragment();
                            break;
                    }

                    if(selectedFragment != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer,selectedFragment).commit();
                    }
                    return true;
                }

            };
}
