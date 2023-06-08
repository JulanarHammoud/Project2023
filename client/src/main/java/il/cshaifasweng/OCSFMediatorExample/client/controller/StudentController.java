package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.entities.SubjectStudent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.scene.control.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


public class StudentController {
    int lastIndex= SimpleClient.getParams().size()-1;
    Student student = (Student) SimpleClient.getParams().get(lastIndex);
    List<SubjectStudent> list =student.getSubjects();

    @FXML
    private Label StName;

    @FXML
    private Button exams;

    @FXML
    private Button grades;

    @FXML
    private Accordion accordian;

    @FXML
    private Button logout;

    @FXML
    void initialize() {
        StName.setText(String.valueOf(student.getFirstName()+ " " + student.getLastName()));

        for(int i=0;i<list.size();i++){
            // Create TitledPane
            TitledPane titledPane = new TitledPane();
            titledPane.setText(list.get(i).getSb_name());
            // Add content to TitledPane 1
            VBox content1 = new VBox();
            Button button = new Button("test1");
            content1.getChildren().add(button);
            titledPane.setContent(content1);
            // Add TitledPanes to the Accordion
            accordian.getPanes().add(titledPane);}
    }


    @FXML
   public void LogOut(ActionEvent event) throws IOException {

        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        message.add(student.getId());
        SimpleClient.getClient().sendToServer(message);

    }


}


