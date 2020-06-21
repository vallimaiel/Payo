package com.Payo.SMSReports.Features.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Payo.SMSReports.Features.Modal.SMS;
import com.Payo.SMSReports.Features.ViewModal.SMSViewModel;
import com.Payo.SMSReports.R;
import com.Payo.SMSReports.Utilities.AppUtils;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class SMSAdapter extends RecyclerView.Adapter<SMSViewHolder> {

    private ArrayList<SMS> smsArrayList;
    Context context;
    EditText addTag = null;
    private SMSViewModel smsViewModel;

    public SMSAdapter(Context context) {
        this.context = context;
        smsViewModel = ViewModelProviders.of((FragmentActivity) context).get(SMSViewModel.class);
    }

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
        if (smsArrayList.get(position).getTag() != null) {
            holder.tagTv.setVisibility(View.VISIBLE);
            holder.tagTv.setText(smsArrayList.get(position).getTag());
        }
        holder.editIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTagDialog(smsArrayList.get(position).getId(), holder, position);
            }
        });
    }

    private void showAddTagDialog(int id, SMSViewHolder holder, int position) {
        addTag = AppUtils.getInstance().addTagAlert(context, new AppUtils.AlertInterface() {
            @Override
            public void onPositiveButtonClick() {
                String tag = addTag.getText().toString();
                if (tag.trim().isEmpty())
                    Toast.makeText(context, "Please enter tag for the SMS", Toast.LENGTH_SHORT).show();
                else
                    updateTag(id, tag, holder, position);
            }
        });

    }

    private void updateTag(Integer id, String tag, SMSViewHolder holder, int position) {
        smsViewModel.updateTag(id, tag);
        notifyItemChanged(position);
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
    ImageView editIcon;

    public SMSViewHolder(@NonNull View itemView) {
        super(itemView);
        smsTv = itemView.findViewById(R.id.sms_tv);
        amountTv = itemView.findViewById(R.id.amount_tv);
        tagTv = itemView.findViewById(R.id.tag_tv);
        editIcon = itemView.findViewById(R.id.edit_icon);
    }
}





