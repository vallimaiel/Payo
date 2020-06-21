package com.Payo.SMSReports.Features.ViewModal;

import android.app.Application;

import com.Payo.SMSReports.Features.Modal.SMS;
import com.Payo.SMSReports.DataBase.SMSRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SMSViewModel extends AndroidViewModel {
    private SMSRepository smsRepository;
    private LiveData<List<SMS>> allSMS;
    private LiveData<List<SMS>> allTag;

    public SMSViewModel(@NonNull Application application) {
        super(application);

        smsRepository = new SMSRepository(application);
    }

    public LiveData<List<SMS>> getAllSMS() {

        allSMS = smsRepository.getSMS();
        return allSMS;
    }

    public void updateTag(Integer id, String tag) {
        smsRepository.updateSMSTag(id, tag);
    }



}
