package il.cshaifasweng.OCSFMediatorExample.client.controller;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.fxml.FXML;


public class TeacherController {
    @FXML
    private ListView<String> ListViewSubject=new ListView<>();

    int lastIndex= SimpleClient.getParams().size()-1;
    Teacher teacher = (Teacher) SimpleClient.getParams().get(lastIndex);
    List<SubjectTeacher> list =teacher.getSubjects();
    ObservableList<String> items = FXCollections.observableArrayList();

    public void initialize(){
        for(int i=0;i<list.size();i++){
            items.add(String.valueOf(list.get(i).getSb_name()));}
        ListViewSubject.setItems(items);
    }

    }

