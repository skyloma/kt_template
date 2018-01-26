package com.loma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.socks.library.KLog;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Observable.interval(0,1, TimeUnit.SECONDS)
                .subscribe(aLong -> {
                    Log.e("zjt",aLong+"");
                },throwable -> {
                    Log.e("zjt",throwable.getLocalizedMessage()+"");
                },() ->{

                    Log.e("zjt", "ok");
                } );
        KLog.d("ddddddddddddddddddddd");

    }
}
