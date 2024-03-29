package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamCourse;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class subjectController {

    @FXML
    private ComboBox<String> Combo;
    @FXML
    private Button Next;
    @FXML
    private Button returnbutton;
    int lastIndex= SimpleClient.getParams().size()-1;
    ExamCourse cr = (ExamCourse) SimpleClient.getParams().get(lastIndex);

    List<SubjectTeacher> list =  cr.getCourse().getSubjectTeacher();

    //int lastsecIndex= SimpleClient.getParams().size()-2;
    //Teacher teacher = (Teacher) SimpleClient.getParams().get(lastsecIndex);
    // List<CourseTeacher> list=teacher.getCourses();

    public subjectController() {
    }

    public void initialize(){
        for(int i=0;i<list.size();i++){
            Combo.getItems().addAll(list.get(i).getSb_name());}
    }

    @FXML
    void Next(ActionEvent event) {
        try {
            System.out.println("I WILL GRAMMAR");
            String choose = Combo.getSelectionModel().getSelectedItem();
            cr.getCourse().setName(choose);
            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#MakeExam2");
            message.add(choose);
            message.add(cr.getExamId());
            message.add(cr.getCourse().getId_String());
            message.add(cr);
            System.out.println("Selected item: " + choose);
            SimpleClient.getClient().sendToServer(message);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

}
