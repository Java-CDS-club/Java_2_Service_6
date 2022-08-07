package appointmentsService.sessionControls;

import appointmentsService.model.Appointment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class AppointmentTest {

    @Test
    void createFaculty() {
    }

    @Test
    void createPerson() {
    }

    @Test
    void createLocation() {
    }

    @Test
    void createCourse() {
    }



    @Test
    void printAllFaculties() {
    }

    @Test
    void printAllLocations() {
    }

    @Test
    void printAllPersons() {
    }


    @Test
    void printAllCourses() {
    }
    @Test
    void createAppointment() {
    }

    @Test
    void changeAppointment() {
    }

    @Test
    void createSomeAppointments() {
        var session = new s_Appointment();
        session.createAppointment("df", true, 2,
                LocalDateTime.parse("2022-07-04T19:34:50.63"),
                LocalDateTime.parse("2022-07-04T20:34:50.63")
                ,2,4,6,2);
        session.createAppointment("Klausur Mathe 2", true, 2,
                LocalDateTime.parse("2022-02-04T19:34:50.63"),
                LocalDateTime.parse("2022-02-04T21:34:50.63")
                ,2,52,6,2);
        var all_appointments_list = session.listAllAppointments();
        Assertions.assertEquals(all_appointments_list.size(), 2);

        var searchResults = session.searchAppointments("Klausur",2,null,null,null,null,null,null);
        Assertions.assertEquals(searchResults.size(), 1);
        for (Appointment result : searchResults) {
            Assertions.assertTrue(result.toString().contains("Semester = 2"));
            Assertions.assertTrue(result.toString().contains("Klausur"));
        }
    }

    @Test
    void searchAppointments() {
    }
}