package com.xuqing.queueevent.queueeventthread;

import com.xuqing.queueevent.QueueEvent;

/**
 * Created by ZBL on 2018/11/19.
 */

public abstract class QueueEventThreadImpl extends QueueEvent<Thread,QueueEventThread> {
    @Override
    protected void onQueueEventDoing(Thread thread) {
        thread.start();
    }
}
