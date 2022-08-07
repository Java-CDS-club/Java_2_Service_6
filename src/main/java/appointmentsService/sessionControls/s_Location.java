package appointmentsService.sessionControls;

import appointmentsService.model.Faculty;
import appointmentsService.model.Location;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class s_Location extends sessionParentClass{
    void createLocation(String _Building, String _Room) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                Location location = new Location();
                location.setBuilding(_Building);
                location.setRoom(_Room);

                session.persist(location);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void changeLocation(int _ID, String _Building, String _Room) {

        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            try {
                tx = session.beginTransaction();

                String hql = "FROM Location l WHERE l.ID = :_ID";
                Query query = session.createQuery(hql, Location.class);
                query.setParameter("building", _Room);
                query.setParameter("room", _Room);

                Optional<Location> first = query.getResultList().stream().findFirst();
                Location location = first.get();
                location.setBuilding(_Building);
                location.setRoom(_Room);
                session.persist(location);

                tx.commit();
            } catch (Exception e) {
                if (tx != null) tx.rollback();
                throw e;
            }
        }
    }

    //---------------------------------------------------------------------

    void printAllLocations(){
        getSessionFactory();
        try (Session session = sessionFactory.openSession()) {

            Transaction tx = null;
            List<Location> locations = session.createQuery("from Location", Location.class).list();
            for (Location l : locations) {
                System.out.println(l.toString());
            }
        }
    }
}
