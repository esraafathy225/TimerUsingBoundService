package com.esraa.hp.boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by HP on 2018-11-17.
 */

public class MyService extends Service{
    private IBinder binder=new MyBinder();


    public class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public int findSquare(int x){
        return x*x;
    }
}
