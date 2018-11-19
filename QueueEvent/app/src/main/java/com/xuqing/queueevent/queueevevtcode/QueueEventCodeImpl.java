package com.xuqing.queueevent.queueevevtcode;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.xuqing.queueevent.QueueEvent;

/**
 * Created by ZBL on 2018/11/16.
 */

public abstract class QueueEventCodeImpl extends QueueEvent<OnCodeEvent,QueueEventCode> {
    private boolean sync=false;
    private Bundle bundle;

    private boolean isSync() {
        return sync;
    }

    public QueueEventCode setSync(boolean sync) {
        this.sync = sync;
        return getQueueEventChildInstance();
    }

    @Override
    protected void onQueueEventDoing(OnCodeEvent onCodeEvent) {
        if(isSync()){
            sendSyncMessage(bundle,onCodeEvent,1);
        }else {
            onCodeEvent.onDoingCode(bundle);
        }
    }

    @Override
    protected void onQueueEventDone(Bundle b,OnCodeEvent onCodeEvent) {
        bundle=b;
        if (isSync()){
            sendSyncMessage(bundle,onCodeEvent,2);
        }else {
            onCodeEvent.onDoneCode(b);
        }
    }

    private void sendSyncMessage(Bundle bundle,OnCodeEvent onCodeEvent,int type){
        Message message=new Message();
        message.setData(bundle);
        message.what=type;
        message.obj=onCodeEvent;
        queueEventHandler.sendMessage(message);
    }

    private Handler queueEventHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int what=msg.what;
            OnCodeEvent onCodeEvent=(OnCodeEvent) msg.obj;
            Bundle bundle=msg.getData();
            switch (what){
                case 1:
                    onCodeEvent.onDoingCode(bundle);
                    break;
                case 2:
                    onCodeEvent.onDoneCode(bundle);
                    break;
            }
        }
    };
}
