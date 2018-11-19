package com.xuqing.queueevent.queueevevtcode;

import android.os.Bundle;

/**
 * Created by ZBL on 2018/11/19.
 */

public class QueueEventCode extends QueueEventCodeImpl{
    private static QueueEventCode queueEventCode;
    private QueueEventCode(){}

    @Override
    protected QueueEventCode getQueueEventChildInstance() {
        return queueEventCode;
    }

    public static QueueEventCode getInstance(){
        if (queueEventCode==null){
            queueEventCode=new QueueEventCode();
        }
        return queueEventCode;
    }

    @Override
    protected void finishChild() {
        queueEventCode=null;
    }
}
