package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.ExamStudentsandGrade;

public class ExamandGrageEvent {
    ExamStudentsandGrade exgr;

    public ExamStudentsandGrade getExgr() {
        return exgr;
    }

    public ExamandGrageEvent(ExamStudentsandGrade exgr) {
        this.exgr = exgr;
    }

    public void setExgr(ExamStudentsandGrade exgr) {
        this.exgr = exgr;
    }
}
