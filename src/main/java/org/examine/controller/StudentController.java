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

	// ������������ѧ��
	@RequestMapping("/selectStudent")
	public String selectStudent(Model model, String username, Integer page) throws Exception {
		if (username == null || username.equals("")) {
			return "redirect:/admin/showStudent";
		}
		if (page == null || page == 0) {
			page = 1;
			// ��ҳ,���뵱ǰҳ�Ͷ��ټ�¼
			PageHelper.startPage(page, 8);
		} else {
			PageHelper.startPage(page, 8);
		}
		// ���浱ǰҳ��
		model.addAttribute("page", page);
		List<StudentCustom> studentCustoms = studentService.findStudentByName(username);
		model.addAttribute("studentList", studentCustoms);
		if (!model.containsAttribute("endPage")) {
			// �������һҳ��ҳ��
			int endPage = studentService.findStudentCount() / 8 + 1;
			model.addAttribute("endPage", endPage);
		}
		return "admin/showStudent";
	}

	// ѧ����Ϣ��ʾ
	@RequestMapping("/showStudent")
	public String showStudent(Model model, Integer page) throws Exception {
		if (page == null || page == 0) {
			page = 1;
			// ��ҳ,���뵱ǰҳ�Ͷ��ټ�¼
			PageHelper.startPage(page, 8);
		} else {
			PageHelper.startPage(page, 8);
		}
		if (!model.containsAttribute("studentCustoms")) {
			// �õ�����ѧ����Ϣ
			List<StudentCustom> studentCustoms = studentService.findAllStudent();
			// ����ѧ����Ϣ
			model.addAttribute("studentList", studentCustoms);
		}
		// �õ���ҳ�Ľ������
		// PageInfo<StudentCustom> stuPageInfo = new
		// PageInfo<>(studentCustoms,8);
		// �õ���ҳ�е�StudentCustom��Ŀ����
		// List<StudentCustom> pageList = stuPageInfo.getList();
		if (!model.containsAttribute("endPage")) {
			// ѧ������
			Integer count = studentService.findStudentCount();
			model.addAttribute("count", count);
			// �������һҳ��ҳ��
			int endPage = count / 8 + 1;
			model.addAttribute("endPage", endPage);
		}
		// ���浱ǰҳ��
		model.addAttribute("page", page);
		return "admin/showStudent";
	}

	// ѧ����Ϣ�޸���ת
	@RequestMapping(value = "/editStudent", method = { RequestMethod.GET })
	public String editStudentUI(Model model, Integer id) throws Exception {
		model.addAttribute("student", studentService.findStudentById(id));
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		return "admin/editStudent";
	}

	// �޸�ѧ����Ϣ
	@RequestMapping(value = "/editStudent", method = { RequestMethod.POST })
	public String editStudent(Student student) throws Exception {
		studentService.updateStudent(student);
		return "redirect:/admin/showStudent";
	}

	// ���ѧ����ת
	@RequestMapping(value = "/addStudent", method = { RequestMethod.GET })
	public String addStudentUI(Model model) throws Exception {
		if (!model.containsAttribute("collegeList")) {
			model.addAttribute("collegeList", collegeService.findAllCollege());
		}
		return "admin/addStudent";
	}

	// ���ѧ��������
	@RequestMapping(value = "/addStudent", method = { RequestMethod.POST })
	public String addStudent(Student student) throws Exception {
		studentService.addStudent(student);
		return "redirect:/admin/showStudent";
	}

	// ɾ��ѧ����Ϣ
	@RequestMapping("/removeStudent")
	public String removeStudent(Integer id) throws Exception {
		studentService.deleteStudent(id);
		return "redirect:/admin/showStudent";
	}

}
