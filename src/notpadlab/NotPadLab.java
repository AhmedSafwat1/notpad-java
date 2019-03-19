/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notpadlab;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author safwat
 */
public class NotPadLab extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        NotPadLabBase root = new NotPadLabBase(primaryStage);
               
        Scene scene = new Scene(root, 500, 600);
        
        primaryStage.setTitle("Not Pad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
