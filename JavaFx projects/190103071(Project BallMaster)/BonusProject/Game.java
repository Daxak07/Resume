import java.io.File;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;

public class Game extends Application{
	private Map map;
	private MyPlayer player;
	private Food food;
	// static String name;
	// public static void main(String[] args){
	// 	Game game = new Game();
	// 	name = args[0];
	// 	game.launch(args);
	// }
	public void start(Stage primaryStage){
		Scanner sc = new Scanner(System.in);
		String fileName = sc.nextLine();
		map = new Map(fileName);
		for (int i = 0; i < map.getSize(); i++) {
			for (int j = 0; j < map.getSize(); j++) {
				Rectangle rect = new Rectangle(j * map.getUnit(), i * map.getUnit(), map.getUnit(), map.getUnit());
				if(map.getMap()[i][j] == 0){
					rect.setFill(Color.WHITE);
					rect.setStroke(Color.BLACK);
				}
				else if (map.getMap()[i][j] == 2){
					rect.setFill(Color.WHITE);
					rect.setStroke(Color.BLACK);
				}
				else{
					rect.setFill(Color.BLACK);
					rect.setStroke(Color.WHITE);
				}
				map.getChildren().add(rect);
			}
		}

		player = new MyPlayer(map);
		food = new Food(map, player);

		Scene scene = new Scene(map, map.getUnit() * map.getSize(), map.getUnit() * map.getSize());

		Thread thread = new Thread( () -> {
			try{
				while(true){
					int[] start = { (int)(player.getBall().getCenterY() - 25) / map.getUnit(),(int)(player.getBall().getCenterX() - 25) / map.getUnit()};
					int[] end = {food.foodPosition.getY(),food.foodPosition.getX()};
					int[][] arr = PathFinderBFS.print(map.getMap(), start, end);

					for (int i = 0; i < arr.length; i++) {
						player.getBall().setCenterY(arr[i][1] * map.getUnit() + 25);
						player.getPosition().setY((int) ((player.getBall().getCenterY() - 25) / map.getUnit()));
						Thread.sleep(50);
						player.getBall().setCenterX(arr[i][0] * map.getUnit() + 25);
						player.getPosition().setX((int) ((player.getBall().getCenterX() - 25) / map.getUnit()));
						Thread.sleep(50);
					}
				}
			}catch(InterruptedException e){}
		});

		primaryStage.setScene(scene);
		primaryStage.show();

		scene.setOnKeyPressed(e -> {
			switch(e.getCode()) {
				case E:
					System.out.println("E keyboard clicked");
					thread.start();
					break;
				case Q:
					primaryStage.close();
					System.exit(0);
			}
		});			
	}
}