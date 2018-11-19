package com.xuqing.queueevent.test;

import com.xuqing.queueevent.queueeventthread.QueueEventThread;

/**
 * Created by ZBL on 2018/11/12.
 */

public class TestThread1 extends Thread{
    @Override
    public void run() {
        System.out.println("Thread1:id="+getId());
        QueueEventThread.getInstance().next();
    }
}
