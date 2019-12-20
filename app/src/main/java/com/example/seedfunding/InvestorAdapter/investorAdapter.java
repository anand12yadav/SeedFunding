package com.example.seedfunding.InvestorAdapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.seedfunding.R;
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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.startup_name.setText(startup_upload.get(i).getStartupName());
        myViewHolder.startup_domain.setText(startup_upload.get(i).getstartupDomain());

    }

    @Override
    public int getItemCount() {
        return startup_upload.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView startup_name, startup_domain;
        CircleImageView startup_profile_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            startup_name = itemView.findViewById(R.id.startup_name);
            startup_domain = itemView.findViewById(R.id.startup_domain);
            startup_profile_image = itemView.findViewById(R.id.startup_profile_image);
        }

    }
}


