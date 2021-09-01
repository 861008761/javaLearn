package com.mylovin.netty.rpc.util;

import com.mylovin.netty.rpc.pojo.Request;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xbmeng
 * @date 1/9/2019 15:56
 */
public class ProtoBufUtil {

    public static void main(String[] args) {
        List<Class> classes = new ArrayList<>();
        classes.add(Request.class);
        java2proto(classes);
    }

    public static void java2proto(List<Class> classes) {
        StringBuilder b = new StringBuilder();
        for (Class aClass : classes) {
            Field[] fields = aClass.getDeclaredFields();
            b.append("message ").append("Grpc").append(aClass.getSimpleName()).append("{").append("\n");
            for (int i = 1; i <= fields.length; i++) {
                Field field = fields[i - 1];
                String name = field.getName();
                String type = field.getType().getName();
                b.append("    ");
                if ("java.lang.String".equalsIgnoreCase(type)) {
                    b.append("string");
                } else if ("java.lang.Double".equalsIgnoreCase(type)) {
                    b.append("double");
                } else if ("java.util.Date".equalsIgnoreCase(type)) {
                    b.append("string");
                } else if ("java.lang.Long".equalsIgnoreCase(type)) {
                    b.append("int64");
                } else if ("java.lang.Integer".equalsIgnoreCase(type)) {
                    b.append("int32");
                } else {
                    throw new RuntimeException(type + " 没处理");
                }
                b.append(" ").append(name).append(" ").append("=").append(" ").append(i).append(";");
                b.append("\n");
            }
            b.append("}");
            b.append("\n");
        }
        System.out.println(b);
    }
}

