package org.examine.service;

import java.util.List;

import org.examine.po.Student;
import org.examine.po.StudentCustom;

public interface StudentService {
	
	// 查询所有学生信息
	List<StudentCustom> findAllStudent() throws Exception;
	
	// 添加学生
	void addStudent(Student student) throws Exception;
	
	// 通过id查找学生
	Student findStudentById(Integer userid);
	
	// 根据姓名查找学生
	List<StudentCustom> findStudentByName(String username) throws Exception;
	
	// 修改学生信息
	void updateStudent(Student student);
	
	// 根据id删除学生信息
	void deleteStudent(Integer id);
	
	// 查询学生数量
	Integer findStudentCount() throws Exception;;
}
