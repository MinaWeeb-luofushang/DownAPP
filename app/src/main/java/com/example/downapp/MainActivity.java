package com.example.downapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.downapp.adapter.HomeAdapter;
import com.example.downapp.entity.RegisterType;

import org.litepal.crud.DataSupport;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private Context context;
    private String userId,userPwd;
    private RecyclerView ryc_view;
    private HomeAdapter homeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPrams();
    }

    private void initPrams(){
        ryc_view = findViewById(R.id.ryc_view);
        StaggeredGridLayoutManager linearLayoutManager= new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        ryc_view.setLayoutManager(linearLayoutManager);

        context = this;
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        userPwd = intent.getStringExtra("userPwd");

        setHomeData();

    }
    //给首页的列表添加数据
    private void setHomeData(){
        List<RegisterType> list  = DataSupport.findAll(RegisterType.class);
        homeAdapter = new HomeAdapter(list);
        ryc_view.setAdapter(homeAdapter);
        homeAdapter.setOnMyClick(new HomeAdapter.ListenOnClick() {
            @Override
            public void myClick(String typeId,String typeName) {
                Toast.makeText(MainActivity.this,"you onclick type id is"+typeId,Toast.LENGTH_LONG).show();
                if (typeId.equals("520")){
                    Intent intent = new Intent(context,MyselfInfo.class);
                    startActivity(intent);
                }else {
                    Intent intent1 = new Intent(context,PlayNew.class);
                    intent1.putExtra("typeId",typeId);
                    intent1.putExtra("typeName",typeName);
                    startActivity(intent1);
                }
            }
        });
    }

    //这里更新数据的地方
    @Override
    protected void onStart() {
        super.onStart();
//        Toast.makeText(this,"这里是OnStar的地方",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        listenUser();
    }

    //判断是否为二次登录的存在
    private void listenUser(){
        SharedPreferences share = getSharedPreferences("userInfo",MODE_PRIVATE);//实例化
        if (share!=null){
            String userId = share.getString("userId","");
            String userPwd = share.getString("userPwd","");
            if (userId==""||userPwd==""){
                System.out.println("这里发现用户没有存下账号密码了");
                finish();
            }else {
                System.out.println("没有问题");
            }

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //清空二次登录
        SharedPreferences share = getSharedPreferences("userInfo",MODE_PRIVATE);//实例化
        SharedPreferences.Editor editor = share.edit();
        editor.putString("userId",null);
        editor.putString("userPwd",null);
        editor.commit();
        finish();
    }
}
