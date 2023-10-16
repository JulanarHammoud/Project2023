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

public class TeacherResultsController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    GetForManager getForManager = (GetForManager) SimpleClient.getParams().get(lastIndex);
    List<Teacher> teachers = getForManager.getTeachers();
    @FXML
    private TableView<Teacher> Ttable;
    @FXML
    private TableColumn<Teacher, String> name;
    ObservableList<Teacher> data ;
    @FXML
    AnchorPane pane;
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
        data = FXCollections.observableArrayList(teachers);
        Ttable.setEditable(true);
        name.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(dataValueFactory.getValue().getUserName()));
        Ttable.setItems(data);
        Ttable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            try {
                if (newSelection != null) {
                    List<ExamStudent> examStudentteacher=  new ArrayList<>();;
                    System.out.println("Selected Name: " + newSelection.getUserName());
                    Teacher T = newSelection;
                    if(T.getPublishedExams().size()!=0) {
                        T.getPublishedExams().get(0).getExamsOfStudents();
                        for (ExamTeacher e : T.getPublishedExams()) {
                            if(e.getExamsOfStudents().size()!=0) {
                                x++;
                                ExamStudent s = e.getExamsOfStudents().get(0);//take the data from the first exam
                                ExamStudent EST = new ExamStudent(s.getTime(), s.getDate(), s.isComputed(), s.getExam(), s.getCode(), s.getTeacherPubId());//fill the data in new exam
                                int gradetotal = 0, i = 0;
                                for (ExamStudent examStudent : e.getExamsOfStudents()) {
                                    if (examStudent.isApprove()) {
                                        gradetotal = gradetotal + examStudent.getGrade();
                                        i++;
                                    }
                                }
                                if(i!=0) {
                                    EST.setGrade(gradetotal / i); // make the grade of the est equals the average of the grades of the exam teacher
                                    EST.setApprove(true);
                                    examStudentteacher.add(EST);
                                }
                            }
                        }
                        if(examStudentteacher.size()!=0){
                            GradesEntity gradesEntity = new GradesEntity(examStudentteacher);
                            String name = T.getFirstName() + " " + T.getLastName();
                            Tname.setText(name);
                            avg.setText(String.valueOf(gradesEntity.getAverage()));
                            mid.setText(String.valueOf(gradesEntity.getMedian()));
                            CategoryAxis xAxis = new CategoryAxis();
                            NumberAxis yAxis = new NumberAxis();

                            // Create the BarChart and set the axis labels
                            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
                            barChart.setTitle("Grades Bar Chart");
                            xAxis.setLabel("Grades");
                            yAxis.setLabel("Distribution");

                            // Create a data series and add data points
                            XYChart.Series<String, Number> series = new XYChart.Series<>();
                            series.setName("Data Series");

                            for (ExamStudent examStudent : examStudentteacher) {
                                gradesEntity.setDistribution(examStudent.getGrade());
                            }
                            series.getData().add(new XYChart.Data<>("0->10", gradesEntity.getDistribution(0)));
                            series.getData().add(new XYChart.Data<>("11->20", gradesEntity.getDistribution(1)));
                            series.getData().add(new XYChart.Data<>("21->30", gradesEntity.getDistribution(2)));
                            series.getData().add(new XYChart.Data<>("31->40", gradesEntity.getDistribution(3)));
                            series.getData().add(new XYChart.Data<>("41->50", gradesEntity.getDistribution(4)));
                            series.getData().add(new XYChart.Data<>("51->60", gradesEntity.getDistribution(5)));
                            series.getData().add(new XYChart.Data<>("61->70", gradesEntity.getDistribution(6)));
                            series.getData().add(new XYChart.Data<>("71->80", gradesEntity.getDistribution(7)));
                            series.getData().add(new XYChart.Data<>("81->90", gradesEntity.getDistribution(8)));
                            series.getData().add(new XYChart.Data<>("91->100", gradesEntity.getDistribution(9)));


                            // Add the data series to the BarChart
                            barChart.getData().add(series);
                            pane.getChildren().add(barChart);
                            pane.setTopAnchor(barChart, 10.0); // Adjust the vertical position
                            pane.setLeftAnchor(barChart, 5.0);

                            GD[] gradesArray = new GD[examStudentteacher.size()]; // Initialize the array
                            int j = 0;
                            for (ExamStudent examStudent : examStudentteacher) {
                                String course = examStudent.getExam().getCourse();
                                int grade = examStudent.getGrade();
                                gradesEntity.setDistribution1(examStudent.getGrade());
                                int distribution = gradesEntity.getDistribution1(examStudent.getGrade());
                                String date = examStudent.getDate();
                                GD gd = new GD(course, date, grade, distribution);
                                gradesArray[j] = gd;
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
                        else{//no grades yet
                            LinkedList message = new LinkedList<>();
                            message.add("WrongResult");
                            message.add(0);
                            SimpleClient.getClient().sendToServer(message);
                        }
                    }
                    else{ // no published exams
                        LinkedList message = new LinkedList<>();
                        message.add("WrongResult");
                        message.add(1);
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
