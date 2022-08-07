package appointmentsService.controller;

import appointmentsService.model.Faculty;
import appointmentsService.repository.FacultyRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/createFaculty")
public class CreateFacultyController {

    private FacultyRepository facultyRepository;

    public CreateFacultyController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @PostMapping("")
    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }
}
