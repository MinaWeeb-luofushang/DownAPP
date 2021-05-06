package com.example.downapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.downapp.entity.OrderEntity;
import com.example.downapp.entity.UserInfo;

public class RegisterActivity extends AppCompatActivity {

    EditText et_user_class,et_user_id,et_user_name,et_user_pwd,et_user_pwd_too,et_user_email,et_user_number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initPrams();
    }

    private void initPrams(){
        et_user_class = findViewById(R.id.et_user_class);
        et_user_id = findViewById(R.id.et_user_id);
        et_user_name = findViewById(R.id.et_user_name);
        et_user_pwd = findViewById(R.id.et_user_pwd);
        et_user_pwd_too = findViewById(R.id.et_user_pwd_too);
        et_user_email = findViewById(R.id.et_user_email);
        et_user_number = findViewById(R.id.et_user_number);
    }

    //设置判断和注册添加
    private void addData(){
        if (et_user_class.getText().length()>4 || et_user_id.getText().length()==6  ||
                et_user_name.getText().length()>=2  ||  et_user_pwd.getText().length()>=4  ||
                et_user_pwd_too.getText().length()>=4   || et_user_email.getText().length()>=7  ||
                et_user_number.getText().length()>=9 ){
            if (!et_user_pwd.getText().toString().equals(et_user_pwd_too.getText().toString())){
                Toast.makeText(this,"您的密码不一致，请确认",Toast.LENGTH_LONG).show();
            }else {
                UserInfo user = new UserInfo();
                user.setUserId(et_user_id.getText().toString());
                user.setUserPwd(et_user_pwd.getText().toString());
                user.setUserNumber(et_user_number.getText().toString());
                user.setUserEmail(et_user_email.getText().toString());
                user.setUserName(et_user_name.getText().toString());
                user.setUserClass(et_user_class.getText().toString());
                user.setPress("unKnow");
                user.save();
                Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }else {
            Toast.makeText(this,"您的输入有误，请确认，再提交",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void tv_sent_info(View view){
        addData();
//        Toast.makeText(this,"该模块正在研发中，请等待下个版本，谢谢您的使用",Toast.LENGTH_LONG).show();
    }
}