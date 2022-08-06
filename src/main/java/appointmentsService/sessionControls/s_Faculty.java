package com.appointmentsService.sessionControls;

import com.appointmentsService.model.Course;
import com.appointmentsService.model.Faculty;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class s_Faculty extends sessionParentClass{
    void createFaculty(String _Title) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Faculty faculty = new Faculty();
                faculty.setTitle(_Title);

                session.persist(faculty);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void changeFaculty(int _ID, String _Title) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                String hql = "FROM Faculty f WHERE f.ID = :_ID";
                Query query = session.createQuery(hql, Faculty.class);
                query.setParameter("title", _Title);

                Optional<Faculty> first = query.getResultList().stream().findFirst();
                Faculty faculty = first.get();
                faculty.setTitle(_Title);
                session.persist(faculty);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void printAllFaculties(){
        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            List<Faculty> faculties = session.createQuery("from Faculty", Faculty.class).list();
            for (Faculty f : faculties) {
                System.out.println(f.toString());
            }
        }
    }
}
