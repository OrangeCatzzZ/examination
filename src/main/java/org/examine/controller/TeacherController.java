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

	// ��ʾ������ʦ��Ϣ
	@RequestMapping("/showTeacher")
	public String showTeacher(Model model) throws Exception {
		model.addAttribute("teacherList", teacherService.findAllTeacher());
		return "admin/showTeacher";
	}

	// �޸���Ϣ��ת
	@RequestMapping(value = "/editTeacher", method = { RequestMethod.GET })
	public String editTeacherUI(Model model, Integer id) throws Exception {
		// ����Ҫ�޸ĵ���ʦ��Ϣ
		model.addAttribute("teacher", teacherService.findTeacherById(id));
		// ����ѧԺ��Ϣ
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		return "admin/editTeacher";
	}

	// �޸���Ϣ�ύ
	@RequestMapping(value = "/editTeacher", method = { RequestMethod.POST })
	public String editTeacher(Teacher teacher) throws Exception {
		// �����޸ĵ���ʦ��Ϣ
		teacherService.updateTeacher(teacher);
		return "redirect:/admin/showTeacher";
	}

	// �����ʦ��ת
	@RequestMapping(value = "/addTeacher", method = { RequestMethod.GET })
	public String addTeacherUI(Model model) throws Exception {
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		return "admin/addTeacher";
	}

	// �����ʦ���ύ
	@RequestMapping(value = "/addTeacher", method = { RequestMethod.POST })
	public String addTeacher(Teacher teacher) throws Exception {
		teacherService.addTeacher(teacher);
		return "redirect:/admin/showTeacher";
	}

	// ɾ����ʦ��Ϣ
	@RequestMapping("/removeTeacher")
	public String removeStudent(Integer id) throws Exception {
		teacherService.deleteTeacher(id);
		return "redirect:/admin/showTeacher";
	}
}
