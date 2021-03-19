import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map extends Pane {
    private int UNIT = 50;
    private int size;
    private int[][] map;
    private Position start;

    Map(String path) throws FileNotFoundException {
        Button button = new Button();
        button.setStyle("-fx-background-color: black");
        getChildren().add(button);

        String str = "";
        File file = new File(path);

        Scanner in = new Scanner(file);
        this.size = in.nextInt();
        map = new int[this.size][this.size];
        setPrefSize(this.UNIT * size -10, this.UNIT * size -10);
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.map[i][j] = in.nextInt();
                Rectangle rec = new Rectangle();
                if (map[i][j] == 2) {
                    this.start = new Position(i, j);
                    rec = new Rectangle(UNIT * j, UNIT * i, getUnit(), getUnit());
                    rec.setFill(Color.WHITE);
                    rec.setStroke(Color.BLACK);
                } else if (map[i][j] == 0) {
                    rec = new Rectangle(UNIT * j, UNIT * i, getUnit(), getUnit());
                    rec.setFill(Color.WHITE);
                    rec.setStroke(Color.BLACK);
                } else {
                    rec = new Rectangle(UNIT * j, UNIT * i, getUnit(), getUnit());
                    rec.setStroke(Color.WHITE);
                    rec.setArcHeight(15);
                    rec.setArcWidth(15);
                    rec.setFill(Color.BLACK);
                }
                getChildren().add(rec);
            }
        }

    }


    public int getUnit() {
        return UNIT;
    }

    public int getSize() {
        return size;
    }

    public int getValue(int a, int b) {
        return map[a][b];
    }

    public Position getStartPositions() {
        return start;
    }
}
