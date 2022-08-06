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
@Table(name="PERSON")
public class Person {
    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    @SequenceGenerator(name = "PERSON_SEQ", allocationSize = 1)
    private int ID;
    @Column(name="NAME")
    private String name;

    public Person() {
    }

    Person(String _Name){
        this.ID = count.incrementAndGet();
        this.name = _Name;
    }

    //---------------------------------------------------------

    public void setName(String _Name){
        this.name = _Name;
    }

    //---------------------------------------------------------

    public String getName(){  return this.name; }
    public int getID(){ return this.ID; }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", Name='" + name + '\'' +
                '}';
    }
}