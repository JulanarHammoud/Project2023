package il.cshaifasweng.OCSFMediatorExample.client.controller;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;
import static java.awt.SystemColor.*;


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

   // int lastsecIndex= SimpleClient.getParams().size()-2;
   // Teacher teacher = (Teacher) SimpleClient.getParams().get(lastsecIndex);


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

        Qtable.setItems(data);




        if (subId.getId()!=-1){
            TableColumn select = new TableColumn("Choose");
            select.setMinWidth(200);
            select.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Question, CheckBox>, ObservableValue<CheckBox>>() {

                @Override
                public ObservableValue<CheckBox> call(
                        TableColumn.CellDataFeatures<Question, CheckBox> arg0) {
                    Question question = arg0.getValue();

                    CheckBox checkBox = new CheckBox();

                    checkBox.selectedProperty().setValue(question.getExist());



                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov,
                                            Boolean old_val, Boolean new_val) {

                            question.setExist(new_val);

                        }
                    });

                    return new SimpleObjectProperty<CheckBox>(checkBox);

                }

            });
            Qtable.getColumns().addAll( select);

        }
    }

    @FXML
    void nextaction(ActionEvent event) {


        LinkedList<Question> selectedQuestions = new LinkedList<>() ;
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#BuildExam");
        message.add(subId.getId());
        System.out.println("client is sendeing these questions");


        for(Question selectedQ :listquestions){
            if(selectedQ.getExist()==true){
                selectedQuestions.add(selectedQ);
                System.out.println(selectedQ.getQuestion());
            }
        }
        System.out.println("im here");
        System.out.println(selectedQuestions.isEmpty());
        message.add(selectedQuestions);
        System.out.println( "the list is null:" + selectedQuestions == null);
        if(selectedQuestions.isEmpty()){
            message.clear();
            message.add("#warningNoQes");
        }
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //setRoot("Login");



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

   // @FXML
    //void Back(ActionEvent event) {
     //   try{
    //        setRoot("teacherpage");
    //    }
    //    catch (IOException e) {
    //        e.printStackTrace();
    //    }
    //}

    @FXML
    void returnbutton(ActionEvent event) {
        try{
            setRoot("teacherpage");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LogOut(ActionEvent event) throws IOException {

        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        //message.add(teacher.getId());
        SimpleClient.getClient().sendToServer(message);

    }

}



