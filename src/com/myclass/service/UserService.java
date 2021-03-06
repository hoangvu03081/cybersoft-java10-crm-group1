package com.myclass.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.UserDto;
import com.myclass.entity.User;
import com.myclass.repository.UserRepository;

public class UserService {
	private UserRepository userRepository;

	public UserService() {
		userRepository = new UserRepository();
	}
	
	public HashMap<Integer,String> getHashMap()
	{
		return userRepository.getAllUsersHashMap();
	}

	public List<UserDto> getAllUsers() {
		List<UserDto> dtos = new ArrayList<UserDto>();
		List<User> users = userRepository.getAllUsers();
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

	public UserDto getUserByEmail(String email) {
		return userRepository.getByEmail(email);
	}
	
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

	public int editUser(UserDto dto, int loginUserId) {
		try {
			User user = userRepository.getById(dto.getUserId());
			if (user != null) {
				user.setEmail(dto.getEmail());
				user.setAvatar(dto.getAvatar());
				user.setFullname(dto.getFullname());

				if ( loginUserId == 1) {
					user.setUserId(dto.getUserId());
					user.setRoleId(dto.getRoleId());					
				}
				
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
