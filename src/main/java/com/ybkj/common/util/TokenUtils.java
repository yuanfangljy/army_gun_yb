package com.ybkj.common.util;

import com.ybkj.common.constant.Constants;

import java.util.UUID;


public class TokenUtils {

    public static String getMemberToken(){
        return Constants.TOKEN_MEMBER+"-"+UUID.randomUUID();
    }

}
