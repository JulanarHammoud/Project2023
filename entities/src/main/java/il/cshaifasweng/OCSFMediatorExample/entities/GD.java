package il.cshaifasweng.OCSFMediatorExample.entities;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GD {
    String course;
    String date;
    int grade;
    int distribution;
    public SimpleStringProperty courseProperty() {
        return new SimpleStringProperty(course);
    }

    public SimpleStringProperty dateProperty() {
        return new SimpleStringProperty(date);
    }

    // IntegerProperty for "grade"
    public SimpleIntegerProperty gradeProperty() {
        return new SimpleIntegerProperty(grade);
    }

    // IntegerProperty for "distribution"
    public SimpleIntegerProperty distributionProperty() {
        return new SimpleIntegerProperty(distribution);
    }

    public GD(String course, String date, int grade, int distribution) {
        this.course = course;
        this.date = date;
        this.grade = grade;
        this.distribution = distribution;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getDistribution() {
        return distribution;
    }

    public void setDistribution(int distribution) {
        this.distribution = distribution;
    }
}
