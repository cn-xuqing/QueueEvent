package com.xuqing.queueevent.test;

import com.xuqing.queueevent.queueeventthread.QueueEventThread;

/**
 * Created by ZBL on 2018/11/12.
 */

public class TestThread4 extends Thread{
    @Override
    public void run() {
        System.out.println("Thread4:id="+getId());
        QueueEventThread.getInstance().next();
    }
}
