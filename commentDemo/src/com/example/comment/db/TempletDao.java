package com.example.comment.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.comment.entity.Templet;
import com.example.comment.ui.activity.MyActivity;
import com.example.comment.utils.AnalysisTempletDBHelper;
import com.example.comment.utils.ProposeTempletDBHelper;
import com.example.comment.utils.TempletConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-5
 * Time: 上午11:28
 * To change this template use File | Settings | File Templates.
 */
public class TempletDao {

    private static final String TAG = "TempletDao";
    private static AnalysisTempletDBHelper mAnalysisTempletDBHelper;
    private static ProposeTempletDBHelper mProposeTempletDBHelper;

    private static SQLiteOpenHelper getHelper(int whichTmpletDataBase) {
        switch (whichTmpletDataBase) {
            case MyActivity.TEMPLET_ANALYSIS:
                if (mAnalysisTempletDBHelper == null) {
                    mAnalysisTempletDBHelper = new AnalysisTempletDBHelper();
                }
                return mAnalysisTempletDBHelper;

            case MyActivity.TEMPLET_PROPOSE:
                if (mProposeTempletDBHelper == null) {
                    mProposeTempletDBHelper = new ProposeTempletDBHelper();
                }
                return mProposeTempletDBHelper;

            default:
                return null;
        }

    }

    /**
     * 插入模板
     *
     * @param templet 要插入的模板
     * @return 是否插入成功
     */
    public static boolean insertTemplet(int whichTmpletDataBase, String classe, String templet) {
        // 获取可写数据库
        SQLiteDatabase writableDatabase = getHelper(whichTmpletDataBase).getWritableDatabase();

        // 包装Values
        ContentValues contentValues = new ContentValues();
        contentValues.put(TempletConstant.Table.TEMPLET_CLASS, classe);  // 类别
        contentValues.put(TempletConstant.Table.TEMPLET_TEXT, templet);  // 模板

        // 插入
        long insert = writableDatabase.insert(TempletConstant.Table.TABLE_NAME, TempletConstant.Table.TEMPLET_TEXT, contentValues);
        // 插入失败
        if (insert == -1) {
            writableDatabase.close();
            return false;
        }
        // 插入成功
        writableDatabase.close();
        return true;
    }

    /**
     * 查找数据库中的所有模板
     *
     * @return 模板集合
     */
    public static List<Templet> queryAllTemplet(int whichTmpletDataBase) {
        // 创建数据集合
        List<Templet> templets = new ArrayList<Templet>();

        // 开始查询
        SQLiteDatabase readableDatabase = getHelper(whichTmpletDataBase).getReadableDatabase();
        Cursor cursor = readableDatabase.query(TempletConstant.Table.TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(TempletConstant.Table.TEMPLET_ID));
                String currentClass = cursor.getString(cursor.getColumnIndex(TempletConstant.Table.TEMPLET_CLASS));
                String currentTemplet = cursor.getString(cursor.getColumnIndex(TempletConstant.Table.TEMPLET_TEXT));
                // 创建模板Bean
                Templet templet = new Templet();
                templet.setId(id);
                templet.setClasse(currentClass);
                templet.setText(currentTemplet);
                // 加入集合
                templets.add(templet);
            }
        }

        // 查询结束
        readableDatabase.close();
        return templets;
    }

    /**
     * 更新数据库中的模板
     *
     * @param newClass   新类别
     * @param newTemplet 新模板
     * @return 是否更新成功
     */
    public static boolean updataTemplet(int whichTmpletDataBase, String id, String newClass, String newTemplet) {
        // 获取可写数据库
        SQLiteDatabase writableDatabase = getHelper(whichTmpletDataBase).getWritableDatabase();
        // 添加新的模板到旧模板位置
        ContentValues values = new ContentValues();
        values.put(TempletConstant.Table.TEMPLET_CLASS, newClass);
        values.put(TempletConstant.Table.TEMPLET_TEXT, newTemplet);
        int affected = writableDatabase.update(TempletConstant.Table.TABLE_NAME, values, TempletConstant.Table.TEMPLET_ID + " = ?", new String[]{id});
        if (affected <= 0) {
            writableDatabase.close();
            return false;
        }
        writableDatabase.close();
        return true;
    }

    /**
     * 删除数据库中的模板
     *
     * @param id 需要删除模板的id
     * @return 是否删除成功
     */
    public static boolean deleteTemplet(int whichTmpletDataBase, String id) {
        // 获取可写数据库
        SQLiteDatabase writableDatabase = getHelper(whichTmpletDataBase).getWritableDatabase();
        // 开始删除
        int affected = writableDatabase.delete(TempletConstant.Table.TABLE_NAME, TempletConstant.Table.TEMPLET_ID + " = ?", new String[]{id});
        if (affected <= 0) {
            writableDatabase.close();
            return false;
        }
        writableDatabase.close();
        return true;
    }

    /**
     * 查询所有的类别有几种
     */
    public static List<String> queryAllClasses(int whichTmpletDataBase) {
        ArrayList<String> classes = new ArrayList<String>();
        SQLiteDatabase readableDatabase = getHelper(whichTmpletDataBase).getReadableDatabase();
        Cursor cursor = readableDatabase.query(TempletConstant.Table.TABLE_NAME, new String[]{TempletConstant.Table.TEMPLET_CLASS}, null, null, null, null, null);
        if (cursor != null && cursor.getCount() != 0) {
            while (cursor.moveToNext()) {
                String classe = cursor.getString(cursor.getColumnIndex(TempletConstant.Table.TEMPLET_CLASS));
                // 去除重复的类别
                if (!classes.contains(classe)) {
                    classes.add(classe);
                }
            }
        }
        readableDatabase.close();
        return classes;
    }
}
