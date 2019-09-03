package org.examine.controller;

import javax.servlet.http.HttpServletRequest;

import org.examine.po.Userlogin;
import org.examine.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginVerifyController {

	@Autowired
	private UserloginService userloginService;

	// 登录跳转
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String loginUI() throws Exception {
		return "../../login";
	}

	// 登陆表单验证
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 用户名密码不能为空
		if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			request.setAttribute("msg", "用户名或密码不能为空");
			return "../../login";
		}
		// 管理员登陆
		Userlogin userlogin = userloginService.findUserByUsername(username);
		if (username.equalsIgnoreCase(userlogin.getUsername()) && password.equals(userlogin.getPassword())
				&& userlogin.getRole() == 0) {
			return "redirect:/admin/showStudent";
		} else if (username.equalsIgnoreCase(userlogin.getUsername()) && password.equals(userlogin.getPassword())
				&& userlogin.getRole() == 1) {//老师登陆
			return "redirect:/admin/showCourse";
		} else if (username.equalsIgnoreCase(userlogin.getUsername()) && password.equals(userlogin.getPassword())
				&& userlogin.getRole() == 2) {//学生登陆
			return "redirect:/admin/showCourse";
		}
		request.setAttribute("msg", "用户名或密码错误");
		return "../../login";
	}
}
