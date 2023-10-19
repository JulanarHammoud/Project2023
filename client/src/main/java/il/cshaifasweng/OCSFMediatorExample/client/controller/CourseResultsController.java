package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class CourseResultsController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    GetForManager getForManager = (GetForManager) SimpleClient.getParams().get(lastIndex);
    List<Teacher> teachers = getForManager.getTeachers();
    List<CourseTeacher> courses = getForManager.getCourses();
    @FXML
    private TableView<CourseTeacher> Ttable;
    @FXML
    private TableColumn<CourseTeacher, String> name;
    ObservableList<CourseTeacher> data ;
    @FXML
    AnchorPane pane;
    @FXML
    AnchorPane pane1;
    @FXML
    Label avg;
    @FXML
    Label Tname;
    @FXML
    Label mid;
    @FXML
    TableView<GD> Gtable;
    @FXML
    TableColumn<GD,String> courset;
    @FXML
    TableColumn<GD,String> date;
    @FXML
    TableColumn<GD,Integer> gradet;
    @FXML
    TableColumn<GD,Integer> distributiont;
    int x=0;
    @FXML
    void initialize() throws IOException {
        // Create the BarChart and set the axis labels
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Grades Bar Chart");
        xAxis.setLabel("Grades");
        yAxis.setLabel("Distribution");
        data = FXCollections.observableArrayList(courses);
        Ttable.setEditable(true);
        name.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(dataValueFactory.getValue().getName()));
        Ttable.setItems(data);
        Ttable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            barChart.getData().clear();
            try {
                if (newSelection != null) {
                    List<ExamStudent> examStudentteacher=  new ArrayList<>();
                    System.out.println("Selected Course: " + newSelection.getName());
                    CourseTeacher C = newSelection;
                    for (Teacher t:teachers) {
                        if (t.getPublishedExams().size() != 0) {
                            for (ExamTeacher e : t.getPublishedExams()) {
                                if (e.getExam().getCourse().equals(C.getName())) { //if the exam is on this course
                                    if (e.getExamsOfStudents().size() != 0) { //there are students who have participated in this exam
                                        ExamStudent s = e.getExamsOfStudents().get(0);//take the data from the first exam
                                        ExamStudent EST = new ExamStudent(s.getTime(), s.getDate(), s.isComputed(), s.getExam(), s.getCode(), s.getTeacherPubId(),s.getTeacherid());//fill the data in new exam
                                        int gradetotal = 0, i = 0;
                                        for (ExamStudent examStudent : e.getExamsOfStudents()) {
                                            if (examStudent.isApprove()) {
                                                gradetotal = gradetotal + examStudent.getGrade();
                                                i++;
                                            }
                                        }
                                        if (i != 0) {
                                            EST.setGrade(gradetotal / i); // make the grade of the est equals the average of the grades of the exam teacher
                                            EST.setApprove(true);
                                            examStudentteacher.add(EST);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(examStudentteacher.size()!=0){ //there are grades in this course
                        x=1;
                        System.out.println(examStudentteacher.size());
                        GradesEntity gradesEntity = new GradesEntity(examStudentteacher);
                        Tname.setText(C.getName());
                        avg.setText(String.valueOf(gradesEntity.getAverage()));
                        mid.setText(String.valueOf(gradesEntity.getMedian()));
                        // Clear the existing children in the AnchorPane
                        pane1.getChildren().clear();
                        // Add the new BarChart
                        pane1.getChildren().add(barChart);
                        pane1.setTopAnchor(barChart, 10.0); // Adjust the vertical position
                        pane1.setLeftAnchor(barChart, 5.0);

                        for (ExamStudent examStudent : examStudentteacher) {
                            gradesEntity.setDistribution(examStudent.getGrade());
                        }

                        // Create a new data series and add data points for each grade category
                        XYChart.Series<String, Number> series = new XYChart.Series<>();
                        series.setName("Data Series");
                        for (int i = 0; i < 10; i++) {
                            // Get the distribution for each grade category
                            int distribution = gradesEntity.getDistribution(i);
                            String label;
                            if(i==0) {label="0->10";}
                            // Set the label for each category based on the grade range
                            else label = (i * 10) + 1 + "->" + ((i + 1) * 10);
                            series.getData().add(new XYChart.Data<>(label, distribution));
                        }

                        // Add the data series to the BarChart
                        barChart.getData().add(series);

                        List<GD> gradesArray=new ArrayList<>();
                        int j = 0;
                        for (ExamStudent examStudent : examStudentteacher) {
                            String course = examStudent.getExam().getCourse();
                            int grade = examStudent.getGrade();
                            gradesEntity.setDistribution1(examStudent.getGrade());
                            int distribution = gradesEntity.getDistribution1(examStudent.getGrade());
                            String date = examStudent.getDate();
                            GD gd = new GD(course, date, grade, distribution);
                            gradesArray.add(gd);
                            j++;
                        }
                        if (j != 0) {
                            ObservableList<GD> studentsList = FXCollections.observableArrayList(gradesArray);
                            Gtable.setEditable(true);
                            courset.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
                            date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
                            gradet.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());
                            distributiont.setCellValueFactory(cellData -> cellData.getValue().distributionProperty().asObject());
                            Gtable.setItems(studentsList);
                        }
                    }
                     if(x==0){//no grades yet
                        LinkedList message = new LinkedList<>();
                        message.add("WrongResult");
                        message.add(0);
                        SimpleClient.getClient().sendToServer(message);
                    }
                }else{
                    //you didn't select anything
                    LinkedList message = new LinkedList<>();
                    message.add("WrongResult");
                    message.add(2);
                    SimpleClient.getClient().sendToServer(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    public void back (ActionEvent event) throws IOException {
        SimpleClient.getParams().add(getForManager);
        try {
            LinkedList<Object> message = new LinkedList<>();
            message.add("ExitMessages");
            message.add(0);
            SimpleClient.getClient().sendToServer(message);
            setRoot("Manager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void LogOut (ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        SimpleClient.getClient().sendToServer(message);
        setRoot("primary");
    }
}
