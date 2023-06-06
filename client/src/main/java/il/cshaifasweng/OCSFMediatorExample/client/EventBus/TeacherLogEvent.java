package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;

public class TeacherLogEvent {
    Teacher teacher;

    public TeacherLogEvent(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
