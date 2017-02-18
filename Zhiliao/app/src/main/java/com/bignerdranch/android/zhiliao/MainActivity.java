package com.bignerdranch.android.zhiliao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import static io.rong.imkit.RongIM.connect;

public class MainActivity extends Activity {

    private static final String token2 = "MSj5ApTgpvwrQoupM2R3sfhJ+LSWphCHE+/oC0b/YHPmWlr46cLgMcZyHvsx4hdQgeWbUuSOIBZ4FcFDMDKcvQ==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
