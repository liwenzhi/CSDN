package com.example.comment.utils;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-5
 * Time: 上午11:35
 * To change this template use File | Settings | File Templates.
 */
public interface TempletConstant {

    /**
     * 诊断分析模板数据库
     */
    interface AnalysisTempletDB {
        // 数据库名称
        String DATABASE_NAME = "analysis_templets.db";

        // 数据库版本号
        int SCHEMA_VERSION = 1;
    }

    /**
     * 医生建议模板数据库
     */
    interface ProposeTempletDB {
        // 数据库名称
        String DATABASE_NAME = "propose_templets.db";

        // 数据库版本号
        int SCHEMA_VERSION = 1;

    }

    interface Table {
        String TABLE_NAME = "templets";
        String TEMPLET_ID = "_id";
        String TEMPLET_CLASS = "class";
        String TEMPLET_TEXT = "text";
    }
}
