package com.xcjy.web.controller.req;

import lombok.Data;

import java.util.Date;

/**
 * Created by tupeng on 2017/7/22.
 */
@Data
public class EmployeeUpdateReq {

    private String id;

    private String roleIds;

    private String name;

    private String sex;

    private Date birthday;

    private String idCard;

    private String education;

    private String graduationSchool;

    private String specialty;

    private String phone;

    private String email;

    private String clamantName;

    private String clamantPhone;

    private String address;

    private String remark;

}
