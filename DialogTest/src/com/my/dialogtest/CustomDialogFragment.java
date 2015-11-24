package com.my.dialogtest;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

@SuppressLint("NewApi")
public class CustomDialogFragment extends DialogFragment {
    private Button cancleBtn;
    private Button okBtn;

    /**
     * ͨ��ϵͳ�ж�DialogFragment����ʾ��ʽ����������Dialog����ʽ��ʾ������Ƕ���Fragment��ʾ
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // �����ͼ
        View view = inflater.inflate(R.layout.purchase_items, container, false);
        cancleBtn = (Button) view.findViewById(R.id.cancle_btn);
        okBtn = (Button) view.findViewById(R.id.ok_btn);
        cancleBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        okBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "you chose ok!", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return view;
    }

    /**
     * The system calls this only when creating the layout in a dialog. ������Dialog��ʱ��ϵͳ�������
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /**
         * ��Ψһ���ܻḲ�����������ԭ����ǵ�ʹ��onCreateView()ȥ�޸�����Dialog�ص��ʱ�����磬
         * dialog����һ��Ĭ�ϵı��⣬����ʹ���߿��ܲ���Ҫ������������ȥ�����⣬�����������ø���ȥ���Dialog��
         */
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
