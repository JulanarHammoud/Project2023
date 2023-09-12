package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Exam;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectAndId;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.set;
import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class ShowExamController {
    RadioButton button1;
    JRadioButtonMenuItem item;
    AnchorPane test;

    int lastIndex= SimpleClient.getParams().size()-1;
    Exam exam = (Exam) SimpleClient.getParams().get(lastIndex);
    List<Question> questions =  exam.getQuestions();

    @FXML
    private Label examTime;

    @FXML
    private Label noteStudent;

    @FXML
    private Label noteTeacher;

    @FXML
    private Label sub;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    void initialize() throws IOException {
        test=new AnchorPane();
        LinkedList<String> answers = new LinkedList<>();


        examTime.setText( "exam time is: " + exam.getTimerr());
        noteStudent.setText("notes for students: " + exam.getStudentNotes());
        noteTeacher.setText("notes for teachesrs: " + exam.getTeacherNotes());
        sub.setText("exam sub is: " + exam.getSubject());


        double i = 100.0; // this index to set the position of the question on the screen
        for(Question q :questions){
            VBox vbox = new VBox(5); // we put every question in vbox
            ToggleGroup TG = new ToggleGroup();
            answers.add(q.getAns1());
            answers.add(q.getAns2());
            answers.add(q.getAns3());
            answers.add(q.getAns4());
            Text text = new Text( q.getQuestion());
            vbox.getChildren().add(text);
            Collections.shuffle(answers);
            for(String ans : answers){
                RadioButton btn = new RadioButton(ans);
                vbox.getChildren().add(btn);
                btn.setToggleGroup(TG);
            }
            AnchorPane.setTopAnchor(vbox, i);
            AnchorPane.setLeftAnchor(text, 20.0);
            mainAnchor.getChildren().add(vbox);
            i = i+ 150;
            answers.clear();

        }

        ScrollPane scroll= new ScrollPane(mainAnchor);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        set(scroll);

    }
}
