package tn.esprit.spring.services;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.Course;
import tn.esprit.spring.repositories.ICourseRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CourseServicesImpl implements ICourseServices {

    private static final Logger logger = LogManager.getLogger(CourseServicesImpl.class);
    private ICourseRepository courseRepository;

    @Override
    public List<Course> retrieveAllCourses() {
        logger.info("Retrieving all courses");
        List<Course> courses = courseRepository.findAll();
        logger.debug("Retrieved courses: {}", courses);
        return courses;
    }

    @Override
    public Course addCourse(Course course) {
        logger.info("Adding a new course: {}", course);
        Course savedCourse = courseRepository.save(course);
        logger.info("Added course: {}", savedCourse);
        return savedCourse;
    }

    @Override
    public Course updateCourse(Course course) {
        logger.info("Updating course: {}", course);
        Course updatedCourse = courseRepository.save(course);
        logger.info("Updated course: {}", updatedCourse);
        return updatedCourse;
    }

    @Override
    public Course retrieveCourse(Long numCourse) {
        logger.info("Retrieving course with ID: {}", numCourse);
        return courseRepository.findById(numCourse).orElseGet(() -> {
            logger.error("Course with ID: {} not found", numCourse);
            return null;
        });
    }
}
