package com.ispan.eeit182.entity;

import java.sql.Date;

public class Student {
	String studentId;
	String name;
	Date   birthday;
	String email;
	
	public Student(String studentId, String name, Date birthday, String email) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
	}

	public Student() {
		super();
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", birthday=" + birthday + ", email=" + email
				+ "]";
	}
	
}
