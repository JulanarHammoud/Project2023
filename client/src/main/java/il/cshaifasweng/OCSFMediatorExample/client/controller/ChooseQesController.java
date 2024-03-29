package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
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
    private TableColumn<Question, String> note;
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
    CourseTeacher courseTeacher=subId.getCourseTeacher();
    int NumberofQusetions;

    public void initialize()  {
        Qtable.setEditable(true);
        question.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        ans1.setCellValueFactory(new PropertyValueFactory<Question, String>("ans1"));
        ans2.setCellValueFactory(new PropertyValueFactory<Question, String>("ans2"));
        ans3.setCellValueFactory(new PropertyValueFactory<Question, String>("ans3"));
        ans4.setCellValueFactory(new PropertyValueFactory<Question, String>("ans4"));
        note.setCellValueFactory(new PropertyValueFactory<Question, String>("note"));
        the_right_ans.setCellValueFactory(new PropertyValueFactory<Question, String>("the_right_ans"));
        //Qtable.setMinSize(1000,1000);
        Qtable.setItems(data);


        for(Question q:listquestions){
            q.setExist(false);
        }

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
                    return new SimpleObjectProperty<CheckBox>(checkBox);
                }
            });
            question.setCellFactory(tc -> {
                TableCell<Question, String> cell = new TableCell<Question, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            Label label = new Label(item);
                            label.setWrapText(true); // Enable text wrapping
                            setGraphic(label);
                        }
                    }
                };

                return cell;
            });

            // Create cell factory for tooltips
            ans1.setCellFactory(tc -> {
                TableCell<Question, String> cell = new TableCell<Question, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                            setTooltip(null);
                        } else {
                            setText(item);
                            Tooltip tooltip = new Tooltip(item);
                            setTooltip(tooltip);
                        }
                    }
                };

                return cell;
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

            NumberofQusetions = 0;
            for(Question selectedQ :listquestions){
                if(selectedQ.getExist()==true){
                    selectedQuestions.add(selectedQ);
                    System.out.println(selectedQ.getQuestion());
                    NumberofQusetions++;
                }
            }
            System.out.println(selectedQuestions.isEmpty());
            message.add(courseTeacher);
            message.add(NumberofQusetions);
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
    void ShowQuestion(ActionEvent event){
        LinkedList<Object> message = new LinkedList<Object>();
        Question question1 = Qtable.getSelectionModel().getSelectedItem();
        message.add("ShowQuestionn");
        message.add(0); // comming from teacher question page
        message.add(subId);
        message.add(teacher);
        message.add(subjectteacher);
        if(question1 == null){
            if(subId.getId()==-1){
                message.add(0);
            } else{
                message.add(1);
            }
            try {
                SimpleClient.getClient().sendToServer(message);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        } else{ // we selected a question
            message.add(question1);
            if(subId.getId()==-1){
                message.add(0); // coming from question table
            } else{
                message.add(1); // coming from make exam
            }
            SimpleClient.getParams().add(message);
            try{
                setRoot("ShowQuestion");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void addQuestion(ActionEvent event) {
        try{
            SimpleClient.getParams().add(subId);
            setRoot("addQuestion");}
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editQuestion(ActionEvent event) {
        Question newSelection = Qtable.getSelectionModel().getSelectedItem();
            if (newSelection != null) {
                System.out.println(newSelection);
                LinkedList<Object> message = new LinkedList<Object>();
                message.add("editquestion");
                message.add(newSelection);
                message.add(subId.getId());
                message.add(subjectteacher);
                message.add(teacher);
                if(subId.getId()==-1){
                    message.add(0);
                }
                else{
                    message.add(1);
                }
                SimpleClient.getParams().add(message);
                try {
                    setRoot("editquestion");
                } catch (IOException e) {
                    e.printStackTrace();
                }
           }
    }

    @FXML
    public void LogOut(ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        message.add(teacher.getId());
        message.add("teacher");
        SimpleClient.getClient().sendToServer(message);
    }

}



