package com.xcjy.web.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private String id;

    private String schoolId;

    private String name;

    private String idCard;

    private String sex;

    private String orignSchool;

    private String grade;

    private Date birthday;

    private String subject;

    private String source;

    private String phone;

    private String parentName;

    private String parentSex;

    private String parentIdCard;

    private String parentPhone;

    private String address;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Boolean deleted;

}