/*package il.cshaifasweng.OCSFMediatorExample.client.controller;
import il.cshaifasweng.OCSFMediatorExample.client.EventBus.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;


public class TeacherController {
    @FXML
    private ListView<String> ListViewSubject=new ListView<>();

    int lastIndex= SimpleClient.getParams().size()-1;
    Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex);
    List<SubjectTeacher> list =teacher.getSubjects();
    ObservableList<String> items = FXCollections.observableArrayList();

    public void initialize(){
        for(int i=0;i<list.size();i++){
            items.add(String.valueOf(list.get(i).getSb_name()));}
        ListViewSubject.setItems(items);
    }

    }*/
package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Subject;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class TeacherController {
    int lastIndex= SimpleClient.getParams().size()-1;
    Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex);
    List<CourseTeacher> courses = teacher.getCourses();


    @FXML
    private Accordion container;

    @FXML
    private Button exams;

    @FXML
    private Button grades;

    @FXML
    private Button questions;
    @FXML
    private Button subjects;


    @FXML
    void initialize() throws IOException {
        for(CourseTeacher course : courses){
            Accordion content = new Accordion();
            TitledPane courseContainer = new TitledPane();
            courseContainer.setText(course.getName());
            List<SubjectTeacher> subjects = course.getSubjectTeacher();
            for (SubjectTeacher sub : subjects){
                TitledPane subContainer = new TitledPane();
                subContainer.setText(sub.getSb_name());
                VBox buttons = new VBox(4);
                Button showExams = new Button("Exams");
                Button makeExam = new Button("Make Exam");
                makeExam.setOnAction(this::examsaction);
                Button qes = new Button("Questions");
                Button grades = new Button("Gardes");
                buttons.getChildren().addAll(showExams,makeExam,qes,grades);
                subContainer.setContent(buttons);
                content.getPanes().add(subContainer);
            }
            courseContainer.setContent(content);
            container.getPanes().add(courseContainer);

        }

    }

    @FXML
    void examsaction(ActionEvent event) {
        try{
            SimpleClient.getParams().add(teacher);
            setRoot("MakeExam");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gradesaction(ActionEvent event) {



    }

    @FXML
    void questionaction(ActionEvent event) {
        try{
            SimpleClient.getParams().add(teacher);
            setRoot("choose_course");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

