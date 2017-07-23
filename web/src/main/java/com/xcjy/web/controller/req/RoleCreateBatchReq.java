package com.xcjy.web.controller.req;

import lombok.Data;

import java.util.List;

/**
 * Created by tupeng on 2017/7/23.
 */
@Data
public class RoleCreateBatchReq {

    private List<RoleCreateReq> roleList;

}
