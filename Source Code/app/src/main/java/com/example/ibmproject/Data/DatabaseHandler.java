package com.example.ibmproject.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.ibmproject.UTILS.ConstantsDatabase;
import com.google.android.gms.common.internal.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.example.ibmproject.UTILS.ConstantsDatabase.DB_NAME;
import static com.example.ibmproject.UTILS.ConstantsDatabase.DB_VERSION;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context context;
    public DatabaseHandler(@Nullable Context context) {
        super(context,DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HEALTH_ANALYSIS="Create Table "+ ConstantsDatabase.TABLE_NAME+"("+ConstantsDatabase.KeyID+" int Primary Key ,"
                +ConstantsDatabase.Keypercentage+" int,"+ConstantsDatabase.KeyStatus+" Text, "+
                ConstantsDatabase.KeyDate+" Long ); ";
        db.execSQL(CREATE_HEALTH_ANALYSIS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ConstantsDatabase.TABLE_NAME);

        onCreate(db);
    }

    public void addInfo(HealthData healthData){
        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put(ConstantsDatabase.KeyStatus,healthData.getStatus());
        values.put(ConstantsDatabase.Keypercentage,healthData.getPercentage());
        values.put(ConstantsDatabase.KeyDate,java.lang.System.currentTimeMillis());

        db.insert(ConstantsDatabase.TABLE_NAME,null,values);
        Log.d("Saved To database","Saved To DB");
        db.close();
    }

    public HealthData gethealthData(int id){

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(ConstantsDatabase.TABLE_NAME,new String[]{ConstantsDatabase.KeyDate,
                        ConstantsDatabase.Keypercentage,ConstantsDatabase.KeyStatus,
                        ConstantsDatabase.KeyDate},ConstantsDatabase.KeyID+"=?",new String[]{String.valueOf(id)},
                null,null,null,null);

        if (cursor!=null)
            cursor.moveToFirst();

        HealthData healthData=new HealthData();
        healthData.setStatus(cursor.getString(cursor.getColumnIndex(ConstantsDatabase.KeyStatus)));
        healthData.setPercentage(cursor.getInt(cursor.getColumnIndex(ConstantsDatabase.Keypercentage)));
        healthData.setID(cursor.getInt(cursor.getColumnIndex(ConstantsDatabase.KeyID)));

        java.text.DateFormat dateFormat= java.text.DateFormat.getDateInstance();
        String formetteddate=dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex
                (ConstantsDatabase.KeyDate))).getTime());
        healthData.setRecorddate(formetteddate);

        return healthData;
    }
    public ArrayList<HealthData> getallHealthData(){
        SQLiteDatabase db=this.getReadableDatabase();

        ArrayList<HealthData> healthDataList=new ArrayList<>();

        Cursor cursor=db.query(ConstantsDatabase.TABLE_NAME,new String[]{ConstantsDatabase.KeyID,ConstantsDatabase.Keypercentage,
                        ConstantsDatabase.KeyStatus,ConstantsDatabase.KeyDate},
                null,null,
                null,null,ConstantsDatabase.KeyDate+" DESC");

        if(cursor.moveToFirst()){
            do{
                HealthData healthData=new HealthData();

                healthData.setStatus(cursor.getString(cursor.getColumnIndex(ConstantsDatabase.KeyStatus)));
                healthData.setPercentage(cursor.getInt(cursor.getColumnIndex(ConstantsDatabase.Keypercentage)));
                healthData.setID(cursor.getInt(cursor.getColumnIndex(ConstantsDatabase.KeyID)));

                java.text.DateFormat dateFormat= java.text.DateFormat.getDateInstance();
                String formetteddate=dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex
                        (ConstantsDatabase.KeyDate))).getTime());
                healthData.setRecorddate(formetteddate);

                healthDataList.add(healthData);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return healthDataList;
    }
}
