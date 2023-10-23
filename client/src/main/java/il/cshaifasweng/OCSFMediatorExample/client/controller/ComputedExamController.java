package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.set;

public class ComputedExamController {
    RadioButton button1;
    JRadioButtonMenuItem item;

    int lastIndex = SimpleClient.getParams().size() - 1;
    StartCompExam initial = (StartCompExam) SimpleClient.getParams().get(lastIndex);
    Student std = initial.getStudent();
    ExamStudent exam = initial.getExam();
    List<DetailedQuestion> questions = exam.getQuestions();
    int hoursParams = 0;
    @FXML
    private Text hourN;

    @FXML
    private Text minuteN;

    @FXML
    private Text secondN;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Label examTime;

    @FXML
    private Label noteStudent;

    @FXML
    private Label noteTeacher;

    @FXML
    private Label sub;
    @FXML
    private List<CheckBox> selectedCheckboxes = new ArrayList<>();
    int timeInMinutes = exam.getExam().getTimerr();
    //int timeInMinutes=130;
    boolean shouldStopSec = false;
    private SimpleIntegerProperty hours;
    private SimpleIntegerProperty minutes;
    private SimpleIntegerProperty seconds;
    Timeline timelineSeconds;
    Timeline timelineMinutes;
    Timeline timelineHours;
    boolean onTime = true;
    Timeline timeline;

    @FXML
    void initialize() throws IOException {
        SimpleClient.setPosition("ComputedExam");

        hours = new SimpleIntegerProperty(hoursParams);
        minutes = new SimpleIntegerProperty(timeInMinutes);
        seconds = new SimpleIntegerProperty(60);

        javafx.util.Duration sec = javafx.util.Duration.seconds(1);

        timeline = new Timeline(new KeyFrame(sec, event -> {
            updateCountdown();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        /*****************************************************************/
        int timer = (exam.getNewTimer() == -1) ? exam.getTimerr() : exam.getNewTimer();
        examTime.setText("exam time is: " + timer);
        noteStudent.setText("notes for students: " + exam.getStudentNotes());
        sub.setText("exam sub is: " + exam.getSubject());

        double i = 150.0; // this index to set the position of the question on the screen
        LinkedList<String> answers = new LinkedList<>();
        for (DetailedQuestion q : questions) {
            VBox vbox = new VBox(5); // we put every question in vbox
            ToggleGroup TG = new ToggleGroup();
            answers.add(q.getQuestion().getAns1());
            answers.add(q.getQuestion().getAns2());
            answers.add(q.getQuestion().getAns3());
            answers.add(q.getQuestion().getAns4());
            Text text = new Text(q.getQuestion().getQuestion() + "\n" + q.getPoints());
            vbox.getChildren().add(text);
            Collections.shuffle(answers);
            for (String ans : answers) {
                RadioButton btn = new RadioButton(ans);
                btn.setOnAction(event -> handleRadioButtonClick(ans,q,questions.indexOf(q)));
                vbox.getChildren().add(btn);
                btn.setToggleGroup(TG);
            }
            AnchorPane.setTopAnchor(vbox, i);
            AnchorPane.setLeftAnchor(vbox, 20.0);
            anchor.getChildren().add(vbox);
            i = i + 150;
            answers.clear();
            System.out.println("Im in Show Exam controller");
        }
        ScrollPane scroll = new ScrollPane(anchor);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        set(scroll);
    }


    @FXML
    public void Finish () {
        LinkedList<Object> message = new LinkedList<Object>();
        // we have to add the exam to the examTeacher
        // and generate new exam for the student and adding it to the list
        message.add("#StdFinishExam");
        int grade = computeGrade(questions);
        exam.setGrade(grade);
        exam.setOnTime(true);
        exam.setQuestions(questions);
        exam.setOnTime(onTime);
        System.out.println("student finished exam with graade: " + grade);
        message.add(std);
        message.add(exam);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        timeline.stop();
    }

    public  void handleRadioButtonClick(String ans, DetailedQuestion question,int position){
        question.setStdAnswer(ans);
        questions.set(position,question);
    }

    public int computeGrade( List<DetailedQuestion> questions){
        int grade =0;
        for (DetailedQuestion q:questions){
           if(q.getStdAnswer() != null) {
               if (q.getStdAnswer().equals(q.getQuestion().getThe_right_ans())) {
                   grade += q.getPoints();
               }
           }
        }
        return grade;
    }
    public void updateCountdown(){
        int timer = (exam.getNewTimer() == -1) ? exam.getTimerr() : exam.getNewTimer();
        int last = SimpleClient.getMesFromClient().size() - 1;
        if(last != -1) {
            //System.out.println(SimpleClient.getMesFromClient().get(last).getClass());
            if (SimpleClient.getMesFromClient().get(last).getClass().equals(UpdateTimer.class)) {
                UpdateTimer updateTimer = (UpdateTimer) SimpleClient.getMesFromClient().get(last);
                int newTimer = updateTimer.getTimer();
                if (exam.getExam().getTimerr() != newTimer) {
                    System.out.println(newTimer);
                    exam.getExam().setTimerr(newTimer);
                    timer = newTimer;
                    examTime.setText("exam time is: " + timer);
                }
                System.out.println("the timer is: " + exam.getTimerr());
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime parsedTime = LocalTime.parse(exam.getTime(),formatter);
        LocalTime currentTime = LocalTime.now();
        LocalTime endTime = parsedTime.plusMinutes(timer);
        Duration remainingTime = Duration.between(currentTime, endTime);
        String formattedTime = String.format("%02d:%02d:%02d",
                remainingTime.toHours(), (remainingTime.toMinutes() % 60), (remainingTime.getSeconds() % 60));
        minuteN.setText(formattedTime);
        if( remainingTime.toHours()<=0 && (remainingTime.toMinutes() % 60)<=0 && (remainingTime.getSeconds() % 60)<=0){
            System.out.println("time finished before submitting");
            onTime = false;
            Finish();
        }
    }
}
