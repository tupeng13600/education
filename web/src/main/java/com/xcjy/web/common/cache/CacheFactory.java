package com.xcjy.web.common.cache;

import com.xcjy.web.Service.SchoolService;
import com.xcjy.web.bean.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by tupeng on 2017/7/25.
 */
@Component
public class CacheFactory {

    public static Map<String, School> schools;

    @Autowired
    private SchoolService schoolService;

    @PostConstruct
    public void init(){
        List<School> schoolList = schoolService.list();
        schools = schoolList.stream().collect(Collectors.toMap(School::getName, school -> school));
    }

}
