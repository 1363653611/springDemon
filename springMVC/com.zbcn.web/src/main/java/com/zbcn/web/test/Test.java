package com.zbcn.web.test;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        //String str = "﻿主键";

//        List<String> list = new ArrayList<>();
//        String str = "﻿主键";
//        list.add("﻿主键");
//        list.add("wangwu");
//        list.add("11111");
//
//        System.out.println(list.contains(str));
//        System.out.println(ifInclude(list,str));
        test();

    }

    public static boolean ifInclude(List<String> list, String str) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).indexOf(str) != -1) {
                return true;
            }
        }
        return false;
    }

    public static void test(){
        List<String> list = new ArrayList<>();
        String str = "主键";
        list.add("﻿主键");
        list.add("﻿业务期间");
        System.out.println(list.contains(str));
        System.out.println(list.get(0).contains(str));
    }
}
