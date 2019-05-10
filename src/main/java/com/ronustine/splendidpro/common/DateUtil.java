package com.ronustine.splendidpro.common;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 时间工具类
 *
 * @author ronustine
 */
public class DateUtil {

    public static <T,R> List<R> multiGetResult(List<Function<List<T>, R>> functions, List<T> list) {
        return functions.stream().map(f -> f.apply(list)).collect(Collectors.toList());
    }

    public static void main(String[] args) {

    }
}
