// package TrafficLightHW;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TrafficLights extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        Rectangle r = new Rectangle(100,50,100,200);
        r.setStroke(Color.BLACK);
        r.setFill(Color.DARKGRAY);
        pane.getChildren().add(r);

        Circle c1 = new Circle(150,95,30);
        c1.setStroke(Color.BLACK);
        c1.setFill(Color.WHITE);
        pane.getChildren().add(c1);

        Circle c2 = new Circle(150,155,30);
        c2.setStroke(Color.BLACK);
        c2.setFill(Color.WHITE);
        pane.getChildren().add(c2);

        Circle c3 = new Circle(150,215,30);
        c3.setStroke(Color.BLACK);
        c3.setFill(Color.WHITE);
        pane.getChildren().add(c3);

        HBox paneForRadioButtons = new HBox(30);
        paneForRadioButtons.setPadding(new Insets(5,5,5,5));
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbYellow = new RadioButton("Yellow");
        RadioButton rbGreen = new RadioButton("Green");
        paneForRadioButtons.getChildren().addAll(rbRed,rbYellow,rbGreen);
        paneForRadioButtons.setAlignment(Pos.CENTER);
        pane.setBottom(paneForRadioButtons);
        ToggleGroup group = new ToggleGroup();
        rbRed.setToggleGroup(group);
        rbYellow.setToggleGroup(group);
        rbGreen.setToggleGroup(group);

        rbRed.setOnAction(event -> {
            if (rbRed.isSelected()) {
                c1.setFill(Color.RED);
                c2.setFill(Color.WHITE);
                c3.setFill(Color.WHITE);
            }
        });
        rbYellow.setOnAction(event -> {
            if (rbYellow.isSelected()) {
                c1.setFill(Color.WHITE);
                c2.setFill(Color.YELLOW);
                c3.setFill(Color.WHITE);
            }
        });
        rbGreen.setOnAction(event -> {
            if (rbGreen.isSelected()) {
                c1.setFill(Color.WHITE);
                c2.setFill(Color.WHITE);
                c3.setFill(Color.GREEN);
            }
        });

        Scene scene = new Scene(pane,300,300);
        primaryStage.setTitle("TrafficLights");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
