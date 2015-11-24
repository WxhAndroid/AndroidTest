package com.my.dialogtest;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.my.dialogtest.NoticeDialogFragment.NoticeDialogListener;

public class SecondActivity extends FragmentActivity implements NoticeDialogListener {
    private Button btn;
    private Button fullBtn;
    boolean mIsLargeLayout;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_second);
        // 获得运行应用程序的屏幕大小级别
        mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);
        btn = (Button) findViewById(R.id.listener_btn);
        // 按钮的点击事件
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showNoticeDialog();
            }
        });
        fullBtn = (Button) findViewById(R.id.fullscreen_btn);
        // 按钮的点击事件
        fullBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    /**
     * 功能描述: 显示通过接口实现的DialogFragment<br>
     */
    @SuppressLint("NewApi")
    public void showNoticeDialog() {
        // 创建DialogFragment对象，并显示
        NoticeDialogFragment dialog = new NoticeDialogFragment();
        dialog.show(getFragmentManager(), "NoticeDialogFragment");
    }

    /**
     * 继承接口所需要实现的函数，即按钮的触发事件
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // 点击确定按钮的触发事件
        Toast.makeText(SecondActivity.this, "you chose fire", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // 点击取消按钮的触发事件
        Toast.makeText(SecondActivity.this, "you chose cancle", Toast.LENGTH_SHORT).show();
    }

    /**
     * 功能描述: 显示根据屏幕大小显示的DialogFragment<br>
     */
    @SuppressLint("NewApi")
    public void showDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        CustomDialogFragment newFragment = new CustomDialogFragment();
//        newFragment.show(fragmentManager, "dialog");
        if (mIsLargeLayout) {
            // 屏幕较大，以Dialog的形式显示
            newFragment.show(fragmentManager, "dialog");
        } else {
            // 屏幕较小，以全屏形式显示
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // 指定一个过渡动画
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 作为全屏显示,使用“content”作为fragment容器的基本视图,这始终是Activity的基本视图
            transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        }
    }

}
