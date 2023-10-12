package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.ToDuration;

public class ToDurationEvent {
    ToDuration toDuration;

    public ToDurationEvent(ToDuration toDuration) {
        this.toDuration = toDuration;
    }

    public ToDuration getToDuration() {
        return toDuration;
    }

    public void setToDuration(ToDuration toDuration) {
        this.toDuration = toDuration;
    }
}
