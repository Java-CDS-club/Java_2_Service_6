package appointmentsService.controller;


import appointmentsService.model.Appointment;
import appointmentsService.repository.AppointmentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/createAppointments")
public class CreateAppointmentController {


    private AppointmentRepository appointmentRepository;

    public CreateAppointmentController(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @PostMapping("")
    public Appointment createAppointment(Appointment appointment) {
    return appointmentRepository.save(appointment);
    }
}
