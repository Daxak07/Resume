import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
public final class QuizViewer extends Application {
    public static void main(String args[]){
        launch(args);
    }
 
    private Desktop desktop = Desktop.getDesktop();
 
    @Override
    public void start(final Stage primaryStage) {
        primaryStage.setTitle("Quiz View");
 
        final FileChooser fileChooser = new FileChooser();
        final Button openButton = new Button("Load File");

 
        openButton.setOnAction( new EventHandler<ActionEvent>(){
        @Override
        public void handle(final ActionEvent e) {



            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file.getName().equals("JavaQuiz.txt")){
            	try{
            	Run1 a = new Run1();
            	Scene ssc = new Scene(a, 600,500);
            	Stage s = new Stage();
            	s.setScene(ssc);
            	s.show();
            }catch(Exception ex){
            	System.out.println(ex);
            }
                
            }else{
                Alert warn = new Alert(AlertType.WARNING);
                warn.setTitle("This is warning");
                warn.setContentText("The file selected does not fit the requirement for a standart Quiz text file format..");
                warn.show();
            }
        
        }
    }
);
 
       
 
 
        GridPane pane = new GridPane();
        GridPane.setConstraints(openButton, 18, 14);
        pane.setHgap(6);
        pane.setVgap(6);
        pane.getChildren().addAll(openButton);

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

        private void openFile(File file) {
            try {
                desktop.open(file);
            }catch (IOException ex) {
                System.out.println(ex);
        }
    }
    

}