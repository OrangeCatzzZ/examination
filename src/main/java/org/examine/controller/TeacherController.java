package org.examine.controller;

import org.examine.po.Teacher;
import org.examine.service.CollegeService;
import org.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private CollegeService collegeService;

	// 显示所有老师信息
	@RequestMapping("/showTeacher")
	public String showTeacher(Model model) throws Exception {
		model.addAttribute("teacherList", teacherService.findAllTeacher());
		return "admin/showTeacher";
	}

	// 修改信息跳转
	@RequestMapping(value = "/editTeacher", method = { RequestMethod.GET })
	public String editTeacherUI(Model model, Integer id) throws Exception {
		// 保存要修改的老师信息
		model.addAttribute("teacher", teacherService.findTeacherById(id));
		// 保存学院信息
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		return "admin/editTeacher";
	}

	// 修改信息提交
	@RequestMapping(value = "/editTeacher", method = { RequestMethod.POST })
	public String editTeacher(Teacher teacher) throws Exception {
		// 更新修改的老师信息
		teacherService.updateTeacher(teacher);
		return "redirect:/admin/showTeacher";
	}

	// 添加老师跳转
	@RequestMapping(value = "/addTeacher", method = { RequestMethod.GET })
	public String addTeacherUI(Model model) throws Exception {
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		return "admin/addTeacher";
	}

	// 添加老师表单提交
	@RequestMapping(value = "/addTeacher", method = { RequestMethod.POST })
	public String addTeacher(Teacher teacher) throws Exception {
		teacherService.addTeacher(teacher);
		return "redirect:/admin/showTeacher";
	}

	// 删除老师信息
	@RequestMapping("/removeTeacher")
	public String removeStudent(Integer id) throws Exception {
		teacherService.deleteTeacher(id);
		return "redirect:/admin/showTeacher";
	}
}
