package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class ExamCourse implements Serializable {
    private CourseTeacher course;
    private int examId;

    public ExamCourse(CourseTeacher course, int examId) {
        this.course = course;
        this.examId = examId;
    }

    public CourseTeacher getCourse() {
        return course;
    }

    public int getExamId() {
        return examId;
    }

}
