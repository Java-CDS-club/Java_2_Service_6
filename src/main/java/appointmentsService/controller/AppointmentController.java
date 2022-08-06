package appointmentsService.controller;


import appointmentsService.model.Appointment;
import appointmentsService.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
//@RequestMapping("/appointment")
public class AppointmentController {

  /**  private AppointmentRepository appointmentRepository;

    public AppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("")
    public List<Appointment> appointmentIndex () {
        return appointmentRepository.findAll();
    }**/
}

