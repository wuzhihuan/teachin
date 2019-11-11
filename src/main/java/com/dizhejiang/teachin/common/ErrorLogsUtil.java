package com.dizhejiang.teachin.common;

import com.dizhejiang.teachin.mapper.TeachinMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.Date;

/**
 * @Author wuqi
 * @Date 2019/11/6
 */
public class ErrorLogsUtil {

    public static void error(Exception e) {
        StackTraceElement stackTraceElement= e.getStackTrace()[0];
        Connection con = null;
        try {
            Class.forName(MySqlConfig.driverClass);
            con =  DriverManager.getConnection(MySqlConfig.url, MySqlConfig.user, MySqlConfig.password);
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        PreparedStatement ps = null;
        String createTime = DateUtil.DateToString(new Date());
        String sql = "INSERT INTO t_error_logs (create_Time,error_Info) VALUES (?, ?)";
        try {
            ps = con.prepareStatement(sql);
            //打印日志，错在第几行
            String errorInfo = e.toString()+",errorMassage:"+stackTraceElement+","+"errorLine:"+stackTraceElement.getLineNumber();
            ps.setString(1, createTime);
            ps.setString(2, errorInfo);
            ps.execute();
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if(ps != null) {
                    ps.close();
                }
                if(con != null) {
                    con.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }
    }
    /**
     *
     *
     *
     *  public static void error(Exception e) {
     *         StackTraceElement stackTraceElement= e.getStackTrace()[0];
     *         Connection con = null;
     *         try {
     *             Class.forName(MySqlConfig.driverClass);
     *             con =  DriverManager.getConnection(MySqlConfig.url, MySqlConfig.user, MySqlConfig.password);
     *         } catch (SQLException e1) {
     *             e1.printStackTrace();
     *         } catch (ClassNotFoundException e1) {
     *             e1.printStackTrace();
     *         }
     *         PreparedStatement ps = null;
     *         String createTime = DateUtil.DateToString(new Date());
     *         String sql = "INSERT INTO t_error_logs (create_Time,error_Info) VALUES (?, ?)";
     *         try {
     *             ps = con.prepareStatement(sql);
     *             //打印日志，错在第几行
     *             String errorInfo = e.toString()+",errorMassage:"+stackTraceElement+","+"errorLine:"+stackTraceElement.getLineNumber();
     *             ps.setString(1, createTime);
     *             ps.setString(2, errorInfo);
     *             ps.execute();
     *         } catch (SQLException e1) {
     *             e1.printStackTrace();
     *         } finally {
     *             try {
     *                 if(ps != null) {
     *                     ps.close();
     *                 }
     *                 if(con != null) {
     *                     con.close();
     *                 }
     *             } catch (SQLException e1) {
     *                 e1.printStackTrace();
     *             }
     *
     *         }
     *     }
     *
     */

}
