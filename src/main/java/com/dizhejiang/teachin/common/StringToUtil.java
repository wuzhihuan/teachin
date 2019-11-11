package com.dizhejiang.teachin.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wuqi
 * @Date 2019/10/26
 */
public class StringToUtil {
    public static List<String> getFullNumFromString(String str){
        List<String> resultList = new ArrayList<>();
        StringBuilder numBuilder = new StringBuilder();
        str = str.replaceAll(" ","");
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= 48 && ch <= 57) {
                numBuilder.append(ch);
                if ( i == str.length() -1) {
                    resultList.add(numBuilder.toString());
                }
            } else {
                if (!numBuilder.toString().equals("") && numBuilder.length() > 0) {
                    resultList.add(numBuilder.toString());
                    numBuilder = new StringBuilder();
                }
            }
        }
        return resultList;
    }


}
