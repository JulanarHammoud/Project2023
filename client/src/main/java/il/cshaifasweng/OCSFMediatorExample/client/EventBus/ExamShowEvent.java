package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.ExamSubjectTeacher;

public class ExamShowEvent {
    ExamSubjectTeacher examSubjectTeacher;

    public ExamShowEvent(ExamSubjectTeacher examSubjectTeacher) {
        this.examSubjectTeacher = examSubjectTeacher;
    }

    public ExamSubjectTeacher getExamSubjectTeacher() {
        return examSubjectTeacher;
    }

    public void setExamSubjectTeacher(ExamSubjectTeacher examSubjectTeacher) {
        this.examSubjectTeacher = examSubjectTeacher;
    }
}
