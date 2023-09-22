package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Exam;

public class ExamEvent {
    Exam exx;

    public ExamEvent(Exam exx) {
        this.exx = exx;
    }

    public Exam getExx() {
        return exx;
    }

    public void setExx(Exam exx) {
        this.exx = exx;
    }
}
