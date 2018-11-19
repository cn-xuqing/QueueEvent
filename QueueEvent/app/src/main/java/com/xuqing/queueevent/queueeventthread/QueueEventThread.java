package com.xuqing.queueevent.queueeventthread;

import com.xuqing.queueevent.QueueEvent;

/**
 * Created by ZBL on 2018/11/15.
 */

public class QueueEventThread extends QueueEventThreadImpl {
    private static QueueEventThread queueEventThread;
    private QueueEventThread() {}

    public static QueueEventThread getInstance() {
        if (queueEventThread == null) {
            queueEventThread = new QueueEventThread();
        }
        return queueEventThread;
    }

    @Override
    protected QueueEventThread getQueueEventChildInstance() {
        return queueEventThread;
    }

    @Override
    protected void finishChild() {
        queueEventThread=null;
    }
}
