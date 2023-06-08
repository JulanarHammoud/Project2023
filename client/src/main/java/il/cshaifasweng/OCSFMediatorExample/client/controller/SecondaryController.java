package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.stlist;


public class SecondaryController {


    @FXML
    private ListView<String> myListView= new ListView<>();
    ObservableList<String> items = FXCollections.observableArrayList();
    int lastIndex= SimpleClient.getParams().size()-1;
    stlist students = (stlist) SimpleClient.getParams().get(lastIndex);
    List<Student> list =students.getStudents();
    public int grade1,grade2;

   public void initialize(){
       for(int i=0;i<list.size();i++){
                   items.add(list.get(i).getFirstName());}
       myListView.setItems(items);
   }
    @FXML
    void clickbutton(ActionEvent event) {
        if(myListView.getSelectionModel().isEmpty()){
            System.out.print("You Don't chose Any Student\n"+"Please choose one. ");
        }else {

          //  this.grade1=list.get(index).getId();
            try{
                LinkedList<Object> message = new LinkedList<Object>();
                System.out.println(" the index func");
                int index=myListView.getSelectionModel().getSelectedIndex();
                System.out.println(index);
                message.add("#ClickGrades");
                message.add(list.get(index).getId());
                SimpleClient.getClient().sendToServer(message);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
