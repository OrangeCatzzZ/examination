package org.examine.controller;

import org.examine.po.Course;
import org.examine.service.CollegeService;
import org.examine.service.CourseService;
import org.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CollegeService collegeService;

	@Autowired
	private TeacherService teacherService;

	// ��ʾ���пγ���Ϣ
	@RequestMapping("/showCourse")
	public String showCourse(Model model) throws Exception {
		model.addAttribute("courseList", courseService.findAllCourse());
		return "admin/showCourse";
	}

	// �޸Ŀγ���Ϣ��ת
	@RequestMapping(value = "/editCourse", method = { RequestMethod.GET })
	public String editCourseUI(Model model, Integer id) throws Exception {
		// ����Ҫ�޸ĵĿγ���Ϣ
		model.addAttribute("course", courseService.findCourseById(id));
		// ����ѧԺ��Ϣ
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		// �����ڿ���ʦ��Ϣ
		if (!model.containsAttribute("teacherList")) {
			model.addAttribute("teacherList", teacherService.findAllTeacher());
		}
		return "admin/editCourse";
	}

	// �޸Ŀγ���Ϣ�ύ
	@RequestMapping(value = "/editCourse", method = { RequestMethod.POST })
	public String editCourse(Course course) throws Exception {
		courseService.updateCourse(course);
		return "redirect:/admin/showCourse";
	}

	// ��ӿγ���ת
	@RequestMapping(value = "/addCourse", method = { RequestMethod.GET })
	public String addCourseUI(Model model) throws Exception {
		// ����ѧԺ��Ϣ
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		// �����ڿ���ʦ��Ϣ
		if (!model.containsAttribute("teacherList")) {
			model.addAttribute("teacherList", teacherService.findAllTeacher());
		}
		return "admin/addCourse";
	}
	
	// ��ӿγ���Ϣ�ύ
	@RequestMapping(value = "/addCourse", method = { RequestMethod.POST })
	public String addCourse(Course course) throws Exception {
		courseService.addCourse(course);
		return "redirect:/admin/showCourse";
	}
	
	// ɾ���γ���Ϣ
	@RequestMapping("/removeCourse")
	public String removeCourse(Integer id) throws Exception {
		courseService.deleteCourse(id);
		return "redirect:/admin/showCourse";
	}

}
