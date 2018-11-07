package com.apap.tutorial8.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.apap.tutorial8.model.UserRoleModel;
import com.apap.tutorial8.service.UserRoleService;

@Controller
@RequestMapping("/user")
public class UserRoleController {
	@Autowired
	private UserRoleService userService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	private String addUserSubmit(@ModelAttribute UserRoleModel user, Model model) {
		if(userService.validatePassword(user.getPassword())) {
			userService.addUser(user);
		} else {
			model.addAttribute("status", "Update Password Gagal, Password Tidak Sesuai Format<br />Password harus mengandung angka dan huruf serta minimal 8 karakter");
		}
		
		return "home";
	}
	
	@RequestMapping(value = "/updatePassword")
	private String updatePassword(Model model) {
		model.addAttribute("status", "");
		return "update-password";
	}
	
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	private String updatePasswordSubmit(@RequestParam("oldPassword") String oldPassword,
										@RequestParam("newPassword") String newPassword,
										@RequestParam("conNewPassword") String conNewPassword,
										HttpServletRequest request, Model model) {
		String user = request.getRemoteUser();
		if(userService.passwordMatcher(oldPassword, user)) {
			if(newPassword.equals(conNewPassword)) {
				if(userService.validatePassword(newPassword)) {
					userService.updatePassword(newPassword, user);
				} else {
					model.addAttribute("status", "Update Password Gagal, Password Tidak Sesuai Format<br />Password harus mengandung angka dan huruf serta minimal 8 karakter");
				}
			} else {
				model.addAttribute("status", "Update Password Gagal, Konfirmasi Password Baru Berbeda");
			}
		} else {
			model.addAttribute("status", "Update Password Gagal, Password Lama Salah");
		}
		
		return "update-password";
	}
}