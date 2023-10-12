package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.GradeandExam;

public class GradeExamEvent {
    GradeandExam gradeexam;

    public GradeandExam getGradeexam() {
        return gradeexam;
    }

    public void setGradeexam(GradeandExam gradeexam) {
        this.gradeexam = gradeexam;
    }

    public GradeExamEvent(GradeandExam gradeexam) {
        this.gradeexam = gradeexam;
    }
}
