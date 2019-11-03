package com.mydemo.toadvanced.file;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.mydemo.toadvanced.BaseActivity;
import com.mydemo.toadvanced.R;
import com.mydemo.toadvanced.utils.ThreadPools;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FileOptionsActivity extends BaseActivity {

    @BindView(R.id.acbtnOptions)
    AppCompatButton acbtnOptions;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.tvNewResult)
    TextView tvNewResult;

    int permissionCode = -1;
    String permission = Manifest.permission.WRITE_EXTERNAL_STORAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_options);
        ButterKnife.bind(this);
        permissionCode = ContextCompat.checkSelfPermission(this, permission);
        if (permissionCode != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{permission}, 0x987);
        }
    }

    @OnClick(R.id.acbtnOptions)
    public void onViewClicked() {
        permissionCode = ContextCompat.checkSelfPermission(this, permission);
        if (permissionCode == PackageManager.PERMISSION_GRANTED){
            ThreadPools.getSingleThreadPool(new Runnable() {
                @Override
                public void run() {
                    writeContent();
                }
            });
        }
    }

    private void writeContent(){
        String  timeStamp = FileUtils.transferLongToDate("yyyy-MM-dd hh:mm:ss", System.currentTimeMillis());
        String strcontent = timeStamp + "：" + tvContent.getText().toString();
        String filePath = "/sdcard/SmartPushMessage/";
        String fileName = "PushMessage.txt";

        String pathName = filePath + fileName;
        File file = new File(pathName);
        if (file.exists() && "txt".equals(file.getName())) {
            String oldContent = FileUtils.getFileContent(file);
            if (TextUtils.isEmpty(oldContent)){
                FileUtils.writeTxtToFile(strcontent, filePath, fileName);
            }else {
                String newContent = oldContent + "\n" + strcontent;
                FileUtils.writeTxtToFile(newContent, filePath, fileName);
            }
        }else {
            FileUtils.writeTxtToFile(strcontent, filePath, fileName);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != 0x987){
            return;
        }
        if (null == grantResults || grantResults.length <= 0){
            return;
        }
        writeContent();
    }
}
