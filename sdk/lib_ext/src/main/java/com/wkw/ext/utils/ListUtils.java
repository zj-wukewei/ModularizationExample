
package com.wkw.ext.utils;

import java.util.List;

/**
 * Created by hzwukewei on 2016/5/11.
 */
public final class ListUtils {

    /**
     * @param list List
     * @return boolean
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * @param list List
     * @return boolean
     */
    public static boolean isNotEmpty(List list) {
        return !isEmpty(list);
    }

    /**
     * @param one   one
     * @param other other
     * @return 是否相等
     */
    public static boolean equal(List one, List other) {
        if (one == null || other == null) {
            return false;
        }

        if (one.size() != other.size()) {
            return false;
        }

        return one.containsAll(other) && other.containsAll(one);
    }
}
