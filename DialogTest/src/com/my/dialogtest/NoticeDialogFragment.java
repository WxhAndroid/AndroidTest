package com.my.dialogtest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * 
 * 〈公用DialogFragment〉<br>
 * 〈定义一个接口，实现该接口并添加按钮点击的事件监听〉
 * 
 * @author 13075578
 */
@SuppressLint("NewApi")
public class NoticeDialogFragment extends DialogFragment {
    // 使用这个接口的实例提供行动的事件
    NoticeDialogListener mListener;

    /**
     * 实现这个接口的类需要实现这两个方法
     */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

    /**
     * 覆盖Fragment.onAttach()这个函数来处理NoticeDialogListener实例
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 校验主Activity实现回调接口
        try {
            // 获得NoticeDialogListener实例，这样我们就能将事件发送到主Activity
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // activity没有实现这个接口则抛出异常
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }

    /**
     * 覆写Fragment类的onCreateDialog方法，在DialogFragment的show方法执行之后， 系统会调用这个回调方法。
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 创建dialog并设置button的点击事件
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("fire_missiles").setPositiveButton("fire", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Send the positive button event back to the host activity
                mListener.onDialogPositiveClick(NoticeDialogFragment.this);
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Send the negative button event back to the host activity
                mListener.onDialogNegativeClick(NoticeDialogFragment.this);
            }
        });
        return builder.create();
    }
}
