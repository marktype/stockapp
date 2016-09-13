package com.example.drawer.stockapp.activity;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
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
import com.example.drawer.stockapp.customview.MyDialog;
import com.example.drawer.stockapp.htttputil.HttpManager;
import com.example.drawer.stockapp.utils.DensityUtils;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.example.drawer.stockapp.utils.ShapePreferenceManager;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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
    private LinearLayout mLinImg;
    private ArrayList<Bitmap> bitmapList;
    private ImageView mAddImg;
    private String[] image = new String[]{""};
    ArrayList<String> list = new ArrayList<>();
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
        mAddImg = (ImageView) findViewById(R.id.add_image);
        mLinImg = (LinearLayout) findViewById(R.id.publish_mood_add_picture_layout);   //图片加载


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


    private void initAddMorePicture() {
//
        ImageView img = (ImageView) LayoutInflater.from(this).inflate(R.layout.item_add_picture, mLinImg, false);
        if (bitmapList != null && bitmapList.size() > 0) {
            int size = bitmapList.size();
            for (int i = 0; i < size; i++) {
                int childCount = mLinImg.getChildCount();
                if (childCount == 5) {
                    mAddImg.setImageBitmap(bitmapList.get(i));
                    mAddImg.setClickable(false);
                    return;
                }
                img.setImageBitmap(bitmapList.get(i));

            }
            mLinImg.addView(img, size - 1);
        }
    }

    /**
     * 显示更换背景对话框
     */
    public void showChangeBgDialog() {
        bitmapList = new ArrayList<Bitmap>();

        final Dialog dialog = new Dialog(this, R.style.myDialog);
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

            Uri contentUri = data.getData();
            Bitmap bitmap = getBitmapFromUri(contentUri);
            bitmap = reduce(bitmap, 720, 720, true);
            if (!bitmapList.contains(bitmap)) {
                bitmapList.add(bitmap);
            }
            initAddMorePicture();
            sendImageToServer(bitmapList);

        }else if (requestCode == CROP_REQUST_CODE) {
            if (data == null) {
                return;
            }
            Bundle bundle = data.getExtras();

            if (bundle != null){
                Bitmap bitmap = bundle.getParcelable("data");
                if (!bitmapList.contains(bitmap)) {
                    bitmapList.add(bitmap);
                }
                initAddMorePicture();
                sendImageToServer(bitmapList);
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
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
     * 发送图片到服务器
     *
     * @param
     */
    private void sendImageToServer(ArrayList<Bitmap> bitmapList) {

        if (bitmapList != null) {
            int num = bitmapList.size();
            for (int i = 0; i < num; i++) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Bitmap bitmap = bitmapList.get(i);
                bitmap.compress(Bitmap.CompressFormat.PNG, 85, baos);
                //字节数组
                byte[] bytes = baos.toByteArray();
                //通过Base64编码
                byte[] dd = Base64.encode(bytes, Base64.DEFAULT);
                list.add(new String(dd));
//                image[i] = new String(dd);
//                sb.append(image[i]).append(",");
            }

        }
//        mPic = new String(dd);
        //到此已经得到了头像的字节字符串

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

    private MyDialog dialog;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.send_dynamic_txt:
                String edit = mEditTxt.getText().toString();
                if (edit.length()<1){
                    Toast.makeText(this,"你还未输入内容哦",Toast.LENGTH_SHORT).show();
                }else if (!TextUtils.isEmpty(token)){
                    dialog = ManagerUtil.getDiaLog(this);
                    SendDynmaic(list,token,edit);
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
                dialog.dismiss();
                String str = (String) o;
                Log.d("tag","str---------"+str);
                if (!TextUtils.isEmpty(str)){
                    try {
                        JSONObject object = new JSONObject(str);
                        if (object.has("Head")){
                            JSONObject head = object.getJSONObject("Head");
                            if (head.getString("Status").equals("1")){
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
