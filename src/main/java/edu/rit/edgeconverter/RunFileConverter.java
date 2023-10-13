package edu.rit.edgeconverter;

import edu.rit.edgeconverter.controller.FileConverterController;
import edu.rit.edgeconverter.view.ConverterGUI;
import javafx.application.Application;
import javafx.stage.Stage;


public class RunFileConverter extends Application {
    public static void main(String[] args)  {
         FileConverterController controller = new FileConverterController();
        launch(args);
       
    }
    @Override
    public void start(Stage primaryStage) {
        ConverterGUI view = new ConverterGUI(primaryStage);
        view.start();
        


    }
}