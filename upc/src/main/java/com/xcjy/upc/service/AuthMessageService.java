package com.xcjy.upc.service;

import com.xcjy.upc.model.UpcLoginSuccessModel;
import com.xcjy.upc.model.UpcUser;

import java.util.Set;

/**
 * Created by tupeng on 2017/7/17.
 */
public interface AuthMessageService {

    UpcUser getUser(String username);

    Set<String> getRole(String username);

    void saveUserMessage(UpcLoginSuccessModel model);

}
