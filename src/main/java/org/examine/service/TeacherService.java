package org.examine.service;

import java.util.List;

import org.examine.po.Teacher;
import org.examine.po.TeacherCustom;


public interface TeacherService {
	
	// ��ѯ������ʦ��Ϣ
	List<TeacherCustom> findAllTeacher() throws Exception;
	
	// �޸���ʦ��Ϣ
	void updateTeacher(Teacher teacher) throws Exception;
	
	// ����id������ʦ��Ϣ
	Teacher findTeacherById(Integer userid) throws Exception;
	
	// �����ʦ
	void addTeacher(Teacher teacher) throws Exception;
	
	// ����idɾ����ʦ
	void deleteTeacher(Integer id) throws Exception;
}
