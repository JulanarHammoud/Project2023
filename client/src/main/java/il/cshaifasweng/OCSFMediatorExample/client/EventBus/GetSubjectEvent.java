package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.GetSubject;

public class GetSubjectEvent {
    GetSubject getSubject;

    public GetSubjectEvent(GetSubject getSubject) {
        this.getSubject = getSubject;
    }

    public GetSubject getGetSubject() {
        return getSubject;
    }

    public void setGetSubject(GetSubject getSubject) {
        this.getSubject = getSubject;
    }
}
