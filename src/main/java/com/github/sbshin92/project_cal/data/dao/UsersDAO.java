package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.sbshin92.project_cal.data.vo.UserVO;

@Mapper
public interface UsersDAO {

    @Select("SELECT user_id as userId,"
            + " user_name as userName, "
            + " user_email as userEmail, "
            + " user_password as userPassword, "
            + " user_authority as userAuthority, "
            + " user_position as userPosition, "
            + " created_at as createdAt, "
            + " updated_at as updatedAt "
            + " FROM users WHERE user_email = #{email}")
    UserVO findByEmail(@Param("email") String email);
    
    @Select("SELECT user_id as userId,"
            + " user_name as userName, "
            + " user_email as userEmail, "
            + " user_password as userPassword, "
            + " user_authority as userAuthority, "
            + " user_position as userPosition, "
            + " created_at as createdAt, "
            + " updated_at as updatedAt "
            + " FROM users WHERE user_id = #{userId}")
    UserVO findByUserId(Integer userId);
    
    @Select("SELECT user_id as userId,"
            + " user_name as userName, "
            + " user_email as userEmail, "
            + " user_password as userPassword, "
            + " user_authority as userAuthority, "
            + " user_position as userPosition, "
            + " created_at as createdAt, "
            + " updated_at as updatedAt "
            + " FROM users WHERE user_name = #{userName}")
    UserVO findByUserName(String userName);
    

    @Select("SELECT user_id as userId,"
            + " user_name as userName, "
            + " user_email as userEmail, "
            + " user_password as userPassword, "
            + " user_authority as userAuthority, "
            + " user_position as userPosition, "
            + " created_at as createdAt, "
            + " updated_at as updatedAt "
            + " FROM users")
    List<UserVO> findAll();

    @Insert("INSERT INTO users(user_name, user_password, user_email) "
            + "VALUES(#{userName}, #{userPassword}, #{userEmail})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer save(UserVO userVO);
    
    // 사용자관리-유저 삭제
    @Delete("DELETE FROM users WHERE user_id = #{userId}")
    int deleteByUserId(@Param("userId") Integer userId);
    
    // 사용자관리-유저 수정
    @Update("UPDATE users SET user_name = #{userName}, user_email = #{userEmail}, user_authority = #{userAuthority}, user_position = #{userPosition} WHERE user_id = #{userId}")
    int updateUser(@Param("userId") int userId, @Param("userName") String userName, @Param("userEmail") String userEmail, @Param("userAuthority") String userAuthority, @Param("userPosition") String userPosition);

    @Select("SELECT user_id as userId,"
            + " user_name as userName, "
            + " user_email as userEmail, "
            + " user_password as userPassword, "
            + " user_authority as userAuthority, "
            + " user_position as userPosition, "
            + " created_at as createdAt, "
            + " updated_at as updatedAt "
            + " FROM users WHERE user_id = #{userId}")
    UserVO findById(@Param("userId") int userId);

    @Update("UPDATE users SET "
            + "user_name = #{userName}, "
            + "user_email = #{userEmail}, "
            + "user_authority = #{userAuthority}, "
            + "user_position = #{userPosition}, "
            + "updated_at = CURRENT_TIMESTAMP "
            + "WHERE user_id = #{userId}")
    void update(UserVO user);
    
    // 로그인시 토큰을 위한 메서드
    @Update("UPDATE users SET token = #{token} WHERE user_email = #{email}")
    void updateToken(@Param("email")String email,@Param("token")String token);
    
    // 비밀번호 초기화를 위한 메서드
    @Update("UPDATE users SET password_reset_token = #{token} WHERE user_email = #{email}")
    void updatePasswordResetToken(@Param("email")String email, @Param("token") String token);
    
    @Update("UPDATE users SET user_password = #{password} WHERE password_reset_token = #{token}")
    void updatePasswordByToken(@Param("token")String token, @Param("password")String password);
}