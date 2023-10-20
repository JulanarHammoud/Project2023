package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.Formangerandcourseteacher;

public class CourseFormangerEvent {
    Formangerandcourseteacher CF;

    public Formangerandcourseteacher getCF() {
        return CF;
    }

    public CourseFormangerEvent(Formangerandcourseteacher CF) {
        this.CF = CF;
    }

    public void setCF(Formangerandcourseteacher CF) {
        this.CF = CF;
    }
}
