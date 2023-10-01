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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ExamEditController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    ExamSubjectTeacherEdit examSubjectTeacherEdit = (ExamSubjectTeacherEdit) SimpleClient.getParams().get(lastIndex);
    Exam exam = examSubjectTeacherEdit.getExam();
    List<Question> questions = exam.getQuestions();
    Teacher teacher = examSubjectTeacherEdit.getTeacher();
    SubjectTeacher subject = examSubjectTeacherEdit.getSubjectTeacher();
    List<Question> listquestions = subject.getQuestions();
    ObservableList<Question> data = FXCollections.observableArrayList(listquestions);
    SubjectAndId subId = new SubjectAndId(subject, exam.getId(), teacher);
    CourseTeacher courseTeacher=subId.getCourseTeacher();

    @FXML
    private Label ETime;
    @FXML
    private Label Studentnote;
    @FXML
    private Label Teachernote;
    @FXML
    private AnchorPane test;
    @FXML
    private TextField Time;
    @FXML
    private TextField Tnote;
    @FXML
    private TextField Snote;
    @FXML
    private Accordion addquestion;
    @FXML
    private Accordion CopyAccordion;
    @FXML
    private Label ExamCopy;

    @FXML
    void initialize() throws IOException {
        Teachernote.setText("notes for teacher: " + exam.getTeacherNotes());
        Studentnote.setText("notes for student: " + exam.getStudentNotes());
        ETime.setText("exam time is: " + exam.getTimerr());
        for(Question q :listquestions){
            q.setExist(false);
            q.setSelect_to_delete(false);
            q.setSelect_to_add(false);
        }
        System.out.println("I reached Edit controller");
        ETime.setText("exam time is: " + exam.getTimerr());
        Teachernote.setText("notes for teachesrs: " + exam.getTeacherNotes());
        Studentnote.setText("notes for students: " + exam.getStudentNotes());

        double i = 100.0;
        int j=0;
        for (Question q : questions) { // show the checkbox and the questions on the exam
            HBox hBox = new HBox(2);
            CheckBox checkbox1 = new CheckBox();
            Text text = new Text(q.getQuestion());
            hBox.getChildren().add(checkbox1);
            hBox.getChildren().add(text);
            test.setLeftAnchor(text, 20.0);
            test.setTopAnchor(hBox, i);
            test.setLeftAnchor(text, 20.0);
            test.getChildren().add(hBox);
            i = i + 20;
            j++;
            checkbox1.setOnAction(e -> { if(checkbox1.isSelected()) {q.setSelect_to_delete(true);} }); // see if the checkbox is checked
        }


        //Accordion to hold the quesion table or to add new question to the questions
        TitledPane questionpane = new TitledPane();
        questionpane.setText("Question Table");
        addquestion.getPanes().add(questionpane);

        TitledPane newquestionpane = new TitledPane(); // Accordion,newquestionpane
        newquestionpane.setText("Write New Question");
        addquestion.getPanes().add(newquestionpane);


        //select=true to disable the checkbox for questions that we already have in the exam, so we will disable the checkbox
        for(Question q :listquestions){
            for (Question Q:questions){
                if(q.getQuestion().equals(Q.getQuestion())){
                    q.setExist(true);
                }
            }
        }


        // Question table to add new questions to the exam
        TableView questiontable = new TableView();
        TableColumn questionColumn = new TableColumn();
        questiontable.setEditable(true);
        questionColumn.setCellValueFactory(new PropertyValueFactory<Question, String>("question"));
        questiontable.setItems(data);
        TableColumn select = new TableColumn("Choose");
        select.setMinWidth(20);
        select.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Question, CheckBox>, ObservableValue<CheckBox>>() {
            @Override
            public ObservableValue<CheckBox> call(TableColumn.CellDataFeatures<Question, CheckBox> arg0) {
                Question question = arg0.getValue();
                CheckBox checkBox = new CheckBox();
                checkBox.selectedProperty().setValue(question.getExist());
                checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                    public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                        question.setSelect_to_add(new_val);
                    }
                });
                if(question.getExist()){
                    checkBox.setDisable(true);
                }
                return new SimpleObjectProperty<CheckBox>(checkBox);
            }
        });
        questiontable.getColumns().addAll(select, questionColumn);
        questionpane.setContent(questiontable);


        // This Accordion to pick if to save the edited exam in a new copy or the same copy
        TitledPane Copy = new TitledPane();
        Copy.setText("Select Copy");
        CopyAccordion.getPanes().add(Copy);
        VBox SelectCopy = new VBox(2);
        Button Old_copy = new Button("Old Copy");
        Button New_Copy = new Button("New Copy");
        SelectCopy.getChildren().add(Old_copy);
        SelectCopy.getChildren().add(New_Copy);
        Copy.setContent(SelectCopy);
        Old_copy.setOnAction(this::Old_copy);
        New_Copy.setOnAction(this::New_Copy);
        if(examSubjectTeacherEdit.getFlag()==2){
            examSubjectTeacherEdit.setFlag(2);
            Copy.setDisable(true);
            examSubjectTeacherEdit.setPressed(true);
            ExamCopy.setText("New Copy");
        }else{
            if(examSubjectTeacherEdit.getFlag()==4){
                examSubjectTeacherEdit.setPressed(true);
                ExamCopy.setText("Same Copy");
            }
        }
    }


    @FXML
    public void Old_copy (ActionEvent event){
        examSubjectTeacherEdit.setFlag(0);
        examSubjectTeacherEdit.setPressed(true);
        ExamCopy.setText("Same Copy");
    }


    @FXML
    public void New_Copy (ActionEvent event){
        examSubjectTeacherEdit.setFlag(1);
        examSubjectTeacherEdit.setPressed(true);
        ExamCopy.setText("New Copy");
    }


    @FXML
    public void deletequestions (ActionEvent event) {
        System.out.println("client is deleting questions");
        LinkedList<Question> selectedQuestions = new LinkedList<Question>();
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#Edit_Q_Exam");

        for(Question question :questions){
            if (question.getSelect_to_delete()) {
                System.out.println("deleting this quesion: " + question.getQuestion());
            }
            else{ // saving the questions that we don't want to delete to save them in the new copy
                selectedQuestions.add(question);
            }
        }

        if(selectedQuestions.isEmpty()){
            System.out.println("ERROR: ExamEditController deleting all the questions");
            message.add(0);
            message.add(examSubjectTeacherEdit.getFlag());
            message.add(exam);
            message.add(courseTeacher);
            message.add(teacher);
            message.add(subject);
            message.add(subId.getId());
            message.add(selectedQuestions);
            message.add(0);
            message.add(1);
        } else if(selectedQuestions.equals(questions)){
            System.out.println("ERROR: ExamEditController not selecting anything to delete");
            message.add(0);
            message.add(examSubjectTeacherEdit.getFlag());
            message.add(exam);
            message.add(courseTeacher);
            message.add(teacher);
            message.add(subject);
            message.add(subId.getId());
            message.add(selectedQuestions);
            message.add(0);
            message.add(2);
        }
        else{ //if the copy button have been pressed
            message.add(0);
            if(!examSubjectTeacherEdit.getPressed()){
                message.add(3);
            } else{
                message.add(examSubjectTeacherEdit.getFlag());
            }// Deleting questions
            message.add(exam);
            message.add(courseTeacher);
            message.add(teacher);
            message.add(subject);
            message.add(subId.getId());
            message.add(selectedQuestions);
            message.add(1);
            message.add(exam.getTeacherNotes());
            message.add(exam.getStudentNotes());
            message.add(Integer.valueOf(exam.getTimerr()));
        }
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @FXML
    public void addquestions (ActionEvent event) {
        LinkedList<Question> selectedQuestions = new LinkedList<Question>(questions);
        System.out.println("client is adding questions");
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#Edit_Q_Exam");
        for (Question question : listquestions) {
            if (question.getSelect_to_add()) {
                System.out.println("Adding this queston: " + question.getQuestion());
                selectedQuestions.add(question);
            }
        }

        if (selectedQuestions.equals(questions)) {
            System.out.println("ERROR: no selected questions to add, at ExamEditController");
            message.add(0);
            message.add(examSubjectTeacherEdit.getFlag());
            message.add(exam);
            message.add(courseTeacher);
            message.add(teacher);
            message.add(subject);
            message.add(subId.getId());
            message.add(selectedQuestions);
            message.add(0);
            message.add(3);
        } else {//if the copy button have been pressed
            message.add(0);
            if(!examSubjectTeacherEdit.getPressed()){
                message.add(3);
            } else{
                message.add(examSubjectTeacherEdit.getFlag());
            }// Add questions
            message.add(exam);
            message.add(courseTeacher);
            message.add(teacher);
            message.add(subject);
            message.add(subId.getId());
            message.add(selectedQuestions);
            message.add(1);
            message.add(exam.getTeacherNotes());
            message.add(exam.getStudentNotes());
            message.add(exam.getTimerr());
        }
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Back (ActionEvent event) {
        LinkedList<Object> message = new LinkedList<Object>();
        examSubjectTeacherEdit.setFlag(3);
        message.add("#GetSubject");
        message.add(subject.getId());
        message.add(teacher);
        message.add(courseTeacher);
            try {
                SimpleClient.getClient().sendToServer(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }

    @FXML
    public void SaveAll(ActionEvent event){
        LinkedList<Question> selectedQuestions = new LinkedList<Question>();
        System.out.println("client is saving all the changes");
        int change=0;
        int time = Integer.valueOf(exam.getTimerr());
        String TN = exam.getTeacherNotes();
        String SN = exam.getStudentNotes();
        if(!(Tnote.getText().equals(""))){
            TN = Tnote.getText();
            change=1;
        }
        if(!(Snote.getText().equals(""))){
            SN = Snote.getText();
            change=1;
        }
        if(!(Time.getText().equals(""))){
            time = Integer.parseInt(Time.getText());
            change=1;
        }
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#Edit_Q_Exam");
        for(Question question :questions){
            System.out.println(question.getQuestion() + "to delete?" +question.getSelect_to_delete());
            if (question.getSelect_to_delete()) {
                System.out.println(question.getQuestion() + "to delete?" +question.getSelect_to_delete());
                System.out.println("deleting this quesion: " + question.getQuestion());
                change = 1;
            }
            else{ // saving the questions that we don't want to delete to save them in the new copy
                selectedQuestions.add(question);
            }
        }

        for (Question question : listquestions) {
            if (question.getSelect_to_add()) {
                System.out.println("Adding this queston: " + question.getQuestion());
                selectedQuestions.add(question);
                change = 1;
            }
        }
        message.add(1); //we are in saveall button
        if(!examSubjectTeacherEdit.getPressed()){
            examSubjectTeacherEdit.setFlag(3);
//            message.add(3);
        }
//        else{
            message.add(examSubjectTeacherEdit.getFlag());
//        }
        message.add(exam);
        message.add(courseTeacher);
        message.add(teacher);
        message.add(subject);
        message.add(subId.getId());
        message.add(selectedQuestions);
        message.add(1);
        message.add(TN);
        message.add(SN);
        message.add(time);
        message.add(change);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    public void EditTime (ActionEvent event) {
//        exam.setTimerr(Integer.valueOf(Time.getText()));
//        ETime.setText("exam time is: " + exam.getTimerr());
//    }
//
//    @FXML
//    public void EditTeacherNote (ActionEvent event) {
//        exam.setTeacherNotes(Tnote.getText());
//        Teachernote.setText("notes for students: " + exam.getTeacherNotes());
//    }
//
//    @FXML
//    public void EditStudentNote (ActionEvent event) {
//        exam.setStudentNotes(Snote.getText());
//        Studentnote.setText("notes for teachesrs: " + exam.getStudentNotes());
//    }

    @FXML
    public void SaveEdits (ActionEvent event){
        int count=0;
        int time = Integer.valueOf(exam.getTimerr());
        String TN = exam.getTeacherNotes();
        String SN = exam.getStudentNotes();
        if(!(Tnote.getText().equals(""))){
            TN = Tnote.getText();
            count++;
        }
        if(!(Snote.getText().equals(""))){
            SN = Snote.getText();
            count++;
        }
        if(!(Time.getText().equals(""))){
            time = Integer.parseInt(Time.getText());
            count++;
        }
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("SaveEditExam");
        if(!examSubjectTeacherEdit.getPressed()){
            message.add(3);
        } else{
            message.add(examSubjectTeacherEdit.getFlag());
        }// Add questions
        message.add(exam);
        message.add(exam.getCourse());
        message.add(teacher);
        message.add(subject);
        message.add(subId.getId());
        message.add(TN);
        message.add(SN);
        message.add(time);
        message.add(count);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
