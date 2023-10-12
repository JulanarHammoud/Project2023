package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.GetForManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.set;
import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class ShowExamManagerController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    LinkedList<Object> message = (LinkedList<Object>) SimpleClient.getParams().get(lastIndex);
    CourseTeacher course = (CourseTeacher) message.get(0);
    GetForManager getForManager = (GetForManager) message.get(1);
    Exam exam = (Exam) message.get(2);
    List<Question> questions = exam.getQuestions();


    @FXML
    private AnchorPane testshowmanager;

    @FXML
    private Label examTime;

    @FXML
    private Label noteStudent;

    @FXML
    private Label noteTeacher;

    @FXML
    private Label sub;
    @FXML
    void initialize() throws IOException {
        examTime.setText("exam time is: " + exam.getTimerr());
        noteStudent.setText("notes for students: " + exam.getStudentNotes());
        noteTeacher.setText("notes for teachesrs: " + exam.getTeacherNotes());
        sub.setText("exam sub is: " + exam.getSubject());

        double i = 150.0; // this index to set the position of the question on the screen
        LinkedList<String> answers = new LinkedList<>();
        for (Question q : questions) {
            VBox vbox = new VBox(6); // we put every question in vbox
            ToggleGroup TG = new ToggleGroup();
            answers.add(q.getAns1());
            answers.add(q.getAns2());
            answers.add(q.getAns3());
            answers.add(q.getAns4());
            Text text = new Text(q.getQuestion());
            Text note = new Text(q.getNote());
            vbox.getChildren().add(text);
            vbox.getChildren().add(note);
            Collections.shuffle(answers);
            for (String ans : answers) {
                RadioButton btn = new RadioButton(ans);
                vbox.getChildren().add(btn);
                btn.setToggleGroup(TG);
            }
            AnchorPane.setTopAnchor(vbox, i);
            AnchorPane.setLeftAnchor(text, 20.0);
            testshowmanager.getChildren().add(vbox);
            i = i + 150;
            answers.clear();
        }
        ScrollPane scroll = new ScrollPane(testshowmanager);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        set(scroll);
    }

    @FXML
    public void back (ActionEvent event) {
        try {
            LinkedList<Object> message1 = new LinkedList<Object>();
            message1.add(course);
            message1.add(getForManager);
            SimpleClient.getParams().add(message1);
            setRoot("ExamManager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void LogOut (ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        SimpleClient.getClient().sendToServer(message);
        setRoot("primary");
    }
}
