package com.cannan.framwork.broadcast;

import android.content.Intent;

/**
 * Created by Cannan on 2017/7/26 0026.
 */

public class HelloBroadcast extends BaseBroadCast {

    BaseBroadCast.ReceiverListener listener;
    public HelloBroadcast(BaseBroadCast.ReceiverListener listener){
         this.listener=listener;
    }

    @Override
    void received(Intent intent) {
        listener.receivedUpdateUI();
    }
}
