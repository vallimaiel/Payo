package com.Payo.SMSReports.DataBase;

import android.app.Application;
import android.os.AsyncTask;

import com.Payo.SMSReports.Features.Modal.SMS;

import java.util.List;

import androidx.lifecycle.LiveData;

public class SMSRepository {

    private SmsDAO smsDAO;
    private LiveData<List<SMS>> books;

    public SMSRepository(Application application) {
        SMSDatabase smsDatabase = SMSDatabase.getInstance(application);
        smsDAO = smsDatabase.smsDAO();
    }

    public LiveData<List<SMS>> getSMS() {
        return smsDAO.getAllSMS();
    }

    public void insertSMS(SMS sms) {

        new InsertCategoryAsyncTask(smsDAO).execute(sms);
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<SMS, Void, Void> {
        private SmsDAO smsDAO;

        public InsertCategoryAsyncTask(SmsDAO smsDAO) {
            this.smsDAO = smsDAO;
        }

        @Override
        protected Void doInBackground(SMS... obj) {

            smsDAO.insert(obj[0]);
            return null;
        }
    }


}
