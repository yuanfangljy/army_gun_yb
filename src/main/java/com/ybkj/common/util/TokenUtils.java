package com.ybkj.common.util;

import com.ybkj.common.constant.Constants;

import java.util.UUID;


public class TokenUtils {

    public static String getMemberToken(){
        return Constants.TOKEN_MEMBER+"-"+UUID.randomUUID();
    }

    /*16 bytes digest for track channel session*/
    public static String channelSessionDigest() {

        return String.format("%016x", System.currentTimeMillis());
    }

    public static void main(String[] args) {
        System.out.println(String.format("%016x", System.currentTimeMillis()));
    }
}
