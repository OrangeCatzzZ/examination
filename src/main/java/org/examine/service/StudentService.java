package org.examine.service;

import java.util.List;

import org.examine.po.Student;
import org.examine.po.StudentCustom;

public interface StudentService {
	
	// ��ѯ����ѧ����Ϣ
	List<StudentCustom> findAllStudent() throws Exception;
	
	// ���ѧ��
	void addStudent(Student student) throws Exception;
	
	// ͨ��id����ѧ��
	Student findStudentById(Integer userid);
	
	// ������������ѧ��
	List<StudentCustom> findStudentByName(String username) throws Exception;
	
	// �޸�ѧ����Ϣ
	void updateStudent(Student student);
	
	// ����idɾ��ѧ����Ϣ
	void deleteStudent(Integer id);
	
	// ��ѯѧ������
	Integer findStudentCount() throws Exception;;
}
