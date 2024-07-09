package com.github.sbshin92.project_cal.data.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.github.sbshin92.project_cal.data.vo.UserVO;

@Mapper
public interface UsersDAO {
	
	@Select("SELECT * "
			+ "FROM users")
	public List<UserVO> findAll();
	
	
//	@Select("SELECT no, name, email, gender\r\n"
//			+ "    FROM users\r\n"
//			+ "    WHERE email=#{value}")
	public UserVO findByEmail(String email);
	
//	@Select("SELECT no, name, email, gender\r\n"
//			+ "    FROM users\r\n"
//			+ "    WHERE email = #{email} AND password = #{password}")
	public UserVO findByEmailAndPassword(String email, String password);

	
		@Insert("INSERT INTO users(user_name, user_password, user_email) "
			+ "    VALUES(#{userName}, #{userPassword}, #{userEmail})")
		@Options(useGeneratedKeys = true, keyProperty = "userId")
	public Integer save(UserVO userVO);
	
}