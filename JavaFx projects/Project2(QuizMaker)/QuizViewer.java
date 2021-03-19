import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class QuizViewer extends Application {
    private Desktop desktop = Desktop.getDesktop();

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FileChooser fileChooser = new FileChooser();
        final Button openBt = new Button("Load File");

        openBt.setOnAction(event -> {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file.getName().equals("JavaQuiz.txt")){
                if (file != null) {
                    try {
                        QuizRunner a = new QuizRunner();
                        Scene ssc = new Scene(a, 600,500);
                        Stage s = new Stage();
                        s.setScene(ssc);
                        s.show();
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }

            }else{
                Alert warn = new Alert(Alert.AlertType.WARNING);
                warn.setTitle("This is warning");
                warn.setContentText("aaaa");
                warn.show();
            }
        });
        GridPane pane = new GridPane();
        GridPane.setConstraints(openBt, 18, 14);
        pane.setHgap(6);
        pane.setVgap(6);
        pane.getChildren().addAll(openBt);

        Scene scene = new Scene(pane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quiz Viewer");
        primaryStage.show();
    }
}
