package org.examine.po;

// student��college�İ�װ��
public class StudentCustom {
	
	private Student student;
    
    private String collegeName;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	@Override
	public String toString() {
		return "StudentCustom [student=" + student + ", collegeName=" + collegeName + "]";
	}
    
}
