package com.bw.movie.mvp.model.utils;

import android.app.Dialog;
import android.content.Context;

import android.view.Gravity;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bw.movie.R;


public class AlertAndAnimationUtils {

    private Dialog dialog;
    public void AlertDialog(final Context context,View view){
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        //将布局设置给Dialog
        dialog.setContentView(view);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        if (dialogWindow == null) {
            return;
        }

        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();//显示对话框
        dialog.setCancelable(false);
    }

    /**
     * 隐藏页面
     */
    public void hideDialog(){
        dialog.dismiss();
    }
}
