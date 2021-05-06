package com.example.downapp.entity;


public class OrderEntity{
    public int orderIndex;
    public String StuId;//学号
    public String ClassNames;//所在班级
    public String StuName;//学生姓名
    public int OrderState;//0为排队中  1为预约成功 -1为已经过期失败
    public String OrderConWhere;//订单的预约地址
    public String OrderConTime;//订单的预约时间
    public String OrderConClassName;//订单的班级


}
