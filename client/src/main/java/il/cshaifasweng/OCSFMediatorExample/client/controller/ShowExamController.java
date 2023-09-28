package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.ActionEvent;
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


public class ShowExamController {
    RadioButton button1;
    JRadioButtonMenuItem item;

        int lastIndex = SimpleClient.getParams().size() - 1;
        ExamSubjectTeacher message = (ExamSubjectTeacher) SimpleClient.getParams().get(lastIndex);
        Exam exam = message.getExam();
        List<Question> questions = exam.getQuestions();
        Teacher teacher = message.getTeacher();
        SubjectTeacher subject = message.getSubjectTeacher();
    @FXML
    private AnchorPane testshow;

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

    @FXML
    void initialize() throws IOException {
        //test = new AnchorPane();
        examTime.setText("exam time is: " + exam.getTimerr());
        noteStudent.setText("notes for students: " + exam.getStudentNotes());
        noteTeacher.setText("notes for teachesrs: " + exam.getTeacherNotes());
        sub.setText("exam sub is: " + exam.getSubject());

        double i = 150.0; // this index to set the position of the question on the screen
            LinkedList<String> answers = new LinkedList<>();
            for (Question q : questions) {
                VBox vbox = new VBox(5); // we put every question in vbox
                ToggleGroup TG = new ToggleGroup();
                answers.add(q.getAns1());
                answers.add(q.getAns2());
                answers.add(q.getAns3());
                answers.add(q.getAns4());
                Text text = new Text(q.getQuestion());
                vbox.getChildren().add(text);
                Collections.shuffle(answers);
                for (String ans : answers) {
                    RadioButton btn = new RadioButton(ans);
                    vbox.getChildren().add(btn);
                    btn.setToggleGroup(TG);
                }
                AnchorPane.setTopAnchor(vbox, i);
                AnchorPane.setLeftAnchor(text, 20.0);
                testshow.getChildren().add(vbox);
                i = i + 150;
                answers.clear();
                System.out.println("Im in Show Exam controller");
            }
            ScrollPane scroll = new ScrollPane(testshow);
            scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            set(scroll);
    }

    /*class DynamicButtonApp extends Application {

        @Override
        public void start(Stage primaryStage) {
            Button dynamicButton = new Button("Click Me");
            dynamicButton.setOnAction(e -> {
                System.out.println("Button Clicked!");
            });

            VBox root = new VBox();
            root.getChildren().add(dynamicButton);

            Scene scene = new Scene(root, 300, 200);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Dynamic Button Example");
            primaryStage.show();

            Button deleatequestions = new Button("deleatequestions");
            HBox dlt_b = new HBox(1);
            dlt_b.getChildren().add(deleatequestions);
            Scene scene4 = new Scene(dlt_b, 300, 200);

            primaryStage.setScene(scene4);
            primaryStage.setTitle("Deleate Questions");
            primaryStage.show();

            Button addquestions = new Button("addquestions");
            HBox add_b = new HBox(1);
            add_b.getChildren().add(deleatequestions);
            Scene scene1 = new Scene(add_b, 300, 200);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Add Questions");
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
    @FXML
    public void deleatequestions (ActionEvent event) {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#GetSubject");
        message.add(subject.getId());
        message.add(teacher);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    @FXML
    public void Finish (ActionEvent event) {
            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#GetSubject");
            message.add(subject.getId());
            message.add(teacher);
            try {
                SimpleClient.getClient().sendToServer(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
}
