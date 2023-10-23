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
import javafx.util.Callback;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class addQuestionController {

    @FXML
    private TextField answ1;

    @FXML
    private TextField answ2;

    @FXML
    private TextField answ3;

    @FXML
    private TextField answ4;

    @FXML
    private TextField correct;
    @FXML
    private TextField notes;

    @FXML
    private Button submit;

    @FXML
    private Accordion subjectaccordion;

    @FXML
    private TextField theQ;
    int lastIndex= SimpleClient.getParams().size()-1;
    SubjectAndId subid = (SubjectAndId) SimpleClient.getParams().get(lastIndex);
    Teacher teacher = subid.getTeacher();
    SubjectTeacher subjectTeacher = subid.getSubject();
    int id = subid.getId();
    int idsubject = subjectTeacher.getId();
    LinkedList<Question> questions = subid.getQuestions();


    CourseTeacher findcourse(List<CourseTeacher> courses,int id){
        for(CourseTeacher s :courses){
            for(SubjectTeacher sub : s.getSubjectTeacher()){
                System.out.println(sub.getSb_name());
                if(id==(sub.getId())){ return s ;}
            }
        }
        CourseTeacher course =null;
        return course;
    }
    List<CourseTeacher> coursessss = teacher.getCourses();
    CourseTeacher courseTeacher=findcourse(coursessss,idsubject);
    List<SubjectTeacher> listsubjects =  courseTeacher.getSubjectTeacher();
    ObservableList<SubjectTeacher> data = FXCollections.observableArrayList(listsubjects);

    @FXML
    void initialize() throws IOException {
        TitledPane subjectnpane = new TitledPane(); // Accordion,newquestionpane
        subjectnpane.setText("Select Subjects");
        subjectaccordion.getPanes().add(subjectnpane);
        TableView subjectntable = new TableView();
        TableColumn subjectColumn = new TableColumn();
        subjectntable.setEditable(true);
        subjectColumn.setCellValueFactory(new PropertyValueFactory<SubjectTeacher, String>("sb_name"));
        subjectntable.setItems(data);
        TableColumn select = new TableColumn("Choose");
        select.setMinWidth(20);
        select.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SubjectTeacher, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<SubjectTeacher, CheckBox> arg0) {
                SubjectTeacher subject = arg0.getValue();
                CheckBox checkBox = new CheckBox();
                //checkBox.selectedProperty().setValue(subject.getExist());
                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                        subject.setSelect_to_add(new_val);
                    }
                });
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        subjectntable.getColumns().addAll(select, subjectColumn);
        subjectnpane.setContent(subjectntable);

    }


    @FXML
    void submitaction(ActionEvent event) {
        try {
            LinkedList<SubjectTeacher> selectedSubjects = new LinkedList<>();

            // Add an event handler to a button or some other UI element to capture selected subjects
                for (SubjectTeacher subject : data) {
                      if (subject.getSelect_to_add()) {
                          System.out.println("selected"+subject.getSb_name());
                        selectedSubjects.add(subject);
                    }
                }

                //System.out.println("in clieeeeent"+ selectedSubjects.get(0).getSb_name());
            System.out.println("Im in add question controller");
            LinkedList<Object> message = new LinkedList<Object>();
            String question = theQ.getText();
            String answer1 = answ1.getText();
            String answer2 = answ2.getText();
            String answer3 = answ3.getText();
            String answer4 = answ4.getText();
            String right = correct.getText();
            String note = notes.getText();
            message.add("MakenewQuestion");
            message.add(subjectTeacher);
            System.out.println("it's subjectteacher" + subjectTeacher.getSb_name());
            message.add(question);
            message.add(answer1);
            message.add(answer2);
            message.add(answer3);
            message.add(answer4);
            message.add(right);
            message.add(note);
            message.add(id);
            message.add(teacher);
            message.add(questions);
            message.add(0);
            message.add(selectedSubjects);
            SimpleClient.getClient().sendToServer(message);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void returnbutton4(ActionEvent event) {
        try{
            LinkedList<Object> message = new LinkedList<Object>();
            if(subid.getQuestions()==null){
                SubjectAndId sub = new SubjectAndId(subjectTeacher,id,teacher);
                SimpleClient.getParams().add(sub);
            }
            else{
                SubjectAndId sub = new SubjectAndId(subjectTeacher,id,teacher,questions);
                SimpleClient.getParams().add(sub);
            }
            setRoot("ChooseQes");
        }
        catch (IOException e) {
            e.printStackTrace();
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


