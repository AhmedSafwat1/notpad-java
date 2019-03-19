package notpadlab;


import com.sun.javafx.tk.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import notpadlab.NotPadLab;
public class NotPadLabBase extends BorderPane {

    protected final MenuBar menuBar;
    protected final Menu File;
    protected final MenuItem newFile;
    protected final MenuItem openFile;
    protected final MenuItem saveFile;
    protected final SeparatorMenuItem separatorMenuItem;
    protected final MenuItem exitFile;
    protected final Menu edit;
    protected final MenuItem cutText;
    protected final MenuItem copyText;
    protected final MenuItem pastText;
    protected final MenuItem deleteText;
    protected final MenuItem sellectAllText;
    protected final Menu about;
    protected final MenuItem aboutItem;
    protected final TextArea textArea;
    Stage primaryStage;
    private void SaveFile2(String content, File file){
                try 

                {
                    FileWriter fileWriter = null;

                    fileWriter = new FileWriter(file);
                    fileWriter.write(content);
                    fileWriter.close();
                } 
                catch (IOException ex) {
                   ex.printStackTrace();
                }
        
            }
    public NotPadLabBase(Stage _p) {
        
        primaryStage = _p;
        menuBar = new MenuBar();
        File = new Menu();
        newFile = new MenuItem();
        openFile = new MenuItem();
        saveFile = new MenuItem();
        separatorMenuItem = new SeparatorMenuItem();
        exitFile = new MenuItem();
        edit = new Menu();
        cutText = new MenuItem();
        copyText = new MenuItem();
        pastText = new MenuItem();
        deleteText = new MenuItem();
        sellectAllText = new MenuItem();
        about = new Menu();
        aboutItem = new MenuItem();
        textArea = new TextArea();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(500.0);
        setPrefWidth(700.0);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while(true)
                {
                textArea.setText("5"+i); 
                i++;
                }//To change body of generated methods, choose Tools | Templates.
            }
        });
        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);

        File.setMnemonicParsing(false);
        File.setText("File");

        newFile.setMnemonicParsing(false);
        newFile.setText("New");
        newFile.setAccelerator(KeyCombination.keyCombination("ctrl+n"));
        openFile.setMnemonicParsing(false);
        openFile.setText("Open");
        openFile.setAccelerator(KeyCombination.keyCombination("ctrl+o"));
        saveFile.setMnemonicParsing(false);
        saveFile.setText("Save");
        saveFile.setAccelerator(KeyCombination.keyCombination("ctrl+s"));
        separatorMenuItem.setMnemonicParsing(false);

        exitFile.setMnemonicParsing(false);
        exitFile.setText("Exit");
        exitFile.setAccelerator(KeyCombination.keyCombination("ctrl+e"));
        // Action File meun
        
        exitFile.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            Platform.exit();
        });
         newFile.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
             if(!textArea.getText().trim().isEmpty() || textArea.getText() == null )
             {
                 Alert alert = new Alert(AlertType.CONFIRMATION);
                 alert.setTitle("alert");
                 alert.setHeaderText("Are you sure want to new open and this file will delete?");
                 alert.setContentText("File not save");
                 Optional<ButtonType> option = alert.showAndWait();
                 if (option.get() == ButtonType.OK) {
                     FileChooser fileChooser = new FileChooser();
                     //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)");
                     File file = fileChooser.showOpenDialog(primaryStage);
                     if (file != null)
                     {
                         File F = new File(file.getAbsolutePath());
                         SaveFile2(textArea.getText(), file);
                     }
                 }
                 else {
                     textArea.setText("");
                 }
             }
             
             textArea.setText("");
        });
          openFile.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
              FileChooser fileChooser = new FileChooser();
              File file = fileChooser.showOpenDialog(primaryStage);
              if (file != null)
              { 
                  textArea.setText("");
                  File F = new File(file.getAbsolutePath());
                  BufferedReader reader;
                  try {
                      reader = new BufferedReader(new FileReader(F));
                      String line = reader.readLine();
                      while (line != null) {
                          textArea.appendText(line +"\n");
                          // read next line
                          line = reader.readLine();
                      }
                      reader.close();
                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }
        });
           saveFile.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
               FileChooser fileChooser = new FileChooser();
               //FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)");
               File file = fileChooser.showSaveDialog(primaryStage);
               if (file != null)
               {
                   File F = new File(file.getAbsolutePath());
                   SaveFile2(textArea.getText(),F);
               }
        });
        
        // ============================
        edit.setMnemonicParsing(false);
        edit.setText("Edit");

        cutText.setMnemonicParsing(false);
        cutText.setText("Cut");

        copyText.setMnemonicParsing(false);
        copyText.setText("Copy");

        pastText.setMnemonicParsing(false);
        pastText.setText("Past");

        deleteText.setMnemonicParsing(false);
        deleteText.setText("Delete");

        sellectAllText.setMnemonicParsing(false);
        sellectAllText.setText("Select All");
        // Action edit meun
        
        cutText.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            content.putString(textArea.getSelectedText());
            
            textArea.replaceSelection("");
            clipboard.setContent(content);
        });
         copyText.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
             final Clipboard clipboard = Clipboard.getSystemClipboard();
             final ClipboardContent content = new ClipboardContent();
             content.putString(textArea.getSelectedText());
             clipboard.setContent(content);
        });
        sellectAllText.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            textArea.selectAll();
            content.putString(textArea.getText());
            clipboard.setContent(content);
        });
        deleteText.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            textArea.setText("");
        });
        pastText.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            Clipboard systemClipboard = Clipboard.getSystemClipboard();
            String clipboardText = systemClipboard.getString();
            
            if(textArea.getSelection() !=null  &&textArea.getSelection().getLength() != 0)
            {
                textArea.replaceSelection(clipboardText);
            }
            else
            {
                textArea.insertText(textArea.getCaretPosition(), clipboardText);
            }
        });
        
        // ============================
        about.setMnemonicParsing(false);
        about.setText("Help");

        aboutItem.setMnemonicParsing(false);
        aboutItem.setText("About");
        aboutItem.setAccelerator(KeyCombination.keyCombination("ctrl+a"));
        setTop(menuBar);
        //  help
        aboutItem.addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("About");
            //  alert.setHeaderText("About us");
            alert.setContentText("Welcom in About us first Gui");
            
            alert.showAndWait();
        });
        //end
        BorderPane.setAlignment(textArea, javafx.geometry.Pos.CENTER);
        textArea.setPrefHeight(200.0);
        textArea.setPrefWidth(200.0);
        setCenter(textArea);

        File.getItems().add(newFile);
        File.getItems().add(openFile);
        File.getItems().add(saveFile);
        File.getItems().add(separatorMenuItem);
        File.getItems().add(exitFile);
        menuBar.getMenus().add(File);
        edit.getItems().add(cutText);
        edit.getItems().add(copyText);
        edit.getItems().add(pastText);
        edit.getItems().add(deleteText);
        edit.getItems().add(sellectAllText);
        menuBar.getMenus().add(edit);
        about.getItems().add(aboutItem);
        menuBar.getMenus().add(about);

    }
}
