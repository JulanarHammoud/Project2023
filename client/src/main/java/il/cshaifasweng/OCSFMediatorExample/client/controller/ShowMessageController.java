package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import il.cshaifasweng.OCSFMediatorExample.entities.MailManagerEntity;
import il.cshaifasweng.OCSFMediatorExample.entities.ManagerMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ShowMessageController {
    int lastIndex = SimpleClient.getParams().size() - 1;
    LinkedList<Object> msg = (LinkedList<Object>) SimpleClient.getParams().get(lastIndex);
    ManagerMessage Message = (ManagerMessage) msg.get(0);
    MailManagerEntity MME = (MailManagerEntity) msg.get(1);
    @FXML
    Label teachername;
    @FXML
    Label time;
    @FXML
    Label msgcontent;
    @FXML
    void initialize() throws IOException {
        teachername.setText(String.valueOf(Message.getTID()));
        time.setText(String.valueOf(Message.getTime()));
        msgcontent.setText(Message.getMessage());
    }
    public void Decline(javafx.event.ActionEvent event) {
        LinkedList<Object> message = new LinkedList<>();
        message.add("AnswerMessage");
        message.add(Message);
        message.add(MME);
        message.add(0);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Accept(ActionEvent event) {
        LinkedList<Object> message = new LinkedList<>();
        message.add("AnswerMessage");
        message.add(Message);
        message.add(MME);
        message.add(Message.getTime());
        message.add(Message.getExamTeacherid());
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
