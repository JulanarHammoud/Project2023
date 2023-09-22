package il.cshaifasweng.OCSFMediatorExample.entities;

public class SubWITHid {
    private int examId=-1;
    private SubjectTeacher subb;

    public SubWITHid(int examId, SubjectTeacher subb) {
        this.examId = examId;
        this.subb = subb;
    }

    public int getExamId() {
        return examId;
    }

    public SubjectTeacher getSubb() {
        return subb;
    }
}
