package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.ExamStudent;
import il.cshaifasweng.OCSFMediatorExample.entities.GradeSt;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.set;

public class TestPaperController {
    int lastIndex= SimpleClient.getParams().size()-1;
    ExamStudent StEx = (ExamStudent) SimpleClient.getParams().get(lastIndex);
    List<Question> questions =  StEx.getQuestions();
    RadioButton button1;
    JRadioButtonMenuItem item;
    AnchorPane test;

    @FXML
    private AnchorPane firstanchor;

    @FXML
    void initialize() throws IOException {

        AnchorPane newRoot = new AnchorPane(); // Create a new AnchorPane for the root

        double i = 100.0; // this index to set the position of the question on the screen

        /////////////////////////////
         // Adjust the position for the first text

        // Create and configure the first text
        Text text11 = new Text("The Subject: "+StEx.getSubject());
        text11.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        //text11.setTextAlignment(TextAlignmcent.CENTER);
        AnchorPane.setTopAnchor(text11, 10.0); // Adjust the vertical position
        AnchorPane.setLeftAnchor(text11, 0.0);

        // Increment the position for the next text


        // Create and configure the second text
        Text text22 = new Text("The Date Of The Exam: "+StEx.getDate());
        text22.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        //text22.setTextAlignment(TextAlignment.CENTER);
        AnchorPane.setTopAnchor(text22, 40.0); // Adjust the vertical position
        AnchorPane.setLeftAnchor(text22, 0.0); // Center horizontally

        // Increment the position for the third text


        // Create and configure the third text
        Text text33 = new Text("The Teacher: "+StEx.getTeacher());
        text33.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
        //text33.setTextAlignment(TextAlignment.CENTER);
        AnchorPane.setTopAnchor(text33, 70.0); // Adjust the vertical position
        AnchorPane.setLeftAnchor(text33, 0.0); // Center horizontally

        // Continue with the rest of your code to display questions and answers

        // Add the texts and other content to the newRoot
        newRoot.getChildren().addAll(text11, text22, text33);
        ////////////////////////////

        for (Question q : questions) {
            VBox vbox = new VBox(5); // we put every question in vbox
            Text text = new Text(q.getQuestion());
            vbox.getChildren().add(text);

            // Display the answers as Text
            Text answer1 = new Text(q.getAns1());
            Text answer2 = new Text(q.getAns2());
            Text answer3 = new Text(q.getAns3());
            Text answer4 = new Text(q.getAns4());

            // Set the selected answer
            System.out.println(""+q.getThe_student_ans()+q.getThe_right_ans());
                answer1.setStyle("-fx-fill: black;"); // Set text color to black for the selected answer
                if (q.getAns1().equals(q.getThe_right_ans())) {
                    answer1.setStyle("-fx-fill: green;");
                }
               else if ((q.getAns1().equals(q.getThe_student_ans()))) {
                    answer1.setStyle("-fx-fill: red;");
                }

                answer2.setStyle("-fx-fill: black;");
                if (q.getAns2().equals(q.getThe_right_ans())) {
                    answer2.setStyle("-fx-fill: green;");
                }
                else if ((q.getAns2().equals(q.getThe_student_ans()))) {
                    answer2.setStyle("-fx-fill: red;");
                }

                answer3.setStyle("-fx-fill: black;");
                if (q.getAns3().equals(q.getThe_right_ans())) {
                    answer3.setStyle("-fx-fill: green;");
                }
                else if ((q.getAns3().equals(q.getThe_student_ans()))) {
                    answer3.setStyle("-fx-fill: red;");
                }

                answer4.setStyle("-fx-fill: black;");
                if (q.getAns4().equals(q.getThe_right_ans())) {
                    answer4.setStyle("-fx-fill: green;");
                }
                else if ((q.getAns4().equals(q.getThe_student_ans()))) {
                    answer4.setStyle("-fx-fill: red;");
                }




            vbox.getChildren().addAll(answer1, answer2, answer3, answer4);
            AnchorPane.setTopAnchor(vbox, i);
            AnchorPane.setLeftAnchor(text, 20.0);
            newRoot.getChildren().add(vbox); // Add the VBox to the newRoot
            i = i + 150;
        }

        ScrollPane scroll = new ScrollPane(newRoot); // Use newRoot as the content of the ScrollPane
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        set(scroll);

    }
}
