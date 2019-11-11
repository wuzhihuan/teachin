package com.dizhejiang.teachin.common;

import com.alibaba.fastjson.JSON;

public class SyseUtil {

	public static void systemErrOutJson(Object obj) {
			System.out.println("********************参数输出*******************");
			System.err.println(JSON.toJSONString(obj));
	}
}
