package com.example.drawer.stockapp.htttputil;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 欢大哥 on 2016/7/8.
 * http管理类，调用接口处理回调信息
 */
public class HttpManager {
    private static HttpManager instance;
    public static final String FAILED = "failed";

    private HttpManager() {

    }

    public static HttpManager newInstance() {
        if (instance == null) {
            instance = new HttpManager();
        }
        return instance;
    }

//    public static final String BASE_URL = "http://183.60.47.101:8012/";      //基地址
    public static final String BASE_URL = "http://matrixswaggerwebapi.lab.supwin.com:8899/";      //基地址
    public static final String UID = "5451221";
    public static final String LANG = "zh-cn";
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");   //数据传输类型
    public static final String TEST_URL = BASE_URL + "quant/ProductList";      //测试地址
    public static final String SHARE_LIST_URL = BASE_URL + "Dynamics/StartShareList";      //牛人分享列表
    public static final String COMMENT_LIST_URL = BASE_URL + "Dynamics/ShareCommentsList";      //评论列表
    public static final String DeleteDynamic_URL = BASE_URL + "Dynamics/DeleteDynamic";      //删除自己的动态
    public static final String StatementDetail_URL = BASE_URL + "Financial/StatementDetail";      //
    public static final String Information_URL = BASE_URL + "HeadLine/Information";      //  一级界面资讯
    public static final String NewsDetail_URL = BASE_URL + "HeadLine/NewsDetail";      //  新闻详情
    public static final String News_Comment_URL = BASE_URL + "HeadLine/Comment";      //  新闻评论或转发
    public static final String CommentsList_URL = BASE_URL + "HeadLine/CommentsList";      //  评论列表
    public static final String MarketData_URL = BASE_URL + "HeadLine/MarketData";      //  指数信息
    public static final String BannerList_URL = BASE_URL + "HeadLine/BannerList";      //  获取banner图片
    public static final String NewsList_URL = BASE_URL + "HeadLine/NewsList";      //  获取列表
    public static final String StrategyList_URL = BASE_URL + "Intelligent/StrategyList";      //  获取策略列表
    public static final String StarPorfolio_URL = BASE_URL + "Intelligent/StarPorfolio";      //  获取牛人组合列表
    public static final String MyPorfolio_URL = BASE_URL + "Intelligent/MyPorfolio";      //  获取我的组合列表
    public static final String MyCollectPorfolio_URL = BASE_URL + "Intelligent/MyCollectPorfolio";      //  获取我的订阅、收藏列表
    public static final String StrategyDetail_URL = BASE_URL + "Intelligent/StrategyDetail";      // 策略详情
    public static final String AlongRecords_URL = BASE_URL + "Intelligent/AlongRecords";      // 跟投记录
    public static final String StrategyBefor_URL = BASE_URL + "Intelligent/StrategyBefor";      // 量化策略支付前信息
    public static final String CollectStrategy_URL = BASE_URL + "Intelligent/CollectStrategy";      // 收藏、订阅策略组合
    public static final String PayStrategy_URL = BASE_URL + "Intelligent/PayStrategy";      // 跟投策略
    public static final String CancelAlong_URL = BASE_URL + "Intelligent/CancelAlong";      // 取消跟头
    public static final String StarPorfolioDetail_URL = BASE_URL + "Intelligent/StarPorfolioDetail";      // 牛人组合详情
    public static final String PayPorfolio_URL = BASE_URL + "Intelligent/PayPorfolio";      // 支付组合
    public static final String CodeList_URL = BASE_URL + "Intelligent/CodeList";      // 股票名称列表
    public static final String ChangePosition_URL = BASE_URL + "Intelligent/ChangePosition";      // 调仓
    public static final String ShortStrategiesList_URL = BASE_URL + "Intelligent/ShortStrategiesList";      // 策略名称列表
    public static final String CreatePorfolio_URL = BASE_URL + "Intelligent/CreatePorfolio";      // 创建组合
    public static final String DeletePorfolio_URL = BASE_URL + "Intelligent/DeletePorfolio";      // 删除组合
    public static final String CodeHotKeys_URL = BASE_URL + "Intelligent/HotKeys";      // 热门搜索股票关键字
    public static final String FindCode_URL = BASE_URL + "Intelligent/FindCode";      // 搜索股票
    public static final String send_dynamic_URL = BASE_URL + "Dynamics/AddDynamic";      // 发布动态
    public static final String Comment_URL = BASE_URL + "Dynamics/Comment";      // 评论或转发
    public static final String Favorites_URL = BASE_URL + "Dynamics/Favorites";      // 收藏或点赞
    public static final String Login_URL = BASE_URL + "Membership/Login";      // 登录
    public static final String USERINFO_URL = BASE_URL + "Membership/UserDetail";      // 获取用户信息
    public static final String Version_URL = BASE_URL + "Membership/Version";      // 版本
    public static final String UpdateCustomerPassword_URL = BASE_URL + "Membership/UpdateCustomerPassword";      // 修改密码
    public static final String RegisterCode_URL = BASE_URL + "Membership/RegisterCode";      // 注册验证码
    public static final String UpdateAvatar_URL = BASE_URL + "Membership/UpdateAvatar";      // 修改用户头像
    public static final String UpdataUser_URL = BASE_URL + "Membership/UpdateUserInfo";      // 修改用户信息
    public static final String ReSetPasswordCode_URL = BASE_URL + "Membership/ReSetPasswordCode";      // 重置密码验证码
    public static final String Register_URL = BASE_URL + "Membership/Register";      // 注册
    public static final String ReSetPassword_URL = BASE_URL + "Membership/ReSetPassword";      // 重置密码(忘记密码)
    public static final String Information_other_URL = BASE_URL + "Other/Information";      // 一级页面
    public static final String HotKeys_URL = BASE_URL + "Other/HotKeys";      // 热门搜索关键字
    public static final String Find_URL = BASE_URL + "Other/Find";      // 搜索股票
    public static final String ProductList_URL = BASE_URL + "Quant/ProductList";      // 产品列表
    public static final String ProductStart_URL = BASE_URL + "Quant/ProductStart";      // 开启产品
    public static final String ProductStop_URL = BASE_URL + "Quant/ProductStop";      // 关闭产品
    public static final String ProductDeposit_URL = BASE_URL + "Quant/ProductDeposit";      // 转入资金到产品
    public static final String ProductWithdraw_URL = BASE_URL + "Quant/ProductWithdraw";      // 从产品转出资金
    public static final String PorfoliosList_URL = BASE_URL + "Quant/PorfoliosList";      // 组合列表
    public static final String PorfoliosView_URL = BASE_URL + "Quant/PorfoliosView";      //
    public static final String SystemsView_URL = BASE_URL + "Quant/SystemsView";      //
    public static final String PorfolioCharts_URL = BASE_URL + "Quant/PorfolioCharts";      //报表图表
    public static final String PorfolioDetails_URL = BASE_URL + "Quant/PorfolioDetails";      //产品详情
    public static final String HoldingList_URL = BASE_URL + "Quant/HoldingList";      //持仓列表
    public static final String StrategyList_Quant_URL = BASE_URL + "Quant/StrategyList";      //策略列表
    public static final String ArticleList_URL = BASE_URL + "QuantCollege/ArticleList";      //文章列表
    public static final String ArticleDetails_URL = BASE_URL + "QuantCollege/ArticleDetails";      //获取文章详细
    public static final String Information_School_URL = BASE_URL + "School/Information";      //一级界面资讯
    public static final String CourseInfoList_URL = BASE_URL + "School/CourseInfoList";      //学堂列表
    public static final String My_School_URL = BASE_URL + "School/MyInformation";      //我的课堂列表
    public static final String CourseCommentsInfo_URL = BASE_URL + "School/CourseCommentsInfo";      //评论转发列表
    public static final String CourseDetail_URL = BASE_URL + "School/CourseDetail";      //课堂详情
    public static final String CourseComment_URL = BASE_URL + "School/CourseComment";      //评论/转发
    public static final String CourseFavorites_URL = BASE_URL + "School/CourseFavorites";      //课堂收藏
    public static final String ExceCollList_URL = BASE_URL + "School/ExceCollList";      //课堂合集列表
    public static final String ExceCollDetail_URL = BASE_URL + "School/ExceCollDetail";      //课堂合集列表详情
    public static final String ExceCollCommInfo_URL = BASE_URL + "School/ExceCollCommInfo";      //评论转发列表
    public static final String ExceCollComment_URL = BASE_URL + "School/ExceCollComment";      //合集进行评论转发
    public static final String ExceCollFavorites_URL = BASE_URL + "School/ExceCollFavorites";      //课堂收藏


    /**
     * 只用传url
     *
     * @param url
     * @return
     */
    public String getHttpData(String url) {
        RequestBody formBody = RequestBody.create(JSON, "");
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
//        Call call = mOkHttpClient.newCall(request);
        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * 处理接口信息post请求(数据格式json传输
     * {"Head": {
     * "Uid": "string",
     * "Lang": "string",
     * "Token": "string"
     * },
     * "Param": {
     * "PageIndex": 0,
     * "PageCount": 0,
     * "PageSize": 0
     * }
     * })
     * 调用
     * HashMap<String,String> map = new HashMap<>();
     * map.put("PageIndex", "0");
     * map.put("PageCount", "0");
     * map.put("PageSize", "0");
     * HttpManager.getHttpDataByTwoLayer("",map,HttpManager.COMMENT_LIST_URL);
     *
     * @return token    用户token（区别用户）
     * map     传参所用的键值对
     * url     请求地址
     */
    public String getHttpDataByTwoLayer(String token, HashMap<String, String> map, String url) {
        Boolean flag = true;      //判断是否只有param一个参数
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    String val = (String) entry.getValue();
                    object2.put(key, val);
                    if (key.equals("Param")){
                        kk.put("Param", val);
                        flag = false;
                    }
                }
            }
            if (flag){
                kk.put("Param", object2);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        Log.d("tag","str-----"+str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
//        mOkHttpClient.newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS);      //设置链接超时
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            } else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * {
     * "Head": {
     * "Uid": "string",
     * "Lang": "string",
     * "Token": "string"
     * },
     * "Param": {
     * "PageInfo": {
     * "PageIndex": 0,
     * "PageCount": 0,
     * "PageSize": 0
     * },
     * "Id": 0,
     * "Type": 0
     * }
     * }
     * 调用 HashMap<String,Object> hashMap = new HashMap<>();
     * HashMap<String,String> map = new HashMap<>();
     * map.put("PageIndex", "0");
     * map.put("PageCount", "0");
     * map.put("PageSize", "0");
     * hashMap.put("PageInfo",map);
     * hashMap.put("Id","0");
     * hashMap.put("Type","0");
     * HttpManager.getHttpDataByThreeLayer("",hashMap,HttpManager.COMMENT_LIST_URL);
     *
     * @param token
     * @param map
     * @param url
     * @return
     */
    public String getHttpDataByThreeLayer(String token, HashMap<String, Object> map, String url) {
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getKey().equals("PageInfo")) {
                        JSONObject object3 = new JSONObject();
                        HashMap<String, String> mapinfo = (HashMap<String, String>) map.get("PageInfo");
                        Iterator iterItem = mapinfo.entrySet().iterator();
                        while (iterItem.hasNext()) {
                            Map.Entry entryThree = (Map.Entry) iterItem.next();
                            String key = (String) entryThree.getKey();
                            String val = (String) entryThree.getValue();
                            object3.put(key, val);
                        }
                        object2.put("PageInfo", object3);
                    } else {
                        String key = (String) entry.getKey();
                        String val = (String) entry.getValue();
                        object2.put(key, val);
                    }
                }
            }
            kk.put("Param", object2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();

        Log.d("tag","str---------"+str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应(同步请求)
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            }else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * 异步请求
         */
//        Call call = mOkHttpClient.newCall(request);
//        //请求加入调度
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("tag", "onFailure: ---" + e.toString());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String info = response.body().string();
//                Log.d("tag", "getHttpData: 2222222--"+info);
//                setHttpInfo(info);
//            }
//        });
        return "";
    }

    /**
     * {
     * "Head": {
     * "Uid": "string",
     * "Lang": "string",
     * "Token": "string"
     * },
     * "Param": {
     * "Id": 0,
     * "Name": "string",
     * "MarketData": [
     * "string"
     * ],
     * "Amount": 0,
     * "TargetReturn": 0
     * }
     * }
     * 调用 HashMap<String,Object> hashMap = new HashMap<>();
     * ArrayList<String> map = new Arraylist<>();
     * map.put("MarketData");
     * hashMap.put("MarketData",map);
     * hashMap.put("Id","0");
     * hashMap.put("Type","0");
     * HttpManager.getHttpDataByThreeLayer("",hashMap,HttpManager.COMMENT_LIST_URL);
     *
     * @param token
     * @param map
     * @param url
     * @return
     */
    public String getHttpDataByThreeLayerArray(String token, HashMap<String, Object> map, String url) {
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getKey().equals("Imgs")){
                        JSONArray object3 = new JSONArray();
                        ArrayList<String> mapinfo = (ArrayList<String>) map.get("Imgs");
                        for (int i = 0; i < mapinfo.size(); i++) {
                            object3.put(i, mapinfo.get(i));
                        }
                        object2.put("Imgs", object3);
                    }else {
                        String key = (String) entry.getKey();
                        String val = (String) entry.getValue();
                        object2.put(key, val);
                    }
                }
            }
            kk.put("Param", object2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应(同步请求)
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            }else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * {
     "Head": {
     "Uid": "string",
     "Lang": "string",
     "Token": "string"
     },
     "Param": {
     "Name": "string",
     "CodeList": [
     {
     "Code": "string",
     "Price": 0,
     "Name": "string",
     "Volume": 0,
     "TradeTime": "2016-08-29T03:10:22.016Z",
     "TradeType": "Open"
     }
     ],
     "Amount": 0,
     "TargetReturn": 0,
     "Desc": "string"
     }
     }
     * @param token
     * @param map
     * @param url
     * @return
     */
    public String getHttpDataByThreeLayerArrayObject(String token, HashMap<String, Object> map, String url) {
        if (TextUtils.isEmpty(token)) {   //token为null请求时会报异常
            token = "";
        }
        JSONObject kk = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Uid", UID);
            object.put("Lang", LANG);
            object.put("Token", token);
            kk.put("Head", object);
            JSONObject object2 = new JSONObject();
            if (map != null) {
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    if (entry.getKey().equals("CodeList")){
                        JSONArray object3 = new JSONArray();

                        ArrayList<HashMap<String,String>> mapinfo = (ArrayList<HashMap<String, String>>) map.get("CodeList");
                        for (int i = 0; i < mapinfo.size(); i++) {
                            Iterator itermap = mapinfo.get(i).entrySet().iterator();
                            JSONObject json = new JSONObject();
                            while (itermap.hasNext()){
                                Map.Entry entryThree = (Map.Entry) itermap.next();
                                String key = (String) entryThree.getKey();
                                String val = (String) entryThree.getValue();
                                json.put(key, val);
                            }
                            object3.put(i, json);
                        }
                        object2.put("CodeList", object3);
                    }else if (entry.getKey().equals("Codes")){
                        JSONArray object3 = new JSONArray();

                        ArrayList<HashMap<String,String>> mapinfo = (ArrayList<HashMap<String, String>>) map.get("Codes");
                        for (int i = 0; i < mapinfo.size(); i++) {
                            Iterator itermap = mapinfo.get(i).entrySet().iterator();
                            JSONObject json = new JSONObject();
                            while (itermap.hasNext()){
                                Map.Entry entryThree = (Map.Entry) itermap.next();
                                String key = (String) entryThree.getKey();
                                String val = (String) entryThree.getValue();
                                json.put(key, val);
                            }
                            object3.put(i, json);
                        }
                        object2.put("Codes", object3);
                    }else {
                        String key = (String) entry.getKey();
                        String val = (String) entry.getValue();
                        object2.put(key, val);
                    }
                }
            }
            kk.put("Param", object2);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String str = kk.toString();

        Log.d("tag","str---调仓--"+str);
        RequestBody formBody = RequestBody.create(JSON, str);
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        //发送请求获取响应(同步请求)
        Response response = null;
        try {
            //请求加入调度
            response = mOkHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                String info = response.body().string();
                Log.d("tag", "getHttpData: 1111111--" + url);
                Log.d("tag", "getHttpData: 2222222--" + info);
                return info;
            }else {
                Log.d("tag", "body-code--" + response.code() + "--string ---" + response.message());
                return FAILED;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
