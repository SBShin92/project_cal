package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
			+ " FROM users")
	public List<UserVO> findAll();
	
	
	// 필요?
//	public UserVO findByEmail(String email);
	
	// 한가?
//	public UserVO findByEmailAndPassword(String email, String password); //기존코드
	 @Select("SELECT user_id as userId, user_name as userName, user_email as userEmail, user_password as userPassword, "
	            + "user_authority as userAuthority, user_position as userPosition, created_at as createdAt, updated_at as updatedAt "
	            + "FROM users WHERE user_email = #{email}")
	public UserVO findByEmail(@Param("email") String email);
	
//		@Insert("INSERT INTO users(user_name, user_password, user_email) "
//			+ "    VALUES(#{userName}, #{userPassword}, #{userEmail})")
//		@Options(useGeneratedKeys = true, keyProperty = "userId")
//	public Integer save(UserVO userVO);
	  @Insert("INSERT INTO users(user_name, user_password, user_email) "
	            + "VALUES(#{userName}, #{userPassword}, #{userEmail})")
	    @Options(useGeneratedKeys = true, keyProperty = "userId")
	    public Integer save(UserVO userVO);
}