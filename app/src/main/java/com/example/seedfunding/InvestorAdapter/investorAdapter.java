package com.example.seedfunding.InvestorAdapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.seedfunding.R;
import com.example.seedfunding.SendFundingInterestActivity;
import com.example.seedfunding.StartupModel.Startup_upload;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class investorAdapter extends RecyclerView.Adapter<investorAdapter.MyViewHolder> {
    Context context;
    ArrayList<Startup_upload> startup_upload;

    public investorAdapter(Context context, ArrayList<Startup_upload> startup_upload) {
        this.context = context;
        this.startup_upload = startup_upload;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new investorAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.startup_profile_in_investor_homepage,viewGroup,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        final Startup_upload user=startup_upload.get(i);
        myViewHolder.startup_name.setText(startup_upload.get(i).getStartupName());
        myViewHolder.startup_domain.setText(startup_upload.get(i).getstartupDomain());
        myViewHolder.view_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SendFundingInterestActivity.class);
                intent.putExtra("StartupName",startup_upload.get(i).getStartupName());
                intent.putExtra("StartupDomain",startup_upload.get(i).getstartupDomain());
                intent.putExtra("FoundersName",startup_upload.get(i).getFoundersName());
                intent.putExtra("TeamMember1",startup_upload.get(i).getTeamMember1());
                intent.putExtra("TeamMember2",startup_upload.get(i).getTeamMember2());
                intent.putExtra("TeamMember3",startup_upload.get(i).getTeamMember3());
                intent.putExtra("TeamMember4",startup_upload.get(i).getTeamMember4());
                intent.putExtra("TeamMember5",startup_upload.get(i).getTeamMember5());
                intent.putExtra("StartupSummary",startup_upload.get(i).getStartupSummary());
                intent.putExtra("currentStartupUserId",user.getStartupId());
                context.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return startup_upload.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView startup_name, startup_domain;
        CircleImageView startup_profile_image;
        Button view_information;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            startup_name = itemView.findViewById(R.id.startup_name);
            startup_domain = itemView.findViewById(R.id.startup_domain);
            startup_profile_image = itemView.findViewById(R.id.startup_profile_image);
            view_information= itemView.findViewById(R.id.view_information);
        }


    }
}


