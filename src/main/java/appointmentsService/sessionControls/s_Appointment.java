package com.appointmentsService.sessionControls;

import com.appointmentsService.model.Appointment;
import com.appointmentsService.model.Person;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class s_Appointment extends sessionParentClass {
    void createAppointment(String _Title,
                           boolean _isPublic,
                           int _Semester,
                           LocalDateTime _startTimeDate,
                           LocalDateTime _endTimeDate,
                           int _CourseID,
                           int _FacultyID,
                           int _LocID,
                           int _OrganizerID) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Appointment appointment = new Appointment();
                appointment.setTitle(_Title);
                appointment.setPublicMode(_isPublic);
                appointment.setSemester(_Semester);
                appointment.setNewStart(_startTimeDate);
                appointment.setNewEnd(_endTimeDate);

                appointment.setCourseID(_CourseID);
                appointment.setFacultyID(_FacultyID);
                appointment.setLocationID(_LocID);
                appointment.setPersonID(_OrganizerID);

                session.persist(appointment);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void changeAppointment(int _ID,
                           String _Title,
                           boolean _isPublic,
                           int _Semester,
                           LocalDateTime _startTimeDate,
                           LocalDateTime _endTimeDate,
                           int _CourseID,
                           int _FacultyID,
                           int _LocID,
                           int _OrganizerID) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                String hql = "FROM Appointments a WHERE a.ID = :_ID";
                Query query = session.createQuery(hql, Appointment.class);
                if (_Title != null) query.setParameter("title", _Title);
                query.setParameter("ispublic", _isPublic);
                query.setParameter("semester", _Semester);
                query.setParameter("startdatetime", _startTimeDate);
                query.setParameter("enddatetime", _endTimeDate);
                query.setParameter("course_id", _CourseID);
                query.setParameter("faculty_id", _FacultyID);
                query.setParameter("location_id", _LocID);
                query.setParameter("organizer_id", _OrganizerID);

                Optional<Appointment> first = query.getResultList().stream().findFirst();
                Appointment appointment = first.get();
                appointment.setTitle(_Title);
                appointment.setSemester(_Semester);
                appointment.setNewStart(_startTimeDate);
                appointment.setNewEnd(_endTimeDate);
                appointment.setCourseID(_CourseID);
                appointment.setFacultyID(_FacultyID);
                appointment.setLocationID(_LocID);
                appointment.setPersonID(_OrganizerID);
                session.persist(appointment);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void printAllAppointments(){
        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            List<Appointment> appointments = session.createQuery("from Appointments", Appointment.class).list();
            for (Appointment a : appointments) {
                System.out.println(a.toString());
            }
        }
    }
}
