package com.dizhejiang.teachin.mapper;

import com.dizhejiang.teachin.dto.UserDto;
import com.dizhejiang.teachin.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
public interface UserMapper {
    /**
     * 通过openid查询
     * @param openid
     * @return
     */
   User selectUserByOpenId(@Param("openid") String openid);

    /**
     * 保存
     * @param user
     * @return
     */
   int save(@Param("user") User user);

    /**
     * 通过id查询用户
     * @param id
     * @return
     */
   User selectUserById(@Param("id") Integer id);

   int updateStatus(@Param("status") String status,@Param("userId") Integer userId);

    /**
     * 更改邮箱
     * @param email
     * @param userId
     * @return
     */
   int  updateEmail(@Param("email") String email,@Param("userId") Integer userId);

    /**
     *
     * @return
     */
   int  updatePhone(@Param("dto") UserDto dto);
   int  update(@Param("user") User user);
   User selectModelByCode(@Param("inviteCode") String inviteCode);
}
