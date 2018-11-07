package com.apap.tutorial8.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDb userDb;
	
	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}
	
	@Override
	public String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}
	
	@Override
	public void updatePassword(String newPassword, String username) {
		UserRoleModel user = userDb.findByUsername(username);
		
		String pass = encrypt(newPassword);
		user.setPassword(pass);
		userDb.save(user);
	}
	
	@Override
	public boolean passwordMatcher(String password, String username) {
		UserRoleModel user = userDb.findByUsername(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if (passwordEncoder.matches(password, user.getPassword())) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public boolean validatePassword(String password) {
		boolean res = password.matches("(?=.*[0-9])(?=.*[a-zA-Z]).{8,}");
		
		return res;
	}
}