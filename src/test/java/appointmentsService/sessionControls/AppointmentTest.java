package appointmentsService.sessionControls;

import appointmentsService.model.Appointment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
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


    /**
     * verifying that the number of total appointments increases whenever an appointment is added
     */
    @Test
    void createSomeAppointments() {

        var session = new s_Appointment();
        session.createAppointment("Gottesdienst", true, 2,
                LocalDateTime.parse("2022-07-04T19:34:50.63"),
                LocalDateTime.parse("2022-07-04T20:34:50.63")
                ,45,4,6,2);

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

        var all_appointments_list = session.listAllAppointments();
        var numberOfAppointments = all_appointments_list.size();

        session.createAppointment("IT-KOM 2022", true, 2,
                LocalDateTime.parse("2022-02-04T19:34:50.63"),
                LocalDateTime.parse("2022-02-04T21:34:50.63")
                ,54,2,6,2);

        all_appointments_list = session.listAllAppointments();
        Assertions.assertEquals(all_appointments_list.size(), numberOfAppointments + 1);

        }

    /**
     * testing the searchAppointments function
     */
    @Test
    void searchAppointments() {
        var session = new s_Appointment();


        var searchResults = session.searchAppointments("Klausur",2,null,null,null, null,null,null);
        Assertions.assertNotEquals(searchResults.size(), 0);

        searchResults = session.searchAppointments("Klausur",3,null,null,null, null,null,null);
        Assertions.assertEquals(searchResults.size(), 0);

        for (Appointment result : searchResults) {
            Assertions.assertTrue(result.toString().contains("Semester = 2"));
            Assertions.assertTrue(result.toString().contains("Klausur"));}

        searchResults = session.searchAppointments(null,2,LocalDateTime.parse("2037-04-02T19:34:50.63"),LocalDateTime.parse("2038-04-02T19:34:50.63"),null,null,null,null);
        Assertions.assertEquals(searchResults.size(), 0);
        for (Appointment result : searchResults) {
            Assertions.assertTrue(result.toString().contains("Semester = 2"));}

        searchResults = session.searchAppointments(null,4, LocalDateTime.parse("2022-04-02T19:34:50.63"),LocalDateTime.parse("2022-05-02T19:34:50.63"),null,null,null,null);
        Assertions.assertNotEquals(searchResults.size(), 0);
        for (Appointment result : searchResults) {
            Assertions.assertTrue(result.toString().contains("Semester = 4"));}
    }

    /**
     * changing some attributes fron existing appointment and verifying the results
     */
    @Test
    void ChangeAttributes(){
        var session = new s_Appointment();


        session.changeAppointment(1, "Klausur Spanisch",null,2,null,null
                ,null,null,null,null);

        var searchResults = session.searchAppointments("Klausur Spanisch",2,LocalDateTime.parse("2022-04-02T19:34:50.63"),null,null, null,null,null);
        Assertions.assertNotEquals(searchResults.size(), 0);

    }
}