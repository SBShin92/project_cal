package com.github.sbshin92.project_cal.service.impl;

import com.github.sbshin92.project_cal.data.dto.UserDTO;
import com.github.sbshin92.project_cal.data.entity.UserEntity;
import com.github.sbshin92.project_cal.data.repository.UserRepository;
import com.github.sbshin92.project_cal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    
    
    @Override
    public Long addUser(UserDTO userDTO) {
        UserEntity userEntity = UserEntity.builder()
                .userId(userDTO.getUserId())
                .userName(userDTO.getUserName())
                .userEmail(userDTO.getUserEmail())
                .userPassword(userDTO.getUserPassword())
                .build();

        userEntity = userRepository.save(userEntity);
        return userEntity.getUserId();
    }



	private UserDTO convertToDTO(UserEntity userEntity) {
        UserDTO userDTO = UserDTO.builder()
        		.userId(userEntity.getUserId())
        		.userName(userEntity.getUserName())
        		.userEmail(userEntity.getUserEmail())
        		.createdAt(userEntity.getCreatedAt())
        		.updatedAt(userEntity.getUpdatedAt())
        		.build();
        // 보안상의 이유로 비밀번호는 DTO에 포함시키지 않습니다.
        return userDTO;
    }
}