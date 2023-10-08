package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.MailManagerEntity;

public class MailManagerEvent {
    MailManagerEntity MM;

    public MailManagerEvent(MailManagerEntity MM) {
        this.MM = MM;
    }
    public MailManagerEvent() {
    }
    public MailManagerEntity getMM() {
        return MM;
    }

    public void setMM(MailManagerEntity MM) {
        this.MM = MM;
    }
}
