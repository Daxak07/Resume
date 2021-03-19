import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import java.util.*;

public class MyPlayer implements Player{
	private Circle ball;
	private Map map;
	private Position position;


	public MyPlayer(Map map){
		this.map = map;
		position = map.getStartPosition();
		ball = new Circle(map.getStartPosition().getX() + map.getUnit()/2, map.getStartPosition().getY() + map.getUnit()/2, map.getUnit()/2);
		ball.setFill(Color.RED);
		map.getChildren().add(ball);
	}
	public Circle getBall(){
		return ball;
	}
    
	@Override
    public void moveUp() {
        if (position.getY() != 0 && map.getMap()[(int)((ball.getCenterY()-map.getUnit())/map.getUnit())][(int)(ball.getCenterX())/map.getUnit()] != 1) {
            position.setY(position.getY() - 1);
        } else if (position.getY() == 0 || map.getMap()[(int)((ball.getCenterY()-map.getUnit())/map.getUnit())][(int)(ball.getCenterX())/map.getUnit()] == 1) {
            position.setY(position.getY());
            System.out.println("Invalid move");
        }
    }

	@Override
    public void moveDown() {
        if (position.getY() + 1 != map.getSize() && map.getMap()[(int)(ball.getCenterY()+map.getUnit())/map.getUnit()][(int)(ball.getCenterX())/map.getUnit()]!=1) {
            position.setY(position.getY() + 1);
        } else if (position.getY() + 1 == map.getSize() || map.getMap()[(int)(ball.getCenterY()+map.getUnit())/map.getUnit()][(int)(ball.getCenterX())/map.getUnit()] ==1) {
            position.setY(position.getY());
            System.out.println("Invalid move");
        }
    }

	@Override
    public void moveLeft() {
        if (position.getX() != 0 && map.getMap()[(int)ball.getCenterY()/map.getUnit()][(int)(ball.getCenterX() - map.getUnit())/map.getUnit()]!=1 ) {
            position.setX(position.getX() - 1);
        }else if (position.getX() == 0 || map.getMap()[(int)ball.getCenterY()/map.getUnit()][(int)(ball.getCenterX() - map.getUnit())/map.getUnit()] == 1) {
            position.setX(position.getX());
            System.out.println("Invalid move");
        }
    }

	@Override
    public void moveRight() {
        if (position.getX() + 1 != map.getSize() && map.getMap()[(int)ball.getCenterY()/map.getUnit()][(int)(ball.getCenterX()/map.getUnit()) + 1] != 1 ) {
            position.setX(position.getX() + 1);
        } else if (position.getX() + 1 == map.getSize()  || map.getMap()[(int)ball.getCenterY()/map.getUnit()][(int)(ball.getCenterX()/map.getUnit()) + 1] == 1) {
            position.setX(position.getX());
            System.out.println("Invalid move");
        }
    }	
	public Position getPosition(){
		return position;
	}
}


