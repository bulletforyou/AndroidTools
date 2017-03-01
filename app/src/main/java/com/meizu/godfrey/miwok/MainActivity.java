package com.meizu.godfrey.miwok;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.meizu.godfrey.miwok.Business.Rescource;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    Toast toast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button_1);
        button2 = (Button) findViewById(R.id.button_2);
        final TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getLocalNumber());


        button1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                toast = Toast.makeText(MainActivity.this, "通话记录生成成功", Toast.LENGTH_SHORT);
                toast.show();
                addCall();
            }

        });


        button2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                addContacts();
                toast = Toast.makeText(MainActivity.this, "联系人添加成功", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }

    private void addCall() {
        String[] numbers = {"13260612005", "13628632030", "10086", "10010", "10000"};
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < numbers.length; j++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(CallLog.Calls.TYPE, CallLog.Calls.INCOMING_TYPE);
                contentValues.put(CallLog.Calls.NUMBER, numbers[j]);
                contentValues.put(CallLog.Calls.DATE, 1488262106);
                contentValues.put(CallLog.Calls.NEW, "0");
                try {
                    getContentResolver().insert(CallLog.Calls.CONTENT_URI, contentValues);
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @return
     * @usage 获取手机号码
     */
    private String getLocalNumber() {
        Context context = getApplicationContext();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String number = telephonyManager.getLine1Number();
        return number;
    }


    public void addContacts() {
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = getApplicationContext().getContentResolver();
        ContentValues contentValues = new ContentValues();
        long contactId = ContentUris.parseId(resolver.insert(uri, contentValues));


                uri = Uri.parse("content://com.android.contacts/data");
                contentValues.put("raw_contact_id", contactId);
                contentValues.put("mimetype", "vnd.android.cursor.item/name");
                contentValues.put("data2","MR.JKS");
//            Log.v("插入数据为:",i.next().toString());
                resolver.insert(uri, contentValues);

                contentValues.clear();
                contentValues.put("raw_contact_id", contactId);
                contentValues.put("mimetype", "vnd.android.cursor.item/phone_v2");
                contentValues.put("data2", "2");
                contentValues.put("data1", "18931234567");
                contentValues.put("data1", "10010");
                resolver.insert(uri, contentValues);

                contentValues.clear();
                contentValues.put("raw_contact_id", contactId);
                contentValues.put("mimetype", "vnd.android.cursor.item/email_v2");
                contentValues.put("data2", "2");
                contentValues.put("data1", "zhouguoping@qq.com");
                resolver.insert(uri, contentValues);
            }
        }


