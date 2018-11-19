package com.xuqing.queueevent.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xuqing.queueevent.R;
import com.xuqing.queueevent.queueeventthread.QueueEventThread;
import com.xuqing.queueevent.queueevevtcode.OnCodeEvent;
import com.xuqing.queueevent.queueevevtcode.QueueEventCode;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends Activity {
    private Button button1,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueueEventThread.getInstance().enqueue(new TestThread1())
                        .enqueue(new TestThread2())
                        .enqueue(new TestThread3())
                        .enqueue(new TestThread4())
                        .setSeeped(2000);
            }
        });
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueueEventThread.getInstance().finish();
            }
        });
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueueEventThread.getInstance().enqueue(new TestThread1());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QueueEventThread.getInstance().finish();
    }
}
