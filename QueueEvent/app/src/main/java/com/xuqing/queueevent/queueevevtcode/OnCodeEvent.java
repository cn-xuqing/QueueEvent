package com.xuqing.queueevent.queueevevtcode;

import android.os.Bundle;

/**
 * Created by ZBL on 2018/11/16.
 */

public abstract class OnCodeEvent {
    protected abstract void onDoingCode(Bundle b);
    protected void onDoneCode(Bundle b){};
}
