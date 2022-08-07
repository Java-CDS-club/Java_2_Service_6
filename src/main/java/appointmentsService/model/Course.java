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
@Table(name="COURSE")
public class Course {
    private static final AtomicInteger count = new AtomicInteger(0);

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSON_SEQ")
    @SequenceGenerator(name = "PERSON_SEQ", allocationSize = 1)
    private int ID;
    @Column(name="TITLE")
    private String Title;
    @Column(name="FORSEMESTER")
    private int forSemester;

    public Course(){}

    Course(String _Title, int _forSemester){
        this.ID = count.incrementAndGet();
        this.Title = _Title;
        this.forSemester = _forSemester;
    }

    //---------------------------------------------------------

    public void setTitle(String _Title){
        if (_Title!= null){ this.Title = _Title; }
    }
    public void setForSemester(int _forSemester){
        this.forSemester = _forSemester;
    }

    //---------------------------------------------------------

    public int getID(){ return this.ID; }
    public String getTitle(){ return this.Title; }
    public int getForWhatSemester(){ return this.forSemester; }

    //----------------------------------------------------------

    @Override
    public String toString(){
        return "Course{" +
                "ID = " + ID +
                "Title = " + Title +
                "forSemester = " + forSemester + "}";
    }
}