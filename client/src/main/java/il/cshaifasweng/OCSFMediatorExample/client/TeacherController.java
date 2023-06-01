package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.client.App;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import il.cshaifasweng.OCSFMediatorExample.entities.stlist;

import java.util.List;

public class TeacherController {

    int lastIndex=SimpleClient.getParams().size()-1;
    Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex);
    List<Subject> list =teacher.getSubjects();

    public void initialize(){

    }


}
