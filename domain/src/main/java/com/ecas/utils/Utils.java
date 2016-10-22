package com.ecas.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Util API for process business domain
 *
 * @author LENOVO
 */
public class Utils {

    public static <T, K> List<T> convert(List<K> list, Converter<T, K> converter) {
        List<T> result = new ArrayList<T>();
        for (K k : list) {
            result.add(converter.convert(k));
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, T parrent, Comparator<T> comparator) {
        List<T> result = new ArrayList<T>();
        for (T t : list) {
            if (comparator.compare(t, parrent)) {
                result.add(t);
            }
        }
        return result;
    }
}
