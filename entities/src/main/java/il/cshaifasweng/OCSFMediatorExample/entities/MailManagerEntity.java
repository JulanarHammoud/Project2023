package il.cshaifasweng.OCSFMediatorExample.entities;

import java.io.Serializable;
import java.util.List;

public class MailManagerEntity implements Serializable {
    List<ManagerMessage> LMTM;
    GetForManager GFM;

    public MailManagerEntity(List<ManagerMessage> LMTM, GetForManager GFM) {
        this.LMTM = LMTM;
        this.GFM = GFM;
    }

    public List<ManagerMessage> getLMTM() {
        return LMTM;
    }

    public void setLMTM(List<ManagerMessage> LMTM) {
        this.LMTM = LMTM;
    }

    public GetForManager getGFM() {
        return GFM;
    }

    public void setGFM(GetForManager GFM) {
        this.GFM = GFM;
    }
}
