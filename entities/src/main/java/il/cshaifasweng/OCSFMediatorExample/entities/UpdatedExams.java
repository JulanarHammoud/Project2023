package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

public class UpdatedExams implements Serializable {
    List<ExamStudent> exams;

    public UpdatedExams(List<ExamStudent> exams) {
        this.exams = exams;
    }

    public List<ExamStudent> getExams() {
        return exams;
    }

    public void setExams(List<ExamStudent> exams) {
        this.exams = exams;
    }
}
