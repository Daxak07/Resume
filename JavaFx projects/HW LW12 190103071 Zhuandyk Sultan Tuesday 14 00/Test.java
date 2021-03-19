import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Test extends Application {
    final int radius = 100;
    int pointCounter = 0;
    int count = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();

        Text A = new Text(65,125,"A");
        Text B = new Text(435,125,"B");
        Text C = new Text(150,440,"C");


        Circle c1 = new Circle(180, 200, radius);
        c1.setFill(Color.TRANSPARENT);
        c1.setStroke(Color.RED);
        c1.setStrokeWidth(3);

        Circle c2 = new Circle(320, 200, radius);
        c2.setFill(Color.TRANSPARENT);
        c2.setStroke(Color.BLUE);
        c2.setStrokeWidth(3);

        Circle c3 = new Circle(250, 300, radius);
        c3.setFill(Color.TRANSPARENT);
        c3.setStroke(Color.GREEN);
        c3.setStrokeWidth(3);

        pane.getChildren().addAll(c1,c2,c3);

        Circle[] circles = {c1, c2, c3};
        Map<Integer, Circle> map = new HashMap<>();

        Set<Integer> set0 = new HashSet<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        Set<Integer> diff = new HashSet<>();

        Set[] sets = {set0, set1, set2};

        pane.setOnMouseClicked(e -> {
            count++;
            for (int i = 0; i < 3; i++) {
                double lenx = e.getX() - circles[i].getCenterX();
                double leny = e.getY() - circles[i].getCenterY();

                VBox box = new VBox();
                box.setLayoutX(e.getX());
                box.setLayoutY(e.getY());
                Text text = new Text(e.getX(), e.getY(), count);
                Circle c = new Circle(e.getX(), e.getY(), 5);
                c.setFill(Color.color(Math.random(), Math.random(), Math.random()));
                box.getChildren().addAll(c, text);

                if (Math.sqrt(Math.pow(lenx,2) + Math.pow(leny,2)) < radius) {
                    pointCounter++;
                    pane.getChildren().add(box);
                    sets[i].add(count);
                }
            }

            System.out.println("Set A : "+set0);
            System.out.println("Set B : "+set1);
            System.out.println("Set C : "+set2);
            if(pointCounter == 0){
                count--;
            }
            if(pointCounter == 3){
                diff.add(count);
            }
            System.out.println("A n B n C : "+diff);
            pointCounter = 0;
            System.out.println("_________________________________");
        });
        pane.getChildren().addAll(A,B,C);

        Scene scene = new Scene(pane,500,500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
