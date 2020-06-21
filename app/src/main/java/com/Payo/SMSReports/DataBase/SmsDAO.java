package com.Payo.SMSReports.DataBase;


import com.Payo.SMSReports.Features.Modal.SMS;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SmsDAO {

    @Insert
    void insert(SMS sms);

    @Update
    void update(SMS obj);

    @Query("SELECT * FROM sms_table")
    LiveData<List<SMS>> getAllSMS();
}
