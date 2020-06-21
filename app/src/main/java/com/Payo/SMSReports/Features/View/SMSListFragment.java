package com.Payo.SMSReports.Features.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.Payo.SMSReports.Features.Modal.SMS;
import com.Payo.SMSReports.R;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SMSListFragment extends Fragment {

    @BindView(R.id.sms_recycler)
    RecyclerView smsRecycler;
    @BindView(R.id.no_data)
    TextView noData;
    private Unbinder unbinder;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_sms_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();
        return view;
    }


    public void setRecyclerArrayList(ArrayList<SMS> smsArrayList) {
        loadRecyclerView(smsArrayList);
    }

    private void loadRecyclerView(ArrayList<SMS> smsArrayList) {
        if (smsArrayList.size() > 0) {
            smsRecycler.setLayoutManager(new LinearLayoutManager(context));
            SMSAdapter smsAdapter = new SMSAdapter();
            smsRecycler.setAdapter(smsAdapter);
            smsAdapter.setSMSList(smsArrayList);
        } else {
            smsRecycler.setVisibility(View.GONE);
            noData.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
