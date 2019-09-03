package org.examine.service;

import java.util.Date;
import java.util.List;

import org.examine.po.Student;
import org.examine.po.StudentCustom;
import org.examine.po.Userlogin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//spring整合junit
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-*.xml" })
public class ServiceTest {

	@Autowired
	private StudentService studentService;

	@Autowired
	private UserloginService userloginService;

	@Test
	public void fun1() throws Exception {
		int index = 10012;
		for (int i = 0; i < 50; i++) {
			Student s = new Student();
			s.setUserid(index);
			index++;
			s.setUsername("小张");
			s.setBirthyear(new Date());
			s.setSex("男");
			s.setGrade(new Date());
			s.setCollegeid(1);
			studentService.addStudent(s);
		}
	}

	@Test
	public void fun2() throws Exception {
		Integer count = studentService.findStudentCount();
		System.out.println(count / 8 + "==========");
	}

	@Test
	public void fun3() throws Exception {
		List<StudentCustom> studentList = studentService.findStudentByName("小张");
		System.out.println(studentList);
	}

	@Test
	public void fun4() {
		Userlogin userlogin = userloginService.findUserByUsername("admin");
		System.out.println(userlogin.getPassword() + "==============");
	}

}
