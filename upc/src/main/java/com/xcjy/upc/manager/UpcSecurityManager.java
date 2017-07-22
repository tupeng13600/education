package com.xcjy.upc.manager;

import com.xcjy.upc.realm.UpcRealm;
import com.xcjy.upc.service.AuthMessageService;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

/**
 * Created by 22670 on 2017/7/16.
 */
public class UpcSecurityManager extends DefaultWebSecurityManager {

    private AuthMessageService authMessageService;

    public UpcSecurityManager(AuthMessageService authMessageService) {
        super(new UpcRealm(authMessageService));
    }

    public void setAuthMessageService(AuthMessageService authMessageService) {
        this.authMessageService = authMessageService;
    }
}
