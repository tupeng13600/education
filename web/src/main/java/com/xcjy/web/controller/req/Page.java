package com.xcjy.web.controller.req;

import lombok.Data;

/**
 * Created by tupeng on 2017/7/23.
 */
@Data
public class Page {

    private Integer page = 0;

    private Integer pageSize = 10;

    private Integer totalCount = 0;

}
