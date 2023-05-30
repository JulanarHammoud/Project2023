package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import il.cshaifasweng.OCSFMediatorExample.entities.stlist;

public class TeacherController {

    int lastIndex=SimpleClient.getParams().size()-1;
    Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex);


}
