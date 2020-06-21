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

    @Query("UPDATE sms_table SET tag = :tag WHERE id = :id")
    int updateTag(int id, String tag);

    @Query("SELECT * FROM sms_table")
    LiveData<List<SMS>> getAllSMS();


}
