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

public class GradesteacherController {
    int lastIndex= SimpleClient.getParams().size()-1;
    GradeTeacher gradeTeacher = (GradeTeacher) SimpleClient.getParams().get(lastIndex);
    Teacher teacher = (Teacher) gradeTeacher.getTeacher();
    CourseTeacher courseteacher = (CourseTeacher) gradeTeacher.getCourseTeacher();
    SubjectTeacher sub = (SubjectTeacher) gradeTeacher.getSubjectTeacher();
    List<ExamTeacher> examTeachersall=(List<ExamTeacher>) gradeTeacher.getExamTeachers();
    List<ExamTeacher> examTeachers = teacher.getPublishedExams();
    @FXML
    private TableView<ExamTeacher> Stable;
    @FXML
    private TableColumn<ExamTeacher, String> name;
    ObservableList<ExamTeacher> data ;
    @FXML
    AnchorPane pane;
    @FXML
    AnchorPane pane1;
    @FXML
    Label avg;
    @FXML
    Label Sname;
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
        System.out.println("IM HEREEEEEEEEE");
        // Create the BarChart and set the axis labels
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Grades Bar Chart");
        xAxis.setLabel("Grades");
        yAxis.setLabel("Distribution");
        List<ExamTeacher> exams=new ArrayList<>();
        int tt;
        for(ExamTeacher examTeacher:examTeachersall){
            tt=0;
            for (ExamTeacher e : examTeachers) {
                if (examTeacher.getTeacherid() == teacher.getId() || examTeacher.getId() == e.getId()) {
                    if (examTeacher.getSubject().equals(sub.getSb_name()) && tt==0) {
                        tt = 1;
                        exams.add(examTeacher);
                        System.out.println(examTeacher.getDate());
                    }
                }
            }
        }
        data = FXCollections.observableArrayList(exams);
        Stable.setEditable(true);
        name.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(dataValueFactory.getValue().getDate()));
        Stable.setItems(data);
        Stable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            barChart.getData().clear();
            try {
                if (newSelection != null) {
                    System.out.println("Selected Date: " + newSelection.getDate());
                    ExamTeacher St = newSelection;
                    if(!St.getExamsOfStudents().isEmpty()) {
                    GradesEntity gradesEntity = new GradesEntity(St.getExamsOfStudents());
                    String name = "Date: "+St.getDate()+"       Starting Time: "+St.getTime();
                    Sname.setText(name);
                    avg.setText(String.valueOf(gradesEntity.getAverage()));
                    mid.setText(String.valueOf(gradesEntity.getMedian()));
                    // Clear the existing children in the AnchorPane
                    pane1.getChildren().clear();
                    // Add the new BarChart
                    pane1.getChildren().add(barChart);
                    pane1.setTopAnchor(barChart, 10.0); // Adjust the vertical position
                    pane1.setLeftAnchor(barChart, 5.0);

                    for (ExamStudent examStudent : St.getExamsOfStudents()) {
                        System.out.println(examStudent.getGrade()+"here"+examStudent.isApprove());
                        if(examStudent.getSubject().equals(sub.getSb_name())){
                            if(examStudent.isApprove()){
                                gradesEntity.setDistribution(examStudent.getGrade());
                                x++;
                            }
                        }
                    }

                    // Create a new data series and add data points for each grade category
                    XYChart.Series<String, Number> series = new XYChart.Series<>();
                    series.setName("Data Series");

                    for (int i = 0; i < 10; i++) {
                        int distribution = gradesEntity.getDistribution(i);
                        String label = (i * 10) + 1 + "->" + ((i + 1) * 10);
                        series.getData().add(new XYChart.Data<>(label, distribution));
                    }

                    if(x!=0){
                        for (int i = 0; i < 10; i++) {
                            // Get the distribution for each grade category
                            int distribution = gradesEntity.getDistribution(i);

                            // Set the label for each category based on the grade range
                            String label = (i * 10) + 1 + "->" + ((i + 1) * 10);

                            series.getData().add(new XYChart.Data<>(label, distribution));
                        }

                        // Add the data series to the BarChart
                        barChart.getData().add(series);

                        List<GD> gradesArray=new ArrayList<>();
                        int c=0;
                        for (ExamStudent examStudent : St.getExamsOfStudents()) {
                            if(examStudent.isApprove()) {
                                String course = examStudent.getStdName();
                                String date = examStudent.getTeacher();
                                int grade = examStudent.getGrade();
                                gradesEntity.setDistribution1(examStudent.getGrade());
                                int distribution = gradesEntity.getDistribution1(examStudent.getGrade());
                                c++;
                                GD gd = new GD(course, date, grade, distribution);
                                gradesArray.add(gd);
                            }
                        }
                        if(c!=0) {
                            if(gradesArray!=null) {
                                ObservableList<GD> studentsList = FXCollections.observableArrayList(gradesArray);
                                Gtable.setEditable(true);
                                courset.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
                                gradet.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());
                                date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
                                distributiont.setCellValueFactory(cellData -> cellData.getValue().distributionProperty().asObject());
                                Gtable.setItems(studentsList);
                            }
                        }
                    } else {//no approved grades yet
                        LinkedList message = new LinkedList<>();
                        message.add("WrongResult");
                        message.add(0);
                        SimpleClient.getClient().sendToServer(message);
                    }
                    }else{
                        LinkedList message = new LinkedList<>();
                        message.add("WrongResult");
                        message.add(0);
                        SimpleClient.getClient().sendToServer(message);
                    }
                } else{
                    //you didn't select anything
                    LinkedList message = new LinkedList<>();
                    message.add("WrongResult");
                    message.add(3);
                    SimpleClient.getClient().sendToServer(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    @FXML
    void back(ActionEvent event) {
        try{
            SimpleClient.getParams().add(teacher);
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
        message.add(teacher.getId());
        message.add("teacher");
        SimpleClient.getClient().sendToServer(message);
    }
}
