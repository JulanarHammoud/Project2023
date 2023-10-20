package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;

public class QuestionManager1 implements Serializable {
    CourseTeacher courseTeacher;
    GetForManager getForManager;

    public QuestionManager1(CourseTeacher courseTeacher, GetForManager getForManager) {
        this.courseTeacher = courseTeacher;
        this.getForManager = getForManager;
    }

    public CourseTeacher getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(CourseTeacher courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public GetForManager getGetForManager() {
        return getForManager;
    }

    public void setGetForManager(GetForManager getForManager) {
        this.getForManager = getForManager;
    }
}
