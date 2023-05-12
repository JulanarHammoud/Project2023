package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;


public class SecondaryController {

    @FXML
    private ListView<String> myListView= new ListView<>();
    ObservableList<String> items = FXCollections.observableArrayList();

   public void initialize(){
      /* for(){
       items.add(Student.ge);}*/


       // Set the items in the ListView
       myListView.setItems(items);
   }
    @FXML
    void clickbutton(ActionEvent event) {
        if(myListView.getSelectionModel().isEmpty()){
            System.out.print("You Don't chose Any Student\n"+"Please choose one. ");
        }else {
            int index=myListView.getSelectionModel().getSelectedIndex();
            try{
                setRoot("Grades");}
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
