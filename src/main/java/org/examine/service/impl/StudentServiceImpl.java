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
	
	// ѧ����Ϣ��ʾ
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

	// ���ѧ��
	@Override
	public void addStudent(Student student) throws Exception {
		studentMapper.insert(student);
	}

	// ͨ��id����ѧ��
	@Override
	public Student findStudentById(Integer userid) {
		Student student = studentMapper.selectByPrimaryKey(userid);
		return student;
	}

	// �޸�ѧ����Ϣ
	@Override
	public void updateStudent(Student student) {
		studentMapper.updateByPrimaryKey(student);
	}

	// ɾ��ѧ��
	@Override
	public void deleteStudent(Integer id) {
		studentMapper.deleteByPrimaryKey(id);
	}

	// ��ѯѧ������
	@Override
	public Integer findStudentCount() throws Exception {
		return studentMapper.countByExample(null);
	}

	// ������������ѧ����Ϣ
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
