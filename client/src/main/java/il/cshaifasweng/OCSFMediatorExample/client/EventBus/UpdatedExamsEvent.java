package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.UpdatedExams;

import java.io.Serializable;

public class UpdatedExamsEvent  {
    UpdatedExams exams;

    public UpdatedExamsEvent(UpdatedExams exams) {
        this.exams = exams;
    }

    public UpdatedExams getExams() {
        return exams;
    }

    public void setExams(UpdatedExams exams) {
        this.exams = exams;
    }
}
