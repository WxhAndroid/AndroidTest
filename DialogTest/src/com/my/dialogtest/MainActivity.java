package com.my.dialogtest;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private int position;
    private OnClickListener mButtonCLick = new OnClickListener() {
        AlertDialog dialog;

        @SuppressLint("NewApi")
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.dialog1:
                    // DialogFragment用法
                    FireMissilesDialogFragment fire = FireMissilesDialogFragment.newInstance("fire_missiles?");
                    fire.show(getFragmentManager(), "dialog");
                    break;
                case R.id.dialog2:
                    dialog = createAlertDialog();
                    dialog.show();
                    break;
                case R.id.dialog3:
                    dialog = createListDialog();
                    dialog.show();
                    break;
                case R.id.dialog4:
                    dialog = createMultipleDialog();
                    dialog.show();
                    break;
                case R.id.dialog5:
                    dialog = createSingleDialog();
                    dialog.show();
                    break;
                case R.id.dialog6:
                    dialog = createCustomDialog();
                    dialog.show();
                    break;
                case R.id.dialog7:
                    // 更多DialogFragment使用
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                    break;

                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btn1.setOnClickListener(mButtonCLick);
        btn2.setOnClickListener(mButtonCLick);
        btn3.setOnClickListener(mButtonCLick);
        btn4.setOnClickListener(mButtonCLick);
        btn5.setOnClickListener(mButtonCLick);
        btn6.setOnClickListener(mButtonCLick);
        btn7.setOnClickListener(mButtonCLick);
    }

    public void init() {
        btn1 = (Button) findViewById(R.id.dialog1);
        btn2 = (Button) findViewById(R.id.dialog2);
        btn3 = (Button) findViewById(R.id.dialog3);
        btn4 = (Button) findViewById(R.id.dialog4);
        btn5 = (Button) findViewById(R.id.dialog5);
        btn6 = (Button) findViewById(R.id.dialog6);
        btn7 = (Button) findViewById(R.id.dialog7);
    }

    /**
     * 一般Dialog
     * 
     * @return
     */
    public AlertDialog createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("A title");
        builder.setMessage("display a message");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // 点击"OK”的触发事件
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // 点击”cancel“触发事件
            }
        });
        return builder.create();
    }

    /**
     * 列表Dialog
     * 
     * @return
     */
    public AlertDialog createListDialog() {
        String colors[] = { "Green", "Red", "Blue", "Yellow" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("pick_color");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // 选择某一项的触发事件，”which“为选择的位置
            }
        });
        return builder.create();
    }

    /**
     * 多选Dialog
     * 
     * @return
     */
    public AlertDialog createMultipleDialog() {
        // 选项的内容
        final String toppings[] = { "Onion", "Lettuce", "Tomato" };
        final List<String> mSelectedItems = new ArrayList(); // 记录所选的项目
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("pick toppings");
        // 设置多选的显示形式
        builder.setMultiChoiceItems(toppings, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    // 选中某一项
                    mSelectedItems.add(toppings[which]);
                } else if (mSelectedItems.contains(toppings[which])) {
                    // 取消选择的某一项需要从列表中移除
                    mSelectedItems.remove(Integer.valueOf(which));
                }
            }
        });
        // 设置一个"ok"按钮
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String message = "";
                // 将选中的信息加到同一个字符串
                for (int i = 0; i < mSelectedItems.size(); i++) {
                    message += mSelectedItems.get(i) + ",";
                }
                // 显示
                Toast.makeText(MainActivity.this, "you chose " + message, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // 点击”cancel“的触发事件
            }
        });

        return builder.create();
    }

    /**
     * 单选Dialog
     * 
     * @return
     */
    public AlertDialog createSingleDialog() {
        // 选项内容
        final String toppings[] = { "Onion", "Lettuce", "Tomato" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("pick toppings");
        // 设置选项的显示形式
        builder.setSingleChoiceItems(toppings, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                position = which;
            }
        });
        // 设置”ok“按钮触发事件
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MainActivity.this, "you chose " + toppings[position], Toast.LENGTH_SHORT).show();
            }
        });
        // 设置”cancel“按钮触发事件
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        return builder.create();
    }

    /**
     * 自定义Dialog
     * 
     * @return
     */
    public AlertDialog createCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // 获得布局
        LayoutInflater inflater = getLayoutInflater();
        // 获得自定义的布局显示
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));
        // 添加”signin“按钮触发事件
        builder.setPositiveButton("signin", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        // 添加”cancel“触发事件
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return builder.create();
    }

    @SuppressLint("NewApi")
    public static class FireMissilesDialogFragment extends DialogFragment {
        /**
         * 创建Fragment对话框实例
         * 
         * @param title：指定对话框的标题。
         * @return：Fragment对话框实例。
         */
        public static FireMissilesDialogFragment newInstance(String title) {
            FireMissilesDialogFragment frag = new FireMissilesDialogFragment();
            Bundle args = new Bundle();
            // 自定义的标题
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        /**
         * 覆写Fragment类的onCreateDialog方法，在DialogFragment的show方法执行之后， 系统会调用这个回调方法。
         */
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // 获取对象实例化时传入的窗口标题。
            String title = getArguments().getString("title");
            // 用builder创建对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage(title);
            builder.setPositiveButton("fire", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // FIRE ZE MISSILES!
                }
            });
            builder.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            // 创建一个dialog对象并返回
            return builder.create();
        }
    }

}
