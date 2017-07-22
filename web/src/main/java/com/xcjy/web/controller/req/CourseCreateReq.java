package com.xcjy.web.controller.req;

import com.xcjy.web.common.enums.CourseType;
import lombok.Data;

import java.util.Date;

/**
 * Created by tupeng on 2017/7/22.
 */
@Data
public class CourseCreateReq {

    private String schoolId;

    private String name;

    private CourseType type;

    private Integer studentNum;

    private Integer price;

}
