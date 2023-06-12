package il.cshaifasweng.OCSFMediatorExample.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Timer;

@Entity
@Table(name= "Exams")
public class Exam implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int IdCode;
    private Timer timer;
    private int NumOfQuestions;
    private String subject;
    private String teacher;
    private String TeacherNotes;
    private String StudentNotes;
    private String code;




}
