package appointmentsService.controller;


import appointmentsService.model.Course;
import appointmentsService.repository.CourseRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }



    public List <Course> courseIndex () {
        return courseRepository.findAll();
    }

}
