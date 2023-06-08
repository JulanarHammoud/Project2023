package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.LinkedList;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private ComboBox<String> role;


    public void initialize(){
        role.getItems().addAll("Student","Teacher","manger");
    }
    @FXML
    void SelectRole(ActionEvent event) throws IOException {
        System.out.println("hi");
        LinkedList<Object> message = new LinkedList<Object>();
        message.add("#Login");
        message.add(username.getText());
        message.add(password.getText());
        String choose = role.getSelectionModel().getSelectedItem();
        System.out.println("Selected item: " + username.getText());
        System.out.println("Selected item: " + password.getText());
        System.out.println("Selected item: " + choose);
        message.add(choose);
        SimpleClient.getClient().sendToServer(message);

    }
}
