import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Scanner;

public class Game extends Application {
    Map map;
    Player player;
    Food food;

    @Override
    public void start(Stage stage) throws Exception {
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        Map map = new Map(fileName);
        Player player = new MyPlayer(map);
        Food food = new Food(map, player);
        stage.setScene(new Scene(map));
        stage.setResizable(false);
        stage.show();
    }
}
