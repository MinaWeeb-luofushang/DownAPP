package com.example.downapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.downapp.entity.RegisterType;
import com.example.downapp.entity.UserInfo;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private Context context;
    private EditText et_uer_id,et_uer_pwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initPrams();
        getBeforeUser();
    }


    private void getBeforeUser(){
        SharedPreferences share = getSharedPreferences("userInfo",MODE_PRIVATE);//实例化
        if (share!=null){
            String userId = share.getString("userId","");
            String userPwd = share.getString("userPwd","");
            if (userId!=""||userPwd!=""){
                selectUser(userId,userPwd);
            }

        }


    }

    private void initPrams(){
        context = this;
        et_uer_id = findViewById(R.id.et_uer_id);
        et_uer_pwd = findViewById(R.id.et_uer_pwd);
        List<RegisterType> list  = DataSupport.findAll(RegisterType.class);
        if (list.size()<=0){
            new RegisterType("001","图书馆座位号","UnKnow").save();
            new RegisterType("091","食堂座位号","UnKnow").save();
            new RegisterType("002","田径场赛道场地","UnKnow").save();
            new RegisterType("003","池塘划船场地","UnKnow").save();
            new RegisterType("004","室内桌球场地","UnKnow").save();
            new RegisterType("005","气排球场地","UnKnow").save();
            new RegisterType("006","乒乓球场地","UnKnow").save();
            new RegisterType("012","羽毛球场地","UnKnow").save();
            new RegisterType("031","足球场地","UnKnow").save();
            new RegisterType("051","篮球场地","UnKnow").save();
            new RegisterType("121","教室座位","UnKnow").save();
            new RegisterType("520","我的订单","UnKnow").save();
        }

    }

    public void tv_login(View view){
        String id = et_uer_id.getText().toString();
        String pwd = et_uer_pwd.getText().toString();

        if (id.length()>3||pwd.length()>=4){
            selectUser(id,pwd);
        }else {
            Toast.makeText(LoginActivity.this,"您的输入不能为空",Toast.LENGTH_SHORT).show();
        }
    }

    private void selectUser(String userId,String userPwd){
        List<UserInfo> data = DataSupport.where("userId = ? and userPwd = ?",userId,userPwd)
                .find(UserInfo.class);
        if (data.size()>0){
            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();

            //设置二次登录
            SharedPreferences share = getSharedPreferences("userInfo",MODE_PRIVATE);//实例化
            SharedPreferences.Editor editor = share.edit();
            editor.putString("userId",userId);
            editor.putString("userPwd",userPwd);
            editor.commit();

            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("userId",userId);
            intent.putExtra("userPwd",userPwd);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(LoginActivity.this,"密码或账号输入错误，请从新输入",Toast.LENGTH_SHORT).show();
        }
    }

    public void tv_register(View view){
        Intent intent = new Intent(context,RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}