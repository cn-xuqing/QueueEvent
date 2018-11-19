package com.xuqing.queueevent.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xuqing.queueevent.R;
import com.xuqing.queueevent.queueeventthread.QueueEventThread;
import com.xuqing.queueevent.queueevevtcode.OnCodeEvent;
import com.xuqing.queueevent.queueevevtcode.QueueEventCode;

public class MainActivity1 extends AppCompatActivity {
    private final static String TAG="MainActivity1";
    private Button button1,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueueEventCode.getInstance().enqueue(new OnCodeEvent(){
                    @Override
                    protected void onDoingCode(Bundle b) {
                        Log.i(TAG,"执行代码1");
                        Bundle bundle=new Bundle();
                        bundle.putString("key",">>>>>>>>>>>>>>>>>>>>>>>>1");
                        QueueEventCode.getInstance().next(bundle);
                    }

                    @Override
                    protected void onDoneCode(Bundle b) {
                        if(b!=null) {
                            Log.i(TAG, b.getString("key"));
                        }else{
                            Log.i(TAG, "无返回值");
                        }
                    }
                }).enqueue(new OnCodeEvent() {
                    @Override
                    protected void onDoingCode(Bundle b) {
                        Log.i(TAG,"执行代码2");
                        QueueEventCode.getInstance().next();
                    }

                    @Override
                    protected void onDoneCode(Bundle b) {
                        if(b!=null) {
                            Log.i(TAG, b.getString("key"));
                        }else{
                            Log.i(TAG, "无返回值");
                        }
                    }
                }).enqueue(new OnCodeEvent() {
                    @Override
                    protected void onDoingCode(Bundle b) {
                        Log.i(TAG,"执行代码3");
                        QueueEventCode.getInstance().next();
                    }

                    @Override
                    protected void onDoneCode(Bundle b) {
                        if(b!=null) {
                            Log.i(TAG, b.getString("key"));
                        }else{
                            Log.i(TAG, "无返回值");
                        }
                    }
                }).enqueue(new OnCodeEvent() {
                    @Override
                    protected void onDoingCode(Bundle b) {
                        Log.i(TAG,"执行代码4");
                        QueueEventCode.getInstance().next();
                    }

                    @Override
                    protected void onDoneCode(Bundle b) {
                        if(b!=null) {
                            Log.i(TAG, b.getString("key"));
                        }else{
                            Log.i(TAG, "无返回值");
                        }
                    }
                }).enqueue(new OnCodeEvent() {
                    @Override
                    protected void onDoingCode(Bundle b) {
                        Log.i(TAG,"执行代码5");
                        QueueEventCode.getInstance().next();
                        button1.setText("123");
                    }

                    @Override
                    protected void onDoneCode(Bundle b) {
                        if(b!=null) {
                            Log.i(TAG, b.getString("key"));
                        }else{
                            Log.i(TAG, "无返回值");
                        }
                    }
                }).setSeeped(5000).setSync(false);
            }
        });
        button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueueEventCode.getInstance().finish();
            }
        });
        button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueueEventCode.getInstance().setSeeped(1000).setSync(true).enqueue(new OnCodeEvent() {
                    @Override
                    protected void onDoingCode(Bundle b) {
                        Log.i(TAG,"执行代码点击");
                        QueueEventCode.getInstance().next();
                        button1.setText("123");
                    }

                    @Override
                    protected void onDoneCode(Bundle b) {
                        if(b!=null) {
                            Log.i(TAG, b.getString("key"));
                        }else{
                            Log.i(TAG, "无返回值");
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        QueueEventThread.getInstance().finish();
    }
}
