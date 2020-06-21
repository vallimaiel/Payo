package com.Payo.SMSReports.Features.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.Payo.SMSReports.Features.Modal.SMS;
import com.Payo.SMSReports.Features.ViewModal.SMSViewModel;
import com.Payo.SMSReports.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.tag_spinner)
    AppCompatSpinner tagSpinner;
    @BindView(R.id.reset_btn)
    Button resetBtn;
    @BindView(R.id.apply)
    Button apply;
    Context mContext;
    private SMSViewModel smsViewModel;
    private String selectedTag;
    private ArrayList<String> tagArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_filter);
        ButterKnife.bind(this);
        initialise();
    }

    private void initialise() {
        initialiseObjects();
    }

    private void initialiseObjects() {
        mContext = FilterActivity.this;
        smsViewModel = ViewModelProviders.of(this).get(SMSViewModel.class);
        setOnClickListenerForViews();
        tagArrayList = new ArrayList<>();
        smsViewModel.getAllSMS().observe(this, new Observer<List<SMS>>() {
            @Override
            public void onChanged(@Nullable List<SMS> tagList) {
                for (int i = 0; i < tagList.size(); i++) {
                    tagArrayList.add(tagList.get(i).getTag());
                }
                loadSpinnerData();
            }
        });

    }

    private void setOnClickListenerForViews() {
        resetBtn.setOnClickListener(this);
        apply.setOnClickListener(this);
        tagSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reset_btn:
                tagSpinner.setSelection(0);
                break;
            case R.id.apply:
                Intent intent = new Intent();
                intent.putExtra("tag", selectedTag);
                setResult(RESULT_OK, intent);
                finish();
                break;
            default:
                break;
        }
    }

    private void loadSpinnerData() {
        ArrayAdapter<String> tagAdapter = new ArrayAdapter<String>(mContext, R.layout.layout_spinner_item, tagArrayList);
        tagSpinner.setAdapter(tagAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.tag_spinner) {
            selectedTag = String.valueOf(parent.getItemAtPosition(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Do Nothing
    }
}
