package com.xcjy.upc;

import com.xcjy.upc.filter.UpcAuthFilter;
import com.xcjy.upc.filter.UpcLoginFilter;
import com.xcjy.upc.manager.UpcSecurityManager;
import com.xcjy.upc.service.AuthMessageService;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by tupeng on 2017/7/16.
 */
public class UpcFilterFactoryBean extends ShiroFilterFactoryBean {

    public UpcFilterFactoryBean(UpcSecurityManager upcSecurityManager, AuthMessageService authMessageService, Map<String, String> defineFilterChain) {
        upcSecurityManager.setAuthMessageService(authMessageService);
        setSecurityManager(upcSecurityManager);

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("upcAuth", new UpcAuthFilter(authMessageService));
        filterMap.put("upcLogin", new UpcLoginFilter(authMessageService));
        setFilters(filterMap);
        Map<String, String> definitionMap = new LinkedHashMap<>();
        if (MapUtils.isNotEmpty(defineFilterChain)) {
            definitionMap.putAll(defineFilterChain);
        }
        definitionMap.put("/upc/login", "upcLogin");
        definitionMap.put("/**", "upcAuth");
        setFilterChainDefinitionMap(definitionMap);
    }
}
