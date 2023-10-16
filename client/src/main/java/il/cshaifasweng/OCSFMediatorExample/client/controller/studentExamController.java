package il.cshaifasweng.OCSFMediatorExample.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javax.swing.*;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

import static il.cshaifasweng.OCSFMediatorExample.client.App.set;
import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;
//import static il.cshaifasweng.OCSFMediatorExample.client.App.setRoot;

public class studentExamController {
    RadioButton button1;
    JRadioButtonMenuItem item;
    AnchorPane test;
    @FXML
    private Label ok;

    @FXML
    void initialize() throws IOException {
        ok.setText("please");
        button1= new RadioButton("true");
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
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        button.setOnAction(event -> {
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
        set(scroll);
    }
}

