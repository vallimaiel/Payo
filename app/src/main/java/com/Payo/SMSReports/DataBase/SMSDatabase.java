package com.Payo.SMSReports.DataBase;


import android.content.Context;
import android.os.AsyncTask;


import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {SMS.class}, version = 1)
public abstract class SMSDatabase extends RoomDatabase {

    public abstract SmsDAO smsDAO();

    private static SMSDatabase instance;

    public static synchronized SMSDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SMSDatabase.class, "sms_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new InitialDataAsyncTask(instance).execute();
        }
    };

    private static class InitialDataAsyncTask extends AsyncTask<Void, Void, Void> {
        private SmsDAO smsDAO;

        public InitialDataAsyncTask(SMSDatabase SMSDatabase) {
            smsDAO = SMSDatabase.smsDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            SMS sms1 = new SMS();
            sms1.setSmsText("Rs 2567 debited from a/c**7890 on 20-06-20");
            sms1.setAmount(2567);
            //Date d1 = new Date(20, 6, 20);
            //sms1.setDate(d1);

            SMS sms2 = new SMS();
            sms2.setSmsText("Rs 256 debited from a/c**7890 on 20-06-13");
            sms2.setAmount(256);
            //Date d2 = new Date(20, 6, 13);
            //sms2.setDate(d2);

            SMS sms3 = new SMS();
            sms3.setSmsText("Rs 2668 credited to a/c**7890 on 20-06-07");
            sms3.setAmount(2668);
            //Date d3 = new Date(20, 6, 7);
            //sms3.setDate(d3);

            SMS sms4 = new SMS();
            sms4.setSmsText("Rs 300 credited to a/c**7890 on 20-06-01");
            sms4.setAmount(300);
            //Date d4 = new Date(20, 6, 1);
            //sms4.setDate(d4);

            SMS sms5 = new SMS();
            sms5.setSmsText("Rs 30 debited from a/c**7890 on 20-05-12 by amazon");
            sms5.setAmount(30);
            //Date d5 = new Date(20, 5, 12);
            //sms5.setDate(d5);

            SMS sms6 = new SMS();
            sms6.setSmsText("Rs 310 credited to a/c**7890 on 20-05-09 by amazon");
            sms6.setAmount(310);
            //Date d6 = new Date(20, 5, 9);
            //sms6.setDate(d6);

            smsDAO.insert(sms1);
            smsDAO.insert(sms2);
            smsDAO.insert(sms3);
            smsDAO.insert(sms4);
            smsDAO.insert(sms5);
            smsDAO.insert(sms6);

            return null;
        }
    }


}
