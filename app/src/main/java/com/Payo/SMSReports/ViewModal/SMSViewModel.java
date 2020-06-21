package com.Payo.SMSReports.ViewModal;

import android.app.Application;

import com.Payo.SMSReports.DataBase.SMS;
import com.Payo.SMSReports.DataBase.SMSRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SMSViewModel extends AndroidViewModel {
    private SMSRepository smsRepository;
    private LiveData<List<SMS>> allSMS;

    public SMSViewModel(@NonNull Application application) {
        super(application);

        smsRepository = new SMSRepository(application);
    }

    public LiveData<List<SMS>> getAllSMS() {

        allSMS = smsRepository.getSMS();
        return allSMS;
    }


}
