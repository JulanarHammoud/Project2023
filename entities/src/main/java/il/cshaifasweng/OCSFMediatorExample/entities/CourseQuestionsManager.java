package il.cshaifasweng.OCSFMediatorExample.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CourseCoustionsM")
public class CourseQuestionsManager {
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "Questions_Table",joinColumns = @JoinColumn(name = "Subject_ID" ),inverseJoinColumns = {@JoinColumn(name = "Question_Column")})
    private List<Question> Questions ;
}
