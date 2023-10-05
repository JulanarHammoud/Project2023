package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class LoginController {
    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private ComboBox<String> role;

    public void initialize(){
        role.getItems().addAll("Student","Teacher","manager");
    }
    @FXML
    void SelectRole(ActionEvent event) throws IOException {
        String choose = role.getSelectionModel().getSelectedItem();
        LinkedList<Object> message = new LinkedList<Object>();
        if(choose.equals("manager")){
            if(password.getText().equals("123")&& username.getText().equals("miral")){
                message.add("#GetAllSubjectsSimpleServer");
                SimpleClient.getClient().sendToServer(message);
            }
        } else{
            System.out.println("hi");

            message.add("#Login");
            message.add(username.getText());
            message.add(password.getText());
            System.out.println("Selected item: " + username.getText());
            System.out.println("Selected item: " + password.getText());
            System.out.println("Selected item: " + choose);
            message.add(choose);
            SimpleClient.getClient().sendToServer(message);
        }
    }

    @FXML
    void returnbutton1(ActionEvent event) {
        try{
            setRoot("primary");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
