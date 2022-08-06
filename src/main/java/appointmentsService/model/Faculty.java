package appointmentsService.model;



import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FACULTY")
public class Faculty {
    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    @SequenceGenerator(name = "PERSON_SEQ", allocationSize = 1)
    private int ID;
    @Column(name="TITLE")
    private String Title;

    public Faculty(){}

    Faculty(String _Title){
        this.ID = count.incrementAndGet();
        this.Title = _Title;
    }

    //---------------------------------------------------------

    void setTitle(String _Title){
        this.Title = _Title;
    }

    //---------------------------------------------------------

    String getName(){  return this.Title; }
    int getID(){ return this.ID; }

    //---------------------------------------------------------

    @Override
    public String toString(){
        return "Faculty{" +
                "ID = " + ID +
                ", Title = " + Title + "}";
    }
}
