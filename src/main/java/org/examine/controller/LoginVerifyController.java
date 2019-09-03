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

	// ��¼��ת
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String loginUI() throws Exception {
		return "../../login";
	}

	// ��½����֤
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// �û������벻��Ϊ��
		if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
			request.setAttribute("msg", "�û��������벻��Ϊ��");
			return "../../login";
		}
		// ����Ա��½
		Userlogin userlogin = userloginService.findUserByUsername(username);
		if (username.equalsIgnoreCase(userlogin.getUsername()) && password.equals(userlogin.getPassword())
				&& userlogin.getRole() == 0) {
			return "redirect:/admin/showStudent";
		} else if (username.equalsIgnoreCase(userlogin.getUsername()) && password.equals(userlogin.getPassword())
				&& userlogin.getRole() == 1) {//��ʦ��½
			return "redirect:/admin/showCourse";
		} else if (username.equalsIgnoreCase(userlogin.getUsername()) && password.equals(userlogin.getPassword())
				&& userlogin.getRole() == 2) {//ѧ����½
			return "redirect:/admin/showCourse";
		}
		request.setAttribute("msg", "�û������������");
		return "../../login";
	}
}
