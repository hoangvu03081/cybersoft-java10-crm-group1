package com.myclass.service;

import org.mindrot.jbcrypt.BCrypt;

import com.myclass.dto.UserDto;
import com.myclass.repository.UserRepository;

public class AuthService {
	
	private UserRepository userRepository;
	
	public AuthService() {
		userRepository = new UserRepository();
	}
	
	public UserDto login(String email, String password) {
		UserDto userDto = userRepository.getByEmail(email);
		
		// Nếu kiểm tra email ko có thì trả về null
		if (userDto == null) {
			return null;
		}
		
		// Nếu có email phù hợp nhưng password sai cũng trả về null
		if (!BCrypt.checkpw(password, userDto.getPassword())) {
			return null;
		}
	
		return userDto;
	}
}	
