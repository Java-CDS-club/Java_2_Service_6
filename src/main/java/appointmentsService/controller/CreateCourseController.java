package appointmentsService.controller;


import appointmentsService.model.Course;
import appointmentsService.repository.CourseRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createCourse")
public class CreateCourseController {

    private CourseRepository courseRepository;

    public CreateCourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostMapping("")
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
}
