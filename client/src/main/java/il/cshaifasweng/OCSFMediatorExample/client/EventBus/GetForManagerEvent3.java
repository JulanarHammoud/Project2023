package il.cshaifasweng.OCSFMediatorExample.client.EventBus;

import il.cshaifasweng.OCSFMediatorExample.entities.GetForManager3;

public class GetForManagerEvent3 {
    GetForManager3 getForManager3;

    public GetForManagerEvent3(GetForManager3 getForManager3) {
        this.getForManager3 = getForManager3;
    }

    public GetForManager3 getGetForManager3() {
        return getForManager3;
    }

    public void setGetForManager3(GetForManager3 getForManager3) {
        this.getForManager3 = getForManager3;
    }
}
