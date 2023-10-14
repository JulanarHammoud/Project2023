package il.cshaifasweng.OCSFMediatorExample.entities;

import il.cshaifasweng.OCSFMediatorExample.entities.Question;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "DtailedQes")
public class DetailedQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    double points;
    String stdAnswer;
    @OneToOne
    @JoinColumn(name = "qes_id")
    Question question;

    public DetailedQuestion(double points, Question question) {
        this.points = points;
        this.question = question;
    }

    public DetailedQuestion(double points, String stdAnswer, Question question) {
        this.points = points;
        this.stdAnswer = stdAnswer;
        this.question = question;
    }


    public String getStdAnswer() {
        return stdAnswer;
    }

    public void setStdAnswer(String stdAnswer) {
        this.stdAnswer = stdAnswer;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public DetailedQuestion() {
    }
}
