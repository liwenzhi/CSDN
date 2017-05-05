package com.example.comment.utils;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.comment.app.App;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-5
 * Time: 上午11:18
 * 诊断分析模板数据库帮助类
 */
public class AnalysisTempletDBHelper extends SQLiteOpenHelper {

    public AnalysisTempletDBHelper() {
        super(App.getInstance(), TempletConstant.AnalysisTempletDB.DATABASE_NAME, null, TempletConstant.AnalysisTempletDB.SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TempletConstant.Table.TABLE_NAME + " (" + TempletConstant.Table.TEMPLET_ID + " integer primary key autoincrement, " + TempletConstant.Table.TEMPLET_CLASS + " varchar(20), " + TempletConstant.Table.TEMPLET_TEXT + " varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
