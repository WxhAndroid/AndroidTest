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
     * 通过系统判断DialogFragment的显示形式，不论是以Dialog的形式显示，还是嵌入的Fragment显示
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 获得视图
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
     * The system calls this only when creating the layout in a dialog. 当创建Dialog的时候系统将会调用
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        /**
         * 你唯一可能会覆盖这个方法的原因就是当使用onCreateView()去修改任意Dialog特点的时候。例如，
         * dialog都有一个默认的标题，但是使用者可能不需要它。因此你可以去掉标题，但是你必须调用父类去获得Dialog。
         */
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
