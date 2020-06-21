package com.Payo.SMSReports.Features.View;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Payo.SMSReports.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReportsFragment extends Fragment {
    @BindView(R.id.pie_chart)
    PieChart pieChart;
    private Unbinder unbinder;
    Context context;
    private Integer[] amount = new Integer[2];
    private String[] transaction = new String[2];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_reports, container, false);
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();
        return view;
    }

    public void setReportsData(Integer totalIncome, Integer totalExpense) {
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Expense - Red , Income - Green");
        pieChart.setCenterTextSize(10);
        amount[0] = totalExpense;
        amount[1] = totalIncome;
        transaction[0] = "Expense";
        transaction[1] = "Income";
        addDataSet();
    }

    private void addDataSet() {
        ArrayList<PieEntry> amountEntrys = new ArrayList<>();
        ArrayList<String> transactionEntrys = new ArrayList<>();

        for (int i = 0; i < amount.length; i++) {
            amountEntrys.add(new PieEntry(amount[i], i));
        }

        for (int i = 1; i < transaction.length; i++) {
            transactionEntrys.add(transaction[i]);
        }

        PieDataSet pieDataSet = new PieDataSet(amountEntrys, "Transactional Details");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }




}
