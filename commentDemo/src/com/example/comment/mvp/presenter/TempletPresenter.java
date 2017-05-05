package com.example.comment.mvp.presenter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.comment.R;
import com.example.comment.adapter.ClassesAdapter;
import com.example.comment.entity.Templet;
import com.example.comment.mvp.model.TempletModel;
import com.example.comment.mvp.view.common.TempletView;
import com.example.comment.utils.ConstantUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-5
 * Time: 上午10:21
 * 模板管理界面
 */
public class TempletPresenter extends BasePresenter<TempletView> {

    private static final String TAG = "TempletPresenter";
    public static final String EXTRA_TEMPLET = "EXTRA_TEMPLET";
    private List<Templet> mTemplets;
    private int mTempletType;
    private EditText mEt_add_templet_class;
    private EditText mEt_add_templet_text;
    private EditText mEt_templet_class;
    private EditText mEt_templet_text;
    private Button mBtn_templet_choose;

    private TempletModel mModel;

    public TempletPresenter() {
        mModel = new TempletModel();
        setModule(mModel);
    }

    public void initData(int templetType) {
        // 设置当前的模板类型
        mTempletType = templetType;


        // 开始查询数据
        mView.showLoading();

        // 根据不同的模板类型,查询数据库中的模板
        mTemplets = mModel.query(mTempletType);

        // 查询完毕
        mView.hideLoading();
        if (mTemplets.size() <= 0) {
            // 没有模板
            mView.showTempletEmpty();
        } else {
            // 有模板
            mView.getAdapter().updata(mTemplets);
            mView.showTemplets();
        }
    }

    /**
     * 编辑模板
     *
     * @param position
     */
    public void edit(final int position) {
        // 先查询有几种类别
        final List<String> classes = mModel.queryClass(mTempletType);

        // 创建模板视图
        View templetView = LayoutInflater.from(mView.getContext()).inflate(R.layout.layout_add_templet, null);
        mEt_templet_class = (EditText) templetView.findViewById(R.id.et_add_templet_class);
        mEt_templet_text = (EditText) templetView.findViewById(R.id.et_add_templet_text);
        mBtn_templet_choose = (Button) templetView.findViewById(R.id.btn_templet_class_choose);

        // 显示之前的类别
        String classe = mView.getAdapter().getItem(position).getClasse();
        mEt_templet_class.setText(classe);
        mEt_templet_class.setSelection(classe.length());

        // 显示之前的模板
        String oldTemplet = mView.getAdapter().getItem(position).getText();
        mEt_templet_text.setText(oldTemplet);
        mEt_templet_text.setSelection(oldTemplet.length());

        mBtn_templet_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(classes, mEt_templet_class);
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(mView.getContext());
        builder.setMessage("请编辑模板");
        builder.setIcon(R.drawable.ic_edit);
        builder.setView(templetView);
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newClass = mEt_templet_class.getText().toString().trim();
                String newTemplet = mEt_templet_text.getText().toString().trim();
                // 更新数据库
                boolean isSuccess = mModel.updata(mTempletType, mView.getAdapter().getItem(position).getId(), newClass, newTemplet);
                if (isSuccess) {
                    Toast.makeText(mView.getContext(), "更新模板成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mView.getContext(), "更新模板失败", Toast.LENGTH_SHORT).show();
                }
                // 更新列表
                initData(mTempletType);
            }
        });

        builder.show();
        mEt_templet_text.requestFocus();
    }

    /**
     * 删除模板
     *
     * @param position
     */
    public void delete(int position) {
        // 开始删除
        boolean isSuccess = mModel.delete(mTempletType, mView.getAdapter().getItem(position).getId());
        if (isSuccess) {
            Toast.makeText(mView.getContext(), "模板删除成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mView.getContext(), "模板删除失败", Toast.LENGTH_SHORT).show();
        }
        // 更新列表
        initData(mTempletType);
    }

    /**
     * 添加模板
     */
    public void addTemplet() {
        // 先获取已存在的类别
        final List<String> classes = mModel.queryClass(mTempletType);

        // 创建对话框
        View templetAddView = LayoutInflater.from(mView.getContext()).inflate(R.layout.layout_add_templet, null);
        mEt_add_templet_class = (EditText) templetAddView.findViewById(R.id.et_add_templet_class);
        mEt_add_templet_text = (EditText) templetAddView.findViewById(R.id.et_add_templet_text);
        // 对话框类别选择按钮
        Button btn_class_choose = (Button) templetAddView.findViewById(R.id.btn_templet_class_choose);
        btn_class_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 展示类别
                showPopupWindow(classes, mEt_add_templet_class);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(mView.getContext());
        builder.setMessage("请编辑模板");
        builder.setView(templetAddView);
        builder.setCancelable(false);
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //关闭对话框
                try {
                    Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                    field.setAccessible(true);
                    field.set(dialog, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("完成", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String templetClass = mEt_add_templet_class.getText().toString().trim();
                String templetText = mEt_add_templet_text.getText().toString().trim();
                if (TextUtils.isEmpty(templetClass)) {
                    //用于不关闭对话框
                    try {
                        Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                        field.setAccessible(true);
                        field.set(dialog, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(mView.getContext(), "类别不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(templetText)) {
                    //用于不关闭对话框
                    try {
                        Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                        field.setAccessible(true);
                        field.set(dialog, false);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(mView.getContext(), "模板不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean isSuccess = mModel.insert(mTempletType, templetClass, templetText);
                if (isSuccess) {
                    Toast.makeText(mView.getContext(), "添加模板成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mView.getContext(), "添加模板失败", Toast.LENGTH_SHORT).show();
                }
                // 更新列表
                initData(mTempletType);

                //关闭对话框
                try {
                    Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
                    field.setAccessible(true);
                    field.set(dialog, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.dismiss();
            }
        });

        builder.show();
    }

    /**
     * 点击模板对话框的模板类别按钮,弹出popupwindow供用户选择类别
     *
     * @param classes
     * @param classEditText
     */
    private void showPopupWindow(final List<String> classes, final EditText classEditText) {
        View popupView = LayoutInflater.from(classEditText.getContext()).inflate(R.layout.layout_popupwindow_templet_class_choose, null);
        ListView lv_classes = (ListView) popupView.findViewById(R.id.lv_popupwindow_templet_classes);
        if (classes == null || classes.size() <= 0) {
            // 创建适配器
            ClassesAdapter classesAdapter = new ClassesAdapter("暂无模板类别");
            lv_classes.setAdapter(classesAdapter);
        } else {
            // 创建适配器
            ClassesAdapter classesAdapter = new ClassesAdapter(classes);
            lv_classes.setAdapter(classesAdapter);
        }
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable(null, ""));
        popupWindow.showAsDropDown(classEditText);

        lv_classes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String classe = classes.get(position);
                classEditText.setText(classe);
                classEditText.setSelection(classe.length());
                if (popupWindow != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    /**
     * 获取选中的模板
     *
     * @param position
     */
    public void getTemplet(int position) {
        if (mView.getAdapter().getTemplets() != null && mView.getAdapter().getTemplets().size() > 0) {
            String templet = mView.getAdapter().getTemplets().get(position).getText();
            if (!TextUtils.isEmpty(templet)) {
                ((Activity) mView.getContext()).setResult(ConstantUtils.RESPONSE_TEMPLEACTIVITY, new Intent().putExtra(ConstantUtils.TEMPLET, templet));
                ((Activity) mView.getContext()).finish();
            }
        }
    }
}
