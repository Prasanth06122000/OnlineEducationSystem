package com.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.course.Dao.ExamDao;
import com.course.controller.CourseController;
import com.course.dto.ExamDto;
import com.course.dto.StudentDto;
import com.course.dto.TeacherDto;
import com.course.entity.Course;
import com.course.exception.GlobalExceptionHandler;
import com.course.exception.ResourceNotFoundException;
import com.course.repositiory.CourseRepository;
import com.course.service.CourseServiceImp;



 class CourseControllerTest {

    @Mock
    private CourseServiceImp courseServiceImp;

    @InjectMocks
    private CourseController courseController;
    
    @Mock
    private ResourceNotFoundException resourceNotFoundException;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;
    
    @Mock
    private CourseRepository courseRepository;

    @Mock
    private ExamDao examDao;

    @InjectMocks
    private CourseServiceImp courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
     void testSaveDetails() {
        Course course = new Course();
        when(courseServiceImp.saveDetails(course)).thenReturn(course);

        Course savedCourse = courseController.saveDetails(course);
        assertEquals(course, savedCourse);
    }

    @Test
     void testUpdate() {
        Course course = new Course();
        when(courseServiceImp.update(course)).thenReturn(course);

        Course updatedCourse = courseController.update(course);
        assertEquals(course, updatedCourse);
    }

    @Test
     void testSearchByCourseId() {
        long id = 1;
        Course course = new Course();
        when(courseServiceImp.searchById(id)).thenReturn(course);

        Course foundCourse = courseController.searchByCourseId(id);
        assertEquals(course, foundCourse);
    }

    @Test
     void testSearchByName() {
        String name = "Java";
        Course course = new Course();
        when(courseServiceImp.searchByName(name)).thenReturn(course);

        Course foundCourse = courseController.searchByName(name);
        assertEquals(course, foundCourse);
    }

    @Test
     void testDelete() {
        long id = 1;
        String expectedResult = "Course deleted successfully";
        when(courseServiceImp.deleteCourse(id)).thenReturn(expectedResult);

        String result = courseController.delete(id);
        assertEquals(expectedResult, result);
    }

    @Test
     void testGetExamCourseId() {
        long courseId = 1;
        ExamDto examDto = new ExamDto();
        when(courseServiceImp.getExamDetails(courseId)).thenReturn(examDto);

        ExamDto result = courseController.getExamCourseId(courseId);
        assertEquals(examDto, result);
    }

    @Test
     void testGetAllCourses() {
        List<Course> courses = List.of(new Course(), new Course());
        when(courseServiceImp.getAllCourse()).thenReturn(courses);

        List<Course> result = courseController.getAllCourses();
        assertEquals(courses, result);
    }

    @Test
     void testAddExamToTheCourse() {
        long courseId = 1;
        int examId = 1;
        doNothing().when(courseServiceImp).addExamTOTheCourse(courseId, examId);

        courseController.addExamToTheCourse(courseId, examId);

        // Assuming void method call success if no exceptions
    }
    @Test
     void testGettersAndSetters() {
        Course course = new Course();
        
        course.setCourseId(1);
        assertEquals(1, course.getCourseId());
        
        course.setCourseName("Test Course");
        assertEquals("Test Course", course.getCourseName());
        
        course.setDescription("Test Description");
        assertEquals("Test Description", course.getDescription());
        
        course.setStartingDate("2024-04-16");
        assertEquals("2024-04-16", course.getStartingDate());
        
        course.setEndDate("2024-04-30");
        assertEquals("2024-04-30", course.getEndDate());
        
        course.setSchedule("Monday, Wednesday, Friday");
        assertEquals("Monday, Wednesday, Friday", course.getSchedule());
        
        course.setMaxStudent(20);
        assertEquals(20, course.getMaxStudent());
        
        course.setTeacherId(101);
        assertEquals(101, course.getTeacherId());
        
        List<Integer> studentIdList = new ArrayList<>();
        studentIdList.add(201);
        studentIdList.add(202);
        course.setStudentId(studentIdList);
        assertEquals(studentIdList, course.getStudentId());
        
        course.setExamId(501);
        assertEquals(501, course.getExamId());
    }
    
    @Test
     void testDefaultConstructor() {
        Course course = new Course();
        assertNotNull(course);
        assertNull(course.getCourseName());
        // similarly, assert for other fields
    }
    @Test
     void testGettersAndSetters1() {
        ExamDto examDto = new ExamDto();
        
        examDto.setExamId(1);
        assertEquals(1, examDto.getExamId());
        
        examDto.setTestName("Test Exam");
        assertEquals("Test Exam", examDto.getTestName());
        
        examDto.setDuration(60);
        assertEquals(60, examDto.getDuration());
        
        examDto.setTestTime("10:00 AM");
        assertEquals("10:00 AM", examDto.getTestTime());
        
        examDto.setNoOfQues(50);
        assertEquals(50, examDto.getNoOfQues());
    }
    
    @Test
     void testDefaultConstructor1() {
        ExamDto examDto = new ExamDto();
        assertNotNull(examDto);
        assertEquals(0, examDto.getDuration()); // Default duration should be 0
        // similarly, assert for other fields
    }
    @Test
     void testGettersAndSetters2() {
        StudentDto studentDto = new StudentDto();
        
        studentDto.setStudentId(1);
        assertEquals(1, studentDto.getStudentId());
        
        studentDto.setFirstName("John");
        assertEquals("John", studentDto.getFirstName());
        
        studentDto.setLastName("Doe");
        assertEquals("Doe", studentDto.getLastName());
        
        studentDto.setDateOfBrith("2000-01-01");
        assertEquals("2000-01-01", studentDto.getDateOfBrith());
        
        studentDto.setContactNum("1234567890");
        assertEquals("1234567890", studentDto.getContactNum());
        
        studentDto.setPassword("password123");
        assertEquals("password123", studentDto.getPassword());
        
        studentDto.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", studentDto.getEmail());
        
        List<Integer> attendances = new ArrayList<>();
        attendances.add(1);
        attendances.add(2);
        studentDto.setAttendances(attendances);
        assertEquals(attendances, studentDto.getAttendances());
        
        studentDto.setCourseId(101);
        assertEquals(101, studentDto.getCourseId());
        
        studentDto.setScore(85);
        assertEquals(85, studentDto.getScore());
        
        studentDto.setAddress("123 Main St, Anytown, USA");
        assertEquals("123 Main St, Anytown, USA", studentDto.getAddress());
    }
    
    @Test
     void testDefaultConstructor2() {
        StudentDto studentDto = new StudentDto();
        assertNotNull(studentDto);
        assertEquals(0, studentDto.getStudentId()); // Default studentId should be 0
        // similarly, assert for other fields
    }
    @Test
     void testGettersAndSetters6() {
        TeacherDto teacherDto = new TeacherDto();
        
        teacherDto.setTeacherId(1);
        assertEquals(1, teacherDto.getTeacherId());
        
        teacherDto.setTeacherName("John Smith");
        assertEquals("John Smith", teacherDto.getTeacherName());
        
        teacherDto.setTeacherContactNum("1234567890");
        assertEquals("1234567890", teacherDto.getTeacherContactNum());
        
        teacherDto.setEmail("john.smith@example.com");
        assertEquals("john.smith@example.com", teacherDto.getEmail());
        
        teacherDto.setAddress("123 Main St, Anytown, USA");
        assertEquals("123 Main St, Anytown, USA", teacherDto.getAddress());
        
        teacherDto.setSubject("Mathematics");
        assertEquals("Mathematics", teacherDto.getSubject());
        
        teacherDto.setExperience("5 years");
        assertEquals("5 years", teacherDto.getExperience());
        
        teacherDto.setQualifaction("Bachelor of Science in Education");
        assertEquals("Bachelor of Science in Education", teacherDto.getQualifaction());
        
        teacherDto.setPassword("password123");
        assertEquals("password123", teacherDto.getPassword());
    }
    
    @Test
     void testDefaultConstructor6() {
        TeacherDto teacherDto = new TeacherDto();
        assertNotNull(teacherDto);
        assertEquals(0, teacherDto.getTeacherId()); // Default teacherId should be 0
        // similarly, assert for other fields
    }
    @Test
    void handleResourceNotFoundException_ShouldReturnNotFound() {
        // Arrange
        String errorMessage = "Resource not found";
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Act
        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleResourceNotFoundException(exception);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    @Test
    void handleGlobalException_ShouldReturnInternalServerError() {
        // Arrange
        Exception exception = new Exception();

        // Act
        ResponseEntity<Object> responseEntity = globalExceptionHandler.handleGlobalException(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("An unexpected error occurred", responseEntity.getBody());
    }
    @Test
     void testConstructor() {
        // Arrange
        String errorMessage = "Resource not found";

        // Act
        ResourceNotFoundException exception = new ResourceNotFoundException(errorMessage);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
    }
    
    // Add more test cases for other methods similar to the one above
    
    
    
    
    @Test
    void saveDetails_ShouldReturnSavedCourse() {
        // Arrange
        Course course = new Course();
        when(courseRepository.save(course)).thenReturn(course);

        // Act
        Course savedCourse = courseService.saveDetails(course);

        // Assert
        assertEquals(course, savedCourse);
    }

    @Test
    void getAllCourse_ShouldReturnListOfCourses() {
        // Arrange
        Course course1 = new Course();
        Course course2 = new Course();
        when(courseRepository.findAll()).thenReturn(List.of(course1, course2));

        // Act
        List<Course> courses = courseService.getAllCourse();

        // Assert
        assertEquals(2, courses.size());
    }

    // Add more test cases for other methods similar to the ones above

    @Test
    void searchByName_WhenCourseNotFound_ShouldThrowResourceNotFoundException() {
        // Arrange
        String name = "Nonexistent Course";
        when(courseRepository.findByCourseName(name)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> courseService.searchByName(name));
    }

   

    // Add more test cases for other error scenarios and edge cases

   

    
    
    
}
