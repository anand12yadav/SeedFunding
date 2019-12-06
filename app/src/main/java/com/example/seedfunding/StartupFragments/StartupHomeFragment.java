package com.example.seedfunding.StartupFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seedfunding.R;
import com.example.seedfunding.RequestFundingActivity;
import com.example.seedfunding.StartupInfoSetupActivity;
import com.example.seedfunding.StartupLoginSignupActivity;


public class StartupHomeFragment extends Fragment {

  CardView card1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_startup_home, container, false);
        // Inflate the layout for this fragment

        return view;
    }


}
