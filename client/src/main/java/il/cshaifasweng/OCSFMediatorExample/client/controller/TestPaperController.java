package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import javafx.event.ActionEvent;


import static il.cshaifasweng.OCSFMediatorExample.client.App.set;
import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class TestPaperController {
    int lastIndex= SimpleClient.getParams().size()-1;
//    int lastIndex2= SimpleClient.getParams().size()-2;
    ExamStudent StEx = (ExamStudent) SimpleClient.getParams().get(lastIndex);
//    GradeSt grade=(GradeSt) SimpleClient.getParams().get(lastIndex);

    List<DetailedQuestion> questions =  StEx.getQuestions();
    RadioButton button1;
    JRadioButtonMenuItem item;
    AnchorPane test;


    @FXML
    private AnchorPane firstanchor;

    @FXML
    void initialize() throws IOException {
//System.out.println(";;;"+grade.getSs().getFirstName());
        AnchorPane newRoot = new AnchorPane(); // Create a new AnchorPane for the root

        double i = 100.0; // this index to set the position of the question on the screen

        /////////////////////////////
         // Adjust the position for the first text
        Button back=new Button("back");
        back.setLayoutX(521);
        back.setLayoutY(10);
//        back.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                try {
//                    SimpleClient.getParams().add(grade);
//                    setRoot("GradesButton");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        //back.setOnAction(this::backaction);
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
        newRoot.getChildren().addAll(text11, text22, text33,back);
        ////////////////////////////

        for (DetailedQuestion q : questions) {
            VBox vbox = new VBox(5); // we put every question in vbox
            Text text = new Text(q.getQuestion().getQuestion());
            vbox.getChildren().add(text);

            // Display the answers as Text
            Text answer1 = new Text(q.getQuestion().getAns1());
            Text answer2 = new Text(q.getQuestion().getAns2());
            Text answer3 = new Text(q.getQuestion().getAns3());
            Text answer4 = new Text(q.getQuestion().getAns4());

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
            newRoot.getChildren().add(vbox); // Add the VBox to the newRoot
            i = i + 150;
        }

        ScrollPane scroll = new ScrollPane(newRoot); // Use newRoot as the content of the ScrollPane
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        set(scroll);

    }


}
