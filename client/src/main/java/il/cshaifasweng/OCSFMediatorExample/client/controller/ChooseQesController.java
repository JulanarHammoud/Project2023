package il.cshaifasweng.OCSFMediatorExample.client.controller;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.CourseTeacher;
import il.cshaifasweng.OCSFMediatorExample.entities.Question;
import il.cshaifasweng.OCSFMediatorExample.entities.SubjectTeacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
    private Button next;

    int lastIndex= SimpleClient.getParams().size()-1;
    SubjectTeacher subjectteacher = (SubjectTeacher) SimpleClient.getParams().get(lastIndex);
    List<Question> listquestions = subjectteacher.getQuestions();
    ObservableList<Question> data = FXCollections.observableArrayList(listquestions);
    // new Question(listquestions.get(0).getQuestion(),listquestions.get(0).getAns1(),listquestions.get(0).getAns2(),listquestions.get(0).getAns3(),listquestions.get(0).getAns4(),listquestions.get(0).getThe_right_ans())


    public void initialize()  {
        question.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        ans1.setCellValueFactory(new PropertyValueFactory<Question, String>("ans1"));
        ans2.setCellValueFactory(new PropertyValueFactory<Question, String>("ans2"));
        ans3.setCellValueFactory(new PropertyValueFactory<Question, String>("ans3"));
        ans4.setCellValueFactory(new PropertyValueFactory<Question, String>("ans4"));
        the_right_ans.setCellValueFactory(new PropertyValueFactory<Question, String>("the_right_ans"));
        Qtable.setItems(data);
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

}



