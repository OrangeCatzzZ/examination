package org.examine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.examine.mapper.CollegeMapper;
import org.examine.mapper.StudentMapper;
import org.examine.po.College;
import org.examine.po.Student;
import org.examine.po.StudentCustom;
import org.examine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private CollegeMapper collegeMapper;
	
	// 学生信息显示
	@Override
	public List<StudentCustom> findAllStudent() throws Exception {
		List<Student> stuList = studentMapper.selectByExample(null);
		List<StudentCustom> studentList = new ArrayList<>();
		for (Student student : stuList) {
			StudentCustom sc = new StudentCustom();
			sc.setStudent(student);
			College college = collegeMapper.selectByPrimaryKey(student.getCollegeid());
			sc.setCollegeName(college.getCollegename());
			studentList.add(sc);
		}
		return studentList;
	}

	// 添加学生
	@Override
	public void addStudent(Student student) throws Exception {
		studentMapper.insert(student);
	}

	// 通过id查找学生
	@Override
	public Student findStudentById(Integer userid) {
		Student student = studentMapper.selectByPrimaryKey(userid);
		return student;
	}

	// 修改学生信息
	@Override
	public void updateStudent(Student student) {
		studentMapper.updateByPrimaryKey(student);
	}

	// 删除学生
	@Override
	public void deleteStudent(Integer id) {
		studentMapper.deleteByPrimaryKey(id);
	}

	// 查询学生数量
	@Override
	public Integer findStudentCount() throws Exception {
		return studentMapper.countByExample(null);
	}

	// 根据姓名查找学生信息
	@Override
	public List<StudentCustom> findStudentByName(String username) throws Exception {
		List<Student> stuList = studentMapper.selectByUsername(username);
		List<StudentCustom> studentList = new ArrayList<>();
		for (Student student : stuList) {
			StudentCustom sc = new StudentCustom();
			sc.setStudent(student);
			College college = collegeMapper.selectByPrimaryKey(student.getCollegeid());
			sc.setCollegeName(college.getCollegename());
			studentList.add(sc);
		}
		return studentList;
	}

}
