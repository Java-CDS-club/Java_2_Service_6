package appointmentsService.controller;

import appointmentsService.model.Person;
import appointmentsService.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/createPerson")
public class CreatePersonController {

    private PersonRepository personRepository;

    public CreatePersonController(PersonRepository personRepository) {
    this.personRepository = personRepository;
}

    @PostMapping("")
    public Person createPerson(Person person) {
        return personRepository.save(person);
    }
}
