package com.wkw.knowledge.entity;

import java.io.Serializable;

public class User implements Serializable {
    private String user_Name;
    private String user_PassWord;
    private Long user_Id;
    private Long group_Id;
    private Integer user_App_Type;
    private boolean user_Is_Working;
    private String name;
    private String avatar;
    private String group_name;




    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }


    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public String getUser_PassWord() {
        return user_PassWord;
    }

    public void setUser_PassWord(String user_PassWord) {
        this.user_PassWord = user_PassWord;
    }

    public Long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public Long getGroup_Id() {
        return group_Id;
    }

    public void setGroup_Id(Long group_Id) {
        this.group_Id = group_Id;
    }

    public Integer getUser_App_Type() {
        return user_App_Type;
    }

    public void setUser_App_Type(Integer user_App_Type) {
        this.user_App_Type = user_App_Type;
    }

    public boolean isUser_Is_Working() {
        return user_Is_Working;
    }

    public void setUser_Is_Working(boolean user_Is_Working) {
        this.user_Is_Working = user_Is_Working;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
