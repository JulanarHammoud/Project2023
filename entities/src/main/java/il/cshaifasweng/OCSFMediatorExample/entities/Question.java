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
    private String the_right_ans; //israa

    public Question(String question, String ans1, String ans2, String ans3, String ans4, String the_right_ans) {
        //Id = id;
        this.question = question;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
        this.the_right_ans = the_right_ans;
    }

    public String GetAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }
    public String GetAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String GetAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String GetAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }
    public String GetThe_right_ans() {
        return the_right_ans;
    }

    public void setThe_right_ans(String the_right_ans) {
        this.the_right_ans = the_right_ans;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Question(){}
}
