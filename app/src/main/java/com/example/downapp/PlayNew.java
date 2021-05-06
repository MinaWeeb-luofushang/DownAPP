package com.example.downapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.downapp.view_util.MarqueeView;

import java.util.Calendar;


public class PlayNew extends AppCompatActivity {

    private MarqueeView mav_show_tip;
    private TextView tv_start_day,et_start_time,tv_title_type,tv_title_type_id;
    private Button btn_send_order;
    private EditText et_explanation,et_end_time,et_class_id,et_stu_name,et_stu_id;
    private Context context;
    private CheckBox cb_agreement;
    private AlertDialog dialog;
    private String showMav = "请填写好你的输入信息，如果系统无法识别您的预约，您的订单将无效处理，" +
            "确定好您预约的场地编号和说明。感谢您的使用，如有其他公告请注意学校的" +
            "官网 http://www.qinghuabeida.cn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_new);
        initPrams();
        listenSentClick();
        setDataTime();
    }

    //ui 绑定
    private void initPrams(){
        context = this;
        mav_show_tip = findViewById(R.id.mav_show_tip);
        tv_start_day = findViewById(R.id.tv_start_day);
        btn_send_order = findViewById(R.id.btn_send_order);
        et_explanation = findViewById(R.id.et_explanation);
        tv_title_type = findViewById(R.id.tv_title_type);
        tv_title_type_id = findViewById(R.id.tv_title_type_id);
        et_end_time = findViewById(R.id.et_end_time);
        et_start_time = findViewById(R.id.et_start_time);
        et_class_id = findViewById(R.id.et_class_id);
        et_stu_name = findViewById(R.id.et_stu_name);
        et_stu_id = findViewById(R.id.et_stu_id);
        cb_agreement = findViewById(R.id.cb_agreement);
        btn_send_order.setBackground(getResources().getDrawable(R.color.colorPrimary));
        btn_send_order.setEnabled(false);

        Intent getType = getIntent();
        String typeId = getType.getStringExtra("typeId");
        String typeName = getType.getStringExtra("typeName");

        tv_title_type.setText(typeName);
        tv_title_type_id.setText("编号:"+typeId);

        mav_show_tip.setText(showMav);
        mav_show_tip.startScroll();

    }

    //弹框日历选择
    private void setDataTime(){
        tv_start_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog1 = new DatePickerDialog(PlayNew.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                Toast.makeText(context,calendar.getTime().toString(),Toast.LENGTH_LONG).show();
                                int setMonth = calendar.get(Calendar.MONTH)+1;
                                tv_start_day.setText("日期:"+calendar.get(Calendar.YEAR)+"."+setMonth+"."+calendar.get(Calendar.DATE));
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                dialog1.show();

            }
        });


        et_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(PlayNew.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        et_start_time.setText("时间:"+hourOfDay+":"+minute);
                    }
                },16 , 30, true).show();
            }
        });
        et_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(PlayNew.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        setTitle(hourOfDay + ":" + minute);
                        et_end_time.setText(hourOfDay+":"+minute);
                    }
                },18 , 30, true).show();
            }
        });
    }

    //查看输入是否符合标准
    private void listenSentClick(){

        btn_send_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_class_id.getText())||
                        TextUtils.isEmpty(et_end_time.getText())||
                        TextUtils.isEmpty(et_start_time.getText())||
                        TextUtils.isEmpty(et_stu_name.getText())||
                        TextUtils.isEmpty(et_explanation.getText())){
                    //测试弹框
                    setOrderSuccess();
                    Toast.makeText(context,"您的输入出错，请检查预约的订单",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(context,"您的输入很完美 预约订单已经发出",Toast.LENGTH_LONG).show();
                    
                }
            }
        });
        cb_agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb_agreement.isChecked()){
                    btn_send_order.setBackground(getResources().getDrawable(R.color.colorAgreement));
                    btn_send_order.setEnabled(true);
                }else {
                    btn_send_order.setBackground(getResources().getDrawable(R.color.colorPrimary));
                    btn_send_order.setEnabled(false);
                }
            }
        });

    }
    //设置预约订单下单成功 提示弹框
    private void setOrderSuccess(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.Transparent);
        dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.activity_send_success_alert);
        Display d = getWindowManager().getDefaultDisplay();
        int h,w,tSp;
        w = (int)(d.getWidth() * 0.8);
        h = (int)(w * 0.626);
        dialog.getWindow().setLayout(w, h);
    }

    public void btn_cancel(View view){
        dialog.dismiss();
//        onBackPressed();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        mav_show_tip.stopScroll();
        finish();

    }
}