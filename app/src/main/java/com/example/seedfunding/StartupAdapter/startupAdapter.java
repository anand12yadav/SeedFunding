package com.example.seedfunding.StartupAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.seedfunding.InvestorModel.Investor_upload;
import com.example.seedfunding.R;
import com.example.seedfunding.RequestFundingActivity;
import com.example.seedfunding.SendFundingInterestActivity;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class startupAdapter extends RecyclerView.Adapter<startupAdapter.MyViewHolder> {

 Context context;
 ArrayList<Investor_upload> investor_upload;

    public startupAdapter(Context context, ArrayList<Investor_upload> investor_upload) {
        this.context = context;
        this.investor_upload = investor_upload;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.investor_profile_in_startup_homepage,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.investor_name.setText(investor_upload.get(i).getInvestorName());
        myViewHolder.investor_domain.setText(investor_upload.get(i).getInvestorDomain());
        myViewHolder.view_information_investor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, RequestFundingActivity.class);
                intent.putExtra("InvestorName",investor_upload.get(i).getInvestorName());
                intent.putExtra("InvestorDomain",investor_upload.get(i).getInvestorDomain());
                intent.putExtra("InvestorAbout",investor_upload.get(i).getInvestorAbout());
                intent.putExtra("InvestorBudget",investor_upload.get(i).getInvestorBudget());
                intent.putExtra("InvestorEducation",investor_upload.get(i).getInvestorEducation());
                intent.putExtra("InvestorExperience",investor_upload.get(i).getInvestorExperience());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return investor_upload.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView investor_name,investor_domain;
        CircleImageView investor_profile_image;
        Button view_information_investor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            investor_profile_image=itemView.findViewById(R.id.investorProfileImage);
            investor_name=itemView.findViewById(R.id.investor_name);
            investor_domain=itemView.findViewById(R.id.investor_domain);
            view_information_investor= itemView.findViewById(R.id.view_investor_information);
        }
    }
}
