package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.StudentWillDoEx;

public class StudentWillDoExEvent{
 private StudentWillDoEx ss;

    public StudentWillDoEx getSs() {
        return ss;
    }

    public void setSs(StudentWillDoEx ss) {
        this.ss = ss;
    }

    public StudentWillDoExEvent(StudentWillDoEx ss) {
        this.ss = ss;
    }
}
