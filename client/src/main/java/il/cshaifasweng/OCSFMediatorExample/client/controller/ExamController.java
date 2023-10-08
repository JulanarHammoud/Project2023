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
    private TextField PutSNotes;

    @FXML
    private TextField PutTNotes;

    @FXML
    private TextField PutTimer;

    int lastIndex= SimpleClient.getParams().size()-1;
    LinkedList<Object> mes = (LinkedList<Object>)SimpleClient.getParams().get(lastIndex);
    Teacher teacher = (Teacher) mes.getFirst();
    CourseTeacher courseteacher = (CourseTeacher) mes.get(1);
    SubjectTeacher sub = (SubjectTeacher) mes.get(2);

    @FXML
    void Next(ActionEvent event) {
        try{

            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#MakeExam");
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
            System.out.println("Selected item: " + PutTNotes.getText());
            System.out.println("Selected item: " + PutTimer.getText());
            System.out.println("Selected item: " + PutSNotes.getText());
            SimpleClient.getClient().sendToServer(message);
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
        message.add(teacher.getId());
        message.add("teacher");
        SimpleClient.getClient().sendToServer(message);
    }
}