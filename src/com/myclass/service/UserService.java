package com.myclass.service;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

public class UserService {
	private UserRepository userRepository;

	public UserService() {
		userRepository = new UserRepository();
	}

	public List<UserDto> getAllUsers() {
		List<UserDto> dtos = new ArrayList<UserDto>();
		List<User> users = userRepository.getAllUsers();
		System.out.println("[DEBUG_UserService]");
		for (User user : users) {
			UserDto dto = new UserDto();
			dto.setUserId(user.getUserId());
			dto.setFullname(user.getFullname());
			dto.setAvatar(user.getAvatar());
			dto.setEmail(user.getEmail());
			dto.setRoleId(user.getRoleId());
			dtos.add(dto);
		}
		return dtos;
	}

	public List<UserDto> getAllWithRoleDescription() {
		return userRepository.getAllWithRoleDescription();
	}

//	public UserDto getUserByEmail(String email) {
//		UserDto dto = null;
//		User user = userRepository.getByEmail(email);
//		if (user != null) {
//			dto = new UserDto();
//			dto.setUserId(user.getUserId());
//			dto.setFullname(user.getFullname());
//			dto.setAvatar(user.getAvatar());
//			dto.setEmail(user.getEmail());
//			dto.setRoleId(user.getRoleId());
//		}
//		return dto;
//	}
	
	public UserDto getUserById(int id) {
		UserDto dto = null;
		User user = userRepository.getById(id);
		if (user != null) {
			dto = new UserDto();
			dto.setUserId(user.getUserId());
			dto.setFullname(user.getFullname());
			dto.setAvatar(user.getAvatar());
			dto.setEmail(user.getEmail());
			dto.setRoleId(user.getRoleId());
		}
		return dto;
	}

	public int addUser(UserDto dto) {

		try {
			String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
			User user = new User(dto.getUserId(), dto.getEmail(), hashed, dto.getFullname(), dto.getAvatar(),
					dto.getRoleId());
			return userRepository.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int editUser(UserDto dto) {
		try {
			User user = userRepository.getById(dto.getUserId());
			if (user != null) {
				user.setUserId(dto.getUserId());
				user.setEmail(dto.getEmail());
				user.setAvatar(dto.getAvatar());
				user.setFullname(dto.getFullname());
				user.setRoleId(dto.getRoleId());
				if (!dto.getPassword().isEmpty()) {
					user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
				}
			}
			return userRepository.editUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public int removeUser(int id) {
		return userRepository.removeUser(id);
	}
}
