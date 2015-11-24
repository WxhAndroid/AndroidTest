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
        // �������Ӧ�ó������Ļ��С����
        mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);
        btn = (Button) findViewById(R.id.listener_btn);
        // ��ť�ĵ���¼�
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showNoticeDialog();
            }
        });
        fullBtn = (Button) findViewById(R.id.fullscreen_btn);
        // ��ť�ĵ���¼�
        fullBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    /**
     * ��������: ��ʾͨ���ӿ�ʵ�ֵ�DialogFragment<br>
     */
    @SuppressLint("NewApi")
    public void showNoticeDialog() {
        // ����DialogFragment���󣬲���ʾ
        NoticeDialogFragment dialog = new NoticeDialogFragment();
        dialog.show(getFragmentManager(), "NoticeDialogFragment");
    }

    /**
     * �̳нӿ�����Ҫʵ�ֵĺ���������ť�Ĵ����¼�
     */
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        // ���ȷ����ť�Ĵ����¼�
        Toast.makeText(SecondActivity.this, "you chose fire", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // ���ȡ����ť�Ĵ����¼�
        Toast.makeText(SecondActivity.this, "you chose cancle", Toast.LENGTH_SHORT).show();
    }

    /**
     * ��������: ��ʾ������Ļ��С��ʾ��DialogFragment<br>
     */
    @SuppressLint("NewApi")
    public void showDialog() {
        FragmentManager fragmentManager = getFragmentManager();
        CustomDialogFragment newFragment = new CustomDialogFragment();
//        newFragment.show(fragmentManager, "dialog");
        if (mIsLargeLayout) {
            // ��Ļ�ϴ���Dialog����ʽ��ʾ
            newFragment.show(fragmentManager, "dialog");
        } else {
            // ��Ļ��С����ȫ����ʽ��ʾ
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            // ָ��һ�����ɶ���
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // ��Ϊȫ����ʾ,ʹ�á�content����Ϊfragment�����Ļ�����ͼ,��ʼ����Activity�Ļ�����ͼ
            transaction.add(android.R.id.content, newFragment).addToBackStack(null).commit();
        }
    }

}
