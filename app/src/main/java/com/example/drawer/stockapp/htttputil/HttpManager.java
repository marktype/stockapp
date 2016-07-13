package com.example.drawer.stockapp.htttputil;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 欢大哥 on 2016/7/8.
 * http管理类，调用接口处理回调信息
 */
public class HttpManager {
    public static final String BASE_URL = "http://183.60.47.101:8012/";      //基地址
    public static final String TEST_URL = BASE_URL+"quant/ProductList";      //测试地址

    /**
     * 处理接口信息post请求
     * @return
     */
    public static String getHttpData(String token, HashMap<String,String> map) {
        final String[] dataInfo = {null};
        if (TextUtils.isEmpty(token)){   //token为null请求时会报异常
            token = "";
        }
        FormBody.Builder builder = new FormBody.Builder();
           //请求体（此时为空若要添加则 new FormBody.Builder().add("","").build()）
        if (map!= null){
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                String val = (String) entry.getValue();
                builder.add(key,  val);
            }
        }
        RequestBody formBody = builder.build();
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(TEST_URL)
                .post(formBody)
                .header("Uid", "5451221")
                .addHeader("Lang", "zh-cn")
                .addHeader("Token", token)
                .build();
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("tag", "onFailure: ---" + e.toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                dataInfo[0] = response.body().string();
                Log.d("tag", "----" + dataInfo[0]);
            }
        });
            return dataInfo[0];
        }

}
