package org.examine.service;

import java.util.List;

import org.examine.po.Course;
import org.examine.po.Teacher;

public interface CourseService {

	// 查询所有课程信息
	List<Course> findAllCourse() throws Exception;

	// 根据id查找课程信息
	Course findCourseById(Integer courseid) throws Exception;
	
	// 更新课程信息
	void updateCourse(Course course) throws Exception;
	
	// 添加课程信息
	void addCourse(Course course) throws Exception;

	// 删除课程信息
	void deleteCourse(Integer id);

}
