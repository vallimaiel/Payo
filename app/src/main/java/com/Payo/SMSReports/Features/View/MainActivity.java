package com.Payo.SMSReports.Features.View;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.Payo.SMSReports.Features.Modal.SMS;
import com.Payo.SMSReports.R;
import com.Payo.SMSReports.Features.ViewModal.SMSViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.filter_category)
    TextView filterCategory;
    @BindView(R.id.amount)
    TextView amount;
    @BindView(R.id.filter_icon)
    ImageView filterIcon;
    @BindView(R.id.tab_id)
    TabLayout tabId;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Context context;
    private SMSViewModel smsViewModel;
    private ArrayList<SMS> smsArrayList;
    private Integer totalExpense;
    private Integer totalIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialise();
    }

    private void initialise() {
        initialiseObject();
        disableDefaultToolbar();
        smsViewModel.getAllSMS().observe(this, new Observer<List<SMS>>() {
            @Override
            public void onChanged(@Nullable List<SMS> smsList) {
                smsArrayList = (ArrayList<SMS>) smsList;
                fetchIncomeSMSList(smsArrayList);
                fetchExpenseSMSList(smsArrayList);
                amount.setText("+" + totalIncome + "  -" + totalExpense);
                loadTabs();
            }
        });
    }

    private void fetchIncomeSMSList(ArrayList<SMS> smsArrayList) {
        totalIncome = 0;
        for (int i = 0; i < smsArrayList.size(); i++) {
            if (smsArrayList.get(i).getSmsText().contains("credit")) {
                totalIncome = totalIncome + smsArrayList.get(i).getAmount();
            }
        }
    }

    private void fetchExpenseSMSList(ArrayList<SMS> smsArrayList) {
        totalExpense = 0;
        for (int i = 0; i < smsArrayList.size(); i++) {
            if (smsArrayList.get(i).getSmsText().contains("debit")) {
                totalExpense = totalExpense + smsArrayList.get(i).getAmount();
            }
        }
    }

    private void initialiseObject() {
        context = MainActivity.this;
        smsViewModel = ViewModelProviders.of(this).get(SMSViewModel.class);
    }

    private void disableDefaultToolbar() {
        setSupportActionBar(toolbar);
        title.setText(context.getResources().getString(R.string.title));
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadTabs() {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        SMSListFragment smsListFragment = new SMSListFragment();
        ReportsFragment reportsFragment = new ReportsFragment();
        tabAdapter.addFragment(smsListFragment, context.getResources().getString(R.string.sms_list));
        tabAdapter.addFragment(reportsFragment, context.getResources().getString(R.string.reports));
        viewPager.setAdapter(tabAdapter);
        tabId.setupWithViewPager(viewPager);
        smsListFragment.setRecyclerArrayList(smsArrayList);
        reportsFragment.setReportsData(totalIncome, totalExpense);
    }
}
