package appointmentsService.sessionControls;

import appointmentsService.model.Appointment;
import appointmentsService.model.Course;
import appointmentsService.model.Person;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class s_Course extends sessionParentClass{
    void createCourse(String _Title, int _forSemester) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Course course = new Course();
                course.setTitle(_Title);
                course.setForSemester(_forSemester);

                session.persist(course);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void changeCourse(int _ID, String _Title, int _forSemester) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                String hql = "FROM Course c WHERE c.ID = :_ID";
                Query query = session.createQuery(hql, Course.class);
                if (_Title != null) query.setParameter("title", _Title);
                query.setParameter("forsemester", _forSemester);

                Optional<Course> first = query.getResultList().stream().findFirst();
                Course course = first.get();
                course.setTitle(_Title);
                course.setForSemester(_forSemester);
                session.persist(course);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void printAllCourses(){
        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            List<Course> courses = session.createQuery("from Course", Course.class).list();
            for (Course c : courses) {
                System.out.println(c.toString());
            }
        }
    }
}
