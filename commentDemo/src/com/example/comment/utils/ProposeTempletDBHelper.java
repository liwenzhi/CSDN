package com.example.comment.utils;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.comment.app.App;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-8
 * Time: 下午1:59
 * To change this template use File | Settings | File Templates.
 */
public class ProposeTempletDBHelper extends SQLiteOpenHelper {

    public ProposeTempletDBHelper() {
        super(App.getInstance(), TempletConstant.ProposeTempletDB.DATABASE_NAME, null, TempletConstant.ProposeTempletDB.SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "+TempletConstant.Table.TABLE_NAME+" (_id integer primary key autoincrement, "+TempletConstant.Table.TEMPLET_CLASS+" varchar(20), "+TempletConstant.Table.TEMPLET_TEXT+" varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
