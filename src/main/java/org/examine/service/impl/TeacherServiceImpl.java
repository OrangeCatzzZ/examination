package org.examine.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.examine.mapper.CollegeMapper;
import org.examine.mapper.TeacherMapper;
import org.examine.po.College;
import org.examine.po.Teacher;
import org.examine.po.TeacherCustom;
import org.examine.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Autowired
	private CollegeMapper collegeMapper;

	// 查询所有老师信息
	@Override
	public List<TeacherCustom> findAllTeacher() throws Exception {
		List<Teacher> teacherList = teacherMapper.selectByExample(null);
		List<TeacherCustom> teacherCustoms = new ArrayList<>();
		for (Teacher teacher : teacherList) {
			TeacherCustom tc = new TeacherCustom();
			tc.setTeacher(teacher);
			College college = collegeMapper.selectByPrimaryKey(teacher.getCollegeid());
			tc.setCollegeName(college.getCollegename());
			teacherCustoms.add(tc);
		}
		return teacherCustoms;
	}

	// 根据id查找老师信息
	@Override
	public Teacher findTeacherById(Integer userid) {
		return teacherMapper.selectByPrimaryKey(userid);
	}

	// 修改老师信息
	@Override
	public void updateTeacher(Teacher teacher) {
		teacherMapper.updateByPrimaryKey(teacher);
	}

	@Override
	public void addTeacher(Teacher teacher) throws Exception {
		teacherMapper.insert(teacher);
	}

	@Override
	public void deleteTeacher(Integer id) throws Exception {
		teacherMapper.deleteByPrimaryKey(id);
	}

}
