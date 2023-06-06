package il.cshaifasweng.OCSFMediatorExample.client.EventBus;
import il.cshaifasweng.OCSFMediatorExample.entities.stlist;

public class stdlEvent {
    private stlist stlist;

    public stlist getStudent() {
        return stlist;
    }

    public void setStudent(stlist student) {
        this.stlist = student;
    }
    public stdlEvent(stlist student){this.stlist=student;}
}
