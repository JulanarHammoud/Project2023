package il.cshaifasweng.OCSFMediatorExample.client.controller;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.LinkedList;

public class ExamController {

                @FXML
                private TextField Course;

                @FXML
                private Button Next;

                @FXML
                private TextField NumQ;

                @FXML
                private TextField PutSNotes;

                @FXML
                private TextField PutTNotes;

                @FXML
                private TextField PutTimer;

                @FXML
                private TextField SNotes;

                @FXML
                private TextField TNotes;

                @FXML
                private ComboBox<String> chooseCourse;

                @FXML
                private TextField putNumQ;

                @FXML
                private TextField timer;

        public void initialize(){chooseCourse.getItems().addAll("English","History","Hebrow","Math","Arabic");}

        @FXML
        void Next(ActionEvent event) {
        try{
                LinkedList<Object> message = new LinkedList<Object>();
                message.add("#MakeExam");
                message.add(putNumQ.getText());
                message.add(PutTNotes.getText());
                message.add(PutTimer.getText());
                message.add(PutSNotes.getText());
                String choose = chooseCourse.getSelectionModel().getSelectedItem();
                System.out.println("Selected item: " + putNumQ.getText());
                System.out.println("Selected item: " + PutTNotes.getText());
                System.out.println("Selected item: " + PutTimer.getText());
                System.out.println("Selected item: " + PutSNotes.getText());
                System.out.println("Selected item: " + choose);
                message.add(choose);
                SimpleClient.getClient().sendToServer(message);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}


