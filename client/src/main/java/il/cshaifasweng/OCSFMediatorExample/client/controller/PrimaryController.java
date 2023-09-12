package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.SimpleClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;

import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class PrimaryController {

	@FXML
	private Button start_button;

	@FXML
	void start(ActionEvent event) {
		try{
			//SimpleClient.getClient().sendToServer("#ListStudents");
			//System.out.println("we sent to server");
			setRoot("Login");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}