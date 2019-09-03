package org.examine.service;

import java.util.List;

import org.examine.po.Teacher;
import org.examine.po.TeacherCustom;


public interface TeacherService {
	
	// 查询所有老师信息
	List<TeacherCustom> findAllTeacher() throws Exception;
	
	// 修改老师信息
	void updateTeacher(Teacher teacher) throws Exception;
	
	// 根据id查找老师信息
	Teacher findTeacherById(Integer userid) throws Exception;
	
	// 添加老师
	void addTeacher(Teacher teacher) throws Exception;
	
	// 根据id删除老师
	void deleteTeacher(Integer id) throws Exception;
}
