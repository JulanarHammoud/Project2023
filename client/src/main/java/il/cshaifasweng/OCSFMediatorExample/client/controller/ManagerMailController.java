package il.cshaifasweng.OCSFMediatorExample.client.controller;

import com.mysql.cj.xdevapi.Client;
import com.sun.jdi.BooleanValue;
import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;
import static java.time.zone.ZoneRulesProvider.refresh;
import static javafx.beans.property.BooleanProperty.booleanProperty;

public class ManagerMailController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    MailManagerEntity MME = (MailManagerEntity) SimpleClient.getParams().get(lastIndex);
    List<ManagerMessage> listMTM = MME.getLMTM();
    GetForManager GFM = MME.getGFM();
    @FXML
    private TableView<ManagerMessage> Mtable;
    @FXML
    private TableColumn<ManagerMessage, Integer> id;
    @FXML
    private TableColumn<ManagerMessage, String> teachermessage;
    @FXML
    private TableColumn<ManagerMessage, Integer> time;
    ObservableList<ManagerMessage> data;
    @FXML
    void initialize() throws IOException {
        LinkedList<ManagerMessage> managerMessages = new LinkedList<>();
        for (ManagerMessage m : listMTM) {
            managerMessages.add(m);
        }
        data = FXCollections.observableArrayList(managerMessages);
        Mtable.setItems(data);
        Mtable.setEditable(true);
        id.setCellValueFactory(new PropertyValueFactory<ManagerMessage, Integer>("TID"));
        teachermessage.setCellValueFactory(new PropertyValueFactory<ManagerMessage, String>("message"));
        time.setCellValueFactory(new PropertyValueFactory<ManagerMessage, Integer>("time"));
    }
    @FXML
    public void open(ActionEvent event) throws IOException{
        ManagerMessage m = Mtable.getSelectionModel().getSelectedItem();
        LinkedList<Object> message = new LinkedList<>();
        if(m==null){
            message.add("noselection");
            SimpleClient.getClient().sendToServer(message);
        }
        else{
            message.add(m);
            message.add(MME);
            try {
                LinkedList<Object> message1 = new LinkedList<>();
                message1.add("ExitMessages");
                message1.add(0);
                SimpleClient.getClient().sendToServer(message1);
                SimpleClient.getParams().add(message);
                setRoot("ShowMessage");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void back (ActionEvent event) throws IOException {
        SimpleClient.getParams().add(GFM);
        try {
            LinkedList<Object> message = new LinkedList<>();
            message.add("ExitMessages");
            message.add(0);
            SimpleClient.getClient().sendToServer(message);
            setRoot("Manager");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    public void LogOut (ActionEvent event) throws IOException {
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#LogOut");
        SimpleClient.getClient().sendToServer(message);
    }
}
