import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyPlayer implements Player {
    Map map;
    Position positions;
    Circle ball;

    public MyPlayer(Map map) {
        this.map = map;
        positions = map.getStartPositions();
        ball = new Circle(positions.getX() * map.getUnit() + map.getUnit() / 2,
                positions.getY() * map.getUnit() + map.getUnit() / 2, 20, Color.RED);

        map.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.D ) {
                moveLeft();
            } else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.A) {
                moveRight();
            } else if (e.getCode() == KeyCode.UP || e.getCode() == KeyCode.W) {
                moveUp();
            } else if (e.getCode() == KeyCode.DOWN || e.getCode() == KeyCode.S) {
                moveDown();
            }
            ball.setCenterX(positions.getX() * map.getUnit() + map.getUnit() / 2);
            ball.setCenterY(positions.getY() * map.getUnit() + map.getUnit() / 2);
        });
        this.map.getChildren().add(ball);
    }

    @Override
    public void moveRight() {
        if (positions.getX() + 1 != map.getSize() && map.getValue(positions.getY(),positions.getX() + 1) != 1 ) {
            positions.setX(positions.getX() + 1);
        } else if (positions.getX() + 1 == map.getSize()  || map.getValue(positions.getY(),positions.getX() + 1) == 1) {
            positions.setX(positions.getX());
            System.out.println("Invalid move");
        }
    }

    @Override
    public void moveLeft() {
        if (positions.getX() != 0 && map.getValue(positions.getY(),positions.getX()-1)!=1 ) {
            positions.setX(positions.getX() - 1);
        }else if (positions.getX() == 0 || map.getValue(positions.getY(),positions.getX() - 1) == 1) {
            positions.setX(positions.getX());
            System.out.println("Invalid move");
        }
    }

    @Override
    public void moveUp() {
        if (positions.getY() != 0 && map.getValue(positions.getY()-1,positions.getX()) != 1) {
            positions.setY(positions.getY() - 1);
        } else if (positions.getY() == 0 || map.getValue(positions.getY()-1,positions.getX()) == 1) {
            positions.setY(positions.getY());
            System.out.println("Invalid move");
        }
    }

    @Override
    public void moveDown() {
        if (positions.getY() + 1 != map.getSize() && map.getValue(positions.getY()+1,positions.getX())!=1) {
            positions.setY(positions.getY() + 1);
        } else if (positions.getY() + 1 == map.getSize() || map.getValue(positions.getY()+1,positions.getX()) ==1) {
            positions.setY(positions.getY());
            System.out.println("Invalid move");
        }
    }

    @Override
    public Position getPosition() {
        return positions;
    }
}
