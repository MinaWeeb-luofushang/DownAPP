package com.example.downapp.entity;

import org.litepal.crud.DataSupport;
//首页list的数据
public class RegisterType extends DataSupport {
    private int id;
    private String typeId;
    private String typeName;
    private String press;


    public RegisterType(String typeId,String typeName,String press){
        this.typeId = typeId;
        this.typeName = typeName;
        this.press = press;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
