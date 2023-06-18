package il.cshaifasweng.OCSFMediatorExample.client.controller;

import il.cshaifasweng.OCSFMediatorExample.client.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.*;
//import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class studentExamController {
    RadioButton button1;
    JRadioButtonMenuItem item;
    AnchorPane test;
    @FXML
    private Label ok;

    @FXML
    void initialize() throws IOException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("studentExam.fxml"));
       // Parent root = loader.load();
        //Parent root = App.loadFXML("studentExam");
        ok.setText("please");
        button1= new RadioButton("true");
        //item = new RadioButtonMenuItem("hi");
        Button button = new Button("hiii");
        test=new AnchorPane();
        AnchorPane.setTopAnchor(button, 10.0);
        test.getChildren().add(button);;
        AnchorPane.setLeftAnchor(button1, 50.0);
        test.getChildren().add(button1);
        int j =0;
        LinkedList<RadioButton> listr= new LinkedList<>();

        ToggleGroup bg = new ToggleGroup();
        for(double i = 50.0;i<1000.0;i=i+50.0){
            if (j%5==0){
                Text text = new Text( (1 +j/5) + ". here we will write the qeustions !!");
                AnchorPane.setTopAnchor(text, i);
                AnchorPane.setLeftAnchor(text, 20.0);
                test.getChildren().add(text);
                Collections.shuffle(listr);
                double t=i;
                for(RadioButton radio :listr){
                    t+=50.0;
                    AnchorPane.setTopAnchor(radio, t-25);
                    AnchorPane.setLeftAnchor(radio, 20.0);
                    radio.setOnAction(event -> {
                        // Handle button click event here
                        System.out.println("choosing this " + button1.getText());
                        boolean bool = Integer.valueOf(radio.getText()) % 4 == 0;
                        System.out.println(bool);
                    });
                    test.getChildren().add(radio);

                }
                bg= new ToggleGroup();
                listr.clear();

            }
            else{
            RadioButton button2= new RadioButton(Integer.toString(j));
            button2.setToggleGroup(bg);
            listr.add(button2);

            }
            j++;
        }
        ScrollPane scroll= new ScrollPane(test);
        //scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        button.setOnAction(event -> {
            // Handle button click event here
            try {
                setRoot("primary");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Button Clicked!");
        });
        button1.setOnAction(event -> {
            // Handle button click event here
            System.out.println("choosing this " + button1.getText());
        });
      /*  root.getChildren().add(button);
        root.getChildren().add(button1);
        test.setTopAnchor(button1,10.0);*/
        set(scroll);

    }


    }

