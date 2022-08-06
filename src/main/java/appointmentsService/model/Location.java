package appointmentsService.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "LOCATION")
public class Location {
    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    @SequenceGenerator(name = "PERSON_SEQ", allocationSize = 1)
    private int ID;
    @Column(name="BUILDING")
    private String Building;
    @Column(name="ROOM")
    private String Room;

    public Location(){}

    Location(String _Building, String _Room){
        this.ID = count.incrementAndGet();
        if (_Building != null){ this.Building = _Building; }
        if (_Room != null){ this.Room = _Room; }
    }

    //---------------------------------------------------------

    void setRoom(String _Room){
        if (_Room != null){ this.Room = _Room; }
    }
    void setBuilding(String _Building){
        if (_Building != null){ this.Building = _Building; }
    }

    //---------------------------------------------------------

    int getID(){ return this.ID; }
    String getBuilding(){ return this.Building; }
    String getRoom(){ return this.Room; }

    //---------------------------------------------------------

    @Override
    public String toString(){
        return "Person{" +
                "ID = " + ID +
                ", Building = " + Building +
                ", Room = " + Room + "}";
    }
}
