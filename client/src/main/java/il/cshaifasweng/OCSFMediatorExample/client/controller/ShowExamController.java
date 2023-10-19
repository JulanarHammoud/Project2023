package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static il.cshaifasweng.OCSFMediatorExample.client.App.set;
import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;


public class ShowExamController {
    RadioButton button1;
    JRadioButtonMenuItem item;

    int lastIndex = SimpleClient.getParams().size() - 1;
    ExamSubjectTeacher message = (ExamSubjectTeacher) SimpleClient.getParams().get(lastIndex);
    Exam exam = message.getExam();
    List<Question> questions = exam.getQuestions();
    Teacher teacher = message.getTeacher();
    SubjectTeacher subject = message.getSubjectTeacher();
    CourseTeacher courseTeacher = message.getCourseTeacher();
    List<DetailedQuestion> detailedQuestions = new ArrayList<>();
    @FXML
    private AnchorPane testshow;

    @FXML
    private Button back1;

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

    private String Date;

    private String Time;

    private String Code;

    private String Type;
    int datecheck,timecheck;
    int CR,X,T;

    @FXML
    void initialize() throws IOException {
        CR=0; Code=""; Time=""; Date=""; X=0;T=0;
        for(Question question : questions){
            DetailedQuestion q =new DetailedQuestion();
            q.setQuestion(question);
            q.setPoints(0);
            detailedQuestions.add(q);
        }
        //test = new AnchorPane();
        examTime.setText("exam time is: " + exam.getTimerr());
        noteStudent.setText("notes for students: " + exam.getStudentNotes());
        noteTeacher.setText("notes for teachesrs: " + exam.getTeacherNotes());
        sub.setText("exam sub is: " + exam.getSubject());
        if(message.isFlag()){
            Label date = new Label("Date");
            //<Label fx:id="examTime1" layoutX="20.0" layoutY="133.0" prefHeight="26.0" prefWidth="41.0" text="Date" />
            date.setLayoutX(5.0);
            date.setLayoutY(133.0);
            date.setPrefHeight(26.0);
            date.setPrefWidth(41.0);
            testshow.getChildren().add(date);
//<TextField fx:id="date" layoutX="52.0" layoutY="133.0" prefHeight="26.0" prefWidth="97.0" />
            TextField fillDate = new TextField();
            fillDate.setPromptText("dd.MM.yyyy");
            fillDate.setLayoutX(35.0);
            fillDate.setLayoutY(133.0);
            fillDate.setPrefHeight(26.0);
            fillDate.setPrefWidth(97.0);
            fillDate.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    System.out.println("user changed the date to: " + newValue);
                    Date = newValue;
                    X=1;
                }
            });
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            testshow.getChildren().addAll(fillDate);
            //<Label fx:id="examTime11" layoutX="166.0" layoutY="133.0" prefHeight="26.0" prefWidth="41.0" text="Time" />
            Label time = new Label("Time");
            time.setLayoutX(166.0);
            time.setLayoutY(133.0);
            time.setPrefHeight(26.0);
            time.setPrefWidth(41.0);
            testshow.getChildren().add(time);
            //<TextField fx:id="time" layoutX="196.0" layoutY="133.0" prefHeight="26.0" prefWidth="97.0" />
            TextField fillTime = new TextField();
            fillTime.setLayoutX(196.0);
            fillTime.setLayoutY(133.0);
            fillTime.setPrefHeight(26.0);
            fillTime.setPrefWidth(97.0);
            fillTime.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    System.out.println("user changed the time to: " + newValue);
                    Time = newValue;
                    T=1;
                }
            });
            DateTimeFormatter TimeFormatter = DateTimeFormatter.ofPattern("HH:MM");
            testshow.getChildren().add(fillTime);
            //<ComboBox fx:id="type" layoutX="321.0" layoutY="133.0" prefHeight="26.0" prefWidth="110.0" promptText="select type" />
            ComboBox<String> type = new ComboBox<>();
            type.setLayoutX(321.0);
            type.setLayoutY(133.0);
            type.setPrefHeight(26.0);
            type.setPrefWidth(110.0);
            type.setPromptText("select type");
            type.getItems().addAll("computed","manual");
            type.valueProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    System.out.println("user changed the type to: " + newValue);
                    Type = newValue;
                }
            });
            testshow.getChildren().add(type);
            //<Label fx:id="examTime111" layoutX="447.0" layoutY="133.0" prefHeight="26.0" prefWidth="41.0" text="Code" />
            Label code = new Label("Code");
            code.setLayoutX(447.0);
            code.setLayoutY(133.0);
            code.setPrefHeight(26.0);
            code.setPrefWidth(41.0);
            testshow.getChildren().add(code);
            //<TextField fx:id="code" layoutX="480.0" layoutY="133.0" prefHeight="26.0" prefWidth="97.0" />
            TextField fillCode = new TextField();
            fillCode.setLayoutX(480.0);
            fillCode.setLayoutY(133.0);
            fillCode.setPrefHeight(26.0);
            fillCode.setPrefWidth(97.0);
            fillCode.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    System.out.println("user changed the code to: " + newValue);
                        Code = newValue;
                        CR=1;
                }
            });
            testshow.getChildren().add(fillCode);
        }
        double i = 130.0; // this index to set the position of the question on the screen
        if(message.isFlag()){
            i+=70;
        }
        LinkedList<String> answers = new LinkedList<>();
        for (Question q : questions) {
            VBox vbox = new VBox(6); // we put every question in vbox
            if(message.isFlag()){
                Spinner<Integer> numericSpinner = new Spinner<>();

                // Set the value factory and range (you can adjust this as needed).
                SpinnerValueFactory.IntegerSpinnerValueFactory valueFactory =
                        new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);

                numericSpinner.setValueFactory(valueFactory);
                numericSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
                    for(DetailedQuestion qes : detailedQuestions){
                        if(qes.getQuestion().getId() == q.getId()){
                            qes.setPoints(newValue);

                        }
                    }
                });
                AnchorPane.setTopAnchor(numericSpinner,i);
                AnchorPane.setRightAnchor(numericSpinner,20.0);
                testshow.getChildren().add(numericSpinner);
            }
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
            AnchorPane.setLeftAnchor(vbox, 20.0);
            testshow.getChildren().add(vbox);
            i = i + 150;
            answers.clear();
            System.out.println("Im in Show Exam controller");
        }
        ScrollPane scroll = new ScrollPane(testshow);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        set(scroll);
    }

    @FXML
    public void Finish (ActionEvent event) {
        LinkedList<Object> messageToServer = new LinkedList<Object>();
         int x=0;
         int t=1;
         int good=1;
         int c=1;
         int ggg=1;
         int tm=0;
         int nnnnn=0;
        if(message.isFlag()){
            // publishing new exam
            exam.setQuestions(questions);
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            try {
                datecheck=1;
                LocalDate date1 = LocalDate.parse(Date, dateFormatter);
            } catch (Exception e) {
                good=0;
                datecheck=0;
                System.out.println("Invalid date format. Please use dd.MM.yyyy.");
            }
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:MM");
            try {
                timecheck=1;
                LocalTime date1 = LocalTime.parse(Time, timeFormatter);
            } catch (Exception e) {
                good=0;
                timecheck=0;
                System.out.println("Invalid Time format. Please use dd.MM.yyyy.");
            }

            if(datecheck==1) { // if the format is good continue to check if the date is not in the past
                LocalDate currentDate = LocalDate.now();
                LocalDate dateToCheck = LocalDate.parse(Date, dateFormatter);
                if(X==0){
                    good = 0;
                    x=0;
                }
                else if (dateToCheck.isBefore(currentDate)) {
                    good = 0;
                    x=0;
                    System.out.println("The date is in the past.");
                } else if (dateToCheck.equals(currentDate)){
                    x = 1;
                }else{
                    nnnnn=1;
                    x=1;
                }
            }
            if(timecheck==1) { // if the format is good continue to check if the date is not in the past
                LocalTime currentTime = LocalTime.now();
                LocalTime timeToCheck = LocalTime.parse(Time, timeFormatter);
                if(T==0){
                    good = 0;
                    tm=0;
                } else{
                    tm=1;
                }
            }
            if(Type==null){
                good=0;
                t=0;
            }
            System.out.println(Code.length());
            if(CR==0){
                c=0;
                good=0;
            } else  if(Code.length()!=4){
                c=0;
                good=0;
            }
            for(Question q:questions){
                for(DetailedQuestion qes : detailedQuestions){
                    if(qes.getPoints()==0){
                        ggg=0;
                        good=0;
                    }
                }
            }
            if(good==1){
                messageToServer.add("#PublishExam");
                boolean isComputed = (Type.equals("computed")) ? true:false ;
                LocalTime currentTime = LocalTime.parse(Time);
                Duration timeToAdd = Duration.ofMinutes(exam.getTimerr());
                LocalTime newTime = currentTime.plus(timeToAdd);
                ExamTeacher examTeacher = new ExamTeacher(exam,Date,Time,isComputed,Code,0,0,newTime.toString(),exam.getTeacherid());

                ExamStudent examStudent = new ExamStudent(Time,Date,isComputed,exam,Code,teacher.getId(),exam.getTeacherid());
                examStudent.setQuestions(detailedQuestions);
               double totalpoints=0;
                for(DetailedQuestion qes : detailedQuestions){
                     totalpoints =totalpoints+ qes.getPoints();
                     System.out.println(qes.getPoints());
                }
                System.out.println("the total points is : " +totalpoints);
                for(DetailedQuestion qes : detailedQuestions){
                    System.out.println(qes.getPoints());
                    double realpoints=(qes.getPoints()/totalpoints)*100;
                    System.out.println(realpoints);
                    qes.setPoints(realpoints);
                    System.out.println(qes.getPoints());
                }
                System.out.println(detailedQuestions.get(0).getPoints());
                System.out.println("th first q points is: " + examStudent.getQuestions().get(0).getPoints());
                messageToServer.add(examTeacher);
                messageToServer.add(teacher);
                messageToServer.add(examStudent);
            }
            else{
                messageToServer.add("dateexception");
                messageToServer.add(datecheck);
                messageToServer.add(x);
                messageToServer.add(t);
                messageToServer.add(c);
                messageToServer.add(ggg);
                messageToServer.add(timecheck);
                messageToServer.add(tm);
            }
        }
        else {
            messageToServer.add("#GetSubject");
            messageToServer.add(subject.getId());
            messageToServer.add(teacher);
            messageToServer.add(courseTeacher);}
        try {
            SimpleClient.getClient().sendToServer(messageToServer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void backaction(ActionEvent event) {
        GetSubject getSubject = new GetSubject(subject,teacher,courseTeacher);
        SimpleClient.getParams().add(getSubject);
        try {
            setRoot("allExams");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
