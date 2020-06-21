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


    public void updateSMSTag(int id, String tag) {
        new UpdateTagAsyncTask(smsDAO, id, tag).execute();
    }

    public void insertSMS(SMS sms) {

        new InsertSMSAsyncTask(smsDAO).execute(sms);
    }

    private static class InsertSMSAsyncTask extends AsyncTask<SMS, Void, Void> {
        private SmsDAO smsDAO;

        public InsertSMSAsyncTask(SmsDAO smsDAO) {
            this.smsDAO = smsDAO;
        }

        @Override
        protected Void doInBackground(SMS... obj) {

            smsDAO.insert(obj[0]);
            return null;
        }
    }

    private static class UpdateTagAsyncTask extends AsyncTask<Void, Void, Void> {
        private SmsDAO smsDAO;
        private Integer id;
        private String tag;

        public UpdateTagAsyncTask(SmsDAO smsDAO, Integer id, String tag) {
            this.smsDAO = smsDAO;
            this.id = id;
            this.tag = tag;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            smsDAO.updateTag(id, tag);
            return null;
        }
    }


}
