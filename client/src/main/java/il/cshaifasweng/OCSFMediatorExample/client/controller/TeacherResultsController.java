package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.GD;
import il.cshaifasweng.OCSFMediatorExample.entities.GetForManager;
import il.cshaifasweng.OCSFMediatorExample.entities.Teacher;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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
    TableColumn<GD,Integer> gradet;
    @FXML
    TableColumn<GD,Integer> distributiont;
    @FXML
    void initialize() throws IOException {
//        data = FXCollections.observableArrayList(teachers);
//        Ttable.setEditable(true);
//        name.setCellValueFactory(dataValueFactory ->
//                new SimpleStringProperty(dataValueFactory.getValue().getUserName()));
//        Ttable.setItems(data);
//        Ttable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
//            try {
//                if (newSelection != null) {
//                    System.out.println("Selected Name: " + newSelection.getUserName());
//                    Teacher T = newSelection;
//                    T.getPublishedExams().get(0).getExamsOfStudents();
//                    for (ExamTeacher e:T.getPublishedExams()) {
//                        GradesEntity gradesEntity = new GradesEntity(e.getExamsOfStudents());
//
//                        String name = T.getFirstName() + " " + T.getLastName();
//                        Tname.setText(name);
//                        avg.setText(String.valueOf(gradesEntity.getAverage()));
//                        mid.setText(String.valueOf(gradesEntity.getMedian()));
//                        CategoryAxis xAxis = new CategoryAxis();
//                        NumberAxis yAxis = new NumberAxis();
//                        // Create the BarChart and set the axis labels
//                        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
//                        barChart.setTitle("Grades Bar Chart");
//                        xAxis.setLabel("Grades");
//                        yAxis.setLabel("Distribution");
//
//                        // Create a data series and add data points
//                        XYChart.Series<String, Number> series = new XYChart.Series<>();
//                        series.setName("Data Series");
//
//                        for (ExamStudent examStudent : e.getExamsOfStudents()) {
//                            if (examStudent.isApprove()) {
//                                gradesEntity.setDistribution(examStudent.getGrade());
//                            }
//                        }
//                        series.getData().add(new XYChart.Data<>("0->10", gradesEntity.getDistribution(1)));
//                        series.getData().add(new XYChart.Data<>("11->20", gradesEntity.getDistribution(2)));
//                        series.getData().add(new XYChart.Data<>("21->30", gradesEntity.getDistribution(3)));
//                        series.getData().add(new XYChart.Data<>("31->40", gradesEntity.getDistribution(4)));
//                        series.getData().add(new XYChart.Data<>("41->50", gradesEntity.getDistribution(5)));
//                        series.getData().add(new XYChart.Data<>("51->60", gradesEntity.getDistribution(6)));
//                        series.getData().add(new XYChart.Data<>("61->70", gradesEntity.getDistribution(7)));
//                        series.getData().add(new XYChart.Data<>("71->80", gradesEntity.getDistribution(8)));
//                        series.getData().add(new XYChart.Data<>("81->90", gradesEntity.getDistribution(9)));
//                        series.getData().add(new XYChart.Data<>("91->100", gradesEntity.getDistribution(10)));
//
//
//                        // Add the data series to the BarChart
//                        barChart.getData().add(series);
//                        pane.getChildren().add(barChart);
//                        pane.setTopAnchor(barChart, 10.0); // Adjust the vertical position
//                        pane.setLeftAnchor(barChart, 5.0);
//
//                        GD[] gradesArray = new GD[T.getPublishedExams().size()]; // Initialize the array
//                        int i = 0, c = 0;
//                        for (ExamStudent examStudent : e.getExamsOfStudents()) {
//                            if (examStudent.isApprove()) {
//                                String course = examStudent.getExam().getCourse();
//                                int grade = examStudent.getGrade();
//                                gradesEntity.setDistribution1(examStudent.getGrade());
//                                int distribution = gradesEntity.getDistribution1(examStudent.getGrade());
//                                c++;
//                                GD gd = new GD(course, grade, distribution);
//                                gradesArray[i] = gd;
//                                i++;
//                            }
//                        }
//                        if (c != 0) {
//                            ObservableList<GD> studentsList = FXCollections.observableArrayList(gradesArray);
//                            Gtable.setEditable(true);
//                            courset.setCellValueFactory(cellData -> cellData.getValue().courseProperty());
//                            gradet.setCellValueFactory(cellData -> cellData.getValue().gradeProperty().asObject());
//                            distributiont.setCellValueFactory(cellData -> cellData.getValue().distributionProperty().asObject());
//                            Gtable.setItems(studentsList);
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
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
