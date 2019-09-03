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

	// 显示所有课程信息
	@RequestMapping("/showCourse")
	public String showCourse(Model model) throws Exception {
		model.addAttribute("courseList", courseService.findAllCourse());
		return "admin/showCourse";
	}

	// 修改课程信息跳转
	@RequestMapping(value = "/editCourse", method = { RequestMethod.GET })
	public String editCourseUI(Model model, Integer id) throws Exception {
		// 保存要修改的课程信息
		model.addAttribute("course", courseService.findCourseById(id));
		// 保存学院信息
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		// 保存授课老师信息
		if (!model.containsAttribute("teacherList")) {
			model.addAttribute("teacherList", teacherService.findAllTeacher());
		}
		return "admin/editCourse";
	}

	// 修改课程信息提交
	@RequestMapping(value = "/editCourse", method = { RequestMethod.POST })
	public String editCourse(Course course) throws Exception {
		courseService.updateCourse(course);
		return "redirect:/admin/showCourse";
	}

	// 添加课程跳转
	@RequestMapping(value = "/addCourse", method = { RequestMethod.GET })
	public String addCourseUI(Model model) throws Exception {
		// 保存学院信息
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		// 保存授课老师信息
		if (!model.containsAttribute("teacherList")) {
			model.addAttribute("teacherList", teacherService.findAllTeacher());
		}
		return "admin/addCourse";
	}
	
	// 添加课程信息提交
	@RequestMapping(value = "/addCourse", method = { RequestMethod.POST })
	public String addCourse(Course course) throws Exception {
		courseService.addCourse(course);
		return "redirect:/admin/showCourse";
	}
	
	// 删除课程信息
	@RequestMapping("/removeCourse")
	public String removeCourse(Integer id) throws Exception {
		courseService.deleteCourse(id);
		return "redirect:/admin/showCourse";
	}

}
