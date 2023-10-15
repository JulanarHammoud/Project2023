package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectAndId;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class TeacherController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex);
    List<CourseTeacher> courses = teacher.getCourses();


    @FXML
    private Accordion container;
    @FXML
    private Label tName;

    String selectedSub, selectedCour;
    SubjectTeacher subject;


    @FXML
    void initialize() throws IOException {
        tName.setText(String.valueOf(teacher.getFirstName() + " " + teacher.getLastName()));
        for (CourseTeacher course : courses) {
            Accordion content = new Accordion();
            TitledPane courseContainer = new TitledPane();
            courseContainer.setText(course.getName());
            courseContainer.expandedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) selectedCour = courseContainer.getText();
                }
            });
            List<SubjectTeacher> subjects = course.getSubjectTeacher();
            for (SubjectTeacher sub : subjects) {
                TitledPane subContainer = new TitledPane();
                subContainer.setText(sub.getSb_name());
                VBox buttons = new VBox(4);
                Button showExams = new Button("Exams");

                showExams.setOnAction(event -> {
                    try {
                        showExams(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                Button makeExam = new Button("Make Exam");
                makeExam.setOnAction(this::examsaction);
                Button qes = new Button("Questions");
                qes.setOnAction(this::questionaction);
                Button grades = new Button("Grades");
                grades.setOnAction(this::gradesaction);
                buttons.getChildren().addAll(showExams, makeExam, qes, grades);
                subContainer.setContent(buttons);
                subContainer.expandedProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        if (newValue) selectedSub = subContainer.getText();
                        for (CourseTeacher c : courses) {
                            if (c.getName().equals(selectedCour)) {

                                List<SubjectTeacher> subjects = c.getSubjectTeacher();
                                for (SubjectTeacher sub : subjects)
                                    if (sub.getSb_name().equals(selectedSub))
                                        subject = sub;
                            }
                        }
                    }
                });
                content.getPanes().add(subContainer);
            }
            courseContainer.setContent(content);
            container.getPanes().add(courseContainer);
        }

    }

    @FXML
    void examsaction(ActionEvent event) {
        try {
            LinkedList<Object> message = new LinkedList<Object>();
            message.add(teacher);
            for (CourseTeacher c : courses) {
                if (c.getName().equals(selectedCour)) {
                    message.add(c);
                    List<SubjectTeacher> subjects = c.getSubjectTeacher();
                    for (SubjectTeacher sub : subjects)
                        if (sub.getSb_name().equals(selectedSub))
                            message.add(sub);
                }
            }
            SimpleClient.getParams().add(message);
            System.out.println(" sending " + selectedCour + " and " + selectedSub);
            setRoot("MakeExam");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void gradesaction(ActionEvent event) {
//        try{
//            LinkedList<Object> message = new LinkedList<Object>();
//            message.add("#GetGrades");
//            message.add(subject);
//            message.add(teacher);
//
//            for (CourseTeacher c : courses) {
//                if (c.getName().equals(selectedCour)) {
//                    message.add(c);
//                }
//                SimpleClient.getParams().add(message);
//                setRoot("Gradesteacher");
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }
    }

    @FXML
    void questionaction(ActionEvent event) {
        try {
            LinkedList<Object> message = new LinkedList<Object>();
            SubjectAndId sub = new SubjectAndId(subject, -1, teacher);
            SimpleClient.getParams().add(sub);
            setRoot("ChooseQes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showExams(ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#GetSubject");
        message.add(subject.getId());
        message.add(teacher);

        for (CourseTeacher c : courses) {
            if (c.getName().equals(selectedCour)) {
                message.add(c);
            }
            SimpleClient.getClient().sendToServer(message);
        }
    }

    @FXML
    void  publishedExams(ActionEvent event) throws IOException{
        SimpleClient.getParams().add(teacher);
        setRoot("PublishedExam");
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
