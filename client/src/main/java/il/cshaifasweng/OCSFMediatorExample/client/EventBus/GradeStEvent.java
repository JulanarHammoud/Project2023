package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.GradeSt;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;

public class GradeStEvent {
   GradeSt dd;

    public GradeSt getDd() {
        return dd;
    }

    public void setDd(GradeSt dd) {
        this.dd = dd;
    }

    public GradeStEvent(GradeSt dd) {
        this.dd = dd;
    }
}
