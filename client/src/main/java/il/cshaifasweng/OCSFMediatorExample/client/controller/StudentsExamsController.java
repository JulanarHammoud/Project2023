package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import javax.swing.text.TabableView;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.set;

public class StudentsExamsController {

    int lastIndex = SimpleClient.getParams().size() - 1;
    StudentsExams fromServer = (StudentsExams) SimpleClient.getParams().get(lastIndex);
    Teacher teacher = fromServer.getTeacher();
    ExamTeacher exam = fromServer.getExam();
    List<ExamStudent> studentsExams = exam.getExamsOfStudents();
    ObservableList<ExamStudent> data ;

    @FXML
    TableView<ExamStudent> table;
    @FXML
    private TableColumn<ExamStudent, String> name;
    @FXML
    AnchorPane pane;


    public void initialize() {
        data = FXCollections.observableArrayList(studentsExams);
        name.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(dataValueFactory.getValue().getExam().getSubject()));
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println("Selected Name: " + newSelection.getStdName());
                ExamStudent StEx = newSelection;
                List<DetailedQuestion> questions = StEx.getQuestions();
                double i = 100.0; // this index to set the position of the question on the screen

                /////////////////////////////
                // Adjust the position for the first text

                // Create and configure the first text
                javafx.scene.text.Text text11 = new javafx.scene.text.Text("The Subject: "+StEx.getSubject());
                text11.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
                //text11.setTextAlignment(TextAlignmcent.CENTER);
                AnchorPane.setTopAnchor(text11, 10.0); // Adjust the vertical position
                AnchorPane.setLeftAnchor(text11, 0.0);

                // Increment the position for the next text


                // Create and configure the second text
                javafx.scene.text.Text text22 = new javafx.scene.text.Text("The Date Of The Exam: "+StEx.getDate());
                text22.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
                //text22.setTextAlignment(TextAlignment.CENTER);
                AnchorPane.setTopAnchor(text22, 40.0); // Adjust the vertical position
                AnchorPane.setLeftAnchor(text22, 0.0); // Center horizontally

                // Increment the position for the third text


                // Create and configure the third text
                javafx.scene.text.Text text33 = new javafx.scene.text.Text("The Teacher: "+StEx.getTeacher());
                text33.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
                //text33.setTextAlignment(TextAlignment.CENTER);
                AnchorPane.setTopAnchor(text33, 70.0); // Adjust the vertical position
                AnchorPane.setLeftAnchor(text33, 0.0); // Center horizontally

                // Continue with the rest of your code to display questions and answers

                // Add the texts and other content to the newRoot
                pane.getChildren().addAll(text11, text22, text33);
                ////////////////////////////

                for (DetailedQuestion q : questions) {
                    VBox vbox = new VBox(5); // we put every question in vbox
                    javafx.scene.text.Text text = new javafx.scene.text.Text(q.getQuestion().getQuestion());
                    vbox.getChildren().add(text);

                    // Display the answers as Text
                    javafx.scene.text.Text answer1 = new javafx.scene.text.Text(q.getQuestion().getAns1());
                    javafx.scene.text.Text answer2 = new javafx.scene.text.Text(q.getQuestion().getAns2());
                    javafx.scene.text.Text answer3 = new javafx.scene.text.Text(q.getQuestion().getAns3());
                    javafx.scene.text.Text answer4 = new Text(q.getQuestion().getAns4());

                    // Set the selected answer
                    System.out.println(""+q.getStdAnswer()+q.getQuestion().getThe_right_ans());
                    answer1.setStyle("-fx-fill: black;"); // Set text color to black for the selected answer
                    if (q.getQuestion().getAns1().equals(q.getQuestion().getThe_right_ans())) {
                        answer1.setStyle("-fx-fill: green;");
                    }
                    else if ((q.getQuestion().getAns1().equals(q.getStdAnswer()))) {
                        answer1.setStyle("-fx-fill: red;");
                    }

                    answer2.setStyle("-fx-fill: black;");
                    if (q.getQuestion().getAns2().equals(q.getQuestion().getThe_right_ans())) {
                        answer2.setStyle("-fx-fill: green;");
                    }
                    else if ((q.getQuestion().getAns2().equals(q.getStdAnswer()))) {
                        answer2.setStyle("-fx-fill: red;");
                    }

                    answer3.setStyle("-fx-fill: black;");
                    if (q.getQuestion().getAns3().equals(q.getQuestion().getThe_right_ans())) {
                        answer3.setStyle("-fx-fill: green;");
                    }
                    else if ((q.getQuestion().getAns3().equals(q.getStdAnswer()))) {
                        answer3.setStyle("-fx-fill: red;");
                    }

                    answer4.setStyle("-fx-fill: black;");
                    if (q.getQuestion().getAns4().equals(q.getQuestion().getThe_right_ans())) {
                        answer4.setStyle("-fx-fill: green;");
                    }
                    else if ((q.getQuestion().getAns4().equals(q.getStdAnswer()))) {
                        answer4.setStyle("-fx-fill: red;");
                    }




                    vbox.getChildren().addAll(answer1, answer2, answer3, answer4);
                    AnchorPane.setTopAnchor(vbox, i);
                    AnchorPane.setLeftAnchor(text, 20.0);
                    pane.getChildren().add(vbox); // Add the VBox to the newRoot
                    i = i + 150;
                }

                ScrollPane scroll = new ScrollPane(pane); // Use newRoot as the content of the ScrollPane
                scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                set(scroll);

            }


        });



    }
}
