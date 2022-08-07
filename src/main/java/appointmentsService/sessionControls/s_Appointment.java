package appointmentsService.sessionControls;

import appointmentsService.model.Appointment;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    /** change attributes of one appointment
     * @param _ID the id of the appointment to change (required argument)
     * @param _Title the new title (null if shoudn't change)
     * @param _isPublic the new privacy setting (null if shoudn't change)
     * @param _Semester the new semester (null if shoudn't change)
     * @param _startTimeDate the new start date and time (null if shoudn't change)
     * @param _endTimeDate the new end date and time (null if shoudn't change)
     * @param _CourseID the new course id (null if shoudn't change)
     * @param _FacultyID the new fuculty id (null if shoudn't change)
     * @param _LocID the new location id (null if shoudn't change)
     * @param _OrganizerID the new organizer id (null if shoudn't change)
     */
    void changeAppointment(int _ID,
                           String _Title,
                           Boolean _isPublic,
                           Integer _Semester,
                           LocalDateTime _startTimeDate,
                           LocalDateTime _endTimeDate,
                           Integer _CourseID,
                           Integer _FacultyID,
                           Integer _LocID,
                           Integer _OrganizerID) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                String hql = "FROM Appointment a WHERE a.ID = :_ID";
                Query query = session.createQuery(hql, Appointment.class).setParameter("_ID", _ID);


                Optional<Appointment> first = query.getResultList().stream().findFirst();
                Appointment appointment = first.get();
                if (_Title != null) appointment.setTitle(_Title);
                if (_Semester != null) appointment.setSemester(_Semester);
                if (_startTimeDate != null) appointment.setNewStart(_startTimeDate);
                if (_endTimeDate != null) appointment.setNewEnd(_endTimeDate);
                if (_CourseID != null) appointment.setCourseID(_CourseID);
                if (_FacultyID != null) appointment.setFacultyID(_FacultyID);
                if (_LocID != null) appointment.setLocationID(_LocID);
                if (_OrganizerID != null) appointment.setPersonID(_OrganizerID);
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

    /**
     * List all public appointments in the database
     *
     * @return list of type <Appointment> containing all appointments
     */
    public List<Appointment> listAllAppointments() {
        return searchAppointments(null, null, null, null, null, null, null, null);
    }

    /** Search for a public appointments by certain criteria
     * @param searchTerm search term included in the title of the appointment
     * @param fromDate define the start of time interval, can only be used in conjunction with "toDate" (see below)
     * @param toDate define the end of time interval, can only be used in conjunction with "fromDate"
     * @param courseID show appointments from one course
     * @param facultyID show appointments from one faculty
     * @param locationID show appointments from one location
     * @param personId show appointments from one organizer
     * @return list of type <Appointment> containing matching appointments
     */
    public List<Appointment> searchAppointments(String searchTerm, Integer semester, Timestamp fromDate, Timestamp toDate, BigInteger courseID,
                                                BigInteger facultyID, BigInteger locationID, BigInteger personId) {
        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Appointment> cr = cb.createQuery(Appointment.class);
            Root<Appointment> root = cr.from(Appointment.class);
            ArrayList<Predicate> predicates = new ArrayList<Predicate>();

            predicates.add(cb.isTrue(root.get("isPublic")));
            if (searchTerm != null) {
                predicates.add(cb.like(root.get("Title"), "%" + searchTerm + "%"));
            }
            if (fromDate != null  && toDate != null) {
                predicates.add(cb.or(cb.lessThan(root.get("Start"), toDate), cb.greaterThan(root.get("End"), fromDate )));
            }
            if (semester != null) {
                predicates.add(cb.equal(root.get("Semester"), semester));
            }
            if (courseID != null) {
                predicates.add(cb.equal(root.get("COURSE_ID"), courseID));
            }
            if (facultyID != null) {
                predicates.add(cb.equal(root.get("FACULTY_ID"), facultyID));
            }
            if (locationID != null) {
                predicates.add(cb.equal(root.get("LOCATION_ID"), locationID));
            }
            if (personId != null) {
                predicates.add(cb.equal(root.get("PERSON_ID"), personId));
            }
            Predicate[] array = predicates.toArray(new Predicate[0]);
            cr.select(root).where(array);
            return session.createQuery(cr).list();

        }
    }
}
