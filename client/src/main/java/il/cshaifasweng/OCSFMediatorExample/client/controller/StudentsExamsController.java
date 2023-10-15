package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class StudentsExamsController implements Serializable {

    int lastIndex = SimpleClient.getParams().size() - 1;
    StudentsExams fromServer = (StudentsExams) SimpleClient.getParams().get(lastIndex);
    Teacher teacher = fromServer.getTeacher();
    ExamTeacher exam = fromServer.getExam();
    List<ExamStudent> studentsExams = exam.getExamsOfStudents();
    ObservableList<ExamStudent> data ;
    ExamStudent selectedExam = new ExamStudent();

    @FXML
    TableView<ExamStudent> table;
    @FXML
    private TableColumn<ExamStudent, String> name;
    @FXML
    AnchorPane pane;
    @FXML
    private Button approve;
    @FXML
    private Button back2;
    @FXML
    private Button logout1;

    public void initialize() {
        SimpleClient.setPosition("StudentsExams");
        int last = SimpleClient.getMesFromClient().size() - 1;
        System.out.println(" the list size is: " + last) ;
        if(last != -1)
        {
            System.out.println(SimpleClient.getMesFromClient().get(last).getClass());
            if(SimpleClient.getMesFromClient().get(last).getClass().equals(UpdatedExams.class)) {
               UpdatedExams stdsExams = (UpdatedExams) SimpleClient.getMesFromClient().get(last);
                studentsExams = stdsExams.getExams();
            }
        }
        System.out.println("the students exam is null : " + studentsExams == null);
        data = FXCollections.observableArrayList(studentsExams);
        name.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(dataValueFactory.getValue().getStdName()));
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
           try{
               if (newSelection != null) {
//                   if(newSelection.isApprove()){
//                       approve.setDisable(true);
//                   }
//                   else {
//                       approve.setDisable(false);
//                   }
                   System.out.println("Selected Name: " + newSelection.getStdName());
                   ExamStudent StEx = newSelection;
                   selectedExam =newSelection;
                   List<DetailedQuestion> questions = StEx.getQuestions();
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
                   Text text22 = new Text("Student Grade is: "+StEx.getGrade());
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
                   pane.getChildren().addAll(text11, text22, text33);
                   ////////////////////////////

                   System.out.println("the questio list is null: " + questions == null);
                   for (DetailedQuestion q : questions) {
                       VBox vbox = new VBox(6); // we put every question in vbox
                       javafx.scene.text.Text text = new javafx.scene.text.Text(q.getQuestion().getQuestion());
                       vbox.getChildren().add(text);
                       System.out.println(q.getQuestion().getQuestion());

                       // Display the answers as Text
                       javafx.scene.text.Text answer1 = new javafx.scene.text.Text(q.getQuestion().getAns1());
                       javafx.scene.text.Text answer2 = new javafx.scene.text.Text(q.getQuestion().getAns2());
                       javafx.scene.text.Text answer3 = new javafx.scene.text.Text(q.getQuestion().getAns3());
                       javafx.scene.text.Text answer4 = new Text(q.getQuestion().getAns4());

                       // Set the selected answer
                       System.out.println("checccccck");
                       System.out.println(q.getStdAnswer());
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
                       Text stdAnswer = new Text();
                       if(q.getStdAnswer() == null){
                           stdAnswer.setText("student didn't answer this question");
                           stdAnswer.setStyle("-fx-fill: red;");
                       }
                       vbox.getChildren().add(stdAnswer);
                       AnchorPane.setTopAnchor(vbox, i);
                       AnchorPane.setLeftAnchor(vbox, 20.0);
                       pane.getChildren().add(vbox); // Add the VBox to the newRoot
                       i = i + 150;
                   }

//                   ScrollPane scroll = new ScrollPane(pane); // Use newRoot as the content of the ScrollPane
//                   scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//                   //set(scroll);

               }
           }catch (Exception e){
               e.printStackTrace();
           }


        });
        table.setItems(data);
    }


    @FXML
    public void approve (ActionEvent event) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Approve the Grade");
        dialog.setHeaderText("do you want to approve the student grade?");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField reason = new TextField();
        reason.setPromptText("reason");
        ComboBox<String> change = new ComboBox<String>();
        change.getItems().addAll("yes" , "no");
        TextField numericField = new TextField();
        numericField.setPromptText("Enter new grade");
        numericField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!isNumeric(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
        grid.add(change, 0, 0);
        grid.add(reason,1,0);
        grid.add(numericField,0,1);
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == ButtonType.APPLY) {
                String approval = change.getSelectionModel().getSelectedItem();
                String grade = numericField.getText();
//                String grade="40";
                String reasonText = reason.getText();
                // Handle the APPLY action here, e.g., send data to the server, update the grade, etc.
                System.out.println("User chose: " + approval);
                System.out.println("New grade: " + grade);
                System.out.println("Reason: " + reasonText);
                return new Pair<>(approval, grade);
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(pair -> {
                    String approval = pair.getKey();
                    String grade = pair.getValue();
            LinkedList<Object> message = new LinkedList<>();
            boolean approved = (approval == "yes") ? true:false;
            message.add("#ApprovingGrade");


            int i=studentsExams.indexOf(selectedExam);
           String student=studentsExams.get(i).getStdName();
            System.out.println(";;;;;"+i+studentsExams.get(i).getCode());
            if(!grade.equals("")){
                selectedExam.setGrade(Integer.parseInt(grade));
            }
            selectedExam.setApprove(approved);
            System.out.println("pp"+studentsExams.get(i).getGrade()+studentsExams.get(i).isApprove());
            exam.setExamsOfStudents(studentsExams);
            message.add(exam);
            System.out.println("pp"+exam.getExamsOfStudents().get(0).getGrade()+student);
            message.add(teacher);
            message.add(student);
            message.add(approved);
            message.add(grade);
            message.add(selectedExam);
            try {
                SimpleClient.getClient().sendToServer(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }
    private boolean isNumeric(String str) {
        // Check if a string is numeric
        return str.matches("\\d*");
    }
    @FXML
    void backaction(ActionEvent event) {
        SimpleClient.setPosition("");
        SimpleClient.getParams().add(teacher);
        try {
            setRoot("PublishedExam");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void logoutactio(ActionEvent event) throws IOException {
        SimpleClient.setPosition("");
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        message.add(teacher.getId());
        message.add("teacher");
        SimpleClient.getClient().sendToServer(message);
    }




}
