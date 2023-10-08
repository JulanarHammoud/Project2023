package il.cshaifasweng.OCSFMediatorExample.entities;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "Question")
public class Question implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String question;
    private String ans1; //israa
    private String ans2; //israa
    private String ans3; //israa
    private String ans4; //israa
    private String note;
    private String the_right_ans; //israa
    private String the_student_ans;
    @Transient
    private Boolean exist = false;
    @Transient
    private Boolean select_to_delete =false;
    @Transient
    private Boolean select_to_add = false;

    public Question(String question, String ans1, String ans2, String ans3, String ans4, String note,String the_right_ans) {
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.note = note;
        this.the_right_ans = the_right_ans;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }

    public String getThe_right_ans() {
        return the_right_ans;
    }

    public void setThe_right_ans(String the_right_ans) {
        this.the_right_ans = the_right_ans;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public Boolean getSelect_to_delete() {
        return select_to_delete;
    }

    public void setSelect_to_delete(Boolean select_to_delete) {
        this.select_to_delete = select_to_delete;
    }

    public Boolean getSelect_to_add() {
        return select_to_add;
    }

    public void setSelect_to_add(Boolean select_to_add) {
        this.select_to_add = select_to_add;
    }

    public String getThe_student_ans() {
        return the_student_ans;
    }

    public void setThe_student_ans(String the_student_ans) {
        this.the_student_ans = the_student_ans;
    }

    public Question(){}
}
