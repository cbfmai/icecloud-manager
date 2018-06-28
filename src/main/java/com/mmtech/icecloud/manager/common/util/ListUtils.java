package com.mmtech.icecloud.manager.common.util;

import java.util.List;

/**
 * @author Adam DENG
 * @Date 2018/6/15 14:48
 */
public class ListUtils {

    public static boolean isEmptyList(List list) {
        return list == null || list.isEmpty();
    }

    public static boolean isNotEmptyList(List list) {
        return list != null && list.size() > 0;
    }

}
