package com.example.hk.mycontacts;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.example.hk.adapter.MainAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String DATABASE_PATH ="/data/data/com.example.hk.mycontacts/databases";;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        initView();


    }

    private void initView(){
        lv=(ListView)findViewById(R.id.lv_main);

        initList();
        importDatabase();
    }

    /**
     * 初始化电话类型数据列表
     */
    private void initList() {
        //添加测试数据
        ArrayList<String> data = new ArrayList<String>();
        data.add("本地电话");
        //初始化适配器
        MainAdapter ptAdapter = new MainAdapter(this, data);
        //给列表设置适配器
        lv.setAdapter(ptAdapter);
    }

    /**
     * @description 从raw文件夹中导入随APK发布的数据库
     */
    private void importDatabase() {
        try {
            //创建数据库目录，若数据库目录不存在，创建单层目录
            File dirFile = new File(DATABASE_PATH);
            if(!dirFile.exists()){
                dirFile.mkdir();
            }
            //创建将被导入的数据库File对象
            File file = new File(DATABASE_PATH, "phone.db");
            //判断文件是否存在，如不存在则创建该文件，存在就直接返回
            if (!file.exists()) {
                file.createNewFile();
            } else {
                return;
            }
            //获得自带数据库的输入流
            InputStream ip = getResources().openRawResource(R.raw.phone);
            //创建将被导入的数据库输出流
            FileOutputStream fop = new FileOutputStream(file);
            //创建缓冲区
            byte[] buffer = new byte[1024];
            //将数据读入缓冲区，并写入输出流
            while (ip.read(buffer) != -1) {
                //将缓冲区中的数据写入输出流
                fop.write(buffer);
                //重置缓冲区
                buffer = new byte[1024];
            }
            //关闭输入输出流
            ip.close();
            fop.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

