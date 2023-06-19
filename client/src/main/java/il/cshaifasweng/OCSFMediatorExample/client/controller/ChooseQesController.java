package il.cshaifasweng.OCSFMediatorExample.client.controller;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;


public class ChooseQesController {
    @FXML
    private TableView<Question> Qtable;

    @FXML
    private Button add;

    @FXML
    private Button edit;

    @FXML
    private TableColumn<Question, String> the_right_ans;

    @FXML
    private TableColumn<Question, String> ans1;

    @FXML
    private TableColumn<Question, String> ans2;

    @FXML
    private TableColumn<Question, String> ans3;

    @FXML
    private TableColumn<Question, String> ans4;

    @FXML
    private TableColumn<Question, String> question;
    @FXML
    private Button next;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableColumn<Question, Boolean> choose;

    int lastIndex= SimpleClient.getParams().size()-1;
    SubjectAndId subId = (SubjectAndId) SimpleClient.getParams().get(lastIndex);
    SubjectTeacher subjectteacher = subId.getSubject();
    List<Question> listquestions = subjectteacher.getQuestions();
    ObservableList<Question> data = FXCollections.observableArrayList(listquestions);
    // new Question(listquestions.get(0).getQuestion(),listquestions.get(0).getAns1(),listquestions.get(0).getAns2(),listquestions.get(0).getAns3(),listquestions.get(0).getAns4(),listquestions.get(0).getThe_right_ans())


    public void initialize()  {
        //rrr
        for(Question q :listquestions){
            q.setExist(false);
        }
        Qtable.setEditable(true);
        question.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        ans1.setCellValueFactory(new PropertyValueFactory<Question, String>("ans1"));
        ans2.setCellValueFactory(new PropertyValueFactory<Question, String>("ans2"));
        ans3.setCellValueFactory(new PropertyValueFactory<Question, String>("ans3"));
        ans4.setCellValueFactory(new PropertyValueFactory<Question, String>("ans4"));
        the_right_ans.setCellValueFactory(new PropertyValueFactory<Question, String>("the_right_ans"));
        choose.setCellValueFactory(new PropertyValueFactory<Question, Boolean>("exist"));
        choose.setCellFactory(CheckBoxTableCell.forTableColumn(choose));
        choose.setEditable(true);
        choose.setOnEditCommit(event -> {
            Question person = event.getRowValue();
            person.setExist(event.getNewValue());
        });
        Qtable.setItems(data);




        if (subId.getId()==-1){
            Qtable.getColumns().remove(choose);

        }
    }

    @FXML
    void nextaction(ActionEvent event) {
        try {
            System.out.println("next");
            setRoot("Login");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void addQuestion(ActionEvent event) {
        try{
            SimpleClient.getParams().add(subId.getId());
            SimpleClient.getParams().add(Qtable);
        SimpleClient.getParams().add(subjectteacher);
        setRoot("addQuestion");}
        catch (IOException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void editQuestion(ActionEvent event) {

        Qtable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        Qtable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                System.out.println(newSelection);
                LinkedList<Object> message = new LinkedList<Object>();
                message.add("editquestion");
                message.add(newSelection);
                message.add(subId.getId());
                message.add(subjectteacher);
                try {
                    SimpleClient.getClient().sendToServer(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}



