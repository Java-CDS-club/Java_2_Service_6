package appointmentsService.sessionControls;

import appointmentsService.model.Appointment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.sql.Timestamp;
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
    void createSomeAppointments() {

        var session = new s_Appointment();
        session.createAppointment("Gottesdienst", true, 2,
                LocalDateTime.parse("2022-07-04T19:34:50.63"),
                LocalDateTime.parse("2022-07-04T20:34:50.63")
                ,45,4,6,2);

        var all_appointments_list = session.listAllAppointments();
        Assertions.assertEquals(all_appointments_list.size(), 1);

        session.createAppointment("IT-KOM 2022", true, 2,
                LocalDateTime.parse("2022-02-04T19:34:50.63"),
                LocalDateTime.parse("2022-02-04T21:34:50.63")
                ,54,2,6,2);

        all_appointments_list = session.listAllAppointments();
        Assertions.assertEquals(all_appointments_list.size(), 2);

        }
    @Test
    void searchAppointments() {
        var session = new s_Appointment();
        session.createAppointment("Klausur Mathe 2", true, 2,
                LocalDateTime.parse("2022-01-04T19:34:50.63"),
                LocalDateTime.parse("2022-01-04T21:34:50.63")
                ,5,4,6,23);

        session.createAppointment("Klausur Betriebsysteme 2", true, 2,
                LocalDateTime.parse("2022-05-02T19:34:50.63"),
                LocalDateTime.parse("2022-05-05T21:34:50.63")
                ,6,4,6,21);

        session.createAppointment("Klausur Graphentheorie", true, 4,
                LocalDateTime.parse("2022-04-02T19:34:50.63"),
                LocalDateTime.parse("2022-04-02T21:34:50.63")
                ,2,4,6,3);

        session.createAppointment("Stochastik Übung Gruppe 1", true, 2,
                LocalDateTime.parse("2022-03-02T19:34:50.63"),
                LocalDateTime.parse("2022-03-02T21:34:50.63"),
                7,4,6,7);

        var searchResults = session.searchAppointments("Klausur",2,null,null,null, null,null,null);
        Assertions.assertEquals(searchResults.size(), 2);
        for (Appointment result : searchResults) {
            Assertions.assertTrue(result.toString().contains("Semester = 2"));
            Assertions.assertTrue(result.toString().contains("Klausur"));}

        searchResults = session.searchAppointments(null,2,null,null,null,null,null,null);
        Assertions.assertEquals(searchResults.size(), 3);
        for (Appointment result : searchResults) {
            Assertions.assertTrue(result.toString().contains("Semester = 2"));}

        searchResults = session.searchAppointments(null,4,null,null,null,null,null,null);
        Assertions.assertEquals(searchResults.size(), 1);
        for (Appointment result : searchResults) {
            Assertions.assertTrue(result.toString().contains("Semester = 4"));}

        session.changeAppointment(3, null,true,2,null,null
                ,null,null,null,null);

        searchResults = session.searchAppointments("Graphentheorie",2,null,null,null, null,null,null);
        Assertions.assertEquals(searchResults.size(), 1);
    }
    @Test
    void ChangeAttributes(){
        var session = new s_Appointment();

        session.createAppointment("Klausur Graphentheorie", true, 4,
                LocalDateTime.parse("2022-04-02T19:34:50.63"),
                LocalDateTime.parse("2022-04-02T21:34:50.63")
                ,2,4,6,3);
        var searchResults = session.searchAppointments("Klausur Graphentheorie",4,null,null,null, null,null,null);
        Assertions.assertEquals(searchResults.size(), 1);

        session.changeAppointment(1, "Klausur Spanisch",null,2,null,null
                ,null,null,null,null);

        searchResults = session.searchAppointments("Klausur Spanisch",2,Timestamp.valueOf(LocalDateTime.parse("2022-04-02T19:34:50.63")),null,null, null,null,null);
        Assertions.assertEquals(searchResults.size(), 1);

        searchResults = session.searchAppointments("Klausur Graphentheorie",4, null,null,null, null,null,null);
        Assertions.assertEquals(searchResults.size(), 0);

    }
}