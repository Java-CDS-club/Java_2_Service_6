package appointmentsService.controller;


import appointmentsService.model.Faculty;
import appointmentsService.repository.FacultyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private FacultyRepository facultyRepository;

    public FacultyController(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @GetMapping
    public List <Faculty> facultyIndex () {
        return facultyRepository.findAll();
    }
}
