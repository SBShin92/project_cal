package com.github.sbshin92.project_cal.data.dao;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;
import com.github.sbshin92.project_cal.data.vo.UserVO;

/**
 * 사용자 관련 데이터베이스 작업을 처리하는 DAO 인터페이스입니다.
 * MyBatis의 @Mapper 어노테이션을 사용하여 자동으로 구현체를 생성합니다.
 */
@Mapper
public interface UsersDAO {
    
    /**
     * 모든 사용자를 조회합니다.
     * @return 모든 사용자 목록
     */
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
    
    /**
     * 이메일로 사용자를 조회합니다.
     * @param email 조회할 사용자의 이메일
     * @return 조회된 사용자 정보
     */
    @Select("SELECT * FROM users WHERE user_email = #{email}")
    UserVO findByEmail(@Param("email") String email);
    
    /**
     * 이메일과 비밀번호로 사용자를 조회합니다.
     * @param email 조회할 사용자의 이메일
     * @param password 조회할 사용자의 비밀번호
     * @return 조회된 사용자 정보
     */
    @Select("SELECT * FROM users WHERE user_email = #{email} AND user_password = #{password}")
    UserVO findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
    
    /**
     * 새 사용자를 저장합니다.
     * @param userVO 저장할 사용자 정보
     * @return 저장된 사용자의 ID
     */
    @Insert("INSERT INTO users(user_name, user_password, user_email) "
            + "VALUES(#{userName}, #{userPassword}, #{userEmail})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    Integer save(UserVO userVO);
    
    /**
     * 사용자 이름으로 사용자를 조회합니다.
     * @param username 조회할 사용자의 이름
     * @return 조회된 사용자 정보
     */
    @Select("SELECT * FROM users WHERE user_name = #{username}")
    UserVO findByUsername(@Param("username") String username);
}