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
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class StudentResultsController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    GetForManager getForManager = (GetForManager) SimpleClient.getParams().get(lastIndex);
    List<Student> students = getForManager.getStudents();
    @FXML
    private TableView<Student> Stable;
    @FXML
    private TableColumn<Student, String> name;
    ObservableList<Student> data ;
    @FXML
    AnchorPane pane;
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
    TableColumn<GD,Integer> gradet;
    @FXML
    TableColumn<GD,Integer> distributiont;
    @FXML
    void initialize() throws IOException {
        data = FXCollections.observableArrayList(students);
        Stable.setEditable(true);
        name.setCellValueFactory(dataValueFactory ->
                new SimpleStringProperty(dataValueFactory.getValue().getUserName()));
        Stable.setItems(data);
        Stable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            try {
                if (newSelection != null) {
                    System.out.println("Selected Name: " + newSelection.getUserName());
                    Student St = newSelection;
                    GradesEntity gradesEntity = new GradesEntity(St.getStudentExams());
                    String name = St.getFirstName()+" "+St.getLastName();
                    Sname.setText(name);
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

                    for (ExamStudent examStudent : St.getStudentExams()) {
                        System.out.println("hereeeeeeeeee"+examStudent.isApprove());
                        if(examStudent.isApprove()){
                            gradesEntity.setDistribution(examStudent.getGrade());
                        }
                    }

                    series.getData().add(new XYChart.Data<>("0->10", gradesEntity.getDistribution(1)));
                    series.getData().add(new XYChart.Data<>("11->20", gradesEntity.getDistribution(2)));
                    series.getData().add(new XYChart.Data<>("21->30", gradesEntity.getDistribution(3)));
                    series.getData().add(new XYChart.Data<>("31->40", gradesEntity.getDistribution(4)));
                    series.getData().add(new XYChart.Data<>("41->50", gradesEntity.getDistribution(5)));
                    series.getData().add(new XYChart.Data<>("51->60", gradesEntity.getDistribution(6)));
                    series.getData().add(new XYChart.Data<>("61->70", gradesEntity.getDistribution(7)));
                    series.getData().add(new XYChart.Data<>("71->80", gradesEntity.getDistribution(8)));
                    series.getData().add(new XYChart.Data<>("81->90", gradesEntity.getDistribution(9)));
                    series.getData().add(new XYChart.Data<>("91->100", gradesEntity.getDistribution(10)));

                    // Add the data series to the BarChart
                    barChart.getData().add(series);
                    pane.getChildren().add(barChart);
                    pane.setTopAnchor(barChart, 10.0); // Adjust the vertical position
                    pane.setLeftAnchor(barChart, 5.0);

                    GD[] gradesArray = new GD[St.getStudentExams().size()]; // Initialize the array
                    int i = 0,c=0;
                    for (ExamStudent examStudent : St.getStudentExams()) {
                        if(examStudent.isApprove()) {
                            String course = examStudent.getExam().getCourse();
                            int grade = examStudent.getGrade();
                            gradesEntity.setDistribution(examStudent.getGrade());
                            int distribution = gradesEntity.getDistribution(examStudent.getGrade()/10);
                            c++;
                            GD gd = new GD(course, grade, distribution);
                            gradesArray[i] = gd;
                            i++;
                        }
                    }
                    if(c!=0){
                        ObservableList<GD> studentsList = FXCollections.observableArrayList(gradesArray);
                        Gtable.setEditable(true);
                        courset.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
                        gradet.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());
                        distributiont.setCellValueFactory(cellData -> cellData.getValue().distributionProperty().asObject());
                        Gtable.setItems(studentsList);
                    }
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