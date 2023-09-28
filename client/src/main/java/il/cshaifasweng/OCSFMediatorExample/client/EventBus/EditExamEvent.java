package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.ExamSubjectTeacherEdit;

public class EditExamEvent{
    ExamSubjectTeacherEdit examSubjectTeacherEdit;

    public EditExamEvent(ExamSubjectTeacherEdit examSubjectTeacherEdit) {
        this.examSubjectTeacherEdit = examSubjectTeacherEdit;
    }

    public ExamSubjectTeacherEdit getExamSubjectTeacherEdit() {
        return examSubjectTeacherEdit;
    }

    public void setExamSubjectTeacherEdit(ExamSubjectTeacherEdit examSubjectTeacherEdit) {
        this.examSubjectTeacherEdit = examSubjectTeacherEdit;
    }
}
