package com.example.drawer.stockapp.activity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class SendDynamicActivity extends BascActivity implements View.OnClickListener{
    private static int CAMERA_REQUST_CODE = 1;
    private static int GALLERY_REQUST_CODE = 2;
    private static int CROP_REQUST_CODE = 3;
    private String mPictureFile, filePath;
    private EditText mEditTxt;
    private Uri fileUri;//通过此uri得到本地图片,设置为背景
    private String localTempImgFileName = "bankgroup.jpg";
    private String localTempImgDir = "com.stock";
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_dynamic);
        tintManager.setStatusBarTintResource(R.color.write_color);
        token = ShapePreferenceManager.getMySharedPreferences(this).getString(ShapePreferenceManager.TOKEN,null);
        initWight();
    }

    public void initWight(){
        RelativeLayout mTitleRelat = (RelativeLayout) findViewById(R.id.send_dynamic_title);    //title布局
        //设置距离顶部状态栏高度
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                DensityUtils.dp2px(this,50));
        params.setMargins(0, ManagerUtil.getStatusBarHeight(this),0,0);
        mTitleRelat.setLayoutParams(params);

        ImageView mBackImg = (ImageView) findViewById(R.id.back_img);
        mEditTxt = (EditText) findViewById(R.id.edit_txt);     //发表内容
        TextView mSendTxt = (TextView) findViewById(R.id.send_dynamic_txt);
        ImageView mAddImg = (ImageView) findViewById(R.id.add_image);

        mAddImg.setOnClickListener(this);
        mSendTxt.setOnClickListener(this);
        mBackImg.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(this);
        ManagerUtil.setStataBarColor(this,tintManager);
    }

    /**
     * 显示更换背景对话框
     */
    public void showChangeBgDialog() {
        final Dialog dialog = new Dialog(this, R.style.dialog_no_black_border);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LinearLayout dialogLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.pic_select_item_layout, null, false);
        dialog.setContentView(dialogLayout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

        /**
         * 相册获取图片
         */
        dialogLayout.findViewById(R.id.pick_picture_album_btn).setOnClickListener(new View.OnClickListener() {
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
        dialogLayout.findViewById(R.id.pick_picture_camera_btn).setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(SendDynamicActivity.this, "没有找到储存目录", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SendDynamicActivity.this, "没有储存卡", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
//                Picasso.with(SendDynamicActivity.this).load(originalUri).resize(720,720).centerCrop().into(friendGroupBackGroundImageView);
//                sp.edit().putString("filePath", originalUri.toString()).commit();
            }
        }else if (requestCode == CROP_REQUST_CODE) {
            if (data == null) {
                return;
            }
            Bundle bundle = data.getExtras();

            if (bundle != null){
                Bitmap bitMap = bundle.getParcelable("data");
                //将bitmap上传到服务器
//                friendGroupBackGroundImageView.setImageBitmap(bitMap);
            }
        }
    }
    @Override
    public void onConfigurationChanged(Configuration config) {
        super.onConfigurationChanged(config);
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
//        localTempImgFileName = getFileName();//获取文件
        File file = new File(dir, localTempImgFileName);//localTempImgDir和localTempImageFileName是自己定义的名字
        Uri uri = Uri.fromFile(file);
        return uri;
    }

    private Uri getFilePath() {
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM"
                + "/" + localTempImgDir + "/" + localTempImgFileName);
        Uri uri = null;
        try {
            uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(),
                    file.getAbsolutePath(), null, null));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return uri;
    }

    /**
     * 保存bitmap图像到本地文件
     *
     * @param bitMap
     * @return返回一个file类型的uri
     */
    private Uri saveBitMap(Bitmap bitMap) {
        //获取保存到的文件夹路劲
        File rootFile = new File(Environment.getExternalStorageDirectory() + "/com.bruce");
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }

        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");//格式大小写有区别
        String sysDatetime = fmt.format(Calendar.getInstance().getTime());//2016年02月25日  13:23:40
        mPictureFile = sysDatetime + ".jpg";
        filePath = getPhotoPath() + mPictureFile;//获取保存到的文件夹路劲
        //保存的文件file
//        File imageFile = new File(rootFile.getAbsolutePath() + "image.png");
        File imageFile = new File(filePath);
        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            /**
             * 将图像压缩--图像格式--图像压缩质量--输出流
             */
            bitMap.compress(Bitmap.CompressFormat.PNG, 10, fos);
            fos.flush();
            fos.close();
            bitMap.recycle();
            return Uri.fromFile(imageFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.send_dynamic_txt:
                String edit = mEditTxt.getText().toString();
                if (!TextUtils.isEmpty(token)){
                    SendDynmaic(null,token,edit);
                }else {
                    Toast.makeText(this,"你还未登录，请登录后发表",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.add_image:
                showChangeBgDialog();
                break;

        }
    }

    /**
     * 发表动态
     */

    private void SendDynmaic(final ArrayList<String> list, final String token, final String content){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                HashMap<String,Object> hashMap = new HashMap<>();
                if (list != null){
                    ArrayList<String> map = new ArrayList<>();
                    map.addAll(list);
                    hashMap.put("Imgs",map);
                }
                hashMap.put("Content",content);
                String message = HttpManager.newInstance().getHttpDataByThreeLayerArray(token,hashMap,HttpManager.send_dynamic_URL);
                return message;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String str = (String) o;
                if (!TextUtils.isEmpty(str)){
                    try {
                        JSONObject object = new JSONObject(str);
                        if (object.has("Status")){
                            if (object.getString("Status").equals(1)){
                                Toast.makeText(SendDynamicActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(SendDynamicActivity.this,"发表成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.execute();
    }

}
