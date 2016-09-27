package com.example.drawer.stockapp.fragment;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.AllCanUseActivity;
import com.example.drawer.stockapp.activity.AlterNameActivity;
import com.example.drawer.stockapp.activity.AlterPasswordActivity;
import com.example.drawer.stockapp.activity.LoginActivity;
import com.example.drawer.stockapp.customview.CustomDialog;
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.customview.MyReboundScrollView;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.model.UserInfo;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 *
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment implements View.OnClickListener{
        private View mView;
    private PopupWindow mClassifyPop;
    private RelativeLayout mTitleRelat;
    protected SystemBarTintManager tintManager;
    private String token,userId;
    private SharedPreferences sharedPreferences;
    private TextView mSexTxt,scoreTxt,mFansTxt,mAttionTxt,mCollectTxt,mUserName;
    private CircleImageView circleImageView;
    private ImageView mNologin;
    private MyReboundScrollView mScrollview;
    private MyDialog dialog;

    private static int CAMERA_REQUST_CODE = 1;
    private static int GALLERY_REQUST_CODE = 2;
    private static int CROP_REQUST_CODE = 3;
    private Uri fileUri;//通过此uri得到本地图片,设置为背景

    private String localTempImgFileName = "bankgroup.jpg";
    private String localTempImgDir = "com.bruce";
    private UserInfo userInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = ShapePreferenceManager.getMySharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, container, false);
        initWight();

        return mView;
    }



    public void initWight(){
        tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColor(getActivity(),tintManager);


        mTitleRelat = (RelativeLayout) mView.findViewById(R.id.my_info_relat);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(getActivity(),50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(getActivity()),0,0);
        mTitleRelat.setLayoutParams(params);

        circleImageView = (CircleImageView) mView.findViewById(R.id.user_head);      //头像
        mNologin = (ImageView) mView.findViewById(R.id.img_no_login);

        mSexTxt = (TextView) mView.findViewById(R.id.man_sex_txt);     //性别
//        scoreTxt = (TextView) mView.findViewById(R.id.score_txt);     //积分
//        mFansTxt = (TextView) mView.findViewById(R.id.fensi_num_txt);   //粉丝数
//        mAttionTxt = (TextView) mView.findViewById(R.id.foucs_txt);    //关注数
//        mCollectTxt = (TextView) mView.findViewById(R.id.collect_num_txt);   //收藏数
        mUserName = (TextView) mView.findViewById(R.id.user_name);      //用户名

//        TextView mFenSi = (TextView) mView.findViewById(R.id.fensi_num_txt);
//        mFenSi.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格
//        TextView mFoucs = (TextView) mView.findViewById(R.id.foucs_txt);
//        mFoucs.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));
//        TextView mCollect = (TextView) mView.findViewById(R.id.collect_num_txt);
//        mCollect.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));

//        LinearLayout mAttention = (LinearLayout) mView.findViewById(R.id.attention_lin);    //关注
//        RelativeLayout mMyWallet = (RelativeLayout) mView.findViewById(R.id.my_wallet_lin);    //我的钱包
        RelativeLayout mName = (RelativeLayout) mView.findViewById(R.id.user_name_lin);    //修改昵称
        RelativeLayout mSex = (RelativeLayout) mView.findViewById(R.id.sex_lin);      //性别
//        LinearLayout mCollectLin = (LinearLayout) mView.findViewById(R.id.collect_lin);    //收藏
        RelativeLayout mAllCan = (RelativeLayout) mView.findViewById(R.id.all_can_lin);    //通用
        RelativeLayout mBindPhone = (RelativeLayout) mView.findViewById(R.id.bind_phone_lin);  //绑定手机号
        RelativeLayout mAlterPassword = (RelativeLayout) mView.findViewById(R.id.reset_password_lin);  //修改密码
//        RelativeLayout mMyQuan = (RelativeLayout) mView.findViewById(R.id.my_quan_lin);     //优惠券

        mName.setBackgroundColor(getResources().getColor(R.color.write_color));
        mSex.setBackgroundColor(getResources().getColor(R.color.write_color));
        mAllCan.setBackgroundColor(getResources().getColor(R.color.write_color));
        mBindPhone.setBackgroundColor(getResources().getColor(R.color.write_color));
        mAlterPassword.setBackgroundColor(getResources().getColor(R.color.write_color));


        TextView mExit = (TextView) mView.findViewById(R.id.exit_txt);    //退出

        //不设置渐变
//        mTitleRelat.getBackground().setAlpha(1);
//        tintManager.setTintAlpha(0);
        mScrollview = (MyReboundScrollView) mView.findViewById(R.id.my_scrollview);

        mScrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    if (view.getScrollY()>100){
                        mTitleRelat.getBackground().setAlpha(255);
                        tintManager.setTintAlpha(1);
                    }else {
                        //不设置渐变
                        mTitleRelat.getBackground().setAlpha(1);
                        tintManager.setTintAlpha(0);
                    }
                }
                return false;
            }
        });


        mSex.setOnClickListener(this);
        mName.setOnClickListener(this);
//        mMyWallet.setOnClickListener(this);
//        mAttention.setOnClickListener(this);
        circleImageView.setOnClickListener(this);
//        mCollectLin.setOnClickListener(this);
        mAllCan.setOnClickListener(this);
        mExit.setOnClickListener(this);
        mNologin.setOnClickListener(this);
        mBindPhone.setOnClickListener(this);
        mAlterPassword.setOnClickListener(this);
//        mMyQuan.setOnClickListener(this);


        /**
         * type广播
         */
        IntentFilter filter = new IntentFilter();
        // 向过滤器中添加action
        filter.addAction("com.stock.altername");
        // 注册广播
        getContext().registerReceiver(isFlashBroad, filter);


    }

    private Boolean isFlash = false;   //是否刷新页面



    /**
     * 广播接收者
     */
    private BroadcastReceiver isFlashBroad = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            isFlash = true;
        }

    };

    @Override
    public void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColor(getActivity(),tintManager);
        token = sharedPreferences.getString(ShapePreferenceManager.TOKEN,"");
        userId = sharedPreferences.getString(ShapePreferenceManager.USER_ID,"");
        if (!TextUtils.isEmpty(token)){
            if (userInfo == null){
                UserInfoAsyn userInfoAsyn = new UserInfoAsyn();
                userInfoAsyn.execute(userId,token);
            }else if (isFlash){
                UserInfoAsyn userInfoAsyn = new UserInfoAsyn();
                userInfoAsyn.execute(userId,token);
                isFlash = false;
            }else {
                parseUserInfo(userInfo);
            }
            mScrollview.setVisibility(View.VISIBLE);
            mTitleRelat.getBackground().setAlpha(1);
            tintManager.setTintAlpha(0);
        } else {
            mNologin.setVisibility(View.VISIBLE);
            mScrollview.setVisibility(View.GONE);
            mTitleRelat.getBackground().setAlpha(255);
            tintManager.setTintAlpha(1);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.attention_lin:
//                Intent intent = new Intent(getContext(), AttentionActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.my_wallet_lin:
//                intent = new Intent(getContext(), MyWalletActivity.class);
//                startActivity(intent);
//                break;
            case R.id.user_head:
                showChangeBgDialog();
                break;
            case R.id.user_name_lin:
                Intent intent = new Intent(getContext(), AlterNameActivity.class);
                startActivity(intent);
                break;
            case R.id.sex_lin:
                getSexPopWin(view);
                break;
            case R.id.man_txt:
                UpdataUserIfoAsyn asyn = new UpdataUserIfoAsyn();
                asyn.execute("Man",token);
                mSexTxt.setText("男");
                Drawable rightDrawable = getResources().getDrawable(R.mipmap.man);
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                mUserName.setCompoundDrawables(null, null, rightDrawable, null);
                mClassifyPop.dismiss();
                break;
            case R.id.woman_txt:
                asyn = new UpdataUserIfoAsyn();
                asyn.execute("Woman",token);
                mSexTxt.setText("女");
                rightDrawable = getResources().getDrawable(R.mipmap.woman);
                rightDrawable.setBounds(0, 0, rightDrawable.getMinimumWidth(), rightDrawable.getMinimumHeight());
                mUserName.setCompoundDrawables(null, null, rightDrawable, null);
                mClassifyPop.dismiss();
                break;
            case R.id.cancel_txt:
                mClassifyPop.dismiss();
                break;
//            case R.id.collect_lin:
//                intent = new Intent(getContext(), CollectionActivity.class);
//                startActivity(intent);
//                break;
            case R.id.all_can_lin:
                intent = new Intent(getContext(), AllCanUseActivity.class);
                startActivity(intent);
                break;
            case R.id.exit_txt:
                popWinDow();
                break;
            case R.id.img_no_login:
                intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.bind_phone_lin:
                popIsTiaoZhuanQQ();
//                intent = new Intent(getContext(), AlterPhoneActivity.class);
//                startActivity(intent);
//                Toast.makeText(getActivity(),"请联系客服  QQ 2436899110",Toast.LENGTH_LONG).show();
                break;
            case R.id.reset_password_lin:
                intent = new Intent(getContext(), AlterPasswordActivity.class);
                startActivity(intent);
                break;
//            case R.id.my_quan_lin:
//                intent = new Intent(getContext(), DiYongQuanActivity.class);
//                startActivity(intent);
//                break;
        }
    }
    /**
     * 退出弹框
     */
    public void popWinDow(){
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setMessageText("确认要退出登录吗？");
        dialog.show();
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().clear().commit();   //清除缓存sp数据
                if (userInfo != null){
                    userInfo = null;
                }
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void popIsTiaoZhuanQQ(){
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setMessageText("修改手机号比较麻烦，请联系爱猫爪客服 QQ 2436899110，点击确认前往");
        dialog.show();
        dialog.setOnPositiveListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url11 = "mqqwpa://im/chat?chat_type=wpa&uin=2436899110&version=1";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url11)));
                dialog.dismiss();
            }
        });
        dialog.setOnNegativeListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }


    /**
     * 性别选择弹框
     */
    public void getSexPopWin(View view){
        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.sex_selcet_layout, null);
        /**
         * 如果pop是null就执行这个方法
         */
        if (mClassifyPop == null) {
            mClassifyPop = new PopupWindow(contentView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            //        实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            //设置SelectPicPopupWindow弹出窗体的背景
            mClassifyPop.setBackgroundDrawable(dw);
            mClassifyPop.setOutsideTouchable(true);
            mClassifyPop.setAnimationStyle(R.style.mypopwindow_anim_style);

            TextView mMan = (TextView) contentView.findViewById(R.id.man_txt);
            TextView mWoman = (TextView) contentView.findViewById(R.id.woman_txt);
            TextView cancel = (TextView) contentView.findViewById(R.id.cancel_txt);
            mWoman.setOnClickListener(this);
            cancel.setOnClickListener(this);
            mMan.setOnClickListener(this);
        }
        //产生背景变暗效果
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.8f;
        getActivity().getWindow().setAttributes(lp);
        mClassifyPop.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
        mClassifyPop.setFocusable(true);
        /**
         * 显示就消失
         */
        if (mClassifyPop.isShowing()) {
            mClassifyPop.dismiss();
        } else {
            mClassifyPop.showAtLocation(view, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置
        }
    }

    /**
     * 获取用户信息
     */
    private class UserInfoAsyn extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Id", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[1],map,HttpManager.USERINFO_URL);
            return message;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
            if (!TextUtils.isEmpty(message)&&message.length()>10){
                Gson gson = new Gson();
                userInfo = gson.fromJson(message,UserInfo.class);   //获取用户信息
                parseUserInfo(userInfo);
            }else {
                Toast.makeText(getContext(),"获取信息失败,请重新登录",Toast.LENGTH_SHORT).show();
                mNologin.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 跟新用户信息
     */
    private class UpdataUserIfoAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Sex", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[1],map,HttpManager.UpdataUser_URL);
            return message;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String message = s;
        }
    }

    /**
     * 解析用户数据
     */
    public void parseUserInfo(UserInfo userInfo){
        if (userInfo.getHead().getStatus() == 0){
//            scoreTxt.setText(userInfo.getResult().getScore()+"");
//            mAttionTxt.setText(userInfo.getResult().getFollower()+"");
//            mFansTxt.setText(userInfo.getResult().getFans()+"");
            if (userInfo.getResult().getNickName() != null&&!TextUtils.isEmpty(userInfo.getResult().getNickName()+"")){
                mUserName.setText(userInfo.getResult().getNickName()+"");
            }
            if (userInfo.getResult().getAvatar() != null&&!TextUtils.isEmpty(userInfo.getResult().getAvatar()+"")){
                Picasso.with(getActivity()).load(userInfo.getResult().getAvatar()+"").into(circleImageView);
            }else {
                Picasso.with(getActivity()).load(R.mipmap.img_place).into(circleImageView);
            }
            mNologin.setVisibility(View.GONE);
        }else {
//            Toast.makeText(getContext(),"获取信息失败,请重新登录",Toast.LENGTH_SHORT).show();
            mNologin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 修改用户头像
     */
    private class UpdateAvterAsyn extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> map = new HashMap<>();
            map.put("Avatar", strings[0]);
            String message = HttpManager.newInstance().getHttpDataByTwoLayer(strings[1],map,HttpManager.UpdateAvatar_URL);
            return message;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            dialog.dismiss();
            UserInfoAsyn userInfoAsyn = new UserInfoAsyn();
            userInfoAsyn.execute(userId,token);
        }
    }

    /**
     * 发送图片到服务器
     *
     * @param bitmap
     */
    private void sendImageToServer(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 85, baos);
        //字节数组
        byte[] bytes = baos.toByteArray();
        //通过Base64编码
        byte[] dd = Base64.encode(bytes, Base64.DEFAULT);
        String mm = new String(dd);
        //到此已经得到了头像的字节字符串
        dialog = ManagerUtil.getDiaLog(getActivity());
        UpdateAvterAsyn updateAvterAsyn = new UpdateAvterAsyn();
        updateAvterAsyn.execute(mm,token);
    }
    /**
     * 显示更换背景对话框
     */
    public void showChangeBgDialog() {
        final Dialog dialog = new Dialog(getContext(), R.style.myDialogFragment);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout dialogLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.pic_select_item_layout, null, false);
        dialog.setContentView(dialogLayout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        TextView picOne = (TextView) dialogLayout.findViewById(R.id.pick_picture_album_btn);
        TextView picTwo = (TextView) dialogLayout.findViewById(R.id.pick_picture_camera_btn);

        picOne.setBackgroundColor(getResources().getColor(R.color.write_color));
        picTwo.setBackgroundColor(getResources().getColor(R.color.write_color));
        /**
         * 相册获取图片
         */
        picOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUST_CODE);
                dialog.dismiss();
            }
        });
        /**
         * 拍照获取图片
         */
        picTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = Environment.getExternalStorageState();
                if (status.equals(Environment.MEDIA_MOUNTED)) {
                    try {
                        Uri uri = setSaveUri();
                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent, CAMERA_REQUST_CODE);
                        dialog.dismiss();
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getContext(), "没有找到储存目录", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "没有储存卡", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //如果返回码是照相机返回码,就进行以下处理
        if (requestCode == CAMERA_REQUST_CODE) {

            fileUri = getFilePath();    //保存uri
            startImageZoom(fileUri);

            //如果返回码是相册,就进行处理
        } else if (requestCode == GALLERY_REQUST_CODE) {

            if (data == null) {
                return;
            } else {
                Uri originalUri = data.getData();        //获得图片的uri

                Bitmap bitmap = getBitmapFromUri(originalUri);
                bitmap = reduce(bitmap, 720, 720, true);
                sendImageToServer(bitmap);
            }
        }else if (requestCode == CROP_REQUST_CODE) {
            if (data == null) {
                return;
            }
            Bundle bundle = data.getExtras();

            if (bundle != null){
                Bitmap bitMap = bundle.getParcelable("data");
                //将bitmap上传到服务器
                sendImageToServer(bitMap);
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
    }

    /**
     * 获取uri所在图片位置
     * @param uri
     * @return
     */
    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 压缩图片
     *
     * @param bitmap   源图片
     * @param width    想要的宽度
     * @param height   想要的高度
     * @param isAdjust 是否自动调整尺寸, true图片就不会拉伸，false严格按照你的尺寸压缩
     * @return Bitmap
     */
    public Bitmap reduce(Bitmap bitmap, int width, int height, boolean isAdjust) {
        // 如果想要的宽度和高度都比源图片小，就不压缩了，直接返回原图
        if (bitmap.getWidth() < width && bitmap.getHeight() < height) {
            return bitmap;
        }
        // 根据想要的尺寸精确计算压缩比例, 方法详解：public BigDecimal divide(BigDecimal divisor, int scale, int roundingMode);
        // scale表示要保留的小数位, roundingMode表示如何处理多余的小数位，BigDecimal.ROUND_DOWN表示自动舍弃
        float sx = new BigDecimal(width).divide(new BigDecimal(bitmap.getWidth()), 4, BigDecimal.ROUND_DOWN).floatValue();
        float sy = new BigDecimal(height).divide(new BigDecimal(bitmap.getHeight()), 4, BigDecimal.ROUND_DOWN).floatValue();
        if (isAdjust) {// 如果想自动调整比例，不至于图片会拉伸
            sx = (sx < sy ? sx : sy);
            sy = sx;// 哪个比例小一点，就用哪个比例
        }
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy);// 调用api中的方法进行压缩，就大功告成了
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
    /**
     * 启动图片裁剪界面
     *
     * @param uri
     */
    private void startImageZoom(Uri uri) {
        Intent imageZoomIntent = new Intent("com.android.camera.action.CROP");
        imageZoomIntent.setDataAndType(uri, "image/*");
        imageZoomIntent.putExtra("crop", "true");    //出现裁剪页面
        imageZoomIntent.putExtra("aspectX", 1);     //裁剪比例
        imageZoomIntent.putExtra("aspectY", 1);
        imageZoomIntent.putExtra("outputX", 300);    //显示宽高,清晰度,不能太高，容易报错
        imageZoomIntent.putExtra("outputY", 300);
        imageZoomIntent.putExtra("return-data", true);
        imageZoomIntent.putExtra("scale", true);
        imageZoomIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//        imageZoomIntent.putExtra("return-data", false);
        startActivityForResult(imageZoomIntent, CROP_REQUST_CODE);
    }

    public String getPhotoPath() {
        return Environment.getExternalStorageDirectory() + "/DCIM/";
    }


    private Uri setSaveUri() {
        //获取保存到的文件夹路劲
        File dir = new File(Environment.getExternalStorageDirectory() + "/DCIM" + "/" + localTempImgDir);
        if (!dir.exists())
            dir.mkdirs();
        localTempImgFileName = getFileName();//获取文件
        File file = new File(dir, localTempImgFileName);//localTempImgDir和localTempImageFileName是自己定义的名字
        Uri uri = Uri.fromFile(file);
        return uri;
    }

    private String getFileName() {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");//格式大小写有区别
        String sysDatetime = fmt.format(Calendar.getInstance().getTime());//2016年02月25日  13:23:40
        localTempImgFileName = sysDatetime + ".jpg";
        return localTempImgFileName;
    }

    private Uri getFilePath() {
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM"
                + "/" + localTempImgDir + "/" + localTempImgFileName);
        Uri uri = null;
        try {
            uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),
                    file.getAbsolutePath(), null, null));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return uri;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (userInfo != null){
            userInfo = null;
        }
        if (isFlashBroad != null){   //注销广播
            getContext().unregisterReceiver(isFlashBroad);
        }
    }
}
