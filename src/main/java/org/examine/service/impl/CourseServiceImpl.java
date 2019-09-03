package org.examine.service.impl;

import java.util.List;

import org.examine.mapper.CourseMapper;
import org.examine.po.Course;
import org.examine.po.Teacher;
import org.examine.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseMapper courseMapper;
	
	// 查询所有课程信息
	@Override
	public List<Course> findAllCourse() throws Exception {
		return courseMapper.selectByExample(null);
	}

	@Override
	public Course findCourseById(Integer courseid) throws Exception {
		return courseMapper.selectByPrimaryKey(courseid);
	}

	@Override
	public void updateCourse(Course course) throws Exception {
		courseMapper.updateByPrimaryKey(course);
	}

	@Override
	public void addCourse(Course course) throws Exception {
		courseMapper.insert(course);
	}

	@Override
	public void deleteCourse(Integer id) {
		courseMapper.deleteByPrimaryKey(id);
	}

}
