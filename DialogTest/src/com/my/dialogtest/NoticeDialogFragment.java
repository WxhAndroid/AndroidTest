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
 * ������DialogFragment��<br>
 * ������һ���ӿڣ�ʵ�ָýӿڲ���Ӱ�ť������¼�������
 * 
 * @author 13075578
 */
@SuppressLint("NewApi")
public class NoticeDialogFragment extends DialogFragment {
    // ʹ������ӿڵ�ʵ���ṩ�ж����¼�
    NoticeDialogListener mListener;

    /**
     * ʵ������ӿڵ�����Ҫʵ������������
     */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(DialogFragment dialog);

        public void onDialogNegativeClick(DialogFragment dialog);
    }

    /**
     * ����Fragment.onAttach()�������������NoticeDialogListenerʵ��
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // У����Activityʵ�ֻص��ӿ�
        try {
            // ���NoticeDialogListenerʵ�����������Ǿ��ܽ��¼����͵���Activity
            mListener = (NoticeDialogListener) activity;
        } catch (ClassCastException e) {
            // activityû��ʵ������ӿ����׳��쳣
            throw new ClassCastException(activity.toString() + " must implement NoticeDialogListener");
        }
    }

    /**
     * ��дFragment���onCreateDialog��������DialogFragment��show����ִ��֮�� ϵͳ���������ص�������
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // ����dialog������button�ĵ���¼�
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
