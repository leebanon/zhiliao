package com.bignerdranch.android.zhiliao;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import utils.DemoContext;
import utils.NetUtils;

/**
 * Created by Administrator on 2/18/2017.
 */

public class LoginActivity extends Activity {
    private String token1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button btn = (Button) findViewById(R.id.sign_in);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sign_in();
            }
        });

    }

    /**
     * 用户登录，用户登录成功，获得 cookie，将cookie 保存
     */
    private void sign_in() {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                Map<String, String> requestParameter = new HashMap<String, String>();

                requestParameter.put("email", "yang115@qq.com");
                requestParameter.put("password", "123456");

                String result = NetUtils.sendPostRequest("email_login", requestParameter);
                return result;
            }

            @Override
            protected void onPostExecute(String result) {

                getToken();
            }
        }.execute();
    }

    /**
     * 获得token
     */
    private void getToken() {

        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {

                String result = NetUtils.sendGetRequest("token");
                return result;
            }

            @Override
            protected void onPostExecute(String result) {

                try {
                    if (result != null) {
                        JSONObject object = new JSONObject(result);
                        JSONObject jobj = object.getJSONObject("result");

                        if (object.getInt("code") == 200) {
                            token1 = jobj.getString("token");
                            connect(token1);

                            SharedPreferences.Editor edit = DemoContext.getInstance().getSharedPreferences().edit();
                            edit.putString("DEMO_TOKEN", token1);
                            edit.apply();

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }

    /**
     * 建立与融云服务器的连接
     *
     * @param token
     */
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))) {

            /**
             * IMKit SDK调用第二步,建立与服务器的连接
             */
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
                 */
                @Override
                public void onTokenIncorrect() {

                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token
                 */
                @Override
                public void onSuccess(String userid) {

                    Log.d("LoginActivity", "--onSuccess" + userid);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }

}
