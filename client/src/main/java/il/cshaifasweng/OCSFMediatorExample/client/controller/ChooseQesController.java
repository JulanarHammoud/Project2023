package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectAndId;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
    Teacher teacher = subId.getTeacher();
    List<Question> listquestions = subjectteacher.getQuestions();
    ObservableList<Question> data = FXCollections.observableArrayList(listquestions);

    public void initialize()  {
        LinkedList<Question> existQ= new LinkedList<>();
        System.out.println(";;;;;;;;;;;;;;;;;;;;;");
        //System.out.println("listquestions: " + listquestions.get(1).getQuestion());
        if(subId.getQuestions()!=null){
           // existQ=subId.getQuestions();
            System.out.println(subId.getQuestions().getFirst().getExist());
            //System.out.println(subId.getQuestions().getFirst().getQuestion()+" "+subId.getQuestions().getFirst().getAns1()+" "+subId.getQuestions().getFirst().getAns2()+" "+subId.getQuestions().getFirst().getAns3()+" "+subId.getQuestions().getFirst().getAns4()+" "+subId.getQuestions().getFirst().getId()+" "+subId.getQuestions().getFirst().getExist());
        }
       // System.out.println(listquestions.get(0).getQuestion()+" "+listquestions.get(0).getAns1()+" "+listquestions.get(0).getAns2()+" "+listquestions.get(0).getAns3()+" "+listquestions.get(0).getAns4()+" "+listquestions.get(0).getId()+" "+listquestions.get(0).getExist());
        for(Question q :listquestions){
           // System.out.println( q.getQuestion() + " is exist : " +existQ.contains(q));
            if(existQ.contains(q)){
                System.out.println(q.getQuestion());
                q.setExist(true);
            }
            else{
                q.setExist(false);
            }
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
            select.setMinWidth(80);
            select.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Question, CheckBox>, ObservableValue<CheckBox>>() {

                @Override
                public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Question, CheckBox> arg0) {
                    Question question = arg0.getValue();
                    CheckBox checkBox = new CheckBox();
                    checkBox.selectedProperty().setValue(question.getExist());
                    checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                        public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                            question.setExist(new_val);
                        }
                    });
                    if(question.getExist()){
                        checkBox.setDisable(true);
                    }
                    return new SimpleObjectProperty<CheckBox>(checkBox);

                }

            });
            Qtable.getColumns().addAll( select);
        }
    }

    @FXML
    void nextaction(ActionEvent event) {
        if (subId.getId()!=-1){ //we are in make exam
            LinkedList<Question> selectedQuestions = new LinkedList<>() ;
            LinkedList<Object> message = new LinkedList<Object>();
            message.add("#BuildExam");
            message.add(teacher);
            message.add(subjectteacher);
            message.add(subId.getId());
            System.out.println("client is sendeing these questions");


            for(Question selectedQ :listquestions){
                if(selectedQ.getExist()==true){
                    selectedQuestions.add(selectedQ);
                    System.out.println(selectedQ.getQuestion());
                }
            }
            if(subId.getQuestions()!=null){
                LinkedList<Object> message1 = new LinkedList<Object>();
                message1.add("#Edit_Q_Exam");
                message1.add(teacher);
                message1.add(subjectteacher);
                message1.add(subId.getId());
                message1.add("");
                message1.add(selectedQuestions);
                try {
                    SimpleClient.getClient().sendToServer(message1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
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
            }
        }
        else{ //we are in question page
            SimpleClient.getParams().add(teacher);
            try {
                setRoot("teacherpage");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void addQuestion(ActionEvent event) {
        try{
           // SimpleClient.getParams().add(subId.getId());
           // SimpleClient.getParams().add(Qtable);
            //SimpleClient.getParams().add(subjectteacher);
            //SimpleClient.getParams().add(teacher);
            //SimpleClient.getParams().add(subId.getQuestions());
            //System.out.println(subId.getQuestions().get(0).getQuestion());
            SimpleClient.getParams().add(subId);
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

    @FXML
    public void LogOut(ActionEvent event) throws IOException {

        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        //message.add(teacher.getId());
        SimpleClient.getClient().sendToServer(message);

    }

}



