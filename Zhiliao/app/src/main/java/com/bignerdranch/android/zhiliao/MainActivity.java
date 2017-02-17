package com.bignerdranch.android.zhiliao;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends Activity {

    private static final String token1 = "MSj5ApTgpvwrQoupM2R3sfhJ+LSWphCHE+/oC0b/YHPmWlr46cLgMcZyHvsx4hdQgeWbUuSOIBZ4FcFDMDKcvQ==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RongIM.connect(token1, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {
                Log.e("onSuccess","onSuccess, userId: "+s);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("onError","onError, errorCode: "+errorCode.getValue());
            }
        });
    }
}
