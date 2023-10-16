package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class ManagerController {
int lastIndex = SimpleClient.getParams().size() - 1;
    GetForManager getForManager = (GetForManager) SimpleClient.getParams().get(lastIndex);
    List<Teacher> teachers = getForManager.getTeachers();
    List<Student> students = getForManager.getStudents();
    List<CourseTeacher> courses = getForManager.getCourses();

    @FXML
    private TitledPane QuestionsPane;
    @FXML
    private TitledPane ExamsPane;
    @FXML
    private TitledPane ResultPane;
    CourseTeacher pickedcourse;
    CourseTeacher pickedcourse1;

    @FXML
    void initialize() throws IOException {
        VBox questionsVbox = new VBox(); // to hold the buttons to the questions pane
        VBox examsVbox = new VBox(); // to hold the buttons to the exams pane
        for(CourseTeacher course : courses){
            Button x =new Button(course.getName());
            Button y =new Button(course.getName());
            questionsVbox.getChildren().add(x);
            examsVbox.getChildren().add(y);
            x.setOnAction(event -> {
                try {
                    pickedcourse = course;
                    x(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            y.setOnAction(event -> {
                try {
                    pickedcourse1 = course;
                    y(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        QuestionsPane.setContent(questionsVbox);
        ExamsPane.setContent(examsVbox);

        VBox resultbuttons = new VBox(3); // Result pane in the Accordion
        Button TeachersButtons = new Button("Teachers Results");
        TeachersButtons.setOnAction(this::TeachersButtons);
        Button StudentsButtons = new Button("Students Results");
        StudentsButtons.setOnAction(this::StudentsButtons);
        Button CourseResultButtons = new Button("Courses Results");
        CourseResultButtons.setOnAction(this::CoursesResultButtons);
        resultbuttons.getChildren().addAll(TeachersButtons,StudentsButtons,CourseResultButtons);
        ResultPane.setContent(resultbuttons);
    }

    @FXML
    void TeachersButtons(ActionEvent event) {
        try {
            SimpleClient.getParams().add(getForManager);
            setRoot("TeacherResults");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void StudentsButtons(ActionEvent event) {
        try {
            SimpleClient.getParams().add(getForManager);
            setRoot("StudentResults");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void CoursesResultButtons(ActionEvent event) {
        try {
            SimpleClient.getParams().add(getForManager);
            setRoot("CourseResults");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void x(ActionEvent event) throws IOException {
        try {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add(pickedcourse);
        message.add(getForManager);
        SimpleClient.getParams().add(message);
        setRoot("QuestionManager");
        } catch (IOException e) {
        e.printStackTrace();
        }
    }
    @FXML
    void y(ActionEvent event) throws IOException {
        try {
            LinkedList<Object> message = new LinkedList<Object>();
            message.add(pickedcourse1);
            message.add(getForManager);
            SimpleClient.getParams().add(message);
            setRoot("ExamManager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void Mail(ActionEvent event) throws IOException{
        try {
            LinkedList<Object> message = new LinkedList<>();
            message.add("MaillManager");
            message.add(getForManager);
            message.add(false);
            SimpleClient.getClient().sendToServer(message);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

@FXML
public void LogOut(ActionEvent event) throws IOException {
    LinkedList<Object> message = new LinkedList<Object>();
    message.add("#LogOut");
    message.add("mangerlogout");
    SimpleClient.getClient().sendToServer(message);
    setRoot("primary");
}
}
