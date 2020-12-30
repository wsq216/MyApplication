package com.example.shopping_android_app.ui.my;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.shopping_android_app.R;
import com.example.shopping_android_app.app.Constants;
import com.example.shopping_android_app.interfaces.me.IUser;
import com.example.shopping_android_app.model.home.me.UserInfoBean;
import com.example.shopping_android_app.presenter.home.UserPresenter;
import com.example.shopping_android_app.utils.BitmapUtils;
import com.example.shopping_android_app.utils.GlideEngine;
import com.example.shopping_android_app.utils.SpUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserDetailActivity extends AppCompatActivity implements IUser.View {

    @BindView(R.id.iv_return)
    ImageView ivReturn;
    @BindView(R.id.tv_tool_title)
    TextView tvToolTitle;
    @BindView(R.id.iv_more)
    ImageView ivMore;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.img_avatar)
    ImageView imgAvatar;
    @BindView(R.id.layout_avatar)
    ConstraintLayout layoutAvatar;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.layout_nickname)
    ConstraintLayout layoutNickname;
    private Unbinder unbinder;

    private OSS ossClient;
    String bucketName = "2002a";
    String ossPoint = "http://oss-cn-beijing.aliyuncs.com";

    String key = "LTAI4GH6Gy8tFbbXJ3vatsAn";  //appkey
    String secret = "YjWyqsTnHX8336jRZ1vg7FSWaojPf8";  //密码
    private IUser.Presenter presenter;


    @Override
    protected void onStart() {
        super.onStart();
        String username = SpUtils.getInstance().getString("username");
        if (username!=null){
            Glide.with(this).load(username).apply(new RequestOptions().circleCrop()).into(imgAvatar);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        unbinder = ButterKnife.bind(this);

        initData();
        initOss();
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhoto();
            }
        });
    }

    private void initData() {
        presenter = new UserPresenter();
    }

    private void openPhoto() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .loadImageEngine(GlideEngine.createGlideEngine()) // Please refer to the Demo GlideEngine.java
                .maxSelectNum(1)
                .imageSpanCount(4)
                .selectionMode(PictureConfig.MULTIPLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private void initOss(){
        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(key, secret, "");
        // 配置类如果不设置，会有默认配置。
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
        conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。
        ossClient = new OSSClient(getApplicationContext(), ossPoint, credentialProvider);
    }
    /**
     * oss上传
     * @param path
     */
    private void uploadHead(String path){

        String fileName = path.substring(path.lastIndexOf("/")+1,path.length());
        PutObjectRequest put = new PutObjectRequest(bucketName, fileName, path);
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                //上次进度
                Log.i("oss_upload",currentSize+"/"+totalSize);
                // 进度百分比的计算
                // int p = (int) (currentSize/totalSize*100);
                if(currentSize == totalSize){
                    //完成
                    String headUrl = request.getUploadFilePath();
                    //
                    Log.i("HeadUrl",headUrl);
                    //request.getUploadFilePath()
                }

            }
        });
        OSSAsyncTask task = ossClient.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
                //成功的回调中读取相关的上传文件的信息  生成一个url地址
                String url = ossClient.presignPublicObjectURL(request.getBucketName(),request.getObjectKey());
                //调用服务器接口 把url上传到服务器的接口
                SpUtils.getInstance().setValue("username",url);

                updateHead(url);
//                Map<String,String> map = new HashMap<>();
//                map.put("avatar",url);
//                presenter.updateUserInfo(map);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 188:
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                if (selectList.size() == 0) return;
                //获取本地图片的选择地址，上传到服务器
                //头像的压缩和二次采样
                //把选中的图片插入到列表
                try {
                    Bitmap scaleBitmp = BitmapUtils.getBitmap(selectList.get(0).getPath(), Constants.HEAD_WIDTH,Constants.HEAD_HEIGHT);
                    Uri uri=Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), scaleBitmp, null,null));
                    //uri转字符串
                    String path = getRealPathFromUri(UserDetailActivity.this, uri);
                    uploadHead(path);
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            default:
                break;
        }
    }


    //uri转字符串的方法
    public static String getRealPathFromUri(UserDetailActivity context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    private void updateHead(String url){
        imgAvatar.post(new Runnable() {
            @Override
            public void run() {
                Glide.with(UserDetailActivity.this).load(url).apply(new RequestOptions().circleCrop()).into(imgAvatar);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void updateUserInfoReturn(UserInfoBean result) {
        if(result.getErrno() == 0){
            Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }
}