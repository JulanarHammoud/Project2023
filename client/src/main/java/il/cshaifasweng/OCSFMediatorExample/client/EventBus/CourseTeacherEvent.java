package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;

public class CourseTeacherEvent{
    CourseTeacher ct;

    public CourseTeacherEvent(CourseTeacher ct) {
        this.ct = ct;
    }

    public CourseTeacher getCt() {
        return ct;
    }

    public CourseTeacherEvent() {
    }

    public void setCt(CourseTeacher ct) {
        this.ct = ct;
    }
}
