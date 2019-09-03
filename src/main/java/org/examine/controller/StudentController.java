package org.examine.controller;

import java.util.List;

import org.examine.po.Student;
import org.examine.po.StudentCustom;
import org.examine.service.CollegeService;
import org.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/admin")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CollegeService collegeService;

	// 根据姓名查找学生
	@RequestMapping("/selectStudent")
	public String selectStudent(Model model, String username, Integer page) throws Exception {
		if (username == null || username.equals("")) {
			return "redirect:/admin/showStudent";
		}
		if (page == null || page == 0) {
			page = 1;
			// 分页,传入当前页和多少记录
			PageHelper.startPage(page, 8);
		} else {
			PageHelper.startPage(page, 8);
		}
		// 保存当前页数
		model.addAttribute("page", page);
		List<StudentCustom> studentCustoms = studentService.findStudentByName(username);
		model.addAttribute("studentList", studentCustoms);
		if (!model.containsAttribute("endPage")) {
			// 保存最后一页的页数
			int endPage = studentService.findStudentCount() / 8 + 1;
			model.addAttribute("endPage", endPage);
		}
		return "admin/showStudent";
	}

	// 学生信息显示
	@RequestMapping("/showStudent")
	public String showStudent(Model model, Integer page) throws Exception {
		if (page == null || page == 0) {
			page = 1;
			// 分页,传入当前页和多少记录
			PageHelper.startPage(page, 8);
		} else {
			PageHelper.startPage(page, 8);
		}
		if (!model.containsAttribute("studentCustoms")) {
			// 得到所有学生信息
			List<StudentCustom> studentCustoms = studentService.findAllStudent();
			// 保存学生信息
			model.addAttribute("studentList", studentCustoms);
		}
		// 得到分页的结果对象
		// PageInfo<StudentCustom> stuPageInfo = new
		// PageInfo<>(studentCustoms,8);
		// 得到分页中的StudentCustom条目对象
		// List<StudentCustom> pageList = stuPageInfo.getList();
		if (!model.containsAttribute("endPage")) {
			// 学生总数
			Integer count = studentService.findStudentCount();
			model.addAttribute("count", count);
			// 保存最后一页的页数
			int endPage = count / 8 + 1;
			model.addAttribute("endPage", endPage);
		}
		// 保存当前页数
		model.addAttribute("page", page);
		return "admin/showStudent";
	}

	// 学生信息修改跳转
	@RequestMapping(value = "/editStudent", method = { RequestMethod.GET })
	public String editStudentUI(Model model, Integer id) throws Exception {
		model.addAttribute("student", studentService.findStudentById(id));
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		return "admin/editStudent";
	}

	// 修改学生信息
	@RequestMapping(value = "/editStudent", method = { RequestMethod.POST })
	public String editStudent(Student student) throws Exception {
		studentService.updateStudent(student);
		return "redirect:/admin/showStudent";
	}

	// 添加学生跳转
	@RequestMapping(value = "/addStudent", method = { RequestMethod.GET })
	public String addStudentUI(Model model) throws Exception {
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		return "admin/addStudent";
	}

	// 添加学生表单处理
	@RequestMapping(value = "/addStudent", method = { RequestMethod.POST })
	public String addStudent(Student student) throws Exception {
		studentService.addStudent(student);
		return "redirect:/admin/showStudent";
	}

	// 删除学生信息
	@RequestMapping("/removeStudent")
	public String removeStudent(Integer id) throws Exception {
		studentService.deleteStudent(id);
		return "redirect:/admin/showStudent";
	}

}
