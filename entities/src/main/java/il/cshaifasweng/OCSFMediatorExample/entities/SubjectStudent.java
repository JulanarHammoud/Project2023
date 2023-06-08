package il.cshaifasweng.OCSFMediatorExample.entities;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="SubjectStudent")
public class SubjectStudent extends Subject {

    public SubjectStudent(String sb_name) {
        super(sb_name);
    }

    public SubjectStudent(){}
}



