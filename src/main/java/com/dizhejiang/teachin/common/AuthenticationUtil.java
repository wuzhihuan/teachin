package com.dizhejiang.teachin.common;


/**
 * @Auther: july
 * @Date: 2019/3/31 20:12
 * @Description:
 */
public class AuthenticationUtil {

    private static ThreadLocal<Integer> loginCache = new ThreadLocal<>();

    /**
     * 获取当前登陆用户信息
     */
    public static Integer getCurrentAccount() {
        return loginCache.get();
    }


    /**
     * 设置登陆信息
     */
    public static void setCurrentAccount(Integer userId) {
        loginCache.set(userId);
    }


    /**
     * 获取userId
     */
    public static Integer getUserId() {
        Integer userId = getCurrentAccount();
     if(userId==null){
         return null;
     }else{
         return userId;
     }

    }


    /**
     * 获取用户名
     */
   /* public static String getUserName() {
        LoginCacheVo loginCache = getCurrentAccount();
        if (loginCache == null || loginCache.getUserVo() == null) {
            return null;
        }
        return loginCache.getUserVo().getUsername();
    }*/


    public static void remove(){
        loginCache.remove();
    }

}
