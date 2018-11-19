package com.xuqing.queueevent;

import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by ZBL on 2018/11/16.
 */

public abstract class QueueEvent<T,T1 extends QueueEvent> {
    public static final String TAG = "QueueEvent";
    /**
     * 线程执行频率，值越高，间隔时间越长，单位毫秒
     */
    private int seeped = 1000;
    /**
     * 对象锁
     */
    private Object lock = new Object();
    /**
     * 线程列队
     */
    private Queue<T> tQueue = new LinkedList<>();
    /**
     * 是否正在运行
     */
    private boolean running = false;

    private T t;

    protected abstract T1 getQueueEventChildInstance();
    protected abstract void onQueueEventDoing(T t);
    protected abstract void finishChild();
    protected void onQueueEventDone(Bundle b,T t){};

    /**
     * 添加单个线程
     *
     * @param t
     */
    public T1 enqueue(T t) {
        if (!tQueue.contains(t)) {
            tQueue.add(t);
        }
        start();
        return getQueueEventChildInstance();
    }

    /**
     * 添加多个线程
     *
     * @param ts
     * @return
     */
    public T1 enqueue(LinkedList<T> ts) {
        for (T t : ts) {
            if (!tQueue.contains(t)) {
                tQueue.add(t);
            }
        }
        start();
        return getQueueEventChildInstance();
    }

    /**
     * 停止运行，不再使用的时候需要主动调用
     */
    public void finish() {
        running = false;
        Log.i(TAG, "任务主线程循环停止");
        finishChild();
    }

    /**
     * 开始运行
     */
    private void start() {
        if (!running) {
            running = true;
            new Thread() {
                @Override
                public void run() {
                    while (running) {
                        synchronized (lock) {
                            t = tQueue.poll();
                            if (t != null) {
                                Log.i(TAG, "开始执行任务");
                                onQueueEventDoing(t);
                                try {
                                    //暂停线程
                                    lock.wait();
                                    Log.i(TAG, "主线程等待中...");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            try {
                                //控制刷新速率
                                sleep(getSeeped());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            Log.i(TAG,"剩余总任务数："+tQueue.size());
                            Log.i(TAG, "任务主线程执行...");
                        }
                    }
                }
            }.start();
        }
    }

    public void next(){
        this.next(null);
    }

    /**
     * 运行下一个线程
     */
    public void next(Bundle b) {
        Log.i(TAG, "任务主线程执行下一个...");
        if(b!=null) {
            onQueueEventDone(b,t);
        }
        new Thread() {
            @Override
            public void run() {
                synchronized (lock){
                    //执行线程
                    lock.notify();
                }
            }
        }.start();
    }

    private int getSeeped() {
        return seeped;
    }

    public T1 setSeeped(int seeped) {
        this.seeped = seeped;
        return getQueueEventChildInstance();
    }
}
