package com.example.downapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.downapp.adapter.OrderAdapter;
import com.example.downapp.entity.OrderEntity;

import java.util.ArrayList;
import java.util.List;

public class MyselfInfo extends AppCompatActivity {

    private ListView lv_order;
    private OrderAdapter adapter;
    private OrderEntity entity;
    private List<OrderEntity> list;
    private Context context;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myself_info);
        context = this;
        initPrams();
        setListView();
        ListenOrderClick();
    }

    private void initPrams(){
        lv_order = findViewById(R.id.lv_order);
    }

    private void setListView(){
        if (list==null){
            list = new ArrayList<>();
            for (int i= 0;i<=19;i++){
                entity = new OrderEntity();
                entity.orderIndex = i;
                entity.StuId = "12138"+i;
                //设置状态
                if (i%2==0){
                    entity.OrderState = 0;
                }else if(i%3==0){
                    entity.OrderState = 1;
                }else {
                    entity.OrderState = -1;
                }

                entity.ClassNames = "物理学院3年级2班";
                entity.OrderConClassName = "物理学院3年级2班";
                entity.StuName = "州基隆";
                entity.OrderConWhere = "足球场地："+i+"号场";
                entity.OrderConTime = "本月16号下午16点到19点";
                list.add(entity);
            }

            adapter = new OrderAdapter(this,list);
            lv_order.setAdapter(adapter);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public void ListenOrderClick(){
        if (adapter!=null){
            //取消预约 删除订单 提示是否确认删除
            adapter.setListenCancelBtn(new OrderAdapter.ListenCancelBtn() {
                @Override
                public void CancelClick(OrderEntity orderEntity) {
                    removeOrder = orderEntity;
                    alertState = 0;
                    setClickShowAlert("订单编号"+orderEntity.orderIndex+"预约取消--系统提示","请您确认取消您的预约订单，或者返回点击取消按钮");

                }
            });
            //修改预约订单 但是需要提示用户 会重新排队的弹框
            adapter.setListenUpdateTv(new OrderAdapter.ListenUpdateTv() {
                @Override
                public void UpdateClick(OrderEntity orderEntity) {
                    removeOrder = orderEntity;
                    alertState = 1;
                    setClickShowAlert(orderEntity.orderIndex+"预约修改--系统提示","您确认要修改您的订单么，" +
                            "因为这样可能让您重新排队，请您慎重选择,系统会直接删除，然后调转到大厅页面");

                }
            });
        }
    }
    private OrderEntity removeOrder;
    private int alertState;//0是直接删除不用跳转到下订单界面 1是需要跳转
    //设置预约订单下单成功 提示弹框
    private void setClickShowAlert(String titleTx,String conTx){
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.Transparent);
        dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.activity_send_success_alert);
        Display d = getWindowManager().getDefaultDisplay();
        TextView tv_alter_title = dialog.findViewById(R.id.tv_alter_title);
        TextView tv_con_text = dialog.findViewById(R.id.tv_con_text);
        Button btn_back = dialog.findViewById(R.id.btn_back);
        btn_back.setVisibility(View.VISIBLE);
        tv_alter_title.setText(titleTx);
        tv_con_text.setText(conTx);

        int h,w,tSp;
        w = (int)(d.getWidth() * 0.8);
        h = (int)(w * 0.626);
        dialog.getWindow().setLayout(w, h);
    }
    public void btn_back(View view){
        dialog.dismiss();
    }
    public void btn_cancel(View view){
        //判断时修改还是删除预约 修改提示确认之后 关闭弹框 再打开心的弹框 修改订单
        //如果时删除就做删除的动作 然后关闭弹框
        dialog.dismiss();
        list.remove(removeOrder);
        lv_order.setAdapter(adapter);
        if (alertState==1){
            onBackPressed();
        }
    }


    public void tv_cancel_login(View view){
        //清空二次登录
        SharedPreferences share = getSharedPreferences("userInfo",MODE_PRIVATE);//实例化
        SharedPreferences.Editor editor = share.edit();
        editor.putString("userId",null);
        editor.putString("userPwd",null);
        editor.commit();
        finish();
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    public void tv_new_register(View view){
        //清空二次登录
        SharedPreferences share = getSharedPreferences("userInfo",MODE_PRIVATE);//实例化
        SharedPreferences.Editor editor = share.edit();
        editor.putString("userId",null);
        editor.putString("userPwd",null);
        editor.commit();
        finish();
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }
}