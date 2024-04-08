package com.student.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long studentId;
	
	private String firstName;
	
	private String lastName;
	
	private String dateOfBrith;
	
	private Long contactNum;
	
	private String password;
	
	private String email;
	
	private List<Integer> attendances;
	
	private int courseId;
	
	private int score;
	
	

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Integer> getAttendances() {
		return attendances;
	}


	public void setAttendances(List<Integer> attendances) {
		this.attendances = attendances;
	}


	public int getCourseId() {
		return courseId;
	}


	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}


	
	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}



	private String address;


	public long getStudentId() {
		return studentId;
	}


	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getDateOfBrith() {
		return dateOfBrith;
	}


	public void setDateOfBrith(String dateOfBrith) {
		this.dateOfBrith = dateOfBrith;
	}


	public Long getContactNum() {
		return contactNum;
	}


	public void setContactNum(Long contactNum) {
		this.contactNum = contactNum;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}
	
	

}
