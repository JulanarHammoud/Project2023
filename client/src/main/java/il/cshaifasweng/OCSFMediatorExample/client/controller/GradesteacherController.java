//package il.cshaifasweng.OCSFMediatorExample.client.controller;
//
//import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
//import il.cshaifasweng.OCSFMediatorExample.entities.ExamTeacher;
//import il.cshaifasweng.OCSFMediatorExample.entities.GradesEntity;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//
//import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;
//@FXML
//private TableView<Grades> Gtable;
//@FXML
//private TableColumn<Grades, String> examid;
//@FXML
//private TableColumn<Grades, String> studentid;
//@FXML
//private TableColumn<Grades, String> grade;
//
//public class GradesteacherController {
////    List<ExamTeacher> exams = teacher.getPublishedExams();
////				for(ExamTeacher exam:exams){
////        GradesEntity gradesEntity = new ();
////    }
//
//
//
//    @FXML
//    public void back (ActionEvent event) throws IOException {
//        try{
//            //SimpleClient.getParams().add(teacher);
//            setRoot("teacherpage");
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    public void LogOut (ActionEvent event) throws IOException {
//        LinkedList<Object> message = new LinkedList<Object>();
//        message.add("#LogOut");
//       // message.add(teacher.getId());
//        message.add("teacher");
//        SimpleClient.getClient().sendToServer(message);
//    }
//}
