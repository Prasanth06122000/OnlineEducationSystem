package com.course.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import com.course.Dao.ExamDao;
import com.course.dto.ExamDto;
import com.course.entity.Course;
import com.course.repositiory.CourseRepository;
import com.course.exception.ResourceNotFoundException;


@Service
public class CourseServiceImp implements CourseService{
  
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private ExamDao examDao;
	
	@Override
	public Course saveDetails(Course course) {
		// TODO Auto-generated method stub
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourse() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

		@Override
	public Course searchByName(String name) {
	    Optional<Course> optionalCourse = courseRepository.findByCourseName(name);
	    if (optionalCourse.isEmpty()) {
	        throw new ResourceNotFoundException("Course not found for name: " + name);
	    }
	    return optionalCourse.get();
	}

	@Override
	public Course searchById(long id) {
		// TODO Auto-generated method stub
		if(courseRepository.findByCourseId(id).get()==null)
		{
			throw new ResourceNotFoundException("Course not found for Id");
		}
		return courseRepository.findById(id).get();
	}

	@Override
	public String delete(Course course) {
		
		if(searchById(course.getCourseId())==null)
		{
			throw new ResourceNotFoundException("Course not found for Id");
		}
		 courseRepository.delete(course);
		
		return "deleted successfully";
	}

	@Override
	public Course update(Course course) {
		
		if(searchById(course.getCourseId())==null)
		{
			throw new ResourceNotFoundException("Course not found for Id");
		}
		return courseRepository.save(course);
	}
	
	public String addExamToCourse(long courseId,long examId)
	{
	  ExamDto searchByExamId = examDao.searchByExamId(examId);
	  
	  if(searchById(courseId)!=null && searchByExamId!=null)
	  {
		  searchById(courseId).setExamId((int)examId);
		     update(searchById(examId));
		  return "exam Added";
	  }
	  return "Course Not found";
	}

	public String deleteCourse(long id) {
		
		if(searchById(id)==null)
		{
			throw new ResourceNotFoundException("Course not found for Id");
		}
		courseRepository.delete(searchById(id));
		return "deleted sucessfully";
	}

	public ExamDto getExamDetails(long courseId) {
	     if(searchById(courseId)==null)
	     {
	    	 throw new ResourceNotFoundException("Course not found for Id");
	     }
	     
		
		return examDao.searchByExamId(searchById(courseId).getExamId());
	}
	
	
	public void addExamTOTheCourse(long courseId, int examId)
	{
		   Course course = courseRepository.findById(courseId).get();
		   course.setExamId(examId);
		   courseRepository.save(course);
		   
	} 
	
	

}
