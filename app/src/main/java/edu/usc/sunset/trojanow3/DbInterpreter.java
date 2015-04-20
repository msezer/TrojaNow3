package edu.usc.sunset.trojanow3;

import java.util.Date;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;


/*
* This class will be used in handling the data to be sent and get from server
* in a client-server style.
 */
public class DbInterpreter extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DN_NAME = "messages.db";

    private String _message;
    private String _senderName;
    private int _senderID;
    private Date _date;

    public DbInterpreter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, String _message, String _senderName, int _senderID, Date _date) {
        super(context, name, factory, version);
        this._message = _message;
        this._senderName = _senderName;
        this._senderID = _senderID;
        this._date = _date;
    }

    public DbInterpreter(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler, String _message, String _senderName, int _senderID, Date _date) {
        super(context, name, factory, version, errorHandler);
        this._message = _message;
        this._senderName = _senderName;
        this._senderID = _senderID;
        this._date = _date;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public String get_message() {
        return _message;
    }

    public String get_senderName() {
        return _senderName;
    }

    public int get_senderID() {
        return _senderID;
    }

    public Date get_date() {
        return _date;
    }
}
