package edu.rit.edgeconverter.listeners;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class WindowListener {

    public static void onShowing(Stage primaryStage) {
        primaryStage.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Window is opening");
            }
        });
    }
    
    public static void onCloseRequest(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("Window is closing");
            }
        });
    }

    
}
