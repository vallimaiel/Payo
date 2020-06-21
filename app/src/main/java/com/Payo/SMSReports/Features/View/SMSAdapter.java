package com.Payo.SMSReports.Features.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Payo.SMSReports.DataBase.SMS;
import com.Payo.SMSReports.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SMSAdapter extends RecyclerView.Adapter<SMSViewHolder> {

    private ArrayList<SMS> smsArrayList;

    @NonNull
    @Override
    public SMSViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sms_single_item, parent, false);
        return new SMSViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SMSViewHolder holder, int position) {
        String smsText = smsArrayList.get(position).getSmsText() != null ? smsArrayList.get(position).getSmsText() : " ";
        String amount = String.valueOf(smsArrayList.get(position).getAmount());
        holder.smsTv.setText(smsText);
        holder.amountTv.setText(amount);
    }

    public void setSMSList(ArrayList<SMS> smsArrayList) {
        this.smsArrayList = smsArrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (smsArrayList != null)
            return smsArrayList.size();
        else
            return 0;
    }
}

class SMSViewHolder extends RecyclerView.ViewHolder {

    TextView smsTv;
    TextView amountTv;
    TextView tagTv;

    public SMSViewHolder(@NonNull View itemView) {
        super(itemView);
        smsTv = itemView.findViewById(R.id.sms_tv);
        amountTv = itemView.findViewById(R.id.amount_tv);
        tagTv = itemView.findViewById(R.id.tag_tv);
    }
}





