package appointmentsService.sessionControls;

import appointmentsService.model.Faculty;
import appointmentsService.model.Person;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class s_Person extends sessionParentClass{
    void createPerson(String _Name) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Person person = new Person();
                person.setName(_Name);

                session.persist(person);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void changePerson(int _ID, String _Name) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                String hql = "FROM Person p WHERE p.ID = :_ID";
                Query query = session.createQuery(hql, Faculty.class);
                query.setParameter("name", _Name);

                Optional<Person> first = query.getResultList().stream().findFirst();
                Person person = first.get();
                person.setName(_Name);
                session.persist(person);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void printAllPersons(){
        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            List<Person> persons = session.createQuery("from Person", Person.class).list();
            for (Person p : persons) {
                System.out.println(p.toString());
            }
        }
    }
}
