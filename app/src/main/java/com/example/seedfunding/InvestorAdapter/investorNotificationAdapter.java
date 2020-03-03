package com.example.seedfunding.InvestorAdapter;

import android.content.Context;
import android.content.ReceiverCallNotAllowedException;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.seedfunding.InvestorModel.InvestorNotificationModel;
import com.example.seedfunding.R;

import org.w3c.dom.Text;

import java.util.List;

public class investorNotificationAdapter extends RecyclerView.Adapter<investorNotificationAdapter.ViewHolder> {
    private Context mContext;
    private List<InvestorNotificationModel> investorNotificationModel;

    public investorNotificationAdapter(Context mContext, List<InvestorNotificationModel> investorNotificationModel) {
        this.mContext = mContext;
        this.investorNotificationModel = investorNotificationModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.investor_notification_card, viewGroup, false);
        return new investorNotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        InvestorNotificationModel Model=investorNotificationModel.get(i);
        viewHolder.investor_notification_msg.setText(Model.getMessage());
    }

    @Override
    public int getItemCount() {
        return investorNotificationModel.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
         TextView investor_notification_msg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            investor_notification_msg=itemView.findViewById(R.id.investor_notification_msg);
        }
    }
}
