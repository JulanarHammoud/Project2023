package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class ExamController {

    @FXML
    private Button Next;

    @FXML
    private TextField NumQ;

    @FXML
    private TextField PutSNotes;

    @FXML
    private TextField PutTNotes;

    @FXML
    private TextField PutTimer;

    @FXML
    private TextField SNotes;

    @FXML
    private TextField TNotes;


    @FXML
    private TextField putNumQ;

    @FXML
    private TextField timer;

    int lastIndex= SimpleClient.getParams().size()-1;
    LinkedList<Object> mes = (LinkedList<Object>)SimpleClient.getParams().get(lastIndex);
    Teacher teacher = (Teacher) mes.getFirst();
    CourseTeacher courseteacher = (CourseTeacher) mes.get(1);
    SubjectTeacher sub = (SubjectTeacher) mes.get(2);

    public void initialize(){

    }


    @FXML
    void Next(ActionEvent event) {
        try{

            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#MakeExam");
            message.add(putNumQ.getText());
            message.add(PutTNotes.getText());
            message.add(PutTimer.getText());
            message.add(PutSNotes.getText());
            message.add(courseteacher);
            message.add(sub);
            String kl=teacher.getFirstName();
            kl=kl+" ";
            kl=kl+teacher.getLastName();
            message.add(kl);
            message.add(teacher);

            System.out.println("Selected item: " + putNumQ.getText());
            System.out.println("Selected item: " + PutTNotes.getText());
            System.out.println("Selected item: " + PutTimer.getText());
            System.out.println("Selected item: " + PutSNotes.getText());
            SimpleClient.getClient().sendToServer(message);

         /*   LinkedList<Object> list1=new LinkedList<>();
            list1.add("#SubjectTeacher");
            list1.add(choose);
            SimpleClient.getClient().sendToServer(list1);*/

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void returnbutton(ActionEvent event) {
        try{
            SimpleClient.getParams().add(teacher);
            setRoot("teacherpage");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogOut(ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        SimpleClient.getClient().sendToServer(message);
    }


}





//    private int IdCode;
//   // private Timer timer;   v
//    private int NumOfQuestions;  v
//    private String subject;   v
//    private String teacher;
//    private String TeacherNotes;   v
//    private String StudentNotes;   v
//    private String code;
