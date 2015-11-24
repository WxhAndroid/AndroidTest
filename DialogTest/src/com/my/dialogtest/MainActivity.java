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
                    // DialogFragment�÷�
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
                    // ����DialogFragmentʹ��
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
     * һ��Dialog
     * 
     * @return
     */
    public AlertDialog createAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("A title");
        builder.setMessage("display a message");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // ���"OK���Ĵ����¼�
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // �����cancel�������¼�
            }
        });
        return builder.create();
    }

    /**
     * �б�Dialog
     * 
     * @return
     */
    public AlertDialog createListDialog() {
        String colors[] = { "Green", "Red", "Blue", "Yellow" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("pick_color");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // ѡ��ĳһ��Ĵ����¼�����which��Ϊѡ���λ��
            }
        });
        return builder.create();
    }

    /**
     * ��ѡDialog
     * 
     * @return
     */
    public AlertDialog createMultipleDialog() {
        // ѡ�������
        final String toppings[] = { "Onion", "Lettuce", "Tomato" };
        final List<String> mSelectedItems = new ArrayList(); // ��¼��ѡ����Ŀ
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("pick toppings");
        // ���ö�ѡ����ʾ��ʽ
        builder.setMultiChoiceItems(toppings, null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    // ѡ��ĳһ��
                    mSelectedItems.add(toppings[which]);
                } else if (mSelectedItems.contains(toppings[which])) {
                    // ȡ��ѡ���ĳһ����Ҫ���б����Ƴ�
                    mSelectedItems.remove(Integer.valueOf(which));
                }
            }
        });
        // ����һ��"ok"��ť
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                String message = "";
                // ��ѡ�е���Ϣ�ӵ�ͬһ���ַ���
                for (int i = 0; i < mSelectedItems.size(); i++) {
                    message += mSelectedItems.get(i) + ",";
                }
                // ��ʾ
                Toast.makeText(MainActivity.this, "you chose " + message, Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                // �����cancel���Ĵ����¼�
            }
        });

        return builder.create();
    }

    /**
     * ��ѡDialog
     * 
     * @return
     */
    public AlertDialog createSingleDialog() {
        // ѡ������
        final String toppings[] = { "Onion", "Lettuce", "Tomato" };
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("pick toppings");
        // ����ѡ�����ʾ��ʽ
        builder.setSingleChoiceItems(toppings, 0, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                position = which;
            }
        });
        // ���á�ok����ť�����¼�
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Toast.makeText(MainActivity.this, "you chose " + toppings[position], Toast.LENGTH_SHORT).show();
            }
        });
        // ���á�cancel����ť�����¼�
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        return builder.create();
    }

    /**
     * �Զ���Dialog
     * 
     * @return
     */
    public AlertDialog createCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // ��ò���
        LayoutInflater inflater = getLayoutInflater();
        // ����Զ���Ĳ�����ʾ
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));
        // ��ӡ�signin����ť�����¼�
        builder.setPositiveButton("signin", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        // ��ӡ�cancel�������¼�
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        return builder.create();
    }

    @SuppressLint("NewApi")
    public static class FireMissilesDialogFragment extends DialogFragment {
        /**
         * ����Fragment�Ի���ʵ��
         * 
         * @param title��ָ���Ի���ı��⡣
         * @return��Fragment�Ի���ʵ����
         */
        public static FireMissilesDialogFragment newInstance(String title) {
            FireMissilesDialogFragment frag = new FireMissilesDialogFragment();
            Bundle args = new Bundle();
            // �Զ���ı���
            args.putString("title", title);
            frag.setArguments(args);
            return frag;
        }

        /**
         * ��дFragment���onCreateDialog��������DialogFragment��show����ִ��֮�� ϵͳ���������ص�������
         */
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // ��ȡ����ʵ����ʱ����Ĵ��ڱ��⡣
            String title = getArguments().getString("title");
            // ��builder�����Ի���
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
            // ����һ��dialog���󲢷���
            return builder.create();
        }
    }

}
