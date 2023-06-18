package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.SubjectAndId;

public class SubIdEvent {
    SubjectAndId subId;

    public SubIdEvent(SubjectAndId subId) {
        this.subId = subId;
    }

    public SubjectAndId getSubId() {
        return subId;
    }
}
