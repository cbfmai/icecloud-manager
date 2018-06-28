package com.mmtech.icecloud.manager.common;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author Adam DENG
 * @Date 2018/6/15 15:51
 */
public class ShiroMd5Util {

    public static String md5(String password) {
        return new SimpleHash("MD5", password).toHex();
    }
}
