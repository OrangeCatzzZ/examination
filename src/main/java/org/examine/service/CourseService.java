package org.examine.service;

import java.util.List;

import org.examine.po.Course;
import org.examine.po.Teacher;

public interface CourseService {

	// ��ѯ���пγ���Ϣ
	List<Course> findAllCourse() throws Exception;

	// ����id���ҿγ���Ϣ
	Course findCourseById(Integer courseid) throws Exception;
	
	// ���¿γ���Ϣ
	void updateCourse(Course course) throws Exception;
	
	// ��ӿγ���Ϣ
	void addCourse(Course course) throws Exception;

	// ɾ���γ���Ϣ
	void deleteCourse(Integer id);

}
