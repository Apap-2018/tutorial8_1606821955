package com.apap.tutorial8.service;

import com.apap.tutorial8.model.UserRoleModel;

public interface UserRoleService {
	UserRoleModel addUser(UserRoleModel user);
	String encrypt(String password);
	void updatePassword(String newPassword, String username);
	boolean passwordMatcher(String password, String username);
	boolean validatePassword(String password);
}
