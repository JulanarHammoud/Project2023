package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.ExamStudent;

public class ExamStudentEvent {
    ExamStudent ex;

    public ExamStudent getEx() {
        return ex;
    }

    public void setEx(ExamStudent ex) {
        this.ex = ex;
    }

    public ExamStudentEvent(ExamStudent ex) {
        this.ex = ex;
    }
}
