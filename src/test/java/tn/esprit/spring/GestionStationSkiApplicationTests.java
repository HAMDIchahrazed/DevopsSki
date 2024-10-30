package tn.esprit.spring;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.entities.Support;
import tn.esprit.spring.entities.TypeCourse;
import tn.esprit.spring.repositories.ICourseRepository;
import tn.esprit.spring.services.CourseServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class GestionStationSkiApplicationTests {

	@Mock
	ICourseRepository courseRepository;

	@InjectMocks
	CourseServicesImpl courseServices;

	Course course1 = new Course();
	Course course2 = new Course();
	Course course3 = new Course();

	List<Course> courses = new ArrayList<>();

	@BeforeEach
	void setUp() {
		// Initialize course objects with test data
		course1.setNumCourse(1L);
		course1.setLevel(1);
		course1.setTypeCourse(TypeCourse.COLLECTIVE_ADULT);
		course1.setSupport(Support.SKI);
		course1.setPrice(100f);
		course1.setTimeSlot(2);

		course2.setNumCourse(2L);
		course2.setLevel(2);
		course2.setTypeCourse(TypeCourse.COLLECTIVE_CHILDREN);
		course2.setSupport(Support.SNOWBOARD);
		course2.setPrice(150f);
		course2.setTimeSlot(3);

		course3.setNumCourse(3L);
		course3.setLevel(3);
		course3.setTypeCourse(TypeCourse.INDIVIDUAL);
		course3.setSupport(Support.SKI);
		course3.setPrice(200f);
		course3.setTimeSlot(4);

		courses.add(course1);
		courses.add(course2);
	}

	@Test
	@Order(1)
	void testRetrieveCourse() {
		Mockito.when(courseRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(course1));
		Course retrievedCourse = courseServices.retrieveCourse(1L);
		Assertions.assertNotNull(retrievedCourse);
		Assertions.assertEquals(course1.getNumCourse(), retrievedCourse.getNumCourse());
	}

	@Test
	@Order(2)
	void testRetrieveAllCourses() {
		Mockito.when(courseRepository.findAll()).thenReturn(courses);
		List<Course> retrievedCourses = courseServices.retrieveAllCourses();
		Assertions.assertEquals(2, retrievedCourses.size());
	}
}
