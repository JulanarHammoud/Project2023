package il.cshaifasweng.OCSFMediatorExample.client.EventBus;
import il.cshaifasweng.OCSFMediatorExample.entities.StudentWillMakeEx;
public class StudentWillMakeExEvent {
    private StudentWillMakeEx StEx;

    public StudentWillMakeEx getStEx() {
        return StEx;
    }

    public void setStEx(StudentWillMakeExEvent stEx) {
        StEx = stEx.getStEx();
    }

    public StudentWillMakeExEvent(StudentWillMakeEx stEx) {
        StEx = stEx;
    }
}
