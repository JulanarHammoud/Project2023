package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

import static il.cshaifasweng.OCSFMediatorExample.client.App.set;
import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class ComputedExamController {
    RadioButton button1;
    JRadioButtonMenuItem item;

    int lastIndex = SimpleClient.getParams().size() - 1;
    //    ExamSubjectTeacher message = (ExamSubjectTeacher) SimpleClient.getParams().get(lastIndex);
//    Exam exam = message.getExam();
//    List<Question> questions = exam.getQuestions();
//    Teacher teacher = message.getTeacher();
//    SubjectTeacher subject = message.getSubjectTeacher();
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

    // @FXML
    // private AnchorPane mainAnchor;
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
    boolean onTime = false;

    @FXML
    void initialize() throws IOException {

        exam.setExecuted(true);
        hourN.setText(String.valueOf(hoursParams));
        minuteN.setText(String.valueOf(timeInMinutes - 1));
        secondN.setText("60");

        hours = new SimpleIntegerProperty(hoursParams);
        minutes = new SimpleIntegerProperty(timeInMinutes);
        seconds = new SimpleIntegerProperty(60);

        //hour//
        timelineHours = new Timeline(new KeyFrame(Duration.hours(1), e -> {
            hours.set(hours.get() - 1);
        }));
        timelineHours.setCycleCount(Timeline.INDEFINITE);
        timelineHours.play();

        // Create a timeline for minutes
        timelineMinutes = new Timeline(new KeyFrame(Duration.minutes(1), e -> {
            minutes.set(minutes.get() - 1);
        }));
        timelineMinutes.setCycleCount(Timeline.INDEFINITE);
        timelineMinutes.play();

        // Create a timeline for seconds
        timelineSeconds = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            seconds.set(seconds.get() - 1);

            // Handle rollover when seconds reach 0
            if ((seconds.get() == 0) || (seconds.get() < 0)) {
                if (("0".equals(hourN.getText())) && ("0".equals(minuteN.getText()))) {
                    shouldStopSec = true;
                    timelineSeconds.stop();
                    try {
                       if(!onTime){
                           LinkedList<Object> message = new LinkedList<Object>();
                           // we have to add the exam to the examTeacher
                           // and generate new exam for the student and adding it to the list
                           message.add("#StdFinishExam");
                           int grade = computeGrade(questions);
                           exam.setGrade(grade);
                           System.out.println("student finished exam with graade: " + grade);
                           message.add(std);
                           message.add(exam);
                           SimpleClient.getClient().sendToServer(message);
                       }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else {
                    seconds.set(59);
                    minutes.set(minutes.get() - 1);
                }

            }
            // Handle rollover when minutes reach 0
            if (minutes.get() < 0) {
                if ("0".equals(hourN.getText())) {
                    timelineMinutes.stop();
                } else {
                    minutes.set(59);
                    hours.set(hours.get() - 1);
                }

                // Handle rollover when hours reach 0 (or you can stop the timer)
                if (hours.get() < 0) {
                    hours.set(0);
                }
            }

        }));
        timelineSeconds.setCycleCount(Timeline.INDEFINITE);
        timelineSeconds.play();

        // Bind StringProperties to update UI
        StringProperty hourText = new SimpleStringProperty();
        StringProperty minuteText = new SimpleStringProperty();
        StringProperty secondText = new SimpleStringProperty();

        hourText.bind(hours.asString());
        minuteText.bind(minutes.asString());
        secondText.bind(seconds.asString());

        // Example: Print the values to the console
        hourText.addListener((obs, oldVal, newVal) -> hourN.setText(newVal));
        minuteText.addListener((obs, oldVal, newVal) -> minuteN.setText(newVal));
        secondText.addListener((obs, oldVal, newVal) -> secondN.setText(newVal));

        /*****************************************************************/
        //test = new AnchorPane();
        examTime.setText("exam time is: " + exam.getTimerr());
        noteStudent.setText("notes for students: " + exam.getStudentNotes());
        //noteStudent.set
        //noteTeacher.setText("notes for teachesrs: " + exam.getTeacherNotes());
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
    public void Finish (ActionEvent event) {
        onTime = true;
        LinkedList<Object> message = new LinkedList<Object>();
        // we have to add the exam to the examTeacher
        // and generate new exam for the student and adding it to the list
        message.add("#StdFinishExam");
        int grade = computeGrade(questions);
        exam.setGrade(grade);
        exam.setOnTime(true);
        System.out.println("student finished exam with graade: " + grade);
        message.add(std);
        message.add(exam);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

}
