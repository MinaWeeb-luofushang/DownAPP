package com.example.downapp.entity;

import org.litepal.crud.DataSupport;
//订单的数据
public class UserOrder extends DataSupport {
    private int orderIndex;
    private String StuId;//学号
    private String ClassNames;//所在班级
    private String StuName;//学生姓名
    private int OrderState;//0为排队中  1为预约成功 -1为已经过期失败
    private String OrderConWhere;//订单的预约地址
    private String OrderConTime;//订单的预约时间
    private String OrderConClassName;//订单的班级

    private String press;

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getStuId() {
        return StuId;
    }

    public void setStuId(String stuId) {
        StuId = stuId;
    }

    public String getClassNames() {
        return ClassNames;
    }

    public void setClassNames(String classNames) {
        ClassNames = classNames;
    }

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String stuName) {
        StuName = stuName;
    }

    public int getOrderState() {
        return OrderState;
    }

    public void setOrderState(int orderState) {
        OrderState = orderState;
    }

    public String getOrderConWhere() {
        return OrderConWhere;
    }

    public void setOrderConWhere(String orderConWhere) {
        OrderConWhere = orderConWhere;
    }

    public String getOrderConTime() {
        return OrderConTime;
    }

    public void setOrderConTime(String orderConTime) {
        OrderConTime = orderConTime;
    }

    public String getOrderConClassName() {
        return OrderConClassName;
    }

    public void setOrderConClassName(String orderConClassName) {
        OrderConClassName = orderConClassName;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}
