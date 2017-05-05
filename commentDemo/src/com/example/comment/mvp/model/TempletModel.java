package com.example.comment.mvp.model;


import com.example.comment.db.TempletDao;
import com.example.comment.entity.Templet;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-5
 * Time: 上午10:21
 * To change this template use File | Settings | File Templates.
 */
public class TempletModel extends BaseModel {

    /**
     * 查询数据库中的模板
     *
     * @return 模板集合
     */
    public List<Templet> query(int whichTempletDataBase) {
        List<Templet> templets = TempletDao.queryAllTemplet(whichTempletDataBase);
        return templets;
    }

    /**
     * 添加数据库中的模板
     */
    public boolean insert(int whichTempletDataBase, String classe, String templet) {
        boolean isSuccess = TempletDao.insertTemplet(whichTempletDataBase, classe, templet);
        return isSuccess;
    }

    /**
     * 更新数据库
     */
    public boolean updata(int whichTempletDataBase, String id, String newClass, String newTemplet) {
        boolean isSuccess = TempletDao.updataTemplet(whichTempletDataBase, id, newClass, newTemplet);
        return isSuccess;
    }

    /**
     * 删除数据库中的模板
     */
    public boolean delete(int whichTempletDataBase, String id) {
        boolean isSuccess = TempletDao.deleteTemplet(whichTempletDataBase, id);
        return isSuccess;
    }

    /**
     * 查询对应数据库中的模板类别有多少种
     */
    public List<String> queryClass(int whichTempletDataBase) {
        List<String> classes = TempletDao.queryAllClasses(whichTempletDataBase);
        return classes;
    }
}
