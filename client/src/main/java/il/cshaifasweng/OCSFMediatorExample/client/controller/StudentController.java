//package il.cshaifasweng.OCSFMediatorExample.client.controller;
//
//import il.cshaifasweng.OCSFMediatorExample.entities.*;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
//
//import javafx.scene.control.Accordion;
//import javafx.scene.control.TitledPane;
//import javafx.scene.layout.VBox;
//
//
//import javafx.event.ActionEvent;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;
//
//
//public class StudentController {
//    int lastIndex= SimpleClient.getParams().size()-1;
//    Student student = (Student) SimpleClient.getParams().get(lastIndex);
//    List<CourseStudent> list =student.getCourses();
//
//    @FXML
//    private Label StName;
//
//    @FXML
//    private Button exams;
//
//    @FXML
//    private Button grades;
//
//    @FXML
//    private Accordion accordian;
//
//    @FXML
//    private Button logout;
//
//    @FXML
//    void initialize() {
//        StName.setText(String.valueOf(student.getFirstName()+ " " + student.getLastName()));
//
//
//        for(int i=0;i<list.size();i++){
//            // Create TitledPane
//            TitledPane titledPane = new TitledPane();
//            titledPane.setText(list.get(i).getName());
//            // Add content to TitledPane 1
//            VBox content1 = new VBox();
//            Button button = new Button("test1");
//            content1.getChildren().add(button);
//            titledPane.setContent(content1);
//            // Add TitledPanes to the Accordion
//            accordian.getPanes().add(titledPane);}
//    }
//
//
//    @FXML
//   public void LogOut(ActionEvent event) throws IOException {
//
//        LinkedList<Object> message = new LinkedList<Object>();
//        message.add("#LogOut");
//        message.add(student.getId());
//        SimpleClient.getClient().sendToServer(message);
//
//    }
//
//
//
//}
package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.entities.CourseStudent;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectStudent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;

import javafx.scene.layout.VBox;


import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;


public class StudentController {
    int lastIndex= SimpleClient.getParams().size()-1;
    Student student = (Student) SimpleClient.getParams().get(lastIndex);
    List<CourseStudent> list =student.getCourses();

    @FXML
    private Label StName;

    @FXML
    private Accordion accordian;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private Button examButton;

    @FXML
    private TextField examCode;

    @FXML
    private Button exams;

    @FXML
    private Button grades;

    @FXML
    private Button logout;


    @FXML
    void initialize() {
        examButton.setDisable(true);
        combo.setDisable(true);
        examCode.setDisable(true);
        StName.setText(String.valueOf(student.getFirstName() + " " + student.getLastName()));
        combo.getItems().add("computerized");
        combo.getItems().add("manual");
        //  for(int i=0;i<list.size();i++){
        //      // Create TitledPane
        //     TitledPane titledPane = new TitledPane();
        //     titledPane.setText(list.get(i).getName());
        //     // Add content to TitledPane 1
        //     VBox content1 = new VBox();
        //     Button button = new Button("test1");
        //     content1.getChildren().add(button);
        //     titledPane.setContent(content1);
        //     // Add TitledPanes to the Accordion
        //     accordian.getPanes().add(titledPane);}
        //}
    }


    @FXML
    public void LogOut(ActionEvent event) throws IOException {

        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        message.add(student.getId());
        SimpleClient.getClient().sendToServer(message);

    }
    @FXML
    void examsGold(ActionEvent event) {
        examButton.setDisable(false);
        combo.setDisable(false);
        examCode.setDisable(false);
    }

    @FXML
    void comboo(ActionEvent event) {

    }

    @FXML
    void examButtonn(ActionEvent event)  throws IOException{
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#GoToExStudent");
        String id_ex=examCode.getText();
        String choose=combo.getSelectionModel().getSelectedItem();
        if(choose=="manual")
        {System.out.println(id_ex+choose);
            message.add(id_ex);
            message.add(choose);
            String t= String.valueOf(student.getId());
            message.add(t);
            System.out.println(id_ex+choose+t);
            SimpleClient.getClient().sendToServer(message);
        }
    }

    @FXML
    void examCodee(ActionEvent event) {

    }



}




