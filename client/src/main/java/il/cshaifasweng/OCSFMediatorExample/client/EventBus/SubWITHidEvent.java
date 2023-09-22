package il.cshaifasweng.OCSFMediatorExample.client.EventBus;
import il.cshaifasweng.OCSFMediatorExample.entities.SubWITHid;

public class SubWITHidEvent {
    SubWITHid subbANDid;

    public SubWITHidEvent(SubWITHid subbANDid) {
        this.subbANDid = subbANDid;
    }

    public SubWITHid getSubbANDid() {
        return subbANDid;
    }

    public void setSubbANDid(SubWITHid subbANDid) {
        this.subbANDid = subbANDid;
    }
}
